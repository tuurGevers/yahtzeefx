package be.kdg.yahtzeefx.view.game;

import be.kdg.yahtzeefx.view.preferences.PreferenceView;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

public class YahtzeeView extends GridPane {
    private GameView gameView;
    private ScoreView scoreView;
    private SelectedView selectedView;
    private PreferenceView pView;
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
        this.add(pView, 0,0);
        this.add(gameView,0,1);
        this.add(scoreView,1,1);
        this.add(selectedView,0,2);
        this.setPadding(new Insets(10));
        this.minWidth(800);
        this.minHeight(1200);

    }

    public GameView getGameView() {
        return gameView;
    }

    public ScoreView getScoreView() {
        return scoreView;
    }

    public SelectedView getSelectedView() {
        return selectedView;
    }
}
