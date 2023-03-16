package be.kdg.yahtzeefx.view.start;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StartView extends GridPane {
    private Button singlePlayer;
    private Button ai;

    private Button multiplayer;
    private Button tournament;
    private Button continueGame;

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

        spinner = new Spinner<>(2, 5, 2, 1);
        tournamentSpinner = new Spinner<>(2, 5, 2, 1);
        multiplayer = new Button("multiplayer");
        tournament = new Button("tournament");
        continueGame= new Button("continue");
        multiplayerpane = new GridPane();
        tournamentPain = new GridPane();
    }

    private void layoutNodes() {
        this.add(singlePlayer, 1, 1);
        this.add(ai, 2, 1);

        multiplayerpane.add(spinner, 0, 0);
        multiplayerpane.add(multiplayer, 1, 0);
        this.add(multiplayerpane, 3, 1);
        tournamentPain.add(tournamentSpinner, 0, 0);
        tournamentPain.add(tournament, 1, 0);

        this.add(tournamentPain, 4, 1);
        this.add(continueGame, 5, 1);

        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10));

    }

    public Button getSinglePlayer() {
        return singlePlayer;
    }

    public Button getMultiplayer() {
        return multiplayer;
    }

    public int getSpinner() {
        return spinner.getValue();
    }

    public int getTournamentSpinner() {
        return tournamentSpinner.getValue();
    }

    public Button getTournament() {
        return tournament;
    }

    public Button getContinueGame() {
        return continueGame;
    }

    public Button getAi() {
        return ai;
    }
}
