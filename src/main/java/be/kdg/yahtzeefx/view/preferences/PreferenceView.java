package be.kdg.yahtzeefx.view.preferences;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class PreferenceView extends GridPane {
    private Button music;
    private Button sfx;

    public PreferenceView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        music = new Button("Music");
        sfx = new Button("Sound");

    }

    private void layoutNodes() {
        this.add(music, 0, 0);
        this.add(sfx, 1, 0);
    }

    Button getMusic() {
        return music;
    }

    Button getSfx() {
        return sfx;
    }
}


