package be.kdg.yahtzeefx.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ScoreView extends GridPane {
    private Label[] eyeLabels = new Label[6];
    private TextField[] eyeTextFields = new TextField[6];

    public ScoreView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        for (int i =0; i<eyeLabels.length; i++){
            eyeLabels[i] = new Label(String.format("%d", i+1));
            eyeTextFields[i] = new TextField("");
        }

    }

    private void layoutNodes() {
        for (int i =0; i<eyeLabels.length; i++){
            this.add(eyeLabels[i], i, 0);
            this.add(eyeTextFields[i], i, 1);
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
}
