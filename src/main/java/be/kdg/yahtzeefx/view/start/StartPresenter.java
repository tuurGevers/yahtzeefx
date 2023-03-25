package be.kdg.yahtzeefx.view.start;

import be.kdg.yahtzeefx.model.*;
import be.kdg.yahtzeefx.view.game.YahtzeePresenter;
import be.kdg.yahtzeefx.view.game.YahtzeeView;
import be.kdg.yahtzeefx.view.highscores.HighScorePresenter;
import be.kdg.yahtzeefx.view.highscores.HighScoreView;

import java.io.IOException;

public class StartPresenter {
    private final StartView startView;
    private final YahtzeeView gameView;
    private final YahtzeeModel model;
    private final Log logger;
    private final YahtzeePresenter gamePresenter;
    private final HighScoreView highScoreView;
    private final HighScorePresenter highScorePresenter;
    public StartPresenter(StartView view, YahtzeeView gameView, YahtzeeModel model, YahtzeePresenter gamePresenter,HighScoreView highScoreView, HighScorePresenter highScorePresenter) {
        this.startView = view;
        this.gameView = gameView;
        this.model = model;
        this.logger = new Log(model);
        this.gamePresenter = gamePresenter;
        this.highScoreView = highScoreView;
        this.highScorePresenter = highScorePresenter;
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        startView.getSinglePlayer().setOnAction(Event -> {
            model.playClick();

            model.setPlayer(0, new Player(0, "player 1", new Score()));
            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });

        startView.getLeaderBoard().setOnAction(Event -> {
            model.setPlayer(0, new Player(0, "player 1", new Score()));
            model.playClick();

            startView.getScene().setRoot(highScoreView);
            highScoreView.getScene().getWindow().sizeToScene();
        });

        startView.getAi().setOnAction(Event -> {
            model.setPlayer(0, new Player(0, "player 1", new Score()));
            model.getPlayers().add(1, new Player(1, "computer", new Score()));
            model.setMode(Modes.AI);
            model.getComputer().setPlayer(model.getPlayers().get(1));
            model.playClick();

            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();

        });
        startView.getMultiplayer().setOnAction(Event -> {
            model.setPlayer(0, new Player(0, "player 1", new Score()));

            for (int i = 1; i < startView.getSpinner(); i++) {
                model.getPlayers().add(new Player(0, String.format("player %d", i + 1), new Score()));
            }
            model.playClick();

            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });

        startView.getTournament().setOnAction(Event -> {
            model.setMode(Modes.TOURNAMENT);

            model.setPlayer(0, new Player(0, "player 1", new Score()));
            model.playClick();

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
            model.playClick();

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
