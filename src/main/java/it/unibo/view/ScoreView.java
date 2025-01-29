package it.unibo.view;

import it.unibo.model.ScoreModel;
import it.unibo.view.interfaces.ScoreViewInterface;
import javafx.scene.text.Font;

import java.awt.Color;
import java.awt.Graphics;

import it.unibo.model.Scale;

public class ScoreView{
    private final ScoreModel score;
    private final Scale scale;

    public ScoreView(ScoreModel score, Scale scale) {
        this.score = score;
        this.scale = scale;
    }

    final public void draw(Graphics g) {
        int cellsize = this.scale.getScale()/16;
        int x = this.scale.getScale() - 2*cellsize;
        int y = this.scale.getScale() - cellsize;
        g.setColor(Color.BLACK);
        g.drawString("Score: " + this.score.getScore(), x, y);
    }

}