//AISJA

package it.unibo.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import it.unibo.model.BulletModel;

public class BulletController implements ActionListener{
        private final BulletModel bulletModel;

        public BulletController(BulletModel bulletModel) {
            this.bulletModel = bulletModel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.bulletModel.shootBullet();
        }
}
