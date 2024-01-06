package Model;

import java.io.Serializable;

/**
 * Klass som används för att skapa och hålla information om skatterna som används i spelet
 * @author Jonatan Tempel , Ola Persson
 */

public class Skatt implements Serializable {
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

    private boolean allaHittade = false;

    /**
     * Konstruktor som skapar nya objekt av typen skatt som används i spelet.
     * @param poäng int som anger hur många poäng skatten är värd.
     * @param indexEttI int som anger vilken rad den första rutan i skatten ska ligga på.
     * @param indexEttJ int som anger vilken kolumn den första rutan i skatten ska ligga på.
     * @param förändringsVärde int som anger ett förändringsvärde om vilken rad ruta två kommer att ha i skatten.
     * @param förändringsVärde2 int som anger ett förändringsvärde om vilken kolumn ruta två kommer att ha i skatten.
     * @param förändringsVärde3 int som anger ett förändringsvärde om vilken rad ruta tre kommer att ha i skatten.
     * @param förändringsVärde4 int som anger ett förändringsvärde om vilken kolumn ruta tre kommer att ha i skatten.
     * @param förändringsVärde5 int som anger ett förändringsvärde om vilken rad ruta fyra kommer att ha i skatten.
     * @param förändringsVärde6 int som anger ett förändringsvärde om vilken kolumn ruta fyra kommer att ha i skatten.
     * @author Jonatan Tempel
     */
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

    /**
     * metod som används för att returnera hur många poäng en skatt är värd.
     * @return int variablen poäng.
     * @author Jonatan Tempel
     */
    public int getPoäng() {
        return poäng;
    }

    /**
     * Metod som används för att ändra hur många poäng en skatt är värd.
     * @param poäng int som anger hur många poäng skatten ska vara värd
     * @author Jonatan Tempel
     */
    public void setPoäng(int poäng) {
        this.poäng = poäng;
    }

    /**
     * Metod som returnerar startvärdet för vilken rad skatten ska börja i.
     * @return int som anger startraden för ruta ett i skatten
     * @author Jonatan Tempel
     */
    public int getIndexEttI() {
        return indexEttI;
    }

    /**
     * Metod som används för att ange startvärdet för vilken rad skatten ska börja i.
     * @param indexEttI int som anger startraden för första rutan i skatten
     * @author Jonatan Tempel
     */

    public void setIndexEttI(int indexEttI) {
        this.indexEttI = indexEttI;
    }

    /**
     * Metod som returnerar startvärdet för vilken rad skatten ska börja i.
     * @return int som anger vilken kolumn ruta ett i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public int getIndexEttJ() {
        return indexEttJ;
    }

    /**
     * Metod som används för att ange startvärdet för vilken rad skatten ska börja i.
     * @param indexEttJ int som anger start kolumnen för första rutan i skatten
     * @author Jonatan Tempel
     */
    public void setIndexEttJ(int indexEttJ) {
        this.indexEttJ = indexEttJ;
    }

