package be.kdg.yahtzeefx;

import be.kdg.yahtzeefx.model.Player;
import be.kdg.yahtzeefx.model.Score;
import be.kdg.yahtzeefx.model.YahtzeeModel;
import be.kdg.yahtzeefx.view.YahtzeePresenter;
import be.kdg.yahtzeefx.view.YahtzeeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Player player1 = new Player(0, "tuur", new Score());
        ArrayList<Player> players = new ArrayList();
        players.add(player1);
        YahtzeeModel model =
                new YahtzeeModel(players);
        YahtzeeView view =
                new YahtzeeView();
        YahtzeePresenter presenter =
                new YahtzeePresenter(model, view);
        primaryStage.setScene(new Scene(view));
        primaryStage.setTitle("yahtzee");
        presenter.addWindowEventHandlers();
        primaryStage.setWidth(650);
        primaryStage.setHeight(550);
        primaryStage.centerOnScreen();

        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}

