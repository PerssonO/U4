package controller;


import view.*;


import Model.*;
import view.MainFrame;
import java.util.Random;


public class Controller {
    private MainFrame mainframe;
    private Spelplan spelplan;

    public Controller() {
        this.mainframe = new MainFrame(1000, 550, this);
        disableAllSpelknapp(); // när programmet startar är alla spelknappar disabled,
         //test för att kolla att spelplanen har rätt storlek.
       // mainframe.getMainPanel().getLeftPanel().getButton(0,0).setEnabled(false);
        //mainframe.getMainPanel().getLeftPanel().getButton(0,0).setBackground(Color.cyan);
       //mainframe.getMainPanel().getLeftPanel().getButton(0,0).setForeground(Color.cyan);

    }

    public void buttonPressed(ButtonType button) {

        switch (button) {
            case NyttSpel:{
            System.out.println("KNAPP NyttSpel");
            //setUpSpelplan1(); //Lägger till rutor på en spelplan om man klickar på NyttSpel. Mer kod behövs här
                //setUpSpelplan2();
                //setUpSpelplan1();
                slumpadSpelPlan();
                enableAllSpelknapp(); //sätter alla spelknappar till enable (aka man kan trycka på dom)
            break;
            }
            case LaddaSpel:{
                System.out.println("KNAPP LassaSpel");
            break;
            }
            case VisaHigh:{
                System.out.println("KNAPP VISA HS");
                break;
            }
            case SparaSpel:{
                System.out.println("KNAPP SPARA SPEL");
                break;
            }


        }

    }

    public void testmetod(String test) {
        System.out.println(test);
    }


