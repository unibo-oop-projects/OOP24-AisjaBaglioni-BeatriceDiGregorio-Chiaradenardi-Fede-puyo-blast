package it.unibo.view;

import it.unibo.model.Puyo;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PuyoRenderer {
    private static final int CELL_SIZE = 40;  // Dimensione della cella della griglia
    private final Map<String, Color> colorMap;  // Mappa per associare i colori

    public PuyoRenderer() {
        colorMap = new HashMap<>();
        colorMap.put("red", Color.RED);
        colorMap.put("blue", Color.BLUE);
        colorMap.put("green", Color.GREEN);
        colorMap.put("yellow", Color.YELLOW);
        colorMap.put("purple", new Color(128, 0, 128));
        colorMap.put("cyan", Color.CYAN);
    }

    public void render(Graphics g, Puyo puyo) {
        // Ottieni il colore del Puyo dalla mappa
        Color puyoColor = colorMap.getOrDefault(puyo.getColor().toLowerCase(), Color.GRAY);
        int x = puyo.getX() * CELL_SIZE;  // Calcola la posizione x in base alla griglia
        int y = puyo.getY() * CELL_SIZE;  // Calcola la posizione y in base alla griglia

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  // Attiva l'anticrossing per bordi più morbidi

        // Crea un gradiente per il Puyo, dando un effetto di luce
        GradientPaint gradient = new GradientPaint(
            x, y, puyoColor.brighter(), // Partenza del gradiente (più chiaro)
            x + CELL_SIZE, y + CELL_SIZE, puyoColor.darker() // Fine del gradiente (più scuro)
        );

        g2d.setPaint(gradient);
        g2d.fillOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);  // Disegna il cerchio con il gradiente

        // Disegna un bordo scuro per il Puyo
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));  // Imposta uno spessore del bordo
        g2d.drawOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);  // Disegna il bordo

        // Aggiungi un effetto di luce sulla parte superiore per rendere il Puyo più realistico
        g2d.setColor(new Color(255, 255, 255, 80));  // Colore bianco semi-trasparente
        g2d.fillOval(x + 10, y + 10, CELL_SIZE - 20, CELL_SIZE - 20);  // Disegna il riflesso della luce
    }
}
