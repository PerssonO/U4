package Model;

import java.io.Serializable;

/**
 * En klass av spelplan som består av en 2-dim array av rutor och innehåller information om var de olika skatterna och andra typer av ruter finns i spelplanen
 * @author Jonatan Tempel , Ola Persson
 */
public class Spelplan implements Serializable {

    private Ruta[][] spelplan;
    private Skatt skatt1;
    private Skatt skatt2;
    private Skatt skatt3;
    private Skatt skatt4;
    private Skatt skatt5;

    /**
     * Konstruktor som används för att skapa objekt av klassen
     * @author Jonatan Tempel
     */
    public Spelplan() {
        this.spelplan = new Ruta[10][10];
    }

    /**
     * Metod som returnerar den tvådimensionella arrayn som är spelplanen
     * @return tvådimensionell array som är spelplanen
     * @author Jonatan Tempel
     */
    public Ruta[][] getSpelplan() {
        return spelplan;
    }

    /**
     * Metod som används för att ändra på spelplanen.
     * @param spelplan en tvådimensionell array av typen ruta
     * @author Jonatan Tempel
     */
    public void setSpelplan(Ruta[][] spelplan) {
        this.spelplan = spelplan;
    }

    /**
     * metod som används för att ange vilken typ av ruta som ska ligga på en viss plats
     * @param index1 int vars värde bestämmer vilken rad rutan ska ligga på
     * @param index2 int var värde bestämmer vilken kolumn rutan ska ligga på
     * @param ruta variabel som visar vilken typ av ruta det är som ska användas
     * @author Jonatan Tempel
     */
    public void addRuta(int index1, int index2, Ruta ruta){
        this.spelplan[index1][index2] = ruta;
    }

    /**
     * Metod som används för att returnera skatt1
     * @return variabeln skatt1.
     * @author Jonatan Tempel
     */
    public Skatt getSkatt1() {
        return skatt1;
    }

    /**
     * Metod som ger skatt1 värden.
     * @param skatt1 den variabel som används för att ge skatt1 variabeln i klassen sitt värde
     * @author Jonatan tempel
     */
    public void setSkatt1(Skatt skatt1) {
        this.skatt1 = skatt1;
    }

    /**
     * Metod som används för att returnera skatt2
     * @return variabeln skatt2.
     * @author Jonatan Tempel
     */
    public Skatt getSkatt2() {
        return skatt2;
    }

    /**
     * Metod som ger skatt2 värden.
     * @param skatt2 den variabel som används för att ge skatt2 variabeln i klassen sitt värde
     * @author Jonatan tempel
     */
    public void setSkatt2(Skatt skatt2) {
        this.skatt2 = skatt2;
    }

    /**
     * Metod som används för att returnera skatt3
     * @return variabeln skatt3.
     * @author Jonatan Tempel
     */
    public Skatt getSkatt3() {
        return skatt3;
    }

    /**
     * Metod som ger skatt3 värden.
     * @param skatt3 den variabel som används för att ge skatt3 variabeln i klassen sitt värde
     * @author Jonatan tempel
     */
    public void setSkatt3(Skatt skatt3) {
        this.skatt3 = skatt3;
    }

    /**
     * Metod som används för att returnera skatt4
     * @return variabeln skatt4.
     * @author Jonatan Tempel
     */
    public Skatt getSkatt4() {
        return skatt4;
    }

    /**
     * Metod som ger skatt4 värden.
     * @param skatt4 den variabel som används för att ge skatt3 variabeln i klassen sitt värde
     * @author Jonatan tempel
     */
    public void setSkatt4(Skatt skatt4) {
        this.skatt4 = skatt4;
    }

    /**
     * Metod som används för att returnera skatt5
     * @return variabeln skatt5.
     * @author Jonatan Tempel
     */
    public Skatt getSkatt5() {
        return skatt5;
    }

    /**
     * Metod som ger skatt5 värden.
     * @param skatt5 den variabel som används för att ge skatt3 variabeln i klassen sitt värde
     * @author Jonatan tempel
     */
    public void setSkatt5(Skatt skatt5) {
        this.skatt5 = skatt5;
    }

    /**
     * metod som returnerar vilken typ av ruta som en viss plats i arrayen har.
     * @param i int för att bestämma vilken rad i arrayen det handlar om.
     * @param j int för att bestämma vilken kolumn i arrayen det handlar om
     * @return Ruta. Den ruta som ligger på rad i, kolumn j i spelplanen
     * @author Jonatan Tempel
     */
    public Ruta getRuta(int i, int j){
        return spelplan[i][j];
    }

    /**
     * Metod som returnerar värdet på den skatt som matas in i metoden.
     * @param skatt den skatten man vill få rädet på.
     * @return den skatt man vill ha information om.
     */
    public Skatt getSkatt(Skatt skatt){
        return skatt;
    }
   public Ruta getTypeOfRuta (int i, int j){
        return spelplan[i][j];

   }
}
