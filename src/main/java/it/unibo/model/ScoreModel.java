package it.unibo.model;

//import it.unibo.model.interfaces.ScoreModelInterface;

public class ScoreModel {
    private int score;

    public ScoreModel() {
        this.score = 0;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}