package it.unibo.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import it.unibo.model.ScoreModel;
import it.unibo.view.interfaces.ScoreViewInterface;

public class ScoreView implements ScoreViewInterface {
    private final ScoreModel score;

    public ScoreView(ScoreModel score) {
        this.score = score;
    }

    @Override
    final public void draw(final GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillText("Score: " + score.getScore(), 10, 20);
    }

}
