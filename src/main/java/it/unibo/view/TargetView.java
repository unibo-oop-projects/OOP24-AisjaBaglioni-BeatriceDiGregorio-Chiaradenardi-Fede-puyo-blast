package it.unibo.view;

import javax.swing.*;

import it.unibo.model.CannonModel;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.TargetViewInterface;

import java.awt.*;
import java.net.URL;

public class TargetView extends JPanel implements TargetViewInterface {

    private final Image cannonTargetImage;
    private int imageWidth;
    private int imageHeight;
    private final Scale scale;
    private final CannonModel cannonModel;

    public TargetView(Scale scale, CannonModel cannonModel) {
        this.scale = scale;
        this.cannonModel = cannonModel;
        final URL imageUrl = getClass().getClassLoader().getResource("images/CannonSightView.png");
        this.cannonTargetImage = new ImageIcon(imageUrl).getImage();
        this.imageWidth = cannonTargetImage.getWidth(null);
        this.imageHeight = cannonTargetImage.getHeight(null);
    }

    @Override
    final public void draw(final Graphics g) {
        int puyoCellSize = this.scale.getScale() / 16;
        int width = this.scale.getScale() / 10;
        int height = (width * this.imageHeight) / this.imageWidth;
        int offset_gridx = puyoCellSize * 4;
        int offset_gridy = puyoCellSize;
        int grid_width = puyoCellSize * 8;
        int grid_height = puyoCellSize * 8;
        int cannonPosX = offset_gridx + ((int) (cannonModel.getX() * grid_width));
        int cannonPosY = offset_gridy + ((int) ((1.0 - cannonModel.getAngle()) * grid_height));
        g.drawImage(
                cannonTargetImage,
                cannonPosX - width / 2,
                cannonPosY - height / 2,
                cannonPosX + width / 2,
                cannonPosY + height / 2,
                0,
                0,
                imageWidth,
                imageHeight,
                null);
    }
}