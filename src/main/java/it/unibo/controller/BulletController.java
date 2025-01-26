// AISJA

package it.unibo.controller;

import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayDeque;
import java.util.HashMap;

import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.BulletModel;
import it.unibo.model.CannonModel;
import it.unibo.model.Grid;
import it.unibo.model.KeyboardModel;
import it.unibo.model.Point2DI;
import it.unibo.model.Scale;
import it.unibo.model.interfaces.PuyoInterface;
import it.unibo.view.CannonView;

public class BulletController implements TickListenerInterface {
    private final BulletModel bulletModel;
    private final Grid grid;
    private final KeyboardModel k;
    private final ProgressBarController progressBar;
    private final CannonView cannonView;
    private final Scale scale;
    private Point2DI target;

    public BulletController(BulletModel bulletModel, Grid grid, KeyboardModel k, ProgressBarController progressBar,
            CannonView cannonView, Scale scale) {
        this.bulletModel = bulletModel;
        this.grid = grid;
        this.k = k;
        this.progressBar = progressBar;
        this.cannonView = cannonView;
        this.scale = scale;
    }

    @Override
    public void onTick() {
        if (k.isKeyPressed(KeyEvent.VK_SPACE) && !bulletModel.isActive()) {
            Point2DI source = cannonView.getCenter();
            CannonModel cannonModel = cannonView.getCannonModel();
            int puyoCol = Math.min((int) (cannonModel.getX() * 8), 7);
            int puyoRow = Math.min((int) ((1.0 - cannonModel.getAngle()) * 8), 7);
            this.target = new Point2DI(puyoCol, puyoRow);
            int puyoCellSize = this.scale.getScale() / 16;
            int puyoGridOffsetX = puyoCellSize * 4;
            int puyoGridOffsetY = puyoCellSize;
            Point2DI target = new Point2DI(
                    puyoGridOffsetX + (int) (cannonModel.getX() * grid.getCols() * puyoCellSize),
                    puyoGridOffsetY + (int) ((1.0 - cannonModel.getAngle()) * grid.getRows() * puyoCellSize));
            bulletModel.shootBullet(Point2DI.toPoint2D(source), Point2DI.toPoint2D(target));
        }
        if (bulletModel.isActive()) {
            if (!bulletModel.updatePosition()) {
                explodePuyos(target);
            }
        }
    }

    private void explodePuyos(Point2DI target) {
        PuyoInterface puyo = grid.getPuyo(target.x(), target.y());
        if (puyo == null) {
            return;
        }
        ArrayDeque<Point2DI> q = new ArrayDeque<>();
        Map<Point2DI, Integer> d = new HashMap<>();
        q.add(target);
        d.put(target, 0);
        while (!q.isEmpty()) {
            Point2DI u = q.poll();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0 || dx != 0 && dy != 0) {
                        continue;
                    }
                    Point2DI v = Point2DI.add(u, new Point2DI(dx, dy));
                    if (d.containsKey(v) ||
                            !grid.isValidPosition(v.x(), v.y()) ||
                            grid.getPuyo(v.x(), v.y()) == null ||
                            !grid.getPuyo(v.x(), v.y()).getColor().equals(puyo.getColor())) {
                        continue;
                    }
                    q.add(v);
                    d.put(v, d.get(u) + 1);
                }
            }
        }
        for (var entry : d.entrySet()) {
            Point2DI p = entry.getKey();
            int dist = entry.getValue();
            PuyoInterface puyoToExplode = grid.getPuyo(p.x(), p.y());
            int deathClock = 10 + dist * 2;
            puyoToExplode.setDeathClock(Optional.of(deathClock));
        }
    }

}