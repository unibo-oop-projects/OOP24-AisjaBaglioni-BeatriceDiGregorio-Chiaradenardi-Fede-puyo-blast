package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Optional;

import javax.swing.ImageIcon;

import it.unibo.model.Scale;
import it.unibo.model.ScoreModel;
import it.unibo.model.StatusModel;

import java.awt.Font;
import java.awt.Color;

public class EndView {
    private final StatusModel statusModel;
    private final Scale scale;
    private final ScoreModel score;
    private final Image stars;

    public EndView(StatusModel statusModel, Scale scale, ScoreModel score) {
        this.statusModel = statusModel;
        this.scale = scale;
        this.score = score;
        URL image_path = getClass().getClassLoader().getResource("images/stars.png");
        this.stars = new ImageIcon(image_path).getImage();
    }

    public final void draw(final Graphics g) {
        if (this.statusModel.isGameEnded()) {
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, this.scale.getScale(), this.scale.getScale());
            int xstring = this.scale.getScale() / 9;
            int ystring = this.scale.getScale() / 2;
            Font result = new Font("Arial", Font.BOLD, this.scale.getScale() / 12);
            Font points = new Font("Arial", Font.BOLD, this.scale.getScale() / 20);
            String[] string = {"LEVEL FAILED...", "LEVEL COMPLETE!", "NOT BAD."};
            if (this.statusModel.getEndStars().isEmpty()) {
                g.setColor(Color.RED);
                g.setFont(result);
                g.drawString(string[0], xstring, ystring);
            } else if (this.statusModel.getEndStars().equals(Optional.of(3))) {
                g.setColor(Color.GREEN);
                g.setFont(result);
                g.drawString(string[1], xstring, ystring);
            } else {
                g.setColor(Color.YELLOW);
                g.setFont(result);
                g.drawString(string[2], xstring, ystring);
            }
            g.setColor(Color.WHITE);
            g.setFont(points);
            g.drawString("Final score: \n" + String.valueOf(this.score.getScore()), xstring,
                    ystring + this.scale.getScale() / 16);
        }
    }
}