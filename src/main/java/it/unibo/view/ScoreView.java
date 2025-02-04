package it.unibo.view;

import it.unibo.model.ScoreModel;
import it.unibo.view.interfaces.ViewInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import it.unibo.model.Scale;

public class ScoreView implements ViewInterface {
    private final ScoreModel score;
    private final Scale scale;

    public ScoreView(ScoreModel score, Scale scale) {
        this.score = score;
        this.scale = scale;
    }

    @Override
    final public void draw(Graphics g) {
        int x = this.scale.getScale() - this.scale.getScale() / 5;
        int y = this.scale.getScale() - this.scale.getScale() / 7;
        Font myFont = new Font("Arial", Font.BOLD, this.scale.getScale() / 35);
        g.setColor(Color.black);
        g.setFont(myFont);
        g.drawString("Score: " + this.score.getScore(), x, y);
    }
}