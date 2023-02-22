package be.kdg.yahtzeefx.view;

import javafx.scene.layout.*;

public class YahtzeeView extends GridPane {
    private GameView gameView;
    private ScoreView scoreView;

    public YahtzeeView(){
        initialiseNodes();
        layoutNodes();
    }
    private void initialiseNodes() {
        gameView = new GameView();
        scoreView = new ScoreView();

    }

    private void layoutNodes() {
        this.add(gameView,0,0);
        this.add(scoreView,0,1);

    }

    public GameView getGameView() {
        return gameView;
    }

    public ScoreView getScoreView() {
        return scoreView;
    }
}
