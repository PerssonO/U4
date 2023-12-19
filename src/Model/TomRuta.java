package Model;

import java.io.Serializable;

/**
 * Skapar en ruta som ska motsvara om där inte finns något på spelplanen. Returnerar nu en 0:a men fritt fram att
 * plocka bort när det inte behövs längre.
 */
public class TomRuta extends Ruta implements Serializable {
    public TomRuta() {
    }

    @Override
    public String toString() {
        return "0";
    }
}
