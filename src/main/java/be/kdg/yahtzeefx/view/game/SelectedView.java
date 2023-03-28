package be.kdg.yahtzeefx.view.game;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Objects;

/**
 * The type Selected view.
 */
public class SelectedView extends GridPane {
    private ImageView[] dice;

    /**
     * Instantiates a new Selected view.
     */
    public SelectedView(){
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        //voor elke dice wordt er een imageview gemaakt en een id geset
        dice = new ImageView[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/die1.png")).toExternalForm()));
            dice[i].setId(String.valueOf(i));
        }

    }

    private void layoutNodes() {
        //basis styling
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10));
    }

    /**
     * Get dice image view [ ].
     *
     * @return the image view [ ]
     */
    ImageView[] getDice() {
        return dice;
    }
}
