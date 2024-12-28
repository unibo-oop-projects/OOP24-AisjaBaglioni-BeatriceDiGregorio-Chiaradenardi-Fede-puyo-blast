package it.unibo;

import it.unibo.controller.ScreenManager;

public class Main {
    public static void main(String[] args) {
        String[] levels = {"Livello 1", "Livello 2", "Livello 3"};
        ScreenManager screenManager = new ScreenManager(levels);
        screenManager.start();
    }
}
