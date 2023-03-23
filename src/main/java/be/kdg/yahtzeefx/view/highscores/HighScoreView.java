package be.kdg.yahtzeefx.view.highscores;

import be.kdg.yahtzeefx.model.Log;
import be.kdg.yahtzeefx.model.YahtzeeModel;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Map;

public class HighScoreView extends VBox {

    private Label[] scores;
    private Log logger;
    private Map<String, Integer> highScores;
    private Button goBack;
    public HighScoreView(YahtzeeModel model)  {
        this.logger = new Log(model);
        try{
            this.highScores = this.logger.getHighScore();
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
