package be.kdg.yahtzeefx.view.game;

import be.kdg.yahtzeefx.view.preferences.PreferenceView;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;

/**
 * The type Yahtzee view.
 */
public class YahtzeeView extends GridPane {
    private GameView gameView;
    private ScoreView scoreView;
    private SelectedView selectedView;
    private PreferenceView pView;

    /**
     * Instantiates a new Yahtzee view.
     *
     * @param preferenceView the preference view
     */
    public YahtzeeView(PreferenceView preferenceView){
        initialiseNodes(preferenceView);
        layoutNodes();
    }
    private void initialiseNodes(PreferenceView preferenceView) {
        this.pView = preferenceView;
        gameView = new GameView();
        scoreView = new ScoreView();
        selectedView = new SelectedView();
    }

    private void layoutNodes() {

        this.setBackground(new Background(
                new BackgroundImage(
                        new Image(Objects.requireNonNull(getClass().getResource("/images/playing.png")).toExternalForm()),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        new BackgroundPosition(Side.LEFT, 0.0, false, Side.BOTTOM, 0.0, false),
                        new BackgroundSize(0, 0, false, false, true, true)
                )));

        this.add(pView, 0,0);
        this.add(gameView,0,1);
        this.add(scoreView,1,1);
        this.add(selectedView,0,2);
        this.setPadding(new Insets(10));
        this.minWidth(1000);
        this.minHeight(1200);

    }

    /**
     * Gets game view.
     *
     * @return the game view
     */
    public GameView getGameView() {
        return gameView;
    }

    /**
     * Gets score view.
     *
     * @return the score view
     */
    public ScoreView getScoreView() {
        return scoreView;
    }

    /**
     * Gets selected view.
     *
     * @return the selected view
     */
    public SelectedView getSelectedView() {
        return selectedView;
    }
}
