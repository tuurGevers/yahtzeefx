package be.kdg.yahtzeefx;

import be.kdg.yahtzeefx.model.Player;
import be.kdg.yahtzeefx.model.Score;
import be.kdg.yahtzeefx.model.YahtzeeModel;
import be.kdg.yahtzeefx.view.game.YahtzeePresenter;
import be.kdg.yahtzeefx.view.game.YahtzeeView;
import be.kdg.yahtzeefx.view.highscores.HighScorePresenter;
import be.kdg.yahtzeefx.view.highscores.HighScoreView;
import be.kdg.yahtzeefx.view.start.StartPresenter;
import be.kdg.yahtzeefx.view.start.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    static StartView view;
    @Override
    public void start(Stage primaryStage) {
        //players aanmaken
        Player player1 = new Player(0, "player 1", new Score());


        //lijst van players maken
        ArrayList<Player> players = new ArrayList();
        players.add(player1);

        //mvp
        YahtzeeModel model =
                new YahtzeeModel(players);
        view = new StartView();
        YahtzeeView gameView = new YahtzeeView();
        YahtzeePresenter gamePresenter =
                new YahtzeePresenter(model, gameView);
        HighScoreView highScoreView = new HighScoreView(model);
        HighScorePresenter highScorePresenter = new HighScorePresenter(highScoreView);
        StartPresenter presenter =
                new StartPresenter(view, gameView, model, gamePresenter, highScoreView, highScorePresenter);


        primaryStage.setScene(new Scene(view));

        //titel
        primaryStage.setTitle("yahtzee");

        //presenter.addWindowEventHandlers();

        //window grote
        primaryStage.setWidth(750);
        primaryStage.setHeight(600);
        primaryStage.centerOnScreen();

        primaryStage.show();
    }

    public static StartView getView(){
        return view;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

