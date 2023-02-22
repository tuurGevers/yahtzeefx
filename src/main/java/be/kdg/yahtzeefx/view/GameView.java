package be.kdg.yahtzeefx.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameView extends GridPane {
    private ImageView dice1;
    private ImageView dice2;
    private ImageView dice3;
    private ImageView dice4;
    private ImageView dice5;
    private Button trow;
    private Label trowCount;

    public GameView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        dice1 = new ImageView(new Image(getClass().getResource("/images/die1.png").toExternalForm()));
        dice2 = new ImageView(new Image(getClass().getResource("/images/die1.png").toExternalForm()));
        dice3 = new ImageView(new Image(getClass().getResource("/images/die1.png").toExternalForm()));
        dice4 = new ImageView(new Image(getClass().getResource("/images/die1.png").toExternalForm()));
        dice5 = new ImageView(new Image(getClass().getResource("/images/die1.png").toExternalForm()));
        trow = new Button("gooi");
        trowCount = new Label("trows: 1");
    }

    private void layoutNodes() {
        this.add(dice1, 1, 2);
        this.add(dice2, 2, 2);
        this.add(dice3, 3, 2);
        this.add(dice4, 2, 3);
        this.add(dice5, 3, 3);
        this.add(trow, 2, 4, 2, 1);
        this.add(trowCount, 0, 1);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10));

    }

    ImageView getDice1() {
        return dice1;
    }

    ImageView getDice2() {
        return dice2;
    }

    ImageView getDice3() {
        return dice3;
    }

    ImageView getDice4() {
        return dice4;
    }

    ImageView getDice5() {
        return dice5;
    }

    Button getTrow() {
        return trow;
    }

    Label getTrowCount() {
        return trowCount;
    }
}
