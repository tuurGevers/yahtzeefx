package be.kdg.yahtzeefx.view.game;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.YahtzeeModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class SelectedPresenter {
    private YahtzeeModel model;
    private YahtzeeView view;

    public SelectedPresenter(YahtzeeModel model, YahtzeeView view) {
        this.model = model;
        this.view = view;
        addEventHandlers();
    }

    private void addEventHandlers(){
        //select handler voor alle dobbelstenen
        for (ImageView die : view.getSelectedView().getDice()) {
            die.setOnMouseClicked(
                    MouseEvent -> {
                        if (model.trows != 0) {
                            model.getDice()[Integer.parseInt(die.getId())].select();
                            updateView();

                        }
                    }
            );
        }
    }

    public void updateView() {
        //maak een array met de waardes van de dobbelstenen
        int[] aantallen = new int[5];
        Dice[] dice = model.getDice();

        for (int i = 0; i < 5; i++) {
            aantallen[i] = dice[i].getValue();
            //update imageview
            view.getGameView().getDice()[i].setImage(new Image(getClass().getResource("/images/die" + aantallen[i] + ".png").toExternalForm()));
            if (!dice[i].isHeld()) {
                view.getGameView().getDice()[i].setVisible(true);

                ImageView selectedDice = view.getSelectedView().getDice()[i];
                selectedDice.setVisible(false);
            }
        }
    }
}
