package be.kdg.yahtzeefx.view.preferences;

import be.kdg.yahtzeefx.model.MusicPlayer;

public class PreferencePresenter {
    private final PreferenceView view;
    private final MusicPlayer musicPlayer;

    public PreferencePresenter(PreferenceView view, MusicPlayer musicPlayer) {
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

        view.getSfx().setOnAction(ActionEvent -> musicPlayer.toggleSfx());
    }
}
