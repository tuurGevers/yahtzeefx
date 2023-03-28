package be.kdg.yahtzeefx.view.game;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Score view.
 */
public class ScoreView extends GridPane {
    private final Label[] eyeLabels = new Label[13];
    private final TextField[] eyeTextFields = new TextField[13];
    /**
     * The Buttons.
     */
    List<Button> buttons = new ArrayList<>();
    private Label upperBonusLabel, yahtzeeBonusLabel;
    private TextField upperBonusTexfield, yahtzeeBonusTextfield;
    private Button yahtzeeBonusButton;

    /**
     * Instantiates a new Score view.
     */
    public ScoreView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        //alle textfields en basis buttons worden geïnitializeerd en krijgen een id
        for (int i = 0; i < eyeTextFields.length; i++) {
            eyeTextFields[i] = new TextField("");
            eyeTextFields[i].setId(String.valueOf(i));
            buttons.add(new Button("add"));
            buttons.get(i).setId(String.valueOf(i));
        }
        //labels worden geïntialiseerd
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

        //bonussen worden geïnitialiseerd
        upperBonusLabel = new Label("upper bonus");
        yahtzeeBonusLabel = new Label("yahtzee bonus");
        upperBonusTexfield = new TextField("");
        yahtzeeBonusTextfield = new TextField("");
        yahtzeeBonusButton = new Button("add");
        yahtzeeBonusButton.setId("13");
        buttons.add(yahtzeeBonusButton);
    }

    private void layoutNodes() {
        //layout
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

        this.setStyle("-fx-background-color: #F2F2F2; -fx-border-color: black; -fx-border-width: 2px;-fx-end-margin: 1em");
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10));

    }

    /**
     * Get eye text fields text field [ ].
     *
     * @return the text field [ ]
     */
    public TextField[] getEyeTextFields() {
        return eyeTextFields;
    }

    /**
     * Gets buttons.
     *
     * @return the buttons
     */
    public List<Button> getButtons() {
        return buttons;
    }

    /**
     * Gets upper bonus texfield.
     *
     * @return the upper bonus texfield
     */
    public TextField getUpperBonusTexfield() {
        return upperBonusTexfield;
    }

    /**
     * Gets yahtzee bonus textfield.
     *
     * @return the yahtzee bonus textfield
     */
    public TextField getYahtzeeBonusTextfield() {
        return yahtzeeBonusTextfield;
    }

    /**
     * Gets yahtzee bonus button.
     *
     * @return the yahtzee bonus button
     */
    public Button getYahtzeeBonusButton() {
        return yahtzeeBonusButton;
    }
}
