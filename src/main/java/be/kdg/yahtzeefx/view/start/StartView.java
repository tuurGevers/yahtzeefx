package be.kdg.yahtzeefx.view.start;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import java.util.Objects;

public class StartView extends GridPane {
    private Button singlePlayer;
    private Button ai;

    private Button multiplayer;
    private Button tournament;
    private Button continueGame;
    private Button leaderBoard;

    private Spinner<Integer> spinner;
    private Spinner<Integer> tournamentSpinner;

    private GridPane multiplayerpane;
    private GridPane tournamentPain;

    public StartView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        singlePlayer = new Button("single player");
        ai = new Button("play against pc");
        leaderBoard = new Button("LeaderBoard");
        spinner = new Spinner<>(2, 5, 2, 1);
        tournamentSpinner = new Spinner<>(2, 5, 2, 1);
        multiplayer = new Button("multiplayer");
        tournament = new Button("tournament");
        continueGame = new Button("continue");
        multiplayerpane = new GridPane();
        tournamentPain = new GridPane();
    }

    private void layoutNodes() {
        this.add(singlePlayer, 1, 1, 2, 1);
        this.add(ai, 1, 2, 2, 1);

        multiplayerpane.add(spinner, 1, 0);
        multiplayerpane.add(multiplayer, 0, 0);
        this.add(multiplayerpane, 1, 3, 2, 1);
        tournamentPain.add(tournamentSpinner, 1, 0);
        tournamentPain.add(tournament, 0, 0);

        this.add(tournamentPain, 1, 4, 2, 1);
        this.add(continueGame, 1, 5, 2, 1);
        this.add(leaderBoard, 1, 6, 2, 1);

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10));


        this.setBackground(new Background(
                new BackgroundImage(
                        new Image(getClass().getResource("/images/yahtzee.png").toExternalForm()),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        new BackgroundPosition(Side.LEFT, 0.0, false, Side.BOTTOM, 0.0, false),
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true,true,true,true)
                        )));

        for (Node node : this.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setPrefWidth(Double.MAX_VALUE);
            }
        }

    }

    Button getSinglePlayer() {
        return singlePlayer;
    }

    Button getMultiplayer() {
        return multiplayer;
    }

    int getSpinner() {
        return spinner.getValue();
    }

    int getTournamentSpinner() {
        return tournamentSpinner.getValue();
    }

    Button getTournament() {
        return tournament;
    }

    Button getContinueGame() {
        return continueGame;
    }

    Button getLeaderBoard() {
        return leaderBoard;
    }

    Button getAi() {
        return ai;
    }
}
