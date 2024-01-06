package Model;
/**
 * Klass som används för att skapa och hålla information highscore
 * @author Ola Persson
 */

public class HighScore {
    int poäng;
    String namn;

    /**
     * Konstruktor för att skapa high score objekt
     * @param namn String är namn på highsocre listan
     * @param poäng int är poäangen
     * @author Ola Persson
     */

    public HighScore(String namn, int poäng) {
        this.poäng = poäng;
        this.namn = namn;
    }

    /**
     * Metoden hämtar poängen från ett highscore objekt
     * @return  int med poängen
     * @author Ola Persson
     */
    public int getPoäng() {
        return poäng;
    }

    /**
     * Sätter poängen på ett highscore objekt
     * @param poäng int som sätter poängen
     * @author Ola Persson
     */
    public void setPoäng(int poäng) {
        this.poäng = poäng;
    }

    /**
     * Retunerar namnet från ett highscore objekt
     * @return namn som en String
     * @author Ola Persson
     */
    public String getNamn() {
        return namn;
    }

    /**
     * Metoden sätter namnet på ett highscore objekt
     * @param namn String sätter namnet på ett highscore objekt
     * @author Ola Persson
     */
    public void setNamn(String namn) {
        this.namn = namn;
    }

    /**
     * Metoden retunerar en string med Namn och poäng från ett highscore objekt
     * @return String med namn och poäng
     * @author Ola Persson
     */
    @Override
    public String toString() {
        return namn + "," + poäng;
    }
}
