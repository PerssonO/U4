package Model;

/**
 * En klass av spelplan som består av en 2-dim array av rutor.
 */
public class Spelplan {

    private Ruta[][] spelplan;
    private Ruta tomruta;
    private Ruta fälla;
    private Ruta skattruta;
    private Skatt skatt;

    public Spelplan() {
        this.spelplan = new Ruta[10][10];
        this.tomruta = new TomRuta();
        this.skattruta = new SkattRuta();
        this.fälla = new FällaRuta();
        /**
         * Mycket lek och test här i denna konstruktorn för att se att det fungerar som vi tänkt.
         * Fritt fram att plocka bort efter hand som det inte behövs längre
         */
        this.skatt = new Skatt(100, 0, 0, 1,0,2,0, 3,0);

        for (int i = 0; i<spelplan.length; i++) {
            for (int j = 0; j < spelplan[i].length; j++) {
                spelplan[i][j] = tomruta;
            }
        }

        spelplan[skatt.getIndexEttI()][skatt.getIndexEttJ()] = skattruta;
        spelplan[skatt.getIndexTvåI()][skatt.getIndexTvåJ()] = skattruta;
        spelplan[skatt.getIndexTreI()][skatt.getIndexTreJ()] = skattruta;
        spelplan[skatt.getIndexFyraI()][skatt.getIndexFyraJ()] = skattruta;

        this.skatt = new Skatt(200, 9,9,0,-1,-1,-1, -1 ,-2);
        spelplan[skatt.getIndexEttI()][skatt.getIndexEttJ()] = skattruta;
        spelplan[skatt.getIndexTvåI()][skatt.getIndexTvåJ()] = skattruta;
        spelplan[skatt.getIndexTreI()][skatt.getIndexTreJ()] = skattruta;
        spelplan[skatt.getIndexFyraI()][skatt.getIndexFyraJ()] = skattruta;

        this.skatt = new Skatt(200, 4,4,0,-1,0,+1, 0, 2);
        spelplan[skatt.getIndexEttI()][skatt.getIndexEttJ()] = skattruta;
        spelplan[skatt.getIndexTvåI()][skatt.getIndexTvåJ()] = skattruta;
        spelplan[skatt.getIndexTreI()][skatt.getIndexTreJ()] = skattruta;
        spelplan[skatt.getIndexFyraI()][skatt.getIndexFyraJ()] = skattruta;

        this.skatt = new Skatt(200, 0,8,0,1,1,1, 1,0);
        spelplan[skatt.getIndexEttI()][skatt.getIndexEttJ()] = skattruta;
        spelplan[skatt.getIndexTvåI()][skatt.getIndexTvåJ()] = skattruta;
        spelplan[skatt.getIndexTreI()][skatt.getIndexTreJ()] = skattruta;
        spelplan[skatt.getIndexFyraI()][skatt.getIndexFyraJ()] = skattruta;

        spelplan[9][0] = fälla;
        spelplan[8][5] = fälla;
        spelplan[8][9] = fälla;


        //Skriver ut spelplanen i Terminalen. Kan plocka bort när det fungerar.
        for (int i = 0; i<spelplan.length; i++) {
            for (int j = 0; j<spelplan[i].length; j++) {
                System.out.print(spelplan[i][j] + " ");
            }
            System.out.println();
        }

    }

    public Ruta[][] getSpelplan() {
        return spelplan;
    }

    public void setSpelplan(Ruta[][] spelplan) {
        this.spelplan = spelplan;
    }
}