    /*
    Metod för att Lägga in rutor på en spelplan. Hårdkodad spelplan.
     */
    public void setUpSpelplan1(){
        this.spelplan = new Spelplan();

        spelplan.setSkatt1( new Skatt(100, 0, 0, 1,0,2,0, 3,0));
        spelplan.addRuta(spelplan.getSkatt1().getIndexEttI(), spelplan.getSkatt1().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTvåI(), spelplan.getSkatt1().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTreI(), spelplan.getSkatt1().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexFyraI(), spelplan.getSkatt1().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt2(new Skatt(50, 9,9,0,-1,-1,-1, -1 ,-2));
        spelplan.addRuta(spelplan.getSkatt2().getIndexEttI(), spelplan.getSkatt2().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexTvåI(), spelplan.getSkatt2().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexTreI(), spelplan.getSkatt2().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexFyraI(), spelplan.getSkatt2().getIndexFyraJ(), new SkattRuta());


        spelplan.setSkatt3(new Skatt(175, 3,3,0, 1, 1, 1, 2, 1));
        spelplan.addRuta(spelplan.getSkatt3().getIndexEttI(), spelplan.getSkatt3().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexTvåI(), spelplan.getSkatt3().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexTreI(), spelplan.getSkatt3().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexFyraI(), spelplan.getSkatt3().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt4(new Skatt(150, 0,8,0,1,1,1, 1,0));
        spelplan.addRuta(spelplan.getSkatt4().getIndexEttI(), spelplan.getSkatt4().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexTvåI(), spelplan.getSkatt4().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexTreI(), spelplan.getSkatt4().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexFyraI(), spelplan.getSkatt4().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt5(new Skatt(400, 7,0, 1,0, 1,1,2,1 ));
        spelplan.addRuta(spelplan.getSkatt5().getIndexEttI(), spelplan.getSkatt5().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt5().getIndexTvåI(), spelplan.getSkatt5().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt5().getIndexTreI(), spelplan.getSkatt5().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt5().getIndexFyraI(), spelplan.getSkatt5().getIndexFyraJ(), new SkattRuta());

        spelplan.addRuta(0,2, new FällaRuta());
        spelplan.addRuta(8,9, new FällaRuta());
        spelplan.addRuta(0,1, new FällaRuta());

        for (int i = 0; i < spelplan.getSpelplan().length; i++){
            for (int j =0; j<spelplan.getSpelplan()[i].length; j++){
                if (spelplan.getSpelplan()[i][j] == null){
                    spelplan.addRuta(i,j, new TomRuta());
                }
            }
        }

        for (int i = 0; i<spelplan.getSpelplan().length; i++) {
            for (int j = 0; j<spelplan.getSpelplan()[i].length; j++) {
                System.out.print(spelplan.getSpelplan()[i][j] + " ");
            }
            System.out.println();
        }

    }

    /*
    En spelplan till.
     */
    public void setUpSpelplan2(){
        this.spelplan = new Spelplan();

        spelplan.setSkatt1( new Skatt(100, 3, 0, 1,0,2,0, 3,0));
        spelplan.addRuta(spelplan.getSkatt1().getIndexEttI(), spelplan.getSkatt1().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTvåI(), spelplan.getSkatt1().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTreI(), spelplan.getSkatt1().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexFyraI(), spelplan.getSkatt1().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt2(new Skatt(50, 9,5,0,-1,-1,-1, -1 ,-2));
        spelplan.addRuta(spelplan.getSkatt2().getIndexEttI(), spelplan.getSkatt2().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexTvåI(), spelplan.getSkatt2().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexTreI(), spelplan.getSkatt2().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexFyraI(), spelplan.getSkatt2().getIndexFyraJ(), new SkattRuta());


        spelplan.setSkatt3(new Skatt(175, 3,3,0, 1, 1, 1, 2, 1));
        spelplan.addRuta(spelplan.getSkatt3().getIndexEttI(), spelplan.getSkatt3().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexTvåI(), spelplan.getSkatt3().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexTreI(), spelplan.getSkatt3().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexFyraI(), spelplan.getSkatt3().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt4(new Skatt(150, 0,3,0,1,1,1, 1,0));
        spelplan.addRuta(spelplan.getSkatt4().getIndexEttI(), spelplan.getSkatt4().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexTvåI(), spelplan.getSkatt4().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexTreI(), spelplan.getSkatt4().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexFyraI(), spelplan.getSkatt4().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt5(new Skatt(400, 4,9, 0,-1, 0,-2,0,-3 ));
        spelplan.addRuta(spelplan.getSkatt5().getIndexEttI(), spelplan.getSkatt5().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt5().getIndexTvåI(), spelplan.getSkatt5().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt5().getIndexTreI(), spelplan.getSkatt5().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt5().getIndexFyraI(), spelplan.getSkatt5().getIndexFyraJ(), new SkattRuta());

        spelplan.addRuta(4,5, new FällaRuta());
        spelplan.addRuta(8,9, new FällaRuta());
        spelplan.addRuta(0,1, new FällaRuta());

        for (int i = 0; i < spelplan.getSpelplan().length; i++){
            for (int j =0; j<spelplan.getSpelplan()[i].length; j++){
                if (spelplan.getSpelplan()[i][j] == null){
                    spelplan.addRuta(i,j, new TomRuta());
                }
            }
        }

        for (int i = 0; i<spelplan.getSpelplan().length; i++) {
            for (int j = 0; j<spelplan.getSpelplan()[i].length; j++) {
                System.out.print(spelplan.getSpelplan()[i][j] + " ");
            }
            System.out.println();
        }
    }
/*
Metod för att slumpa spelplant. När man lägger till skatt nr5 bråkar den ibland.
 */
    public void slumpadSpelPlan() {
        this.spelplan = new Spelplan();

        Random random = new Random();
        int slump1 = random.nextInt(7);
        int slump2 = random.nextInt(10);
        spelplan.setSkatt1(new Skatt(100, 1, 1, 1, 0, 2, 0, 3, 0));
        spelplan.addRuta(spelplan.getSkatt1().getIndexEttI(), spelplan.getSkatt1().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTvåI(), spelplan.getSkatt1().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTreI(), spelplan.getSkatt1().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexFyraI(), spelplan.getSkatt1().getIndexFyraJ(), new SkattRuta());

        boolean lagttill = false;
        do {
            slump1 = random.nextInt(1,9);
            slump2 = random.nextInt(2,9);

            spelplan.setSkatt2(new Skatt(50, slump1, slump2, 0, -1, -1, -1, -1, -2));
            if (checkRutaförSkatt(spelplan.getSkatt2()) == false) {
                System.out.println("nej kan inte lägga den här");
                lagttill=false;
            }
            else if (kollaGrannar(spelplan.getSkatt2()) == false){
                System.out.println("Det finns grannar");
                lagttill=false;
            }
                else {
                spelplan.addRuta(spelplan.getSkatt2().getIndexEttI(), spelplan.getSkatt2().getIndexEttJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt2().getIndexTvåI(), spelplan.getSkatt2().getIndexTvåJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt2().getIndexTreI(), spelplan.getSkatt2().getIndexTreJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt2().getIndexFyraI(), spelplan.getSkatt2().getIndexFyraJ(), new SkattRuta());
                System.out.println(spelplan.getRuta(slump1, slump2));
                lagttill = true;
            }
        }
        while(!lagttill);

        lagttill = false;
        do {

            slump1 = random.nextInt(2, 9);
            slump2 = random.nextInt(2, 9);

            spelplan.setSkatt3(new Skatt(175, slump1, slump2, 0, -1, -1, -1, -2, -1));
            if (checkRutaförSkatt(spelplan.getSkatt3())==false){
                System.out.println("Kan inte lägga till här");
                lagttill=false;
            }
            else if (kollaGrannar(spelplan.getSkatt3()) == false){
                System.out.println("Det finns grannar");
                lagttill =false;
            }
            else {

                spelplan.addRuta(spelplan.getSkatt3().getIndexEttI(), spelplan.getSkatt3().getIndexEttJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt3().getIndexTvåI(), spelplan.getSkatt3().getIndexTvåJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt3().getIndexTreI(), spelplan.getSkatt3().getIndexTreJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt3().getIndexFyraI(), spelplan.getSkatt3().getIndexFyraJ(), new SkattRuta());
                lagttill = true;
            }
        }
        while (!lagttill);

        lagttill = false;
        do {
            slump1 = random.nextInt(0,9);
            slump2 = random.nextInt(0,9);
            spelplan.setSkatt4(new Skatt(150, slump1, slump2, 0, 1, 1, 1, 1, 0));
            if (checkRutaförSkatt(spelplan.getSkatt4())==false){
                System.out.println("Kan inte lägga till här");
                lagttill=false;
            }
            else if (kollaGrannar(spelplan.getSkatt4()) == false){
                System.out.println("Här finns grannar");
                lagttill=false;
            }
            else {
                spelplan.addRuta(spelplan.getSkatt4().getIndexEttI(), spelplan.getSkatt4().getIndexEttJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt4().getIndexTvåI(), spelplan.getSkatt4().getIndexTvåJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt4().getIndexTreI(), spelplan.getSkatt4().getIndexTreJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt4().getIndexFyraI(), spelplan.getSkatt4().getIndexFyraJ(), new SkattRuta());
                lagttill = true;
            }
        }
        while (!lagttill);

        lagttill =false;
        do {
            slump1 = random.nextInt(0,10);
            slump2 = random.nextInt(4,10);
            spelplan.setSkatt5(new Skatt(400, slump1, slump2, 0, -1, 0, -2, 0, -3));
            if (checkRutaförSkatt(spelplan.getSkatt5())==false){
                System.out.println("kan inte lägga till här");
                lagttill=false;
            }
            else if (kollaGrannar(spelplan.getSkatt5())==false){
                System.out.println("det finns grannar");
            }
            else {
                spelplan.addRuta(spelplan.getSkatt5().getIndexEttI(), spelplan.getSkatt5().getIndexEttJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt5().getIndexTvåI(), spelplan.getSkatt5().getIndexTvåJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt5().getIndexTreI(), spelplan.getSkatt5().getIndexTreJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt5().getIndexFyraI(), spelplan.getSkatt5().getIndexFyraJ(), new SkattRuta());
                lagttill = true;
            }
        }
        while (!lagttill);
        lagttill=false;
        do {
            slump1 = random.nextInt(10);
            slump2 = random.nextInt(10);
            if (spelplan.getRuta(slump1, slump2) instanceof SkattRuta) {
                System.out.println("kan inte lägga till fälla");
            }
            else {
                spelplan.addRuta(slump1, slump2, new FällaRuta());
                lagttill=true;
            }
        }while (!lagttill);
        lagttill=false;
        do {
            slump1 = random.nextInt(10);
            slump2 = random.nextInt(10);
            if (spelplan.getRuta(slump1, slump2) instanceof SkattRuta) {
                System.out.println("kan inte lägga till fälla");
            }
            else {
                spelplan.addRuta(slump1, slump2, new FällaRuta());
                lagttill=true;
            }
        }while (!lagttill);
        lagttill=false;
        do {
            slump1 = random.nextInt(10);
            slump2 = random.nextInt(10);
            if (spelplan.getRuta(slump1, slump2) instanceof SkattRuta) {
                System.out.println("kan inte lägga till fälla");
            }
            else {
                spelplan.addRuta(slump1, slump2, new FällaRuta());
                lagttill=true;
            }
        }while (!lagttill);


        for (int i = 0; i < spelplan.getSpelplan().length; i++) {
            for (int j = 0; j < spelplan.getSpelplan()[i].length; j++) {
                if (spelplan.getSpelplan()[i][j] == null) {
                    spelplan.addRuta(i, j, new TomRuta());
                }
            }
        }

        for (int i = 0; i < spelplan.getSpelplan().length; i++) {
            for (int j = 0; j < spelplan.getSpelplan()[i].length; j++) {
                System.out.print(spelplan.getSpelplan()[i][j] + " ");
            }
            System.out.println();

        }
    }

    //metod som gör alla spelknappar disabled
    public void disableAllSpelknapp(){
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                mainframe.getMainPanel().getLeftPanel().getButton(i,j).setEnabled(false);
            }
        }
    }

    /*
    kollar så att man inte försöker lägga in en skatt på en ruta som redan är en skattruta.
     */
    public boolean checkRutaförSkatt(Skatt skatt){
        if (spelplan.getRuta(spelplan.getSkatt(skatt).getIndexEttI(), spelplan.getSkatt(skatt).getIndexEttJ()) instanceof SkattRuta
        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTvåI(), spelplan.getSkatt(skatt).getIndexTvåJ()) instanceof SkattRuta
        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTreI(), spelplan.getSkatt(skatt).getIndexTreJ()) instanceof SkattRuta
        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexFyraI(), spelplan.getSkatt(skatt).getIndexFyraJ()) instanceof SkattRuta){
            return false;
        }
        else {
            return true;
        }
    }

    /*
    metod som  kollar om det finns grannar till den nya skatten. Try catch används

     */
    public boolean kollaGrannar(Skatt skatt) {
            try {
                if (spelplan.getRuta(spelplan.getSkatt(skatt).getIndexEttI() - 1, spelplan.getSkatt(skatt).getIndexEttJ()) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexEttI() + 1, spelplan.getSkatt(skatt).getIndexEttJ()) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexEttI(), spelplan.getSkatt(skatt).getIndexEttJ() - 1) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexEttI(), spelplan.getSkatt(skatt).getIndexEttJ() + 1) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTvåI() - 1, spelplan.getSkatt(skatt).getIndexTvåJ()) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTvåI() + 1, spelplan.getSkatt(skatt).getIndexTvåJ()) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTvåI(), spelplan.getSkatt(skatt).getIndexTvåJ() - 1) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTvåI(), spelplan.getSkatt(skatt).getIndexTvåJ() + 1) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTreI() - 1, spelplan.getSkatt(skatt).getIndexTreJ()) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTreI() + 1, spelplan.getSkatt(skatt).getIndexTreJ()) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTreI(), spelplan.getSkatt(skatt).getIndexTreJ() - 1) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTreI(), spelplan.getSkatt(skatt).getIndexTreJ() + 1) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexFyraI() - 1, spelplan.getSkatt(skatt).getIndexFyraJ()) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexFyraI() + 1, spelplan.getSkatt(skatt).getIndexFyraJ()) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexFyraI(), spelplan.getSkatt(skatt).getIndexFyraJ() - 1) instanceof SkattRuta
                        || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexFyraI(), spelplan.getSkatt(skatt).getIndexFyraJ() + 1) instanceof SkattRuta) {
                    return false;

                }
            } catch (Exception e) {
                System.out.println("Något gick fel");
            }
                return true;
        }


    //metod som gör alla spelknappar enabled
    public void enableAllSpelknapp(){
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                mainframe.getMainPanel().getLeftPanel().getButton(i,j).setEnabled(true);
            }
        }

    }


}
