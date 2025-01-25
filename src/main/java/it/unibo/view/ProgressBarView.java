package it.unibo.view;

import javax.swing.*;

import java.awt.*;

import it.unibo.model.ProgressBarModel;

import it.unibo.model.Scale;

public class ProgressBarView extends JPanel{

    private final Image progressBarImage;
    private final Image progressBarFullImage;
    private ProgressBarModel progressModel;
    private int imageWidth;
    private int imageHeight;
    private Scale scale;  

    public ProgressBarView(ProgressBarModel progressModel, Scale scale) {
        this.progressBarImage = new ImageIcon(getClass().getClassLoader().getResource("images/" + "ProgressBarEmpty.png"))
                .getImage();
        this.progressBarFullImage = new ImageIcon(getClass().getClassLoader().getResource("images/" + "ProgressBarFull.png"))
                .getImage();
        this.imageWidth = progressBarImage.getHeight(null);
        this.imageHeight = progressBarImage.getWidth(null);
        //this.setPreferredSize(new Dimension(progressBarImage.getWidth(null), progressBarImage.getHeight(null)));

    }

    @Override
    protected void paintComponent(Graphics g) {
        int newWidth = this.scale.getScale() / 7;
        //L'altezza è calcolata in proporzione
        // newWidth : newHeight = imageWidth : imageHeight
        int newHeight = (newWidth * this.imageHeight)/this.imageWidth;
        int y = (this.scale.getScale() * 95)/100;
        int x = (this.scale.getScale() - newWidth) / 2;
        super.paintComponent(g);

        // Disegna l'immagine dello sfondo
        g.drawImage(progressBarImage, x, y, x + newWidth, y + newHeight, 0, imageWidth, 0, imageHeight, null);

        // Calcola la larghezza del riempimento
        // chargeLevel : 1 = dx : newWidth
        // dx = chargeLevel * newWidth
        double dxdouble = this.progressModel.getChargeLevel() * newWidth;
        int dx = (int)dxdouble;
        // Disegna la parte piena
        g.drawImage(progressBarFullImage, x, y, x + dx, y + newHeight, 0, imageWidth, 0, imageHeight, null);
    }
}
