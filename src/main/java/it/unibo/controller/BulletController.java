//AISJA

package it.unibo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.BulletModel;

public class BulletController implements ActionListener, TickListenerInterface{
        private final BulletModel bulletModel;

        public BulletController(BulletModel bulletModel) {
            this.bulletModel = bulletModel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.bulletModel.shootBullet();
        }

        @Override
        public void onTick() {
            this.bulletModel.updatePosition();
        }

}
