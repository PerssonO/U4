package Model;

/**
 * Tom klass som bara kan skapa ett nytt objekt av sig själv.
 * Används för att sätta ut vilka rutor som är Fällor på spelplanen.
 */

public class FällaRuta extends Ruta{
    public FällaRuta() {
    }

    @Override
    public String toString() {
        return "X";
    }
}
