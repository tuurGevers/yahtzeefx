package be.kdg.yahtzeefx.view.start;

import be.kdg.yahtzeefx.model.*;
import be.kdg.yahtzeefx.view.game.YahtzeePresenter;
import be.kdg.yahtzeefx.view.game.YahtzeeView;

import java.io.IOException;

public class StartPresenter {
    private StartView startView;
    private YahtzeeView gameView;
    private YahtzeeModel model;
    private Log logger;
    private YahtzeePresenter gamePresenter;
    public StartPresenter(StartView view, YahtzeeView gameView, YahtzeeModel model, YahtzeePresenter gamePresenter) {
        this.startView = view;
        this.gameView = gameView;
        this.model = model;
        this.logger = new Log(model);
        this.gamePresenter = gamePresenter;
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

            for (int i = 1; i < startView.getSpinner(); i++) {
                model.getPlayers().add(new Player(0, String.format("player %d", i + 1), new Score()));
            }
            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });

        startView.getTournament().setOnAction(Event -> {
            model.setMode(Modes.TOURNAMENT);

            model.setPlayer(0, new Player(0, "player 1", new Score()));

            for (int i = 1; i < startView.getTournamentSpinner(); i++) {
                model.getPlayers().add(new Player(0, String.format("player %d", i + 1), new Score()));
            }
            try {
                model.getLog().createRounds();
            } catch (IOException e) {
                e.printStackTrace();
            }

            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });

        startView.getContinueGame().setOnAction(Event -> {
            try {
                logger.loadSave();
            } catch (IOException e) {
                e.printStackTrace();
            }
            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
            try {
                logger.loadDice();
                gamePresenter.updateView();
                model.setTournamentRound(logger.loadTournamentRound());
                gameView.getGameView().getTournamentRounds().setText(model.getTournamentRound() + "/5");

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateView() {

    }
}
