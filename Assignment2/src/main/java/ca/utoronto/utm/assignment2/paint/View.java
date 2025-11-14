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


        Label lineLabel = new Label("Line:");
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
                undoButton, redoButton, deleteButton, eraserButton, darkMode);

        styleBar.setPadding(new Insets(6));

        BorderPane root = new BorderPane();
        root.setTop(styleBar);
        StackPane center = new StackPane(this.paintPanel);
        root.setCenter(center);
        this.paintPanel.widthProperty().bind(center.widthProperty());
        this.paintPanel.heightProperty().bind(center.heightProperty());
        root.setLeft(this.shapeChooserPanel);

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
            System.out.println("DEBUG: Key pressed: " + code);

            if (code == KeyCode.DELETE) {
                paintModel.deleteLast();
                e.consume();
                return;
            }

            if (code == KeyCode.PLUS || code == KeyCode.ADD ||
                    (code == KeyCode.EQUALS && e.isShiftDown()) || code == KeyCode.EQUALS) {
                System.out.println("DEBUG: Plus key detected");
                double currentWidth = paintModel.getCurrentStrokeWidth();
                double newWidth = currentWidth + 1.0;

                paintModel.setCurrentStrokeWidth(newWidth);
                thicknessSpinner.getValueFactory().setValue((int) newWidth);
                e.consume();
                return;
            }

            if (code == KeyCode.MINUS || code == KeyCode.SUBTRACT) {
                System.out.println("DEBUG: Minus key detected");
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
