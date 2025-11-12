package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    private View view;
    private int paletteRow = 1;
    private Button selectedButton; // keep track of which button is selected

    public ShapeChooserPanel(View view) {
        this.view = view;

        String[] buttonLabels = {"Circle", "Rectangle", "Square", "Squiggle", "Polyline", "Oval", "Triangle"};
        Node[] icons = Icons.createIcons();

        int row = 0;
        for (int i = 0; i < buttonLabels.length; i++) {
            Button button = new Button();
            button.setMinWidth(100);
            button.setMinHeight(40);
            button.setGraphic(icons[i]);

            Tooltip tooltip = new Tooltip(buttonLabels[i]);
            Tooltip.install(button, tooltip);

            button.setUserData(buttonLabels[i]);
            button.setOnAction(this);

            this.add(button, 0, row);
            row++;
        }
        // toggle between fill and outline
        Button fillToggle = new Button("Outline Mode"); // default
        fillToggle.setMinWidth(100);
        fillToggle.setMinHeight(40);

        fillToggle.setOnAction(e -> {
            this.view.getPaintModel().toggleFillMode(); // access PaintModel through View
            if (this.view.getPaintModel().isFillMode()) {
                fillToggle.setText("Fill Mode");
            } else {
                fillToggle.setText("Outline Mode");
            }
            System.out.println("Fill mode: " + this.view.getPaintModel().isFillMode());
        });

        this.add(fillToggle, 0, row);


        Label accessible = new Label("Accessible Palette:");
        this.add(accessible, 1, 0);

        Color[] accessibleColors = {
                Color.web("#000000"),
                Color.web("#0072B2"),
                Color.web("#E69F00"),
                Color.web("#009E73"),
                Color.web("#CC79A7")
        };

        Button colorBtn = null;
        for (Color c : accessibleColors) {
            colorBtn = new Button();
            colorBtn.setMinSize(30, 30);
            colorBtn.setStyle("-fx-background-color: " + toHex(c) + ";");

            Tooltip tip = new Tooltip("CVD-safe color: " + toHex(c));
            Tooltip.install(colorBtn, tip);

            colorBtn.setOnAction(e -> {
                view.getModel().setCurrentColor(c);
            });
            this.add(colorBtn, 1, paletteRow++);
        }
    }


    private String toHex(Color c){
                int r = (int)(c.getRed()*255);
                int g = (int) (c.getGreen() * 255);
                int b = (int)(c.getBlue()*255);
               return String.format("#%02X%02X%02X", r, g, b);
    }

    @Override
    public void handle(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String mode = (String) clickedButton.getUserData();

        view.setMode(mode);
        System.out.println(mode);

        highlightButton(clickedButton);
    }

    private void highlightButton(Button button) {
        if (selectedButton != null) {
            selectedButton.setStyle("");
        }
        selectedButton = button;
        selectedButton.setStyle("-fx-background-color: #87CEFA;");
    }
}
