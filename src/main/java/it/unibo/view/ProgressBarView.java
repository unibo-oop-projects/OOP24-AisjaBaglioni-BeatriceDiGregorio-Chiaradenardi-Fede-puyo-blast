package it.unibo.view;

import javax.swing.*;

import it.unibo.view.interfaces.ProgressBarViewInterface;

import java.awt.*;

public class ProgressBarView extends JPanel implements ProgressBarViewInterface{

    private final Image progressBarImage;
    private final Image progressBarFullImage;
    private int chargeLevel;


    public ProgressBarView(final String imagePathEmpty, final String imagePathFull) {
        //caricamento delle immagini
        progressBarImage = new ImageIcon(getClass().getClassLoader().getResource("images/" + imagePathEmpty)).getImage();
        progressBarFullImage = new ImageIcon(getClass().getClassLoader().getResource("images/" + imagePathFull)).getImage();

        this.chargeLevel = 0;
        this.setPreferredSize(new Dimension(progressBarImage.getWidth(null), progressBarImage.getHeight(null)));
        
    }

    @Override
    public void setProgress(int chargeLevel) {
        this.chargeLevel = chargeLevel;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Disegna l'immagine dello sfondo
        g.drawImage(progressBarImage, 300, 640, null);

        // Calcola la larghezza del riempimento
        int fillWidth = (int) (progressBarFullImage.getWidth(null) * (chargeLevel / 100.0));

        // Disegna la parte piena
        g.drawImage(progressBarFullImage, 300, 640, fillWidth, progressBarFullImage.getHeight(null), null);
    }
}
