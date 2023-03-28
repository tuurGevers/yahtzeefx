package be.kdg.yahtzeefx.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.Objects;

/**
 * The type Music player.
 */
public class MusicPlayer {
    private final MediaPlayer mediaPlayer;
    private boolean playing;
    private boolean sfx;

    /**
     * Instantiates a new Music player.
     */
    public MusicPlayer() {
        Media music = new Media(Objects.requireNonNull(getClass().getResource("/sound/music.mp3")).toExternalForm());
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        this.playing = true;
        this.sfx = true;
    }

    /**
     * Play click.
     */
    public void playClick() {
        if (sfx) {
            Media buttonSound = new Media(Objects.requireNonNull(getClass().getResource("/sound/ui-click.mp3")).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
            mediaPlayer.play();
        }

    }

    /**
     * Play dice.
     */
    public void playDice() {
        if (sfx) {
            Media buttonSound = new Media(Objects.requireNonNull(getClass().getResource("/sound/dice.mp3")).toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(buttonSound);
            mediaPlayer.play();
        }

    }

    /**
     * Play music.
     */
    public void playMusic() {
        mediaPlayer.play();
        playing = true;
    }

    /**
     * Stop music.
     */
    public void stopMusic() {
        mediaPlayer.stop();
        playing = false;
    }

    /**
     * Is playing boolean.
     *
     * @return the boolean
     */
    public boolean isPlaying() {
        return this.playing;
    }

    /**
     * Toggle sfx.
     */
    public void toggleSfx() {
        this.sfx = !this.sfx;
    }
}
