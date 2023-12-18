package Model;

public class HighScore {
    int poäng;
    String namn;

    public HighScore(String namn, int poäng) {
        this.poäng = poäng;
        this.namn = namn;
    }

    public int getPoäng() {
        return poäng;
    }

    public void setPoäng(int poäng) {
        this.poäng = poäng;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    @Override
    public String toString() {
        return namn + "  " + poäng;
    }
}