    /**
     * Metod som returnerar startvärdet för vilken rad skatten ska börja i.
     * @return int som anger vilken rad ruta fyra i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public int getIndexFyraI() {
        return indexFyraI;
    }

    /**
     * Metod som används för att ange startvärdet för vilken rad skatten ska börja i.
     * @param indexFyraI int som anger vilken rad den fjärde rutan i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public void setIndexFyraI(int indexFyraI) {
        this.indexFyraI = indexFyraI;
    }

    /**
     * Metod som returnerar startvärdet för vilken rad skatten ska börja i.
     * @return int som anger vilken kolumn den fjärde rutan i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public int getIndexFyraJ() {
        return indexFyraJ;
    }

    /**
     * Metod som används för att ange startvärdet för vilken rad skatten ska börja i.
     * @param indexFyraJ int som anger vilken kolumn den fjärde rutan i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public void setIndexFyraJ(int indexFyraJ) {
        this.indexFyraJ = indexFyraJ;
    }

    /**
     * Metod som returnerar startvärdet för vilken rad skatten ska börja i.
     * @return int som anger vilken rad den andra rutan i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public int getIndexTvåI() {
        return indexTvåI;
    }

    /**
     * Metod som används för att ange startvärdet för vilken rad skatten ska börja i.
     * @param indexTvåI int som anger vilken rad den andra rutan i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public void setIndexTvåI(int indexTvåI) {
        this.indexTvåI = indexTvåI;
    }

    /**
     * Metod som returnerar startvärdet för vilken rad skatten ska börja i.
     * @return int som anger vilken kolumn den andra rutan i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public int getIndexTvåJ() {
        return indexTvåJ;
    }

    /**
     * Metod som används för att ange startvärdet för vilken rad skatten ska börja i.
     * @param indexTvåJ int som anger vilken kolumn den andra rutan i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public void setIndexTvåJ(int indexTvåJ) {
        this.indexTvåJ = indexTvåJ;
    }

    /**
     * Metod som returnerar startvärdet för vilken rad skatten ska börja i.
     * @return int som anger vilken rad den tredje rutan i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public int getIndexTreI() {
        return indexTreI;
    }

    /**
     * Metod som används för att ange startvärdet för vilken rad skatten ska börja i.
     * @param indexTreI int som anger vilken rad den tredje rutan i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public void setIndexTreI(int indexTreI) {
        this.indexTreI = indexTreI;
    }

    /**
     * Metod som returnerar startvärdet för vilken rad skatten ska börja i.
     * @return int som anger vilken kolumn den tredje rutan i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public int getIndexTreJ() {
        return indexTreJ;
    }

    /**
     * Metod som används för att ange startvärdet för vilken rad skatten ska börja i.
     * @param indexTreJ int som anger vilken kolumn den tredje rutan i skatten kommer att ha.
     * @author Jonatan Tempel
     */
    public void setIndexTreJ(int indexTreJ) {
        this.indexTreJ = indexTreJ;
    }

    /**
     * Metod som returnerar en boolean. Används för att ta reda på om en ruta i skatten har blivit hittad.
     * @return b1 som används för den första rutan i skatten
     * @author Jonatan Tempel
     */
    public boolean isB1() {
        return b1;
    }

    /**
     * Metod som används för att ange värdet på b1.
     * @param b1 boolean vars värde avgörs beroende på om första rutan i skatten är hittad eller inte.
     * @author Jonatan Tempel
     */
    public void setB1(boolean b1) {
        this.b1 = b1;
    }

    /**
     * Metod som returnerar en boolean. Används för att ta reda på om en ruta i skatten har blivit hittad.
     * @return b2 som används för den första rutan i skatten
     * @author Jonatan Tempel
     */
    public boolean isB2() {
        return b2;
    }

    /**
     * Metod som används för att ange värdet på b1.
     * @param b2 boolean vars värde avgörs beroende på om andra rutan i skatten är hittad eller inte.
     * @author Jonatan Tempel
     */
    public void setB2(boolean b2) {
        this.b2 = b2;
    }

    /**
     * Metod som returnerar en boolean. Används för att ta reda på om en ruta i skatten har blivit hittad.
     * @return b3 som används för den första rutan i skatten
     * @author Jonatan Tempel
     */
    public boolean isB3() {
        return b3;
    }

    /**
     * Metod som används för att ange värdet på b1.
     * @param b3 boolean vars värde avgörs beroende på om tredje rutan i skatten är hittad eller inte.
     * @author Jonatan Tempel
     */
    public void setB3(boolean b3) {
        this.b3 = b3;
    }

    /**
     * Metod som returnerar en boolean. Används för att ta reda på om en ruta i skatten har blivit hittad.
     * @return b4 som används för den första rutan i skatten
     * @author Jonatan Tempel
     */
    public boolean isB4() {
        return b4;
    }

    /**
     * Metod som används för att ange värdet på b1.
     * @param b4 boolean vars värde avgörs beroende på om fjärde rutan i skatten är hittad eller inte.
     * @author Jonatan Tempel
     */
    public void setB4(boolean b4) {
        this.b4 = b4;
    }

    /**
     * Metoden berättar om alla rutor i en skatt är hittade
     * @return boolean som säger om att skattrutor i en skatt är hittade
     * @author Ola Persson
     */
    public boolean isAllaHittade() {
        return allaHittade;
    }

    /**
     * Sätter värdet i ett skatt objekt om alla skattrutor är hittade
     * @param allaHittade booolean
     * @author Ola Persson
     */
    public void setAllaHittade(boolean allaHittade) {
        this.allaHittade = allaHittade;
    }
}
