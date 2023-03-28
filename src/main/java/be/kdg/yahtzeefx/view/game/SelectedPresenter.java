package be.kdg.yahtzeefx.view.game;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.MusicPlayer;
import be.kdg.yahtzeefx.model.YahtzeeModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * The type Selected presenter.
 */
public class SelectedPresenter {
    private final YahtzeeModel model;
    private final YahtzeeView view;
    private final MusicPlayer musicPlayer;

    /**
     * Instantiates a new Selected presenter.
     *
     * @param model       the model
     * @param view        the view
     * @param musicPlayer the music player
     */
    public SelectedPresenter(YahtzeeModel model, YahtzeeView view, MusicPlayer musicPlayer) {
        this.model = model;
        this.view = view;
        this.musicPlayer = musicPlayer;
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
                            musicPlayer.playClick();
                        }
                    }
            );
        }
    }

    /**
     * Update view.
     */
    public void updateView() {
        //maak een array met de waardes van de dobbelstenen
        int[] aantallen = new int[5];
        Dice[] dice = model.getDice();

        for (int i = 0; i < 5; i++) {
            aantallen[i] = dice[i].getValue();
            //update imageview
            view.getGameView().getDice()[i].setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/die" + aantallen[i] + ".png")).toExternalForm()));
            if (!dice[i].isHeld()) {
                view.getGameView().getDice()[i].setVisible(true);

                ImageView selectedDice = view.getSelectedView().getDice()[i];
                view.getSelectedView().getChildren().remove(selectedDice);
            }
        }
    }
}
