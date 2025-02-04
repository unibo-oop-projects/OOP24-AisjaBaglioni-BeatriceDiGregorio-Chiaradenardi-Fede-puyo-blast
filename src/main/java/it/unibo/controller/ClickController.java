package it.unibo.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

import it.unibo.model.Point2DI;
import it.unibo.view.interfaces.ClickInterface;

public class ClickController implements MouseListener {
    private Set<ClickInterface> clickables;

    public ClickController(Set<ClickInterface> clickables) {
        this.clickables = clickables;
    }

    void addClickable(ClickInterface clickable) {
        clickables.add(clickable);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Point2DI pos = new Point2DI(x, y);
        for (ClickInterface clickable : clickables) {
            if (clickable.isClicked(pos)) {
                clickable.doAction();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}