package it.unibo;

import it.unibo.controller.ScreenManager;

public class Main {
    public static void main(String[] args) {
        String[] levels = { "1", "2", "3" };
        ScreenManager screenManager = new ScreenManager(levels);
        screenManager.start();
    }
}
