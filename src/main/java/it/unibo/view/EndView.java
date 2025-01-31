package it.unibo.view;

import java.awt.Graphics;

import it.unibo.model.Scale;
import it.unibo.model.StatusModel;
import java.awt.Font;
import java.awt.Color;

public class EndView {
    private final StatusModel statusModel;
    private final Scale scale;

    public EndView(StatusModel statusModel, Scale scale) {
        this.statusModel = statusModel;
        this.scale = scale;
    }

    public final void draw(final Graphics g) {
        if (this.statusModel.isGameEnded()) {
            int xstring = this.scale.getScale()/2;
            int ystring = this.scale.getScale()/2;
            Font myFont = new Font("Arial", Font.BOLD, this.scale.getScale() / 4);
            if (this.statusModel.getEndStars().isEmpty()) {
                g.setColor(Color.RED);
                g.setFont(myFont);
                g.drawString("LEVEL FAILED...", xstring, ystring);
            } else {
                g.setColor(Color.GREEN);
                g.setFont(myFont);
                g.drawString("LEVEL COMPLETE!", xstring, ystring);
            }
        }
    }
}