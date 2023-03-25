package be.kdg.yahtzeefx.view.highscores;

import be.kdg.yahtzeefx.view.start.StartView;

import static be.kdg.yahtzeefx.Main.getView;

public class HighScorePresenter {
    private final HighScoreView view;
    private final StartView startView;

    public HighScorePresenter(HighScoreView view, StartView startView){
        this.view = view;
        this.startView = startView;
        addEventHandlers();
    }
    private void addEventHandlers() {
            view.getGoBack().setOnAction(ActionEvent ->{
                view.getScene().setRoot(startView);
                startView.getScene().getWindow().setWidth(600);
                startView.getScene().getWindow().setHeight(600);

            });
    }

}
