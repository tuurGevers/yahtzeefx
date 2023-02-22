package be.kdg.yahtzeefx.view;

import be.kdg.yahtzeefx.model.Dice;
import be.kdg.yahtzeefx.model.Game;
import be.kdg.yahtzeefx.model.YahtzeeModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        view.getTrow().setOnAction(
                event->{
                    updateView();
                }
        );
        view.getDice1().setOnMouseClicked(
                MouseEvent ->{
                    model.getDice()[0].select();
                    if( model.getDice()[0].isHeld()){
                        view.getDice1().setScaleY(1.5);
                    }else{
                        view.getDice1().setScaleY(1);
                    }
                }
        );

        view.getDice2().setOnMouseClicked(
                MouseEvent ->{
                    model.getDice()[1].select();
                    if( model.getDice()[1].isHeld()){
                        view.getDice2().setScaleY(1.5);
                    }else{
                        view.getDice2().setScaleY(1);
                    }
                }
        );

        view.getDice3().setOnMouseClicked(
                MouseEvent ->{
                    model.getDice()[2].select();
                    if( model.getDice()[2].isHeld()){
                        view.getDice3().setScaleY(1.5);
                    }else{
                        view.getDice3().setScaleY(1);
                    }
                }
        );
        view.getDice4().setOnMouseClicked(
                MouseEvent ->{
                    model.getDice()[3].select();
                    if( model.getDice()[3].isHeld()){
                        view.getDice4().setScaleY(1.5);
                    }else{
                        view.getDice4().setScaleY(1);
                    }
                }
        );
        view.getDice5().setOnMouseClicked(
                MouseEvent ->{
                    model.getDice()[4].select();
                    if( model.getDice()[4].isHeld()){
                        view.getDice5().setScaleY(1.5);
                    }else{
                        view.getDice5().setScaleY(1);
                    }
                }
        );

    }
    private void updateView() {
        model.rollDice();
        int[] aantallen = new int[5];
        Dice[] dice = model.getDice();
        for (int i =0; i<5; i++ ){
            aantallen[i] = dice[i].getValue();
        }
        view.getDice1().setImage(new Image(getClass().getResource("/images/die" +aantallen[0]+".png").toExternalForm()));
        view.getDice2().setImage(new Image(getClass().getResource("/images/die" +aantallen[1]+".png").toExternalForm()));
        view.getDice3().setImage(new Image(getClass().getResource("/images/die" +aantallen[2]+".png").toExternalForm()));
        view.getDice4().setImage(new Image(getClass().getResource("/images/die" +aantallen[3]+".png").toExternalForm()));
        view.getDice5().setImage(new Image(getClass().getResource("/images/die" +aantallen[4]+".png").toExternalForm()));
    }
    public void addWindowEventHandlers () {


    }
}
