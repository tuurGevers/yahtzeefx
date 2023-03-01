package be.kdg.yahtzeefx.view;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.YahtzeeModel;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;
import java.util.Objects;

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

    int diceIndex = 0;

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
                            Map<String, Integer> scoreCard = model.getPlayers().get(0).score.scores;
                            Map<String, Integer> scores = model.scores();
                            if (!scoreCard.containsKey(String.valueOf(Integer.parseInt(button.getId()) + 1))) {
                                scoreCard.put(String.valueOf(Integer.parseInt(button.getId()) + 1), scores.get(String.valueOf(Integer.parseInt(button.getId()) + 1)));
                                model.getPlayers().get(0).score.addScore(scores.getOrDefault(String.valueOf(Integer.parseInt(button.getId()) + 1), 0));
                                view.getGameView().getScore().setText("score: " + model.getPlayers().get(0).score.getPoints());
                                button.setStyle("-fx-background-color: red;");
                                button.setDisable(true);

                            }
                            model.bonusCheck(model.getPlayers().get(0));
                            if (model.getPlayers().get(0).score.upperBonus) {
                                view.getScoreView().getUpperBonusTexfield().setText("+35");
                            }
                            model.setFinished();
                            model.trows = 0;
                        }

                    }
            );
        }

        view.getScoreView().getYahtzeeBonusButton().setOnAction((
                Event -> {
                    if (model.getYahtzee().isValid(model.getDice())) {
                        Map<String, Integer> scoreCard = model.getPlayers().get(0).score.scores;
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
            int index = 0;
            for (TextField field : view.getScoreView().getEyeTextFields()) {
                String key = String.valueOf(Integer.parseInt(field.getId()) + 1);

                if (scores.containsKey(key)) {
                    field.setText(Integer.toString(scores.get(key)));
                } else {
                    field.setText("0");

                }

                index++;
            }
            if (model.getYahtzee().isValid(model.getDice())) {
                view.getScoreView().getYahtzeeBonusTextfield().setText("+100");
            }else{
                view.getScoreView().getYahtzeeBonusTextfield().setText(String.valueOf(model.getPlayers().get(0).score.scores.getOrDefault("14",0)));
            }

            for (int i = 0; i < 5; i++) {
                view.getGameView().getDice()[i].setScaleX(1);
                view.getGameView().getDice()[i].setScaleY(1);
                model.getDice()[i].setHeld(false);
            }


        }
        if(model.isFinished()){
            view.getGameView().getA().setHeaderText("Spel gespeeld!");
            view.getGameView().getA().setContentText(String.format("Uw score was: %d", model.getPlayers().get(0).score.getPoints()));
            view.getGameView().getA().show();
        }
    }

    public void addWindowEventHandlers() {


    }
}
