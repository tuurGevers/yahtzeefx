package be.kdg.yahtzeefx.view.start;

import be.kdg.yahtzeefx.model.Modes;
import be.kdg.yahtzeefx.model.Player;
import be.kdg.yahtzeefx.model.Score;
import be.kdg.yahtzeefx.model.YahtzeeModel;
import be.kdg.yahtzeefx.view.game.YahtzeePresenter;
import be.kdg.yahtzeefx.view.game.YahtzeeView;

public class StartPresenter {
    private StartView startView;
    private YahtzeeView gameView;
    private YahtzeeModel model;

    public StartPresenter(StartView view, YahtzeeView gameView, YahtzeeModel model) {
        this.startView = view;
        this.gameView = gameView;
        this.model = model;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        startView.getSinglePlayer().setOnAction(Event -> {
            model.setPlayer(0, new Player(0, "player 1", new Score()));
            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });

        startView.getMultiplayer().setOnAction(Event -> {
            model.setPlayer(0, new Player(0, "player 1", new Score()));

            for(int i =1; i < startView.getSpinner(); i++){
                model.getPlayers().add(new Player(0, String.format("player %d", i+1), new Score()));
            }
            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });

        startView.getTournament().setOnAction(Event -> {
            model.setMode(Modes.TOURNAMENT);
            model.setPlayer(0, new Player(0, "player 1", new Score()));

            for(int i =1; i < startView.getTournamentSpinner(); i++){
                model.getPlayers().add(new Player(0, String.format("player %d", i+1), new Score()));
            }
            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });
    }

    private void updateView() {

    }
}
