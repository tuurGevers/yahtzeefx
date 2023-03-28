package be.kdg.yahtzeefx.view.game;

import be.kdg.yahtzeefx.Main;
import be.kdg.yahtzeefx.model.*;
import be.kdg.yahtzeefx.view.start.StartView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import static be.kdg.yahtzeefx.Main.getView;

public class YahtzeePresenter {
    private YahtzeeModel model;
    private YahtzeeView view;
    private boolean selected;
    private Log logger;
    private MusicPlayer musicPlayer;


    public YahtzeePresenter(
            YahtzeeModel model,
            YahtzeeView view,
            MusicPlayer musicPlayer) {
        this.model = model;
        this.view = view;

        this.selected = false;
        this.addEventHandlers();
        this.updateView();
        this.logger = new Log(model);
        this.musicPlayer = musicPlayer;

    }

    private void addEventHandlers() {
        //event handler voor gooi knop
        view.getGameView().getTrow().setOnAction(
                event -> {
                    if (!selected && model.trows < 3) {
                        //rol dobbelstenen
                        model.rollDice();
                        try {
                            rolling();

                            updateView();
                            logger.saveMode();
                            logger.saveDice();
                            musicPlayer.playClick();
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        //select handler voor alle dobbelstenen
        for (ImageView die : view.getGameView().getDice()) {
            die.setOnMouseClicked(
                    MouseEvent -> {
                        if (model.trows != 0) {
                            model.getDice()[Integer.parseInt(die.getId())].select();
                            selectDice();
                            try {
                                logger.saveDice();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            musicPlayer.playClick();
                        }

                    }
            );
        }

        //event handler voor alle score buttons
        for (Button button : view.getScoreView().getButtons()) {
            button.setOnAction(
                    Event -> {

                        //map van scores
                        Map<String, Integer> scoreCard = model.currentPlayer().score.scores;
                        Map<String, Integer> scores = model.scores();

                        //check of de speler de waarde bij de button nog niet heeft gekozen
                        if (!scoreCard.containsKey(String.valueOf(Integer.parseInt(button.getId()) + 1)) && !selected) {
                            //de waarde wordt toegevoegd aan de scorekaart van de speler
                            scoreCard.put(String.valueOf(Integer.parseInt(button.getId()) + 1), scores.get(String.valueOf(Integer.parseInt(button.getId()) + 1)));
                            model.currentPlayer().score.addScore(scores.getOrDefault(String.valueOf(Integer.parseInt(button.getId()) + 1), 0));
                            this.selected = true;
                            //ui update
                            view.getGameView().getScore().setText("score: " + model.currentPlayer().score.getPoints());
                            button.setStyle("-fx-background-color: red;");
                        }
                        //check of de upperbous voltooid is en pas ui aan als het zo is
                        model.bonusCheck(model.currentPlayer());
                        if (model.currentPlayer().score.upperBonus) {
                            view.getScoreView().getUpperBonusTexfield().setText("+35");
                        }
                        try {
                            endTurn();
                        } catch (FileException e) {
                            e.printStackTrace();
                        }
                        //einde van de beurt
                        try {
                            logger.saveGame();
                        } catch (FileException e) {
                            e.printStackTrace();
                        }

                        musicPlayer.playClick();

                    }
            );
        }

        //event voor als er een yahtzeebonus is
        view.getScoreView().getYahtzeeBonusButton().setOnAction((
                Event -> {
                    // check of er een yahtzee is en of yhatzee al is ingevuld en als het zo is worden er 100 punten toegevoegd
                    if (model.getYahtzee().isValid(model.getDice())) {
                        Map<String, Integer> scoreCard = model.currentPlayer().score.scores;
                        if (scoreCard.containsKey("12") && scoreCard.get("12") != 0) {
                            if (scoreCard.containsKey("14")) {
                                scoreCard.put("14", scoreCard.get("14") + 100);
                            } else {
                                scoreCard.put("14", 100);
                            }
                        }
                        //einde beurt
                        try {
                            logger.saveGame();
                        } catch (FileException e) {
                            e.printStackTrace();
                        }
                        try {
                            endTurn();
                        } catch (FileException e) {
                            e.printStackTrace();
                        }
                    }

                }
        ));

    }

    private void rolling() throws InterruptedException {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    for (ImageView dice : view.getGameView().getDice())
                        dice.setImage(new Image(getClass().getResource("/images/rolling.gif").toExternalForm()));
                    musicPlayer.playDice();
                }),
                new KeyFrame(Duration.seconds(0.5), e -> {
                   setImages();
                })
        );
        timeline.play();

    }


    private void endTurn() throws FileException {
        view.getScene().getWindow().sizeToScene();

        model.setFinished();
        model.trows = 0;
        model.nextTurn();
        this.selected = false;
        for (int i = 0; i < 5; i++) {
            model.getDice()[i].setHeld(false);
            selectDice();
        }
        view.getGameView().getTrow().fire();

    }

    public void updateView() {

        if (model.currentPlayer().getId() != 0 && model.getMode() == Modes.AI) {

            try {
                model.getComputer().takeTurn(model);
                endTurn();
            } catch (FileException | IOException e) {
                e.printStackTrace();
            }
            view.getGameView().getComputerScore().setText("computer score: " + model.getComputer().getPlayer().score.getPoints());

        }
        selectDice();
        updateUI();
        updateGameUi();

    }

    private void updateGameUi() {
        if (model.trows != 0) {
            //als beurten om zijn
            //krijg actuele scores
            Map<String, Integer> scores = model.scores();
            Map<String, Integer> scoreCard = model.currentPlayer().score.scores;

            //alle buttons die mogelijke waardes hebben worden groen
            for (Button button : view.getScoreView().getButtons()) {
                String key = String.valueOf(Integer.parseInt(button.getId()) + 1);
                if (scores.containsKey(key) && scores.get(key) != 0 && !scoreCard.containsKey(key)) {
                    button.setStyle("-fx-background-color: green");
                    button.setDisable(false);
                } else if (!scoreCard.containsKey(key)) {
                    button.setStyle("-fx-background-color: grey");
                    button.setDisable(false);
                } else {
                    button.setStyle("-fx-background-color: red;");
                    button.setDisable(true);
                }
            }

            //textfielden worden geupdate naar scores
            for (TextField field : view.getScoreView().getEyeTextFields()) {
                String key = String.valueOf(Integer.parseInt(field.getId()) + 1);
                if (scores.containsKey(key) && !scoreCard.containsKey(key)) {
                    field.setText(Integer.toString(scores.get(key)));
                } else if (scoreCard.containsKey(key)) {
                    field.setText(String.valueOf(scoreCard.get(key)));
                } else {
                    field.setText("0");

                }

            }

            //als er een bonus yahtzee is wordt +100 weergegeven
            if (model.getYahtzee().isValid(model.getDice()) && scoreCard.containsKey("12")) {
                view.getScoreView().getYahtzeeBonusTextfield().setText("+100");
            } else {
                view.getScoreView().getYahtzeeBonusTextfield().setText(String.valueOf(model.currentPlayer().score.scores.getOrDefault("14", 0)));
            }

            endGameCheck();
        } else {
            for (Button button : view.getScoreView().getButtons()) {
                button.setStyle("-fx-background-color: red;");
                button.setDisable(true);
            }
        }
    }

    private void endGameCheck() {
        //als het spel gedaan is wordt er een alert getoond
        if (model.isFinished() && model.getMode() != Modes.TOURNAMENT) {
            if (model.getMode() == Modes.AI) {
                if (model.getWinner() != model.getPlayers().get(1)) {
                    endAlert();
                } else {
                    model.restart();
                    view.getGameView().getScene().setRoot(getView());
                }
            } else {
                endAlert();
            }
        } else if (model.isFinished() && model.getTournamentRound() == 5) {
            view.getGameView().getTd().setHeaderText("Spel gespeeld!");
            try {
                view.getGameView().getTd().setContentText(model.getLog().getWinner());
                view.getGameView().getTd().showAndWait();
                logger.addHighScore(Objects.requireNonNullElse(view.getGameView().getTd().getEditor().getText(), "random"), Integer.parseInt(model.playerFromString(model.getLog().getWinner()).score.getPoints()));
                model.restart();
                view.getGameView().getScene().setRoot(getView());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (model.isFinished()) {
            model.reset();
            updateView();
        }
    }

    private void endAlert() {
        view.getGameView().getTd().setHeaderText("Spel gespeeld!");
        view.getGameView().getTd().setContentText(String.format("De hoogste score was: %s", model.getWinner().score.getPoints()));
        view.getGameView().getTd().showAndWait();
        logger.addHighScore(view.getGameView().getTd().getEditor().getText(), Integer.parseInt(model.getWinner().score.getPoints()));
        model.restart();
        view.getGameView().getScene().setRoot(getView());
    }

    private void updateUI() {
        //update de score naar die van de huidige player
        view.getGameView().getScore().setText("score: " + model.currentPlayer().score.getPoints());

        //update value to current round
        view.getGameView().getRounds().setText(String.format("round: %s/13", model.getRound()));

        //update ui naar actuele speler
        view.getGameView().getCurrentPlayer().setText(String.format("current player: %s", model.currentPlayer().getName()));

        setImages();

        //update trowcount label
        view.getGameView().getTrowCount().setText(String.format("trows: %d", model.trows));
        if (model.getMode() == Modes.TOURNAMENT) {
            view.getGameView().getTournamentRounds().setText(model.getTournamentRound() + "/5");
        }
    }

    private void setImages() {
        //maak een array met de waardes van de dobbelstenen
        int[] aantallen = new int[5];
        Dice[] dice = model.getDice();

        for (int i = 0; i < 5; i++) {
            aantallen[i] = dice[i].getValue();
            //update imageview
            view.getGameView().getDice()[i].setImage(new Image(getClass().getResource("/images/die" + aantallen[i] + ".png").toExternalForm()));
        }
    }

    private void selectDice() {
        //maak een array met de waardes van de dobbelstenen
        int[] aantallen = new int[5];
        Dice[] dice = model.getDice();
        int selectedCount = model.getSelectedCount();
        view.getSelectedView().getChildren().removeAll();
        for (int i = 0; i < 5; i++) {
            ImageView selectedDice = view.getSelectedView().getDice()[i];
            aantallen[i] = dice[i].getValue();
            //update imageview
            view.getGameView().getDice()[i].setImage(new Image(getClass().getResource("/images/die" + aantallen[i] + ".png").toExternalForm()));
            if (dice[i].isHeld()) {
                view.getGameView().getDice()[i].setVisible(false);
                if (!view.getSelectedView().getChildren().contains(selectedDice)) {

                    view.getSelectedView().add(selectedDice, i + 1, 0);
                    selectedDice.setVisible(true);
                    selectedDice.setImage(new Image(getClass().getResource("/images/die" + aantallen[i] + ".png").toExternalForm()));
                    view.getScene().getWindow().sizeToScene();

                }
            } else {
                selectedCount--;
                view.getSelectedView().getChildren().remove(selectedDice);
                view.getGameView().getDice()[i].setVisible(true);
            }
        }
    }

    public void addWindowEventHandlers() {
        view.getScene().getWindow().setOnCloseRequest(WindowEvent -> {
            try {
                logger.saveMode();
                logger.saveGame();
            } catch (IOException | FileException e) {
                e.printStackTrace();
            }
        });

    }
}
