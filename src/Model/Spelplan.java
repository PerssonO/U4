package Model;

/**
 * En klass av spelplan som består av en 2-dim array av rutor.
 */
public class Spelplan {

    private Ruta[][] spelplan;
    private Skatt skatt1;
    private Skatt skatt2;
    private Skatt skatt3;
    private Skatt skatt4;
    private Skatt skatt5;

    public Spelplan() {
        this.spelplan = new Ruta[10][10];
    }

        /**
         * Mycket lek och test här i denna konstruktorn för att se att det fungerar som vi tänkt.
         * Fritt fram att plocka bort efter hand som det inte behövs längre
         */




    public Ruta[][] getSpelplan() {
        return spelplan;
    }

    public void setSpelplan(Ruta[][] spelplan) {
        this.spelplan = spelplan;
    }

    public void addRuta(int index1, int index2, Ruta ruta){
        this.spelplan[index1][index2] = ruta;
    }

    public Skatt getSkatt1() {
        return skatt1;
    }

    public void setSkatt1(Skatt skatt1) {
        this.skatt1 = skatt1;
    }

    public Skatt getSkatt2() {
        return skatt2;
    }

    public void setSkatt2(Skatt skatt2) {
        this.skatt2 = skatt2;
    }

    public Skatt getSkatt3() {
        return skatt3;
    }

    public void setSkatt3(Skatt skatt3) {
        this.skatt3 = skatt3;
    }

    public Skatt getSkatt4() {
        return skatt4;
    }

    public void setSkatt4(Skatt skatt4) {
        this.skatt4 = skatt4;
    }

    public Skatt getSkatt5() {
        return skatt5;
    }

    public void setSkatt5(Skatt skatt5) {
        this.skatt5 = skatt5;
    }

    public Ruta getRuta(int i, int j){
        return spelplan[i][j];
    }

    public Skatt getSkatt(Skatt skatt){
        return skatt;
    }
   public Ruta getTypeOfRuta (int i, int j){
        return spelplan[i][j];

   }
}
