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

public class View implements EventHandler<ActionEvent> {

    private PaintModel paintModel;
    private PaintPanel paintPanel;
    private ShapeChooserPanel shapeChooserPanel;
    private ColorPicker colorPicker;

    public View(PaintModel model, Stage stage) {
        this.paintModel = model;
        this.colorPicker = new ColorPicker(Color.BLACK);
        this.paintPanel = new PaintPanel(this.paintModel);
        this.shapeChooserPanel = new ShapeChooserPanel(this);

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
        eraserButton.setOnAction(e -> paintPanel.setMode("Eraser"));


        // --- Line thickness control (Spinner from 1..20, default 1)
        Label lineLabel = new Label("Line:");
        Spinner<Integer> thicknessSpinner = new Spinner<>();
        thicknessSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1));
        thicknessSpinner.setEditable(false); // keep it simple and safe
        thicknessSpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                paintModel.setCurrentStrokeWidth(newVal.doubleValue());
            }
        });

        // If you want the spinner to reflect model changes triggered elsewhere:
        // (Optional) observe model in your panel/observer to call thicknessSpinner.getValueFactory().setValue((int)model.getCurrentStrokeWidth());

        HBox styleBar = new HBox(10, colorPicker, lineLabel, thicknessSpinner, undoButton, redoButton, deleteButton, eraserButton);
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
    }


    public PaintModel getPaintModel() {
        return this.paintModel;
    }

    // ugly way to do this?
        public void setMode(String mode){
            this.paintPanel.setMode(mode);
        }
        private MenuBar createMenuBar() {

        MenuBar menuBar = new MenuBar();
        Menu menu;
        MenuItem menuItem;

        // A menu for File

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

        // Another menu for Edit

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


        @Override
        public void handle(ActionEvent event) {
                System.out.println(((MenuItem) event.getSource()).getText());
                String command = ((MenuItem) event.getSource()).getText();
                System.out.println(command);
                if (command.equals("Exit")) {
                        Platform.exit();
                }
        }

    public PaintModel getModel() {
        return this.paintModel;
    }
}
