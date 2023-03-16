package be.kdg.yahtzeefx.view.game;

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
    private Label rounds;
    private Label tournamentRounds;
    private Label computerScore;


    public GameView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        //voor elke dice wordt er een imageview gemaakt en een id geset
        dice = new ImageView[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = new ImageView(new Image(getClass().getResource("/images/die1.png").toExternalForm()));
            dice[i].setId(String.valueOf(i));
        }
        //andere ui components initialiseren
        trow = new Button("gooi");
        trowCount = new Label("trows: 1");
        score = new Label("score: 0");
        a = new Alert(Alert.AlertType.INFORMATION);
        currentPlayer = new Label("current player: ");
        rounds = new Label("0/13");
        tournamentRounds= new Label("");
        computerScore = new Label("computer score: 0");
    }

    private void layoutNodes() {
        //dobbelstenen en ander ui componenten plaatsen
        this.add(dice[0], 1, 3);
        this.add(dice[1], 2, 3);
        this.add(dice[2], 3, 3);
        this.add(dice[3], 2, 4);
        this.add(dice[4], 3, 4);
        this.add(trow, 2, 5, 2, 1);
        this.add(trowCount, 0, 1);
        this.add(currentPlayer, 1, 1, 3, 1);
        this.add(score, 0, 2);
        this.add(computerScore, 0, 3);

        this.add(rounds, 3, 1);
        this.add(tournamentRounds, 2, 2);

        //basis styling
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

    Label getRounds() {
        return rounds;
    }

    public Label getTournamentRounds() {
        return tournamentRounds;
    }

    Label getComputerScore() {
        return computerScore;
    }
}
