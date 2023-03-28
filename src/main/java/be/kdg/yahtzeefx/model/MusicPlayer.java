package be.kdg.yahtzeefx.model;

import javafx.concurrent.Task;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MusicPlayer {
    private Media music;
    private MediaPlayer mediaPlayer;
    private boolean playing;
    private boolean sfx;

    public MusicPlayer() {
        music = new Media(getClass().getResource("/sound/music.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        this.playing = true;
        this.sfx = true;
    }

    public void playClick() {
        if (sfx) {
            Media buttonSound = new Media(getClass().getResource("/sound/ui-click.mp3").toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
            mediaPlayer.play();
        }

    }

    public void playDice() {
        if (sfx) {
            Media buttonSound = new Media(getClass().getResource("/sound/dice.mp3").toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
            mediaPlayer.play();
        }

    }

    public void playMusic() {
        mediaPlayer.play();
        playing = true;
    }

    public void stopMusic() {
        mediaPlayer.stop();
        playing = false;
    }

    public boolean isPlaying() {
        return this.playing;
    }

    public void toggleSfx() {
        this.sfx = !this.sfx;
    }
}
