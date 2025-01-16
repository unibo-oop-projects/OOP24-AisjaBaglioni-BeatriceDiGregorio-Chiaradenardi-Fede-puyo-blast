//chiara & aisja
package it.unibo.view;

import it.unibo.model.Puyo;

import javax.swing.ImageIcon;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;

public class PuyoRenderer {
    private static final int CELL_SIZE = 40;  //dimensione della cella della griglia
    private final Map<String, Integer> colorMap;  //mappa per associare i colori
    private final Image sprites;

    public PuyoRenderer() {
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


    public void render(Graphics g, Puyo puyo) {
        int offset_animation = 0;
        if (((System.currentTimeMillis()/30) ^ puyo.getIdentifier())%101 < 2) {
            offset_animation = 32 * 16;
        }
        //ottieni il colore del Puyo dalla mappa
        Integer puyoRow = colorMap.get(puyo.getColor().toLowerCase());
        int x = puyo.getX() * CELL_SIZE;  
        int y = puyo.getY() * CELL_SIZE;  

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  //anticrossing per bordi più morbidi

        /*GradientPaint gradient = new GradientPaint(
            x, y, puyoColor.brighter(), 
            x + CELL_SIZE, y + CELL_SIZE, puyoColor.darker() 
        );

        g2d.setPaint(gradient);
        g2d.fillOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10); 

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));  
        g2d.drawOval(x + 5, y + 5, CELL_SIZE - 10, CELL_SIZE - 10);  

        g2d.setColor(new Color(255, 255, 255, 80));  
        g2d.fillOval(x + 10, y + 10, CELL_SIZE - 20, CELL_SIZE - 20);  */
        //  	drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
        g2d.drawImage(sprites, x, y, x + CELL_SIZE, y + CELL_SIZE, offset_animation, 32*puyoRow, offset_animation + 32, 32*puyoRow+32, null);
    }
}
