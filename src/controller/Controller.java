package controller;


import view.*;


import Model.*;
import view.MainFrame;

import java.awt.*;


public class Controller {
    private MainFrame mainframe;
    private Spelplan spelplan;

    private int[] lastMove;
    private Spelare player1;
    private Spelare player2;
    private int round = 1;

    public Controller() {
        this.mainframe = new MainFrame(1000, 550, this);
        this.lastMove = new int[2];
        disableAllSpelknapp(); // när programmet startar är alla spelknappar disabled,
        //test för att kolla att spelplanen har rätt storlek.
        // mainframe.getMainPanel().getLeftPanel().getButton(0,0).setEnabled(false);
        //mainframe.getMainPanel().getLeftPanel().getButton(0,0).setBackground(Color.cyan);
        //mainframe.getMainPanel().getLeftPanel().getButton(0,0).setForeground(Color.cyan);

    }

    public void buttonPressed(ButtonType button) {

        switch (button) {
            case NyttSpel: {
                System.out.println("KNAPP NyttSpel");
                //setUpSpelplan1(); //Lägger till rutor på en spelplan om man klickar på NyttSpel. Mer kod behövs här
                setUpSpelplan2();
                enableAllSpelknapp(); //sätter alla spelknappar till enable (aka man kan trycka på dom)
                player1 = new Spelare();
                player2 = new Spelare();
                break;
            }
            case LaddaSpel: {
                System.out.println("KNAPP LassaSpel");
                break;
            }
            case VisaHigh: {
                System.out.println("KNAPP VISA HS");
                break;
            }
            case SparaSpel: {
                System.out.println("KNAPP SPARA SPEL");
                break;
            }


        }

    }


