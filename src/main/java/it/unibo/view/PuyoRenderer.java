//chiara
package it.unibo.view;

import it.unibo.model.Puyo;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PuyoRenderer {
    private static final int CELL_SIZE = 50;  //dimensione della cella della griglia
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
        Color puyoColor = colorMap.getOrDefault(puyo.getColor().toLowerCase(), Color.GRAY);
        int x = puyo.getX() * CELL_SIZE;
        int y = puyo.getY() * CELL_SIZE;

        //disegna il cerchio del Puyo
        g.setColor(puyoColor);
        g.fillOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);

        //disegna il bordo del Puyo
        g.setColor(Color.BLACK);
        g.drawOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);
    }
}

