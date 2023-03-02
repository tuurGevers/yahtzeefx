package be.kdg.yahtzeefx.view;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.YahtzeeModel;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;

public class YahtzeePresenter {
    private YahtzeeModel model;
    private YahtzeeView view;

    public YahtzeePresenter(
            YahtzeeModel model,
            YahtzeeView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();

    }

    private void addEventHandlers() {
        view.getGameView().getTrow().setOnAction(
                event -> {
                    if (model.trows != 3) {
                        updateView();
                    }
                }
        );

        for (ImageView die : view.getGameView().getDice()) {
            die.setOnMouseClicked(
                    MouseEvent -> {
                        model.getDice()[Integer.parseInt(die.getId())].select();
                        if (model.getDice()[Integer.parseInt(die.getId())].isHeld()) {
                            die.setScaleY(1.5);
                        } else {
                            die.setScaleY(1);
                        }

                    }
            );
        }


        for (Button button : view.getScoreView().getButtons()) {
            button.setOnAction(
                    Event -> {
                        if (model.trows == 3) {
                            Map<String, Integer> scoreCard = model.currentPlayer().score.scores;
                            Map<String, Integer> scores = model.scores();
                            if (!scoreCard.containsKey(String.valueOf(Integer.parseInt(button.getId()) + 1))) {
                                scoreCard.put(String.valueOf(Integer.parseInt(button.getId()) + 1), scores.get(String.valueOf(Integer.parseInt(button.getId()) + 1)));
                                model.currentPlayer().score.addScore(scores.getOrDefault(String.valueOf(Integer.parseInt(button.getId()) + 1), 0));
                                view.getGameView().getScore().setText("score: " + model.currentPlayer().score.getPoints());
                                button.setStyle("-fx-background-color: red;");
                            }
                            model.bonusCheck(model.currentPlayer());
                            if (model.currentPlayer().score.upperBonus) {
                                view.getScoreView().getUpperBonusTexfield().setText("+35");
                            }
                            model.setFinished();
                            model.trows = 0;
                            model.nextTurn();

                        }

                    }
            );
        }

        view.getScoreView().getYahtzeeBonusButton().setOnAction((
                Event -> {
                    if (model.getYahtzee().isValid(model.getDice())) {
                        Map<String, Integer> scoreCard = model.currentPlayer().score.scores;
                        if (scoreCard.containsKey("12")) {
                            if (scoreCard.containsKey("14")) {
                                scoreCard.put("14", scoreCard.get("14") + 100);
                            } else {
                                scoreCard.put("14", 100);
                            }
                        }
                        model.setFinished();
                    }

                }
        ));

    }

    private void updateView() {
        view.getGameView().getCurrentPlayer().setText(String.format("current player: %s", model.currentPlayer().getName()));

        model.rollDice();
        int[] aantallen = new int[5];
        Dice[] dice = model.getDice();
        for (int i = 0; i < 5; i++) {
            aantallen[i] = dice[i].getValue();
        }
        for (int j = 0; j < 5; j++) {
            if (!dice[j].isHeld()) {
                view.getGameView().getDice()[j].setImage(new Image(getClass().getResource("/images/die" + aantallen[j] + ".png").toExternalForm()));
            }
        }
        view.getGameView().getTrowCount().setText(String.format("trows: %d", model.trows));

        if (model.trows == 3) {
            Map<String, Integer> scores = model.scores();
            for (Button button : view.getScoreView().getButtons()) {
                if (scores.containsKey(String.valueOf(Integer.parseInt(button.getId()) + 1)) && scores.get(String.valueOf(Integer.parseInt(button.getId()) + 1)) != 0) {
                    button.setStyle("-fx-background-color: green");
                }
            }
            for (TextField field : view.getScoreView().getEyeTextFields()) {
                String key = String.valueOf(Integer.parseInt(field.getId()) + 1);

                if (scores.containsKey(key)) {
                    field.setText(Integer.toString(scores.get(key)));
                } else {
                    field.setText("0");

                }

            }
            if (model.getYahtzee().isValid(model.getDice())) {
                view.getScoreView().getYahtzeeBonusTextfield().setText("+100");
            } else {
                view.getScoreView().getYahtzeeBonusTextfield().setText(String.valueOf(model.currentPlayer().score.scores.getOrDefault("14", 0)));
            }

            for (int i = 0; i < 5; i++) {
                view.getGameView().getDice()[i].setScaleX(1);
                view.getGameView().getDice()[i].setScaleY(1);
                model.getDice()[i].setHeld(false);
            }
        } else if (model.trows == 1) {
            resetField();
        }
        if (model.isFinished()) {
            view.getGameView().getA().setHeaderText("Spel gespeeld!");
            view.getGameView().getA().setContentText(String.format("Uw score was: %s", model.currentPlayer().score.getPoints()));
            view.getGameView().getA().show();
        }
    }

    private void resetField() {
        Map<String, Integer> currentScore = model.currentPlayer().score.scores;
        System.out.printf("%s, %s", model.currentPlayer().getName(), currentScore.toString());
        view.getGameView().getScore().setText("score: " + model.currentPlayer().score.getPoints());
        for (TextField field : view.getScoreView().getEyeTextFields()) {
            if (currentScore.containsKey(field.getId())) {
                field.setText(String.valueOf(currentScore.get(field.getId())));
            } else {
                field.setText("0");
            }
        }
        for (Button button : view.getScoreView().getButtons()) {
            if (currentScore.containsKey(String.valueOf(Integer.parseInt(button.getId()) + 1))) {
                button.setStyle("-fx-background-color: red;");
                button.setDisable(true);
            } else {
                button.setStyle("-fx-background-color: grey;");
                button.setDisable(false);
            }
        }
    }

    public void addWindowEventHandlers() {


    }
}
