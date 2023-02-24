package be.kdg.yahtzeefx.view;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.YahtzeeModel;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

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
                    updateView();
                }
        );
        view.getGameView().getDice1().setOnMouseClicked(
                MouseEvent -> {
                    model.getDice()[0].select();
                    if (model.getDice()[0].isHeld()) {
                        view.getGameView().getDice1().setScaleY(1.5);
                    } else {
                        view.getGameView().getDice1().setScaleY(1);
                    }
                }
        );

        view.getGameView().getDice2().setOnMouseClicked(
                MouseEvent -> {
                    model.getDice()[1].select();
                    if (model.getDice()[1].isHeld()) {
                        view.getGameView().getDice2().setScaleY(1.5);
                    } else {
                        view.getGameView().getDice2().setScaleY(1);
                    }
                }
        );

        view.getGameView().getDice3().setOnMouseClicked(
                MouseEvent -> {
                    model.getDice()[2].select();
                    if (model.getDice()[2].isHeld()) {
                        view.getGameView().getDice3().setScaleY(1.5);
                    } else {
                        view.getGameView().getDice3().setScaleY(1);
                    }
                }
        );
        view.getGameView().getDice4().setOnMouseClicked(
                MouseEvent -> {
                    model.getDice()[3].select();
                    if (model.getDice()[3].isHeld()) {
                        view.getGameView().getDice4().setScaleY(1.5);
                    } else {
                        view.getGameView().getDice4().setScaleY(1);
                    }
                }
        );
        view.getGameView().getDice5().setOnMouseClicked(
                MouseEvent -> {
                    model.getDice()[4].select();
                    if (model.getDice()[4].isHeld()) {
                        view.getGameView().getDice5().setScaleY(1.3);
                        view.getGameView().getDice5().setScaleX(1.3);

                    } else {
                        view.getGameView().getDice5().setScaleY(1);
                        view.getGameView().getDice5().setScaleX(1);

                    }
                }
        );

        for (Button button : view.getScoreView().getButtons()) {
            button.setOnAction(
                    Event -> {
                        Map<String, Integer> scoreCard = model.getPlayers().get(0).score.scores;
                        Map<String, Integer> scores = model.scores();
                        System.out.println(scores);
                        System.out.println(scoreCard);
                        if (!scoreCard.containsKey(String.valueOf(Integer.parseInt(button.getId()) + 1))) {
                            scoreCard.put(String.valueOf(Integer.parseInt(button.getId()) + 1), scores.get(String.valueOf(Integer.parseInt(button.getId()) + 1)));
                                model.getPlayers().get(0).score.addScore(scores.getOrDefault(String.valueOf(Integer.parseInt(button.getId()) + 1),0));
                            view.getGameView().getScore().setText("score: " + model.getPlayers().get(0).score.getPoints());
                            button.setStyle("-fx-background-color: red;");
                            button.setDisable(true);

                        }
                        System.out.println(scoreCard);

                    }
            );
        }

    }

    private void updateView() {
        model.rollDice();
        int[] aantallen = new int[5];
        Dice[] dice = model.getDice();
        for (int i = 0; i < 5; i++) {
            aantallen[i] = dice[i].getValue();
        }
        view.getGameView().getDice1().setImage(new Image(getClass().getResource("/images/die" + aantallen[0] + ".png").toExternalForm()));
        view.getGameView().getDice2().setImage(new Image(getClass().getResource("/images/die" + aantallen[1] + ".png").toExternalForm()));
        view.getGameView().getDice3().setImage(new Image(getClass().getResource("/images/die" + aantallen[2] + ".png").toExternalForm()));
        view.getGameView().getDice4().setImage(new Image(getClass().getResource("/images/die" + aantallen[3] + ".png").toExternalForm()));
        view.getGameView().getDice5().setImage(new Image(getClass().getResource("/images/die" + aantallen[4] + ".png").toExternalForm()));
        view.getGameView().getTrowCount().setText(String.format("trows: %d", model.trows));

        if (model.trows == 3) {
            model.trows = 0;
            Map<String, Integer> scores = model.scores();
            int index =0;
            for (TextField field : view.getScoreView().getEyeTextFields()) {
                String key = String.valueOf(Integer.parseInt(field.getId()) + 1);
                if (scores.containsKey(key)) {
                    field.setText(Integer.toString(scores.get(key)));
                }else{
                    field.setText("0");

                }
                if(model.getPlayers().get(0).score.scores.get(key) != null){
                }
                index++;
            }
        }
    }

    public void addWindowEventHandlers() {


    }
}
