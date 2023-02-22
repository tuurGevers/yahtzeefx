package be.kdg.yahtzeefx;

import be.kdg.yahtzeefx.model.YahtzeeModel;
import be.kdg.yahtzeefx.view.YahtzeePresenter;
import be.kdg.yahtzeefx.view.YahtzeeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        YahtzeeModel model =
                new YahtzeeModel();
        YahtzeeView view =
                new YahtzeeView();
        YahtzeePresenter presenter =
                new YahtzeePresenter(model, view);
        primaryStage.setScene(new Scene(view));
        primaryStage.setTitle("yahtzee");
        presenter.addWindowEventHandlers();
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.centerOnScreen();

        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}

