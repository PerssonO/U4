package Model;

/**
 * Klass som innehåller information om spelare.
 */

public class Spelare {

    private int score;
    private int liv = 2;

    public Spelare(){
        this.score = 0;
        this.liv = 2;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLiv() {
        return liv;
    }

    public void setLiv(int liv) {
        this.liv = liv;
    }

    @Override
    public String toString() {
        return "Du hade: " + score;
    }
}
