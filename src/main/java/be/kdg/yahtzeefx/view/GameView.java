package be.kdg.yahtzeefx.view;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameView extends GridPane {
    private ImageView[] dice;
    private Button trow;
    private Label trowCount;
    private Label score;
    private Alert a;
    private Label currentPlayer;

    public GameView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        dice = new ImageView[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = new ImageView(new Image(getClass().getResource("/images/die1.png").toExternalForm()));
            dice[i].setId(String.valueOf(i));
        }

        trow = new Button("gooi");
        trowCount = new Label("trows: 1");
        score = new Label("score: 0");
        a = new Alert(Alert.AlertType.INFORMATION);
        currentPlayer = new Label("current player: ");
    }

    private void layoutNodes() {
        this.add(dice[0], 1, 2);
        this.add(dice[1], 2, 2);
        this.add(dice[2], 3, 2);
        this.add(dice[3], 2, 3);
        this.add(dice[4], 3, 3);
        this.add(trow, 2, 4, 2, 1);
        this.add(trowCount, 0, 1);
        this.add(currentPlayer, 1, 1, 3, 1);

        this.add(score, 0, 2);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10));


    }


    Button getTrow() {
        return trow;
    }

    Label getTrowCount() {
        return trowCount;
    }

    Label getScore() {
        return score;
    }

    ImageView[] getDice() {
        return dice;
    }

    Alert getA() {
        return a;
    }

    Label getCurrentPlayer() {
        return currentPlayer;
    }
}
