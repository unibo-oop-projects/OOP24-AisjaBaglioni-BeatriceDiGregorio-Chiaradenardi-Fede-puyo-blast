//chiara
package it.unibo.view;

import it.unibo.model.Puyo;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PuyoRenderer {
    private static final int CELL_SIZE = 40;  //dimensione della cella della griglia
    private final Map<String, Color> colorMap;  //mappa per associare i colori

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
        //ottieni il colore del Puyo dalla mappa
        Color puyoColor = colorMap.getOrDefault(puyo.getColor().toLowerCase(), Color.GRAY);
        int x = puyo.getX() * CELL_SIZE;  
        int y = puyo.getY() * CELL_SIZE;  

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  //anticrossing per bordi più morbidi

        GradientPaint gradient = new GradientPaint(
            x, y, puyoColor.brighter(), 
            x + CELL_SIZE, y + CELL_SIZE, puyoColor.darker() 
        );

        g2d.setPaint(gradient);
        g2d.fillOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10); 

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));  
        g2d.drawOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);  

        g2d.setColor(new Color(255, 255, 255, 80));  
        g2d.fillOval(x + 10, y + 10, CELL_SIZE - 20, CELL_SIZE - 20);  
    }
}
