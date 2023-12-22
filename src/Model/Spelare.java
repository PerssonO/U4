package Model;

import java.io.Serializable;

/**
 * Klass som används för att skapa spelare och som innehåller information om spelare.
 * @author Jonatan Tempel
 */

public class Spelare implements Serializable {

    private int score;
    private int liv = 2;

    /**
     * Konstruktor som används för att skapa objekt av klassen
     * @author Jonatan Tempel
     */
    public Spelare(){
        this.score = 0;
        this.liv = 2;
    }

    /**
     * Metod som används för att returnera hur många poäng en spelare har.
     * @return int som visar värdet på variabeln score.
     * @author Jonatan Tempel
     */
    public int getScore() {
        return score;
    }

    /**
     * Metod som används för att ange värdet på variabeln score
     * @param score int som används för att ange värdet som variabeln ska få.
     * @author Jonatan Tempel
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Metod som returnerar hur många liv en spelare har
     * @return int variabeln liv.
     */
    public int getLiv() {
        return liv;
    }

    /**
     * Metod som används för att ge variabeln liv ett nytt värde
     * @param liv en int som bestämmer värdet på hur många liv en spelare har.
     */
    public void setLiv(int liv) {
        this.liv = liv;
    }
}
