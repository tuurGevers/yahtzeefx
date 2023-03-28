package be.kdg.yahtzeefx.view.highscores;

import be.kdg.yahtzeefx.model.Log;
import be.kdg.yahtzeefx.model.YahtzeeModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Map;

public class HighScoreView extends VBox {

    private final Label[] scores;
    private Map<String, Integer> highScores;
    private Button goBack;
    public HighScoreView(YahtzeeModel model)  {
        Log logger = new Log(model);
        try{
            this.highScores = logger.getHighScore();
        }catch(Exception e){
            e.printStackTrace();
        }
        this.scores = new Label[highScores.size()];
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        int index = 0;
        for (String score : highScores.keySet()) {
            scores[index] = new Label(score + ": " + highScores.get(score));
            index++;
        }
        goBack = new Button("go back");
    }

    private void layoutNodes() {
        getChildren().addAll(scores);
        getChildren().add(goBack);
    }

    Button getGoBack() {
        return goBack;
    }
}
