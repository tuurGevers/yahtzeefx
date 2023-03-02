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
        //event handler voor gooi knop
        view.getGameView().getTrow().setOnAction(
                event -> {
                    if (model.trows != 3) {
                        updateView();
                    }
                }
        );

        //select handler voor alle dobbelstenen
        for (ImageView die : view.getGameView().getDice()) {
            die.setOnMouseClicked(
                    MouseEvent -> {
                        model.getDice()[Integer.parseInt(die.getId())].select();
                        if (model.getDice()[Integer.parseInt(die.getId())].isHeld()) {
                            die.setScaleY(1.2);
                            die.setScaleX(1.2);

                        } else {
                            resetScale(die);

                        }
                    }

            );
        }

        //event handler voor alle score buttons
        for (Button button : view.getScoreView().getButtons()) {
            button.setOnAction(
                    Event -> {
                        //checl of de worpen voltooid zijn
                        if (model.trows == 3) {
                            //map van scores
                            Map<String, Integer> scoreCard = model.currentPlayer().score.scores;
                            Map<String, Integer> scores = model.scores();

                            //check of de speler de waarde bij de button nog niet heeft gekozen
                            if (!scoreCard.containsKey(String.valueOf(Integer.parseInt(button.getId()) + 1))) {
                                //de waarde wordt toegevoegd aan de scorekaart van de speler
                                scoreCard.put(String.valueOf(Integer.parseInt(button.getId()) + 1), scores.get(String.valueOf(Integer.parseInt(button.getId()) + 1)));
                                model.currentPlayer().score.addScore(scores.getOrDefault(String.valueOf(Integer.parseInt(button.getId()) + 1), 0));

                                //ui update
                                view.getGameView().getScore().setText("score: " + model.currentPlayer().score.getPoints());
                                button.setStyle("-fx-background-color: red;");
                            }
                            //check of de upperbous voltooid is en pas ui aan als het zo is
                            model.bonusCheck(model.currentPlayer());
                            if (model.currentPlayer().score.upperBonus) {
                                view.getScoreView().getUpperBonusTexfield().setText("+35");
                            }
                            //einde van de beurt
                            endTurn();

                        }

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
                        endTurn();
                    }

                }
        ));

    }

    private void endTurn() {
        model.setFinished();
        model.trows = 0;
        model.nextTurn();
    }

    private void updateView() {
        //update value to current round
        view.getGameView().getRounds().setText(String.format("round: %s/13", model.getRound()));

        //update ui naar actuele speler
        view.getGameView().getCurrentPlayer().setText(String.format("current player: %s", model.currentPlayer().getName()));

        //rol dobbelstenen
        model.rollDice();

        //maak een array met de waardes van de dobbelstenen
        int[] aantallen = new int[5];
        Dice[] dice = model.getDice();

        for (int i = 0; i < 5; i++) {
            aantallen[i] = dice[i].getValue();
            //update imageview
            view.getGameView().getDice()[i].setImage(new Image(getClass().getResource("/images/die" + aantallen[i] + ".png").toExternalForm()));
        }

        //update trowcount label
        view.getGameView().getTrowCount().setText(String.format("trows: %d", model.trows));

        //als beurten om zijn
        if (model.trows == 3) {
            //krijg actuele scores
            Map<String, Integer> scores = model.scores();
            Map<String, Integer> scoreCard = model.currentPlayer().score.scores;

            //alle buttons die mogelijke waardes hebben worden groen
            for (Button button : view.getScoreView().getButtons()) {
                String key = String.valueOf(Integer.parseInt(button.getId()) + 1);
                if (scores.containsKey(key) && scores.get(key) != 0 && !scoreCard.containsKey(key)) {
                    button.setStyle("-fx-background-color: green");
                }
            }

            //textfoielden worden geupdate naar scores
            for (TextField field : view.getScoreView().getEyeTextFields()) {
                String key = String.valueOf(Integer.parseInt(field.getId()) + 1);
                if (scores.containsKey(key) && !scoreCard.containsKey(key)) {
                    field.setText(Integer.toString(scores.get(key)));
                } else if (!scoreCard.containsKey(key)) {
                    field.setText("0");
                }

            }

            //als er een bonus yahtzee is wordt +100 weergegeven
            if (model.getYahtzee().isValid(model.getDice()) && scoreCard.containsKey("12")) {
                view.getScoreView().getYahtzeeBonusTextfield().setText("+100");
            } else {
                view.getScoreView().getYahtzeeBonusTextfield().setText(String.valueOf(model.currentPlayer().score.scores.getOrDefault("14", 0)));
            }

            //reset stenen
            for (int i = 0; i < 5; i++) {
                resetScale(view.getGameView().getDice()[i]);
                model.getDice()[i].setHeld(false);
            }
            //als het de eerste worp is wordt het vel gerest
        } else if (model.trows == 1) {
            resetField();
        }
        //als het spel gedaan is wordt er een alert getoond
        if (model.isFinished()) {
            view.getGameView().getA().setHeaderText("Spel gespeeld!");
            view.getGameView().getA().setContentText(String.format("Uw score was: %s", model.currentPlayer().score.getPoints()));
            view.getGameView().getA().show();
        }
    }

    private void resetField() {
        //haal scorekaart op
        Map<String, Integer> scoreCard = model.currentPlayer().score.scores;

        //update de score naar die van de huidige player
        view.getGameView().getScore().setText("score: " + model.currentPlayer().score.getPoints());

        //stenen worden nog is gerest voor als speler dobbelsteen aanklinkt voor volgende beurt
        int i = 0;
        for (Dice d : model.getDice()) {
            d.setHeld(false);
            resetScale(view.getGameView().getDice()[i]);
            i++;
        }
        //textfield wordt geupdate naar de scorekaart van de current player
        for (TextField field : view.getScoreView().getEyeTextFields()) {
            String id = String.valueOf(Integer.parseInt(field.getId()) + 1);
            if (scoreCard.containsKey(id)) {
                System.out.println(scoreCard);
                if (scoreCard.containsKey(id)) {
                    field.setText(String.valueOf(scoreCard.get(id)));
                }
            } else {
                field.setText("0");
            }
        }

        //buttons worden geupdate naar scorekaart
        for (Button button : view.getScoreView().getButtons()) {
            if (scoreCard.containsKey(String.valueOf(Integer.parseInt(button.getId()) + 1))) {
                button.setStyle("-fx-background-color: red;");
                button.setDisable(true);
            } else {
                button.setStyle("-fx-background-color: grey;");
                button.setDisable(false);
            }
        }
    }

    //reset scale van stenen
    private void resetScale(ImageView view) {
        view.setScaleY(1);
        view.setScaleX(1);
    }

    public void addWindowEventHandlers() {


    }
}
