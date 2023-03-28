package be.kdg.yahtzeefx;

import be.kdg.yahtzeefx.model.MusicPlayer;
import be.kdg.yahtzeefx.model.Player;
import be.kdg.yahtzeefx.model.Score;
import be.kdg.yahtzeefx.model.YahtzeeModel;
import be.kdg.yahtzeefx.view.game.SelectedPresenter;
import be.kdg.yahtzeefx.view.game.YahtzeePresenter;
import be.kdg.yahtzeefx.view.game.YahtzeeView;
import be.kdg.yahtzeefx.view.highscores.HighScorePresenter;
import be.kdg.yahtzeefx.view.highscores.HighScoreView;
import be.kdg.yahtzeefx.view.preferences.PreferencePresenter;
import be.kdg.yahtzeefx.view.preferences.PreferenceView;
import be.kdg.yahtzeefx.view.start.StartPresenter;
import be.kdg.yahtzeefx.view.start.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {
    static StartView view;

    @Override
    public void start(Stage primaryStage) {
        //players aanmaken
        Player player1 = new Player(0, "player 1", new Score());

        MusicPlayer musicPlayer = new MusicPlayer();

        //lijst van players maken
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);

        //mvp
        YahtzeeModel model =
                new YahtzeeModel(players);
        PreferenceView preferenceView = new PreferenceView();
        view = new StartView();

        YahtzeeView gameView = new YahtzeeView(preferenceView);
        YahtzeePresenter gamePresenter =
                new YahtzeePresenter(model, gameView, musicPlayer);
        HighScoreView highScoreView = new HighScoreView(model);
        HighScorePresenter highScorePresenter = new HighScorePresenter(highScoreView, view);
        StartPresenter presenter =
                new StartPresenter(view, gameView, model, gamePresenter, highScoreView, musicPlayer);
        SelectedPresenter selectedPresenter = new SelectedPresenter(model, gameView, musicPlayer);
        PreferencePresenter preferencePresenter = new PreferencePresenter(preferenceView, musicPlayer);
        primaryStage.setScene(new Scene(view));

        //titel
        primaryStage.setTitle("yahtzee");

        //presenter.addWindowEventHandlers();

        //window grote
        primaryStage.setWidth(750);
        primaryStage.setHeight(600);
        primaryStage.centerOnScreen();
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/images/icon.png")).toExternalForm()));


        primaryStage.show();
    }

    public static StartView getView() {
        return view;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

