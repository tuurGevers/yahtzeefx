package be.kdg.yahtzeefx.view.preferences;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Objects;

public class PreferenceView extends GridPane {
    private Button music;
    private Button sfx;

    public PreferenceView() {
        initializeNodes();
        layoutNodes();
    }

    private void initializeNodes() {
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());
        music = new Button("Music");
        sfx = new Button("Sound");

    }

    private void layoutNodes() {
        this.add(music, 0, 0);
        this.add(sfx, 1, 0);

        for (Node node : this.getChildren()) {
            node.getStyleClass().add("startButtons");
        }
    }

    Button getMusic() {
        return music;
    }

    Button getSfx() {
        return sfx;
    }
}


