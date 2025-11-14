package ca.utoronto.utm.assignment2.paint;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 * View class that sets up the main paint window and handles UI events.
 */
public class View implements EventHandler<ActionEvent> {
    private PaintModel paintModel;
    private PaintPanel paintPanel;
    private ShapeChooserPanel shapeChooserPanel;
    private ColorPicker colorPicker;
    private TextField opacityField;
    private TabPane tabPane;
    private int canvasCount = 1;

    /**
     * Create a new View with a PaintModel and Stage, and set up all UI components.
     *
     * @param model
     * @param stage
     */
    public View(PaintModel model, Stage stage) {
        this.paintModel = model;
        this.colorPicker = new ColorPicker(Color.BLACK);
        this.paintPanel = new PaintPanel(this.paintModel);
        this.shapeChooserPanel = new ShapeChooserPanel(this);
        this.tabPane = new TabPane();

        Label opacityLabel = new Label("Opacity:");

        this.opacityField = new TextField("100");
        opacityField.setPrefWidth(40);



        colorPicker.setOnAction(e -> paintModel.setCurrentColor(colorPicker.getValue()));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            if (!paintModel.getShapes().isEmpty()) {
                paintModel.deleteLast();
            }
        });

        Button undoButton = new Button("Undo");
        undoButton.setOnAction(e -> paintModel.undo());

        Button redoButton = new Button("Redo");
        redoButton.setOnAction(e -> paintModel.redo());

        Button eraserButton = new Button("Eraser");
        eraserButton.setOnAction(e -> {paintPanel.setMode("Eraser");});
        Button darkMode = new Button("Dark Mode");
        darkMode.setOnAction(e -> {model.setDarkMode();});

        Button newCanvasButton = new Button("New Canvas");
        newCanvasButton.setOnAction(e -> createAndSelectNewCanvasTab());

        Button renameCanvasButton = new Button("Rename Canvas");
        renameCanvasButton.setOnAction(e -> renameCurrentCanvas());



        Label lineLabel = new Label("Thickness:");
        Spinner<Integer> thicknessSpinner = new Spinner<>();
        thicknessSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1));
        thicknessSpinner.setEditable(false);
        thicknessSpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                paintModel.setCurrentStrokeWidth(newVal.doubleValue());
            }
        });

        this.colorPicker.setOnAction(e -> {
            Color selectedColor = colorPicker.getValue();
            Color newColor = Color.color(
                    selectedColor.getRed(),
                    selectedColor.getGreen(),
                    selectedColor.getBlue(),
                    paintModel.getCurrentOpacity()
            );
            paintModel.setCurrentColor(newColor);
            updateOpacityField();
        });

        opacityField.setOnAction(e -> {
            try {
                int percentage = Integer.parseInt(opacityField.getText());
                percentage = Math.max(0, Math.min(100, percentage));
                double opacity = percentage / 100.0;

                Color current = paintModel.getCurrentColor();
                Color newColor = Color.color(
                        current.getRed(),
                        current.getGreen(),
                        current.getBlue(),
                        opacity
                );
                paintModel.setCurrentColor(newColor);

                colorPicker.setValue(newColor);
                opacityField.setText(String.valueOf(percentage));
            } catch (NumberFormatException ex) {
                updateOpacityField();
            }
        });

        this.colorPicker.valueProperty().addListener((obs, oldColor, newColor) -> {
            updateOpacityField();
        });


        HBox styleBar = new HBox(10, colorPicker, opacityLabel, opacityField, lineLabel, thicknessSpinner,
                undoButton, redoButton, deleteButton, eraserButton, darkMode, newCanvasButton, renameCanvasButton);

        styleBar.setPadding(new Insets(6));

        BorderPane root = new BorderPane();
        root.setTop(styleBar);

        Tab firstTab = createCanvasTab("Canvas " + canvasCount, this.paintModel, this.paintPanel);
        this.tabPane.getTabs().add(firstTab);
        this.tabPane.getSelectionModel().select(firstTab);

        root.setCenter(this.tabPane);
        root.setLeft(this.shapeChooserPanel);

        this.tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (newTab != null && newTab.getUserData() instanceof CanvasContext) {
                CanvasContext ctx = (CanvasContext) newTab.getUserData();
                switchToCanvas(ctx.model, ctx.panel);
            }
        });


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Paint");
        stage.show();
        paintPanel.requestFocus();

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN),
                () -> paintModel.undo()
        );

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN),
                () -> paintModel.redo()
        );

        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            KeyCode code = e.getCode();

            if (code == KeyCode.DELETE) {
                paintModel.deleteLast();
                e.consume();
                return;
            }

            if (code == KeyCode.PLUS || code == KeyCode.ADD ||
                    (code == KeyCode.EQUALS && e.isShiftDown()) || code == KeyCode.EQUALS) {
                double currentWidth = paintModel.getCurrentStrokeWidth();
                double newWidth = currentWidth + 1.0;

                paintModel.setCurrentStrokeWidth(newWidth);
                thicknessSpinner.getValueFactory().setValue((int) newWidth);
                e.consume();
                return;
            }

            if (code == KeyCode.MINUS || code == KeyCode.SUBTRACT) {
                double currentWidth = paintModel.getCurrentStrokeWidth();
                double newWidth = currentWidth - 1.0;

                if (newWidth >= 1) {
                    paintModel.setCurrentStrokeWidth(newWidth);
                    thicknessSpinner.getValueFactory().setValue((int) newWidth);
                }
                e.consume();
                return;
            }

            if (code == KeyCode.M) {
                paintModel.toggleFillMode();
                shapeChooserPanel.updateFillToggleText();
                e.consume();
            }
        });
        switchToCanvas(this.paintModel, this.paintPanel);
    }

    /**
     * Has the model and panel for a single canvas tab.
     */
    private static class CanvasContext {
        final PaintModel model;
        final PaintPanel panel;

        CanvasContext(PaintModel model, PaintPanel panel) {
            this.model = model;
            this.panel = panel;
        }
    }

    /**
     * Create a new tab with a canvas using th model.
     */
    private Tab createCanvasTab(String title, PaintModel model, PaintPanel panel) {
        StackPane center = new StackPane(panel);
        panel.widthProperty().bind(center.widthProperty());
        panel.heightProperty().bind(center.heightProperty());

        Tab tab = new Tab(title, center);
        tab.setClosable(true);

        tab.setOnCloseRequest(event -> {
            if (tabPane.getTabs().size() == 1) {
                event.consume();
                return;
            }
        });
        tab.setUserData(new CanvasContext(model, panel));
        return tab;
    }

    /**
     * Create a brand new blank canvas in a new tab and switch to it.
     */
    private void createAndSelectNewCanvasTab() {
        canvasCount++;
        PaintModel newModel = new PaintModel();
        PaintPanel newPanel = new PaintPanel(newModel);
        Tab tab = createCanvasTab("Canvas " + canvasCount, newModel, newPanel);
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }

    /**
     * Switch the View's active model and panel to the given canvas.
     */
    private void switchToCanvas(PaintModel model, PaintPanel panel) {
        this.paintModel = model;
        this.paintPanel = panel;

        colorPicker.setValue(paintModel.getCurrentColor());
        updateOpacityField();

        shapeChooserPanel.updateFillToggleText();

        paintPanel.requestFocus();
    }

    /**
     * Rename the currently selected canvas tab.
     */
    private void renameCurrentCanvas() {
        Tab current = tabPane.getSelectionModel().getSelectedItem();
        if (current == null) {
            return;
        }
        TextInputDialog dialog = new TextInputDialog(current.getText());
        dialog.setTitle("Rename Canvas");
        dialog.setHeaderText(null);
        dialog.setContentText("Canvas name:");
        dialog.showAndWait().ifPresent(newName -> {
            String trimmed = newName.trim();
            if (!trimmed.isEmpty()) {
                current.setText(trimmed);
            }
        });
    }
    /**
     * Update the opacity text field based on the model's current opacity.
     */
    private void updateOpacityField() {
        int opacityPercentage = (int)(paintModel.getCurrentOpacity() * 100);
        opacityField.setText(String.valueOf(opacityPercentage));
    }

    /**
     * Get the PaintModel used by this view.
     *
     * @return the PaintModel
     */
    public PaintModel getPaintModel() {
        return this.paintModel;
    }

    /**
     * Set the current drawing mode on the PaintPanel.
     *
     * @param mode
     */
    public void setMode(String mode){
        this.paintPanel.setMode(mode);
    }

    /**
     * Create and return a menu bar for this view.
     *
     * @return the MenuBar
     */
    private MenuBar createMenuBar() {

        MenuBar menuBar = new MenuBar();
        Menu menu;
        MenuItem menuItem;

        menu = new Menu("File");

        menuItem = new MenuItem("New");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Open");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Save");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menu.getItems().add(new SeparatorMenuItem());

        menuItem = new MenuItem("Exit");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuBar.getMenus().add(menu);

        menu = new Menu("Edit");

        menuItem = new MenuItem("Cut");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Copy");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Paste");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menu.getItems().add(new SeparatorMenuItem());
        menuItem = new MenuItem("Undo");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Redo");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuBar.getMenus().add(menu);

        return menuBar;
    }

    /**
     * Get the PaintModel used by this view.
     *
     * @return the PaintModel
     */
    public PaintModel getModel() {
        return this.paintModel;
    }

    /**
     * Handle menu item actions such as Exit.
     *
     * @param event
     */
    @Override
    public void handle(ActionEvent event) {
            System.out.println(((MenuItem) event.getSource()).getText());
            String command = ((MenuItem) event.getSource()).getText();
            System.out.println(command);
            if (command.equals("Exit")) {
                    Platform.exit();
            }
    }

}
