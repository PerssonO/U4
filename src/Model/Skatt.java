package Model;

public class Skatt {
    private int poäng;
    private int indexEttI;
    private int indexEttJ;

    private int indexTvåI;
    private int indexTvåJ;
    private int indexTreI;
    private int indexTreJ;
    private int indexFyraI;
    private int indexFyraJ;
    private boolean b1;
    private boolean b2;
    private boolean b3;
    private boolean b4;

    public Skatt(int poäng, int indexEttI, int indexEttJ, int förändringsVärde, int förändringsVärde2, int förändringsVärde3, int förändringsVärde4, int förändringsVärde5, int förändringsVärde6 ) {
        this.poäng = poäng;
        this.indexEttI = indexEttI;
        this.indexEttJ = indexEttJ;
        this.indexTvåI = indexEttI + förändringsVärde;
        this.indexTvåJ = indexEttJ + förändringsVärde2;
        this.indexTreI = indexEttI + förändringsVärde3;
        this.indexTreJ = indexEttJ + förändringsVärde4;
        this.indexFyraI = indexEttI + förändringsVärde5;
        this.indexFyraJ = indexEttJ + förändringsVärde6;
    }

    public int getPoäng() {
        return poäng;
    }

    public void setPoäng(int poäng) {
        this.poäng = poäng;
    }

    public int getIndexEttI() {
        return indexEttI;
    }

    public void setIndexEttI(int indexEttI) {
        this.indexEttI = indexEttI;
    }

    public int getIndexEttJ() {
        return indexEttJ;
    }

    public void setIndexEttJ(int indexEttJ) {
        this.indexEttJ = indexEttJ;
    }

    public int getIndexFyraI() {
        return indexFyraI;
    }

    public void setIndexFyraI(int indexFyraI) {
        this.indexFyraI = indexFyraI;
    }

    public int getIndexFyraJ() {
        return indexFyraJ;
    }

    public void setIndexFyraJ(int indexFyraJ) {
        this.indexFyraJ = indexFyraJ;
    }

    public int getIndexTvåI() {
        return indexTvåI;
    }

    public void setIndexTvåI(int indexTvåI) {
        this.indexTvåI = indexTvåI;
    }

    public int getIndexTvåJ() {
        return indexTvåJ;
    }

    public void setIndexTvåJ(int indexTvåJ) {
        this.indexTvåJ = indexTvåJ;
    }

    public int getIndexTreI() {
        return indexTreI;
    }

    public void setIndexTreI(int indexTreI) {
        this.indexTreI = indexTreI;
    }

    public int getIndexTreJ() {
        return indexTreJ;
    }

    public void setIndexTreJ(int indexTreJ) {
        this.indexTreJ = indexTreJ;
    }

    public boolean isB1() {
        return b1;
    }

    public void setB1(boolean b1) {
        this.b1 = b1;
    }

    public boolean isB2() {
        return b2;
    }

    public void setB2(boolean b2) {
        this.b2 = b2;
    }

    public boolean isB3() {
        return b3;
    }

    public void setB3(boolean b3) {
        this.b3 = b3;
    }

    public boolean isB4() {
        return b4;
    }

    public void setB4(boolean b4) {
        this.b4 = b4;
    }
}
