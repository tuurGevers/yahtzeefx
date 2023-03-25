package be.kdg.yahtzeefx.view.preferences;

import be.kdg.yahtzeefx.model.MusicPlayer;
import be.kdg.yahtzeefx.model.YahtzeeModel;

public class PreferencePresenter {
    private YahtzeeModel model;
    private PreferenceView view;
    private MusicPlayer musicPlayer;

    public PreferencePresenter(YahtzeeModel model, PreferenceView view, MusicPlayer musicPlayer) {
        this.model = model;
        this.view = view;
        this.musicPlayer = musicPlayer;
        addEventListeners();
    }

    private void addEventListeners() {
        view.getMusic().setOnAction(ActionEvent ->{
            if(musicPlayer.isPlaying()){
                musicPlayer.stopMusic();
            }else{
                musicPlayer.playMusic();
            }
        });

        view.getSfx().setOnAction(ActionEvent ->{
            musicPlayer.toggleSfx();
        });
    }
}
