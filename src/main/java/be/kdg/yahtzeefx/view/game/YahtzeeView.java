package be.kdg.yahtzeefx.view.game;

import javafx.scene.layout.*;

public class YahtzeeView extends GridPane {
    private GameView gameView;
    private ScoreView scoreView;
    private SelectedView selectedView;
    public YahtzeeView(){
        initialiseNodes();
        layoutNodes();
    }
    private void initialiseNodes() {
        gameView = new GameView();
        scoreView = new ScoreView();
        selectedView = new SelectedView();
    }

    private void layoutNodes() {
        this.add(gameView,0,0);
        this.add(scoreView,1,0);
        this.add(selectedView,0,1);

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
