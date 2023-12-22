package Model;

import java.io.Serializable;

/**
 * Skapar en ruta som ska motsvara om där inte finns något på spelplanen.
 * @author Jonatan Tempel
 */
public class TomRuta extends Ruta implements Serializable {
    /**
     * konstruktor som skapar ett objekt av klassen
     * @author Jonatan Tempel
     */
    public TomRuta() {
    }

    /**
     * Metod som används vid testing och utskrifter i terminalen.
     * @return en string som visar 0
     * @author Jonatan Tempel
     */
    @Override
    public String toString() {
        return "0";
    }
}
