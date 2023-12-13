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

        /**
         * Mycket lek och test här i denna konstruktorn för att se att det fungerar som vi tänkt.
         * Fritt fram att plocka bort efter hand som det inte behövs längre
         */
        this.skatt1 = new Skatt(100, 0, 0, 1,0,2,0, 3,0);

        for (int i = 0; i<spelplan.length; i++) {
            for (int j = 0; j < spelplan[i].length; j++) {
                spelplan[i][j] = new TomRuta();
            }
        }

        spelplan[skatt1.getIndexEttI()][skatt1.getIndexEttJ()] = new SkattRuta();
        spelplan[skatt1.getIndexTvåI()][skatt1.getIndexTvåJ()] = new SkattRuta();
        spelplan[skatt1.getIndexTreI()][skatt1.getIndexTreJ()] = new SkattRuta();;
        spelplan[skatt1.getIndexFyraI()][skatt1.getIndexFyraJ()] = new SkattRuta();

        this.skatt2 = new Skatt(200, 9,9,0,-1,-1,-1, -1 ,-2);
        spelplan[skatt2.getIndexEttI()][skatt2.getIndexEttJ()] = new SkattRuta();
        spelplan[skatt2.getIndexTvåI()][skatt2.getIndexTvåJ()] = new SkattRuta();
        spelplan[skatt2.getIndexTreI()][skatt2.getIndexTreJ()] = new SkattRuta();
        spelplan[skatt2.getIndexFyraI()][skatt2.getIndexFyraJ()] = new SkattRuta();

        this.skatt3 = new Skatt(200, 4,4,0,-1,0,+1, 0, 2);
        spelplan[skatt3.getIndexEttI()][skatt3.getIndexEttJ()] = new SkattRuta();
        spelplan[skatt3.getIndexTvåI()][skatt3.getIndexTvåJ()] = new SkattRuta();
        spelplan[skatt3.getIndexTreI()][skatt3.getIndexTreJ()] = new SkattRuta();
        spelplan[skatt3.getIndexFyraI()][skatt3.getIndexFyraJ()] = new SkattRuta();

        this.skatt4 = new Skatt(200, 0,8,0,1,1,1, 1,0);
        spelplan[skatt4.getIndexEttI()][skatt4.getIndexEttJ()] = new SkattRuta();
        spelplan[skatt4.getIndexTvåI()][skatt4.getIndexTvåJ()] = new SkattRuta();
        spelplan[skatt4.getIndexTreI()][skatt4.getIndexTreJ()] = new SkattRuta();
        spelplan[skatt4.getIndexFyraI()][skatt4.getIndexFyraJ()] = new SkattRuta();

        spelplan[9][0] = new FällaRuta();
        spelplan[8][5] = new FällaRuta();
        spelplan[8][9] = new FällaRuta();


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
