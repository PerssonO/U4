package Model;

import java.io.Serializable;

/**
 * En subklass till klassen Ruta som används för att skapa rutor av typen fälla.
 * @author Jonatan Tempel
 */

public class FällaRuta extends Ruta implements Serializable {

    /**
     * Konstruktor som skapar ett nytt objekt av klassen.
     * @author Jonatan Tempel
     */
    public FällaRuta() {
    }

    /**
     * Metod som använts vid testning av programmet och skriver ut ett X
     * @return String
     * @author Jonatan Tempel
     */
    @Override
    public String toString() {
        return "X";
    }
}
