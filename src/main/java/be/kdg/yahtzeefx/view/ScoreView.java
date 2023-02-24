package be.kdg.yahtzeefx.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ScoreView extends GridPane {
    private Label[] eyeLabels = new Label[13];
    private TextField[] eyeTextFields = new TextField[13];
    private Button[] buttons = new Button[13];

    public ScoreView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        for (int i = 0; i < eyeTextFields.length; i++) {
            eyeTextFields[i] = new TextField("");
            eyeTextFields[i].setId(String.valueOf(i));
            buttons[i] = new Button("add");
            buttons[i].setId(String.valueOf(i));

        }
        for (int j = 0; j < 6; j++) {
            eyeLabels[j] = new Label(String.format("%d", j + 1));
        }
        eyeLabels[6] = new Label("3 of a kind");
        eyeLabels[7] = new Label("4 of a kind");
        eyeLabels[8] = new Label("full house");
        eyeLabels[9] = new Label("small straight");
        eyeLabels[10] = new Label("large straight");
        eyeLabels[11] = new Label("yahtzee");
        eyeLabels[12] = new Label("chance");


    }

    private void layoutNodes() {
        for (int i = 0; i < eyeLabels.length; i++) {
            this.add(eyeLabels[i], 0, i);
            this.add(eyeTextFields[i], 1, i);
            eyeTextFields[i].setEditable(false);
            this.add(buttons[i], 2, i);
        }

        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10));

    }

    public Label[] getEyeLabels() {
        return eyeLabels;
    }

    public TextField[] getEyeTextFields() {
        return eyeTextFields;
    }

    public Button[] getButtons() {
        return buttons;
    }
}