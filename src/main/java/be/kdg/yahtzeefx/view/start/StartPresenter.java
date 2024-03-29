package be.kdg.yahtzeefx.view.start;

import be.kdg.yahtzeefx.model.*;
import be.kdg.yahtzeefx.view.game.YahtzeePresenter;
import be.kdg.yahtzeefx.view.game.YahtzeeView;
import be.kdg.yahtzeefx.view.highscores.HighScoreView;

import java.io.IOException;

/**
 * The type Start presenter.
 */
public class StartPresenter {
    private final StartView startView;
    private final YahtzeeView gameView;
    private final YahtzeeModel model;
    private final Log logger;
    private final YahtzeePresenter gamePresenter;
    private final HighScoreView highScoreView;
    private final MusicPlayer musicPlayer;

    /**
     * Instantiates a new Start presenter.
     *
     * @param view          the view
     * @param gameView      the game view
     * @param model         the model
     * @param gamePresenter the game presenter
     * @param highScoreView the high score view
     * @param musicPlayer   the music player
     */
    public StartPresenter(StartView view, YahtzeeView gameView, YahtzeeModel model, YahtzeePresenter gamePresenter, HighScoreView highScoreView, MusicPlayer musicPlayer) {
        this.startView = view;
        this.gameView = gameView;
        this.model = model;
        this.logger = new Log(model);
        this.gamePresenter = gamePresenter;
        this.highScoreView = highScoreView;
        this.musicPlayer = musicPlayer;
        this.musicPlayer.playMusic();
        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        startView.getSinglePlayer().setOnAction(Event -> {
            musicPlayer.playClick();

            model.setPlayer(0, new Player(0, "player 1", new Score()));
            model.setMode(Modes.SINGLE);
            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();

        });

        startView.getLeaderBoard().setOnAction(Event -> {
            model.setPlayer(0, new Player(0, "player 1", new Score()));
            musicPlayer.playClick();

            startView.getScene().setRoot(highScoreView);
            highScoreView.getScene().getWindow().sizeToScene();
        });

        startView.getAi().setOnAction(Event -> {
            model.setPlayer(0, new Player(0, "player 1", new Score()));
            model.getPlayers().add(1, new Player(1, "computer", new Score()));
            model.setMode(Modes.AI);
            model.getComputer().setPlayer(model.getPlayers().get(1));
            musicPlayer.playClick();

            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();

        });
        startView.getMultiplayer().setOnAction(Event -> {
            model.setPlayer(0, new Player(0, "player 1", new Score()));
            model.setMode(Modes.SINGLE);

            for (int i = 1; i < startView.getSpinner(); i++) {
                model.getPlayers().add(new Player(0, String.format("player %d", i + 1), new Score()));
            }
            musicPlayer.playClick();

            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });

        startView.getTournament().setOnAction(Event -> {
            model.setMode(Modes.TOURNAMENT);

            model.setPlayer(0, new Player(0, "player 1", new Score()));
            musicPlayer.playClick();

            for (int i = 1; i < startView.getTournamentSpinner(); i++) {
                model.getPlayers().add(new Player(0, String.format("player %d", i + 1), new Score()));
            }
            gameView.getGameView().getTournamentRounds().setVisible(true);
            startView.getScene().setRoot(gameView);
            gameView.getScene().getWindow().sizeToScene();
        });

        startView.getContinueGame().setOnAction(Event -> {
            musicPlayer.playClick();
            try {
                if (logger.getMode() != Modes.AI) {
                    try {
                        logger.loadSave();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        logger.loadAi();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (model.getMode() == Modes.TOURNAMENT)
                gameView.getGameView().getTournamentRounds().setVisible(true);


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
