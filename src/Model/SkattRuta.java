package Model;

import java.io.Serializable;

/**
 * Klass som används för att skapa skattrutor som används i spelet
 * @author Jonatan Tempel
 */
public class SkattRuta extends Ruta implements Serializable {

    /**
     * Konstruktor som används för att skapa objekt av klassen
     * @author Jonatan Tempel
     */
    public SkattRuta(){}

    /**
     * Metod som används vid testning av spelet och vid utskrift till terminalen
     * @return String som är 1.
     * @author Jonatan Tempel
     */
    @Override
    public String toString() {
        return "1";
    }
}
