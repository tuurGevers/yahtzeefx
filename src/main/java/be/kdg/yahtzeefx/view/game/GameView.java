package be.kdg.yahtzeefx.view.game;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Objects;

/**
 * The type Game view.
 */
public class GameView extends GridPane {
    private ImageView[] dice;
    private Button trow;
    private Label trowCount;
    private Label score;
    private TextInputDialog td;
    private Label currentPlayer;
    private Label rounds;
    private Label tournamentRounds;
    private Label computerScore;

    /**
     * Instantiates a new Game view.
     */
    public GameView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

        //voor elke dice wordt er een imageview gemaakt en een id geset
        dice = new ImageView[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/die1.png")).toExternalForm()));
            dice[i].setId(String.valueOf(i));
        }
        //andere ui components initialiseren
        trow = new Button("gooi");
        trowCount = new Label("trows: 1");
        score = new Label("score: 0");
         td = new TextInputDialog("enter name");

        currentPlayer = new Label("current player: ");
        rounds = new Label("0/13");
        tournamentRounds= new Label("");
        computerScore = new Label("");

    }

    private void layoutNodes() {
        //dobbelstenen en ander ui componenten plaatsen
        this.add(dice[0], 1, 3);
        this.add(dice[1], 2, 3);
        this.add(dice[2], 3, 3);
        this.add(dice[3], 2, 4);
        this.add(dice[4], 3, 4);
        this.add(trow, 2, 5, 2, 1);
        this.add(trowCount, 1, 1);
        this.add(currentPlayer, 2, 1, 3, 1);
        this.add(score, 0, 1);
        this.add(computerScore, 6, 1);

        this.add(rounds, 5, 1);
        this.add(tournamentRounds, 7, 1);
        this.tournamentRounds.setVisible(false);
        this.trow.getStyleClass().add("startButtons");
        for (Node node : this.getChildren()) {
            if (node instanceof Label) {
                node.getStyleClass().add("gameLabel");
            }
        }
        //basis styling
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10));
        this.setMinSize(600, 600);
    }


    /**
     * Gets trow.
     *
     * @return the trow
     */
    Button getTrow() {
        return trow;
    }

    /**
     * Gets trow count.
     *
     * @return the trow count
     */
    Label getTrowCount() {
        return trowCount;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    Label getScore() {
        return score;
    }

    /**
     * Get dice image view [ ].
     *
     * @return the image view [ ]
     */
    ImageView[] getDice() {
        return dice;
    }

    /**
     * Gets td.
     *
     * @return the td
     */
    TextInputDialog getTd() {
        return td;
    }

    /**
     * Gets current player.
     *
     * @return the current player
     */
    Label getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets rounds.
     *
     * @return the rounds
     */
    Label getRounds() {
        return rounds;
    }

    /**
     * Gets tournament rounds.
     *
     * @return the tournament rounds
     */
    public Label getTournamentRounds() {
        return tournamentRounds;
    }

    /**
     * Gets computer score.
     *
     * @return the computer score
     */
    Label getComputerScore() {
        return computerScore;
    }
}
