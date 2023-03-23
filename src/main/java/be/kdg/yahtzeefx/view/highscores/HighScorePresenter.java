package be.kdg.yahtzeefx.view.highscores;

import static be.kdg.yahtzeefx.Main.getView;

public class HighScorePresenter {
    private final HighScoreView view;

    public HighScorePresenter(HighScoreView view){
        this.view = view;

        addEventHandlers();
    }
    private void addEventHandlers() {
            view.getGoBack().setOnAction(ActionEvent ->{
                view.getScene().setRoot(getView());
                getView().getScene().getWindow().sizeToScene();

            });
    }

}
