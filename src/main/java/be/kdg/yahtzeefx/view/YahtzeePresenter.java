package be.kdg.yahtzeefx.view;

import be.kdg.yahtzeefx.model.YahtzeeModel;

public class YahtzeePresenter {
    private YahtzeeModel model;
    private YahtzeeView view;
    public YahtzeePresenter(
            YahtzeeModel model,
            YahtzeeView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }
    private void addEventHandlers() {

    }
    private void updateView() {

    }
    public void addWindowEventHandlers () {


    }
}
