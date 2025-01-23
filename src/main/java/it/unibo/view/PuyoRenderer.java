//chiara & aisja
package it.unibo.view;

import it.unibo.model.Grid;
import it.unibo.model.Scale;
import it.unibo.model.interfaces.PuyoInterface;

import javax.swing.ImageIcon;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;

public class PuyoRenderer {
    // private static final int CELL_SIZE = 40; //dimensione della cella della
    // griglia
    private final Map<String, Integer> colorMap; // mappa per associare i colori
    private final Image sprites;
    private Scale scale;
    // sinistra, su, destra, giù
    private static final int[] SPRITE_MAPPER = {
            0, // 0000
            1, // 0001
            4, // 0010
            5, // 0011
            2, // 0100
            3, // 0101
            6, // 0110
            7, // 0111
            8, // 1000
            9, // 1001
            12, // 1010
            13, // 1011
            10, // 1100
            11, // 1101
            14, // 1110
            15 // 1111
    };

    public PuyoRenderer(Scale scale) {
        this.scale = scale;
        colorMap = new HashMap<>();
        colorMap.put("red", 0);
        colorMap.put("green", 1);
        colorMap.put("blue", 2);
        colorMap.put("yellow", 3);
        colorMap.put("purple", 4);
        colorMap.put("pink", 5);
        colorMap.put("cyan", 6);
        URL image_path = getClass().getClassLoader().getResource("images/puyosprites.png");
        sprites = new ImageIcon(image_path).getImage();
    }

    // AISJA
    public int sameColorNeighbour(Grid grid, int row, int col, String color) {
        if (row < 0 || col < 0 || row >= grid.getRows() || col >= grid.getCols()) {
            return 0;
        }
        PuyoInterface puyo = grid.getPuyo(col, row);
        if (puyo == null) {
            return 0;
        }

        if (puyo.getColor().equals(color)) {
            return 1;
        }
        return 0;
    }

    public void render(Graphics g, Grid grid, int row, int col) {
        PuyoInterface puyo = grid.getPuyo(col, row);
        int cellsize = this.scale.getScale() / 16;
        // metà dei pezzi avanzati
        int offset_gridx = cellsize * 4;
        int offset_gridy = cellsize;
        int offset_animation = 0;

        // ottieni il colore del Puyo dalla mappa
        Integer puyoRow = colorMap.get(puyo.getColor().toLowerCase());
        int x = puyo.getX() * cellsize;
        int y = puyo.getY() * cellsize;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // anticrossing per
                                                                                                  // bordi più morbidi
        String puyoColor = puyo.getColor();
        int mask = 0;
        // null, null, null, null
        mask |= sameColorNeighbour(grid, row, col - 1, puyoColor); // <-
        // null, null, null, sinistra
        mask <<= 1;
        // null, null, sinistra, null
        mask |= sameColorNeighbour(grid, row - 1, col, puyoColor); // ^
        // null, null, sinistra, su
        mask <<= 1;
        // null, sinistra, su, null
        mask |= sameColorNeighbour(grid, row, col + 1, puyoColor); // ->
        // null, sinistra, su, destra
        mask <<= 1;
        // sinistra, su, destra, null
        mask |= sameColorNeighbour(grid, row + 1, col, puyoColor); // v
        // sinistra, su, destra, giù

        if (mask != 0) {
            offset_animation = SPRITE_MAPPER[mask] * 32;
        } else {
            if (((System.currentTimeMillis() / 30) ^ puyo.getIdentifier()) % 101 < 2) {
                offset_animation = 32 * 16;
            }
        }
        /*
         * GradientPaint gradient = new GradientPaint(
         * x, y, puyoColor.brighter(),
         * x + CELL_SIZE, y + CELL_SIZE, puyoColor.darker()
         * );
         * 
         * g2d.setPaint(gradient);
         * g2d.fillOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);
         * 
         * g2d.setColor(Color.BLACK);
         * g2d.setStroke(new BasicStroke(2));
         * g2d.drawOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);
         * 
         * g2d.setColor(new Color(255, 255, 255, 80));
         * g2d.fillOval(x + 10, y + 10, CELL_SIZE - 20, CELL_SIZE - 20);
         */
        // drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1,
        // int sx2, int sy2, ImageObserver observer)
        g2d.drawImage(sprites, x + offset_gridx, y + offset_gridy, x + cellsize + offset_gridx,
                y + cellsize + offset_gridy, offset_animation, 32 * puyoRow, offset_animation + 32, 32 * puyoRow + 32,
                null);
    }
}
