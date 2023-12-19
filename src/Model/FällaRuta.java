package Model;

import java.io.Serializable;

/**
 * Tom klass som bara kan skapa ett nytt objekt av sig själv.
 * Används för att sätta ut vilka rutor som är Fällor på spelplanen.
 */

public class FällaRuta extends Ruta implements Serializable {
    public FällaRuta() {
    }

    @Override
    public String toString() {
        return "X";
    }
}
