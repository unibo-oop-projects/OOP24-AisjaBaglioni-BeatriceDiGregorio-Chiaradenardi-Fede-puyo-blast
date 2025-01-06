package it.unibo.view;

import it.unibo.model.CannonModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CannonView extends JPanel {
    private final CannonModel model; 
    private int cannonX = 350; // Posizione iniziale orizzontale del cannone
    private int cannonY = 500; // Posizione iniziale verticale del cannone

    public CannonView(final CannonModel model) {
        this.model = model;
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true); // Rendi il pannello focalizzabile
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
    }
    //final nelle funzioni

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCannon(g);
        drawChargeBar(g);
    }

    final public void drawCannon(Graphics g) {
        // Disegna il cannone (puoi sostituire con un'immagine)
        g.setColor(Color.BLACK);
        g.fillRect(cannonX, cannonY, 100, 20); // Corpo del cannone
        g.setColor(Color.GRAY);
        g.fillRect(cannonX + 50, cannonY - 20, 20, 20); // Bocca del cannone
    }

    final public void drawChargeBar(Graphics g) {
        // Disegna la barra di caricamento
        g.setColor(Color.RED);
        g.fillRect(cannonX, cannonY - 50, 100, 10); // Barra di carica piena
        g.setColor(Color.GREEN);
        g.fillRect(cannonX, cannonY - 50, model.getChargeLevel(), 10); // Barra di carica attuale
    }

    final public void update() {
        repaint(); // Rende la vista aggiornata
    }

    final public void handleKeyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE -> {
                System.out.println("Sparato con forza: " + model.getChargeLevel());
                model.resetCharge();
            }
            case KeyEvent.VK_UP -> model.increaseCharge(); // Aumenta la carica
            case KeyEvent.VK_DOWN -> model.decreaseCharge(); // Diminuisci la carica
            case KeyEvent.VK_W -> moveCannonUp(); // Muovi il cannone verso l'alto
            case KeyEvent.VK_S -> moveCannonDown(); // Muovi il cannone verso il basso
            case KeyEvent.VK_A -> moveCannonLeft(); // Muovi il cannone verso sinistra
            case KeyEvent.VK_D -> moveCannonRight(); // Muovi il cannone verso destra
        }
        update(); // Aggiorna la vista
    }

    final public void moveCannonUp() {
        if (cannonY > 0) { // Limite superiore
            cannonY -= 10; // Sposta verso l'alto di 10 pixel
        }
    }

    final public void moveCannonDown() {
        if (cannonY < getHeight() - 20) { // Limite inferiore
            cannonY += 10; // Sposta verso il basso di 10 pixel
        }
    }

    final public void moveCannonLeft() {
        if (cannonX > 0) { // Limite sinistro
            cannonX -= 10; // Sposta a sinistra di 10 pixel
        }
    }

    final public void moveCannonRight() {
        if (cannonX < getWidth() - 100) { // Limite destro
            cannonX += 10; // Sposta a destra di 10 pixel
        }
    }
}