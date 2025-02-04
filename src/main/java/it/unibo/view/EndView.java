package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Optional;

import javax.swing.ImageIcon;

import it.unibo.model.Scale;
import it.unibo.model.ScoreModel;
import it.unibo.model.StatusModel;
import it.unibo.view.interfaces.ViewInterface;

import java.awt.Font;
import java.awt.Color;

public class EndView implements ViewInterface {
    private final StatusModel statusModel;
    private final Scale scale;
    private final ScoreModel score;
    private final Image[] stars;
    private int imageWidth;
    private int imageHeight;

    public EndView(StatusModel statusModel, Scale scale, ScoreModel score) {
        this.statusModel = statusModel;
        this.scale = scale;
        this.score = score;
        this.stars = new Image[4];
        for (int i = 0; i <= 3; i++) {
            URL image_path = getClass().getClassLoader().getResource("images/" + String.valueOf(i) + "stars.png");
            this.stars[i] = new ImageIcon(image_path).getImage();
        }
        this.imageWidth = this.stars[0].getWidth(null);
        this.imageHeight = this.stars[0].getHeight(null);
    }

    @Override
    public final void draw(final Graphics g) {
        int newWidth = this.scale.getScale() / 2;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        int cellsize = this.scale.getScale() / 16;

        if (this.statusModel.isGameEnded()) {
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, this.scale.getScale(), this.scale.getScale());
            int xstring = this.scale.getScale() / 9;
            int ystring = this.scale.getScale() / 2;
            Font result = new Font("Arial", Font.BOLD, this.scale.getScale() / 12);
            Font points = new Font("Arial", Font.BOLD, this.scale.getScale() / 20);
            String[] string = { "LEVEL FAILED...", "LEVEL COMPLETE!", "NOT BAD." };
            if (this.statusModel.getEndStars().isEmpty()) {
                g.drawImage(
                        this.stars[0],
                        xstring,
                        ystring - cellsize * 5,
                        xstring + newWidth,
                        ystring - cellsize * 5 + newHeight,
                        0, 0,
                        imageWidth, imageHeight, null);

                g.setColor(Color.RED);
                g.setFont(result);
                g.drawString(string[0], xstring, ystring);
            } else if (this.statusModel.getEndStars().equals(Optional.of(3))) {
                g.drawImage(
                        this.stars[3],
                        xstring,
                        ystring - cellsize * 5,
                        xstring + newWidth,
                        ystring - cellsize * 5 + newHeight,
                        0, 0,
                        imageWidth, imageHeight, null);
                g.setColor(Color.GREEN);
                g.setFont(result);
                g.drawString(string[1], xstring, ystring);
            } else {
                if (this.statusModel.getEndStars().equals(Optional.of(1))) {
                    g.drawImage(
                            this.stars[1],
                            xstring,
                            ystring - cellsize * 5,
                            xstring + newWidth,
                            ystring - cellsize * 5 + newHeight,
                            0, 0,
                            imageWidth, imageHeight, null);
                } else if (this.statusModel.getEndStars().equals(Optional.of(2))) {
                    g.drawImage(
                            this.stars[2],
                            xstring,
                            ystring - cellsize * 5,
                            xstring + newWidth,
                            ystring - cellsize * 5 + newHeight,
                            0, 0,
                            imageWidth, imageHeight, null);
                }
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