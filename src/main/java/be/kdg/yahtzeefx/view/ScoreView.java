package be.kdg.yahtzeefx.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreView extends GridPane {
    private Label[] eyeLabels = new Label[13];
    private TextField[] eyeTextFields = new TextField[13];
    List<Button> buttons = new ArrayList<>();
    private Label upperBonusLabel, yahtzeeBonusLabel;
    private TextField upperBonusTexfield, yahtzeeBonusTextfield;
    private Button yahtzeeBonusButton;

    public ScoreView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        for (int i = 0; i < eyeTextFields.length; i++) {
            eyeTextFields[i] = new TextField("");
            eyeTextFields[i].setId(String.valueOf(i));
            buttons.add(new Button("add"));
            buttons.get(i).setId(String.valueOf(i));
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
        upperBonusLabel = new Label("upper bonus");
        yahtzeeBonusLabel = new Label("yahtzee bonus");
        upperBonusTexfield = new TextField("");
        yahtzeeBonusTextfield = new TextField("");
        yahtzeeBonusButton = new Button("add");
        yahtzeeBonusButton.setId("13");
        buttons.add(yahtzeeBonusButton);
    }

    private void layoutNodes() {
        for (int i = 0; i < 6; i++) {
            this.add(eyeLabels[i], 0, i);
            this.add(eyeTextFields[i], 1, i);
            eyeTextFields[i].setEditable(false);
            this.add(buttons.get(i), 2, i);
        }
        this.add(upperBonusLabel, 0, 6);
        this.add(upperBonusTexfield, 1, 6);
        upperBonusTexfield.setEditable(false);
        for (int i = 6; i < eyeTextFields.length; i++) {
            this.add(eyeLabels[i], 0, i + 1);
            this.add(eyeTextFields[i], 1, i + 1);
            eyeTextFields[i].setEditable(false);
            this.add(buttons.get(i), 2, i + 1);
        }
        this.add(yahtzeeBonusLabel, 0, 14);
        this.add(yahtzeeBonusTextfield, 1, 14);
        this.add(yahtzeeBonusButton, 2, 14);

        upperBonusTexfield.setEditable(false);
        yahtzeeBonusTextfield.setEditable(false);

        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10));

    }

    public TextField[] getEyeTextFields() {
        return eyeTextFields;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public TextField getUpperBonusTexfield() {
        return upperBonusTexfield;
    }

    public TextField getYahtzeeBonusTextfield() {
        return yahtzeeBonusTextfield;
    }

    public Button getYahtzeeBonusButton() {
        return yahtzeeBonusButton;
    }
}
