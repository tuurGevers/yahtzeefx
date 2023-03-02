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
        //players aanmaken
        Player player1 = new Player(0, "tuur", new Score());
        Player player2 = new Player(0, "john doe", new Score());
        Player player3 = new Player(0, "plater 3", new Score());
        Player player4 = new Player(0, "plater 4", new Score());

        //lijst van players maken
        ArrayList<Player> players = new ArrayList();
        players.add(player1);
        players.add(player2);
        //players.add(player3);
        //players.add(player4);

        //mvp
        YahtzeeModel model =
                new YahtzeeModel(players);
        YahtzeeView view =
                new YahtzeeView();
        YahtzeePresenter presenter =
                new YahtzeePresenter(model, view);


        primaryStage.setScene(new Scene(view));

        //titel
        primaryStage.setTitle("yahtzee");

        presenter.addWindowEventHandlers();

        //window grote
        primaryStage.setWidth(750);
        primaryStage.setHeight(600);
        primaryStage.centerOnScreen();

        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}