    /*
    Metod för att Lägga in rutor på en spelplan. Hårdkodad spelplan.
     */
    public void setUpSpelplan1() {
        this.spelplan = new Spelplan();

        spelplan.setSkatt1(new Skatt(100, 0, 0, 1, 0, 2, 0, 3, 0));
        spelplan.addRuta(spelplan.getSkatt1().getIndexEttI(), spelplan.getSkatt1().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTvåI(), spelplan.getSkatt1().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTreI(), spelplan.getSkatt1().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexFyraI(), spelplan.getSkatt1().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt2(new Skatt(50, 9, 9, 0, -1, -1, -1, -1, -2));
        spelplan.addRuta(spelplan.getSkatt2().getIndexEttI(), spelplan.getSkatt2().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexTvåI(), spelplan.getSkatt2().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexTreI(), spelplan.getSkatt2().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexFyraI(), spelplan.getSkatt2().getIndexFyraJ(), new SkattRuta());


        spelplan.setSkatt3(new Skatt(175, 4, 4, 0, -1, 0, +1, 0, 2));
        spelplan.addRuta(spelplan.getSkatt3().getIndexEttI(), spelplan.getSkatt3().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexTvåI(), spelplan.getSkatt3().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexTreI(), spelplan.getSkatt3().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexFyraI(), spelplan.getSkatt3().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt4(new Skatt(150, 0, 8, 0, 1, 1, 1, 1, 0));
        spelplan.addRuta(spelplan.getSkatt4().getIndexEttI(), spelplan.getSkatt4().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexTvåI(), spelplan.getSkatt4().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexTreI(), spelplan.getSkatt4().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexFyraI(), spelplan.getSkatt4().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt5(new Skatt(400, 7, 0, 1, 0, 1, 1, 2, 1));
        spelplan.addRuta(spelplan.getSkatt5().getIndexEttI(), spelplan.getSkatt5().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt5().getIndexTvåI(), spelplan.getSkatt5().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt5().getIndexTreI(), spelplan.getSkatt5().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt5().getIndexFyraI(), spelplan.getSkatt5().getIndexFyraJ(), new SkattRuta());

        spelplan.addRuta(0, 2, new FällaRuta());
        spelplan.addRuta(8, 9, new FällaRuta());
        spelplan.addRuta(0, 1, new FällaRuta());

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

    /*
    En spelplan till.
     */
    public void setUpSpelplan2() {
        this.spelplan = new Spelplan();

        spelplan.setSkatt1(new Skatt(100, 3, 0, 1, 0, 2, 0, 3, 0));
        spelplan.addRuta(spelplan.getSkatt1().getIndexEttI(), spelplan.getSkatt1().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTvåI(), spelplan.getSkatt1().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTreI(), spelplan.getSkatt1().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexFyraI(), spelplan.getSkatt1().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt2(new Skatt(50, 9, 5, 0, -1, -1, -1, -1, -2));
        spelplan.addRuta(spelplan.getSkatt2().getIndexEttI(), spelplan.getSkatt2().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexTvåI(), spelplan.getSkatt2().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexTreI(), spelplan.getSkatt2().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexFyraI(), spelplan.getSkatt2().getIndexFyraJ(), new SkattRuta());


        spelplan.setSkatt3(new Skatt(175, 4, 4, 0, -1, 1, +1, 1, 0));
        spelplan.addRuta(spelplan.getSkatt3().getIndexEttI(), spelplan.getSkatt3().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexTvåI(), spelplan.getSkatt3().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexTreI(), spelplan.getSkatt3().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexFyraI(), spelplan.getSkatt3().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt4(new Skatt(150, 0, 3, 0, 1, 1, 1, 1, 0));
        spelplan.addRuta(spelplan.getSkatt4().getIndexEttI(), spelplan.getSkatt4().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexTvåI(), spelplan.getSkatt4().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexTreI(), spelplan.getSkatt4().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexFyraI(), spelplan.getSkatt4().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt5(new Skatt(400, 4, 9, 0, -1, 0, -2, 0, -3));
        spelplan.addRuta(spelplan.getSkatt5().getIndexEttI(), spelplan.getSkatt5().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt5().getIndexTvåI(), spelplan.getSkatt5().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt5().getIndexTreI(), spelplan.getSkatt5().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt5().getIndexFyraI(), spelplan.getSkatt5().getIndexFyraJ(), new SkattRuta());

        spelplan.addRuta(4, 5, new FällaRuta());
        spelplan.addRuta(8, 9, new FällaRuta());
        spelplan.addRuta(0, 1, new FällaRuta());

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
    public void disableAllSpelknapp() {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                mainframe.getMainPanel().getLeftPanel().getButton(i, j).setEnabled(false);
            }
        }
    }

    //metod som gör alla spelknappar enabled
    public void enableAllSpelknapp() {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                mainframe.getMainPanel().getLeftPanel().getButton(i, j).setEnabled(true);
            }
        }

    }

    //Metoden tar emot värdet från knappen på spelplanen och uppdaterar last move
    public void updateLastMove(int[] indexPlatser) {
        lastMove = indexPlatser;
        newRound();

    }

    //Metoden som körs efter någon har tryckt på en spelknapp och lastmove har uppdaterats
    private void newRound() {
        //System.out.println(lastMove[0]);
        //System.out.println(lastMove[1]);
        disableButton();
        updateFärgSpelplan();
        updateSkatter();
        checkIfFälla();
        checkIfLastSkattruta();
        printScore(); //Ta bor sen
        checkIfAllaSkatterHittade();

        round++;
        printPlayerTurn();
        
        


    }

    private void printPlayerTurn() {
        System.out.println("-----------------------------------");
        if (round%2 == 0){
            System.out.println("Spelare 2 tur");
        }
        else {
            System.out.println("Spelare 1 tur");
        }
    }

    private void printScore() {
        System.out.println("Player 1 " + player1.getScore());
        System.out.println("Player 2 " + player2.getScore());
    }

    //Kollar om alla skatter är hittade och spelet är slut
    private void checkIfAllaSkatterHittade() {
        if      (spelplan.getSkatt1().isAllaHittade() &&
                spelplan.getSkatt2().isAllaHittade() &&
                spelplan.getSkatt3().isAllaHittade() &&
                spelplan.getSkatt4().isAllaHittade() &&
                spelplan.getSkatt5().isAllaHittade()){
            System.out.println("ALLA SKATTER HITTADE");
            disableAllSpelknapp();
        }

    }
    //Stänger av en knapp. Används så man inte kan trycka på samma spelknapp två gånger
    private void disableButton() {
        mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0],lastMove[1]).setEnabled(false);
    }
    //Kollar om alla rutor i en skatt är hittade så att poäng kan delas ut
    private void checkIfLastSkattruta() {
        if (spelplan.getSkatt1().isB1() && spelplan.getSkatt1().isB2() && spelplan.getSkatt1().isB3() && spelplan.getSkatt1().isB4()){
            System.out.println("1. Du hittade en skatt Lägg en metod här för att dela ut poäng");
            spelplan.getSkatt1().setB1(false);
            spelplan.getSkatt1().setAllaHittade(true);
            gePoäng(1);
        }
        if (spelplan.getSkatt2().isB1() && spelplan.getSkatt2().isB2() && spelplan.getSkatt2().isB3() && spelplan.getSkatt2().isB4()){
            System.out.println("2. Du hittade en skatt Lägg en metod här för att dela ut poäng");
            spelplan.getSkatt2().setB1(false);
            spelplan.getSkatt2().setAllaHittade(true);
            gePoäng(2);
        }
        if (spelplan.getSkatt3().isB1() && spelplan.getSkatt3().isB2() && spelplan.getSkatt3().isB3() && spelplan.getSkatt3().isB4()){
            System.out.println("3. Du hittade en skatt Lägg en metod här för att dela ut poäng");
            spelplan.getSkatt3().setB1(false);
            spelplan.getSkatt3().setAllaHittade(true);
            gePoäng(3);
        }
        if (spelplan.getSkatt4().isB1() && spelplan.getSkatt4().isB2() && spelplan.getSkatt4().isB3() && spelplan.getSkatt4().isB4()){
            System.out.println("4. Du hittade en skatt Lägg en metod här för att dela ut poäng");
            spelplan.getSkatt4().setB1(false);
            spelplan.getSkatt4().setAllaHittade(true);
            gePoäng(4);
        }
        if (spelplan.getSkatt5().isB1() && spelplan.getSkatt5().isB2() && spelplan.getSkatt5().isB3() && spelplan.getSkatt5().isB4()){
            System.out.println("5. Du hittade en skatt Lägg en metod här för att dela ut poäng");
            spelplan.getSkatt5().setB1(false);
            spelplan.getSkatt5().setAllaHittade(true);
            gePoäng(5);
        }





    }

    private void gePoäng(int i) {
        int poäng = 0;
        if (i == 1){
        poäng = spelplan.getSkatt1().getPoäng();
            }
        if (i == 2) {
            poäng = spelplan.getSkatt2().getPoäng();
        }
        if (i == 3){
                poäng = spelplan.getSkatt3().getPoäng();
            }
        if (i == 4){
            poäng = spelplan.getSkatt3().getPoäng();
        }
        if (i == 5){
            poäng = spelplan.getSkatt3().getPoäng();
        }

        if (round%2 == 0) {
        player2.setScore(player2.getScore()+poäng);
        }
        if (round%2 != 0) {
            player1.setScore(player1.getScore() + poäng);
        }

    }




    //Metoden kollar om platsen är en fälla
    private void checkIfFälla() {
        if (spelplan.getTypeOfRuta(lastMove[0], lastMove[1]) instanceof FällaRuta) {
        System.out.println("Du gick i en fälla Kör en metod som gör något roligt");

        }
    }
    //Kollar med alla skatter om draget hamnade på en av deras indexplatser. Om rätt ändrar boolean till att den är hittad
    private void updateSkatter(){
        if (spelplan.getSkatt1().getIndexEttI() == lastMove[0] && spelplan.getSkatt1().getIndexEttJ() == lastMove[1]) {
            spelplan.getSkatt1().setB1(true);
        }
        if (spelplan.getSkatt1().getIndexTvåI() == lastMove[0] && spelplan.getSkatt1().getIndexTvåJ() == lastMove[1]) {
            spelplan.getSkatt1().setB2(true);
        }
        if (spelplan.getSkatt1().getIndexTreI() == lastMove[0] && spelplan.getSkatt1().getIndexTreJ() == lastMove[1]) {
            spelplan.getSkatt1().setB3(true);
        }
        if (spelplan.getSkatt1().getIndexFyraI() == lastMove[0] && spelplan.getSkatt1().getIndexFyraJ() == lastMove[1]) {
            spelplan.getSkatt1().setB4(true);
        }


        if (spelplan.getSkatt2().getIndexEttI() == lastMove[0] && spelplan.getSkatt2().getIndexEttJ() == lastMove[1]) {
            spelplan.getSkatt2().setB1(true);
        }
        if (spelplan.getSkatt2().getIndexTvåI() == lastMove[0] && spelplan.getSkatt2().getIndexTvåJ() == lastMove[1]) {
            spelplan.getSkatt2().setB2(true);
        }
        if (spelplan.getSkatt2().getIndexTreI() == lastMove[0] && spelplan.getSkatt2().getIndexTreJ() == lastMove[1]) {
            spelplan.getSkatt2().setB3(true);
        }
        if (spelplan.getSkatt2().getIndexFyraI() == lastMove[0] && spelplan.getSkatt2().getIndexFyraJ() == lastMove[1]) {
            spelplan.getSkatt2().setB4(true);
        }

        if (spelplan.getSkatt3().getIndexEttI() == lastMove[0] && spelplan.getSkatt3().getIndexEttJ() == lastMove[1]) {
            spelplan.getSkatt3().setB1(true);
        }
        if (spelplan.getSkatt3().getIndexTvåI() == lastMove[0] && spelplan.getSkatt3().getIndexTvåJ() == lastMove[1]) {
            spelplan.getSkatt3().setB2(true);
        }
        if (spelplan.getSkatt3().getIndexTreI() == lastMove[0] && spelplan.getSkatt3().getIndexTreJ() == lastMove[1]) {
            spelplan.getSkatt3().setB3(true);
        }
        if (spelplan.getSkatt3().getIndexFyraI() == lastMove[0] && spelplan.getSkatt3().getIndexFyraJ() == lastMove[1]) {
            spelplan.getSkatt3().setB4(true);
        }

        if (spelplan.getSkatt4().getIndexEttI() == lastMove[0] && spelplan.getSkatt4().getIndexEttJ() == lastMove[1]) {
            spelplan.getSkatt4().setB1(true);
        }
        if (spelplan.getSkatt4().getIndexTvåI() == lastMove[0] && spelplan.getSkatt4().getIndexTvåJ() == lastMove[1]) {
            spelplan.getSkatt4().setB2(true);
        }
        if (spelplan.getSkatt4().getIndexTreI() == lastMove[0] && spelplan.getSkatt4().getIndexTreJ() == lastMove[1]) {
            spelplan.getSkatt4().setB3(true);
        }
        if (spelplan.getSkatt4().getIndexFyraI() == lastMove[0] && spelplan.getSkatt4().getIndexFyraJ() == lastMove[1]) {
            spelplan.getSkatt4().setB4(true);
        }

        if (spelplan.getSkatt5().getIndexEttI() == lastMove[0] && spelplan.getSkatt5().getIndexEttJ() == lastMove[1]) {
            spelplan.getSkatt5().setB1(true);
        }
        if (spelplan.getSkatt5().getIndexTvåI() == lastMove[0] && spelplan.getSkatt5().getIndexTvåJ() == lastMove[1]) {
            spelplan.getSkatt5().setB2(true);
        }
        if (spelplan.getSkatt5().getIndexTreI() == lastMove[0] && spelplan.getSkatt5().getIndexTreJ() == lastMove[1]) {
            spelplan.getSkatt5().setB3(true);
        }
        if (spelplan.getSkatt5().getIndexFyraI() == lastMove[0] && spelplan.getSkatt5().getIndexFyraJ() == lastMove[1]) {
            spelplan.getSkatt5().setB4(true);
        }



    }
    //Uppdaterar färgen på knappen beroende på vilken sort det var
    private void updateFärgSpelplan() {
        if (spelplan.getTypeOfRuta(lastMove[0], lastMove[1]) instanceof TomRuta) {
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0],lastMove[1]).setBackground(Color.cyan);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0],lastMove[1]).setForeground(Color.cyan);
        }
        if (spelplan.getTypeOfRuta(lastMove[0], lastMove[1]) instanceof SkattRuta) {
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0],lastMove[1]).setBackground(Color.yellow);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0],lastMove[1]).setForeground(Color.yellow);
        }
        if (spelplan.getTypeOfRuta(lastMove[0], lastMove[1]) instanceof FällaRuta) {
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0],lastMove[1]).setBackground(Color.red);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0],lastMove[1]).setForeground(Color.red);
        }



    }


}
