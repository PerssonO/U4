package controller;


import view.*;
import java.io.Serializable;


import Model.*;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.*;




public class Controller implements Serializable {
    private MainFrame mainframe;
    private Spelplan spelplan;

    private int[] lastMove;
    private Spelare player1;
    private Spelare player2;
    private int round = 1;
    private ArrayList<String> infoRuta;
    private ArrayList<int[]> gjordaDrag;

    private transient ArrayList<HighScore> hs;

    int fällaCounter = 1;

    public Controller() {
        this.mainframe = new MainFrame(1000, 550, this);
        this.lastMove = new int[2];
        this.infoRuta = new ArrayList<String>();
        disableAllSpelknapp();
        this.hs = new ArrayList<HighScore>();
        this.gjordaDrag = new ArrayList<int[]>();
        setupHs();
        infoRuta.add("Välkommen till världens roligaste spel");
        updateInfoRuta();
    }

    private void setupHs() {

        Scanner myReader = null;
        try {
            File myObj = new File("highscore.txt");
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String[] denna = data.split(",");
                if (denna.length == 2) {
                    String namn = denna[0];
                    int poäng = Integer.parseInt(denna[1]);
                    hs.add(new HighScore(namn, poäng));
                }

            }
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
        myReader.close();

        hs.sort(Comparator.comparingInt(HighScore::getPoäng).reversed());
    }

    public void buttonPressed(ButtonType button) {

        switch (button) {
            case NyttSpel: {
            /*
            man väljer vilken spelplan man vill starta från inforutan sen klickar man på nyttspel.
             */

                String[] valbara = new String[3];
                valbara[0] = "Spelplan 1";
                valbara[1] = "Spelplan 2";
                valbara[2] = "Slumpad spelplan";

                int val =  JOptionPane.showOptionDialog(null, "Speltyp", "Välj vilken sorts bana",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, valbara, valbara[0]);

                setupspelplan(val);


                setAllButtonsToStart();
                enableAllSpelknapp();
                player1 = new Spelare();
                player2 = new Spelare();
                mainframe.getMainPanel().getRightPanel().getBtnNyttSpel().setEnabled(false);
                round =1;
                fällaCounter =1;
                updateScore();
                updateLiv();
                updatePlayerTurn();
                gjordaDrag.clear();
                break;
            }
            case LaddaSpel: {
               laddaSpel();
               updateScore();
               updateLiv();
               updatePlayerTurn();
               enableAllSpelknapp();
               disableSpeladeKnappar(gjordaDrag);
               mainframe.getMainPanel().getRightPanel().getBtnNyttSpel().setEnabled(false);
                break;
            }
            case VisaHigh: {
                showHighscore();
                break;
            }
            case SparaSpel: {
                infoRuta.clear();
                infoRuta.add("Spelet sparades");
                updateInfoRuta();
                disableAllSpelknapp();
                sparSpel();
                break;
            }
        }
    }

    private void sparSpel() {
        try {
            FileOutputStream fos = new FileOutputStream("test.dat");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            SparaSpel ss = new SparaSpel();

            ss.sparadController = this;

            oos.writeObject(ss);
            oos.flush();
            oos.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void laddaSpel() {
        try {
            FileInputStream fis = new FileInputStream("test.dat");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            SparaSpel ss = (SparaSpel) ois.readObject();

            Controller sparadController = ss.sparadController;
            //this.mainframe = sparadController.mainframe;
            //this.mainframe = new MainFrame(1000, 550, sparadController);
            this.round = sparadController.round;
            this.infoRuta = sparadController.infoRuta;
            this.lastMove = sparadController.lastMove;
            this.player1 = sparadController.player1;
            this.player2 = sparadController.player2;
            this.spelplan = sparadController.spelplan;
            this.fällaCounter = sparadController.fällaCounter;
            this.gjordaDrag = sparadController.gjordaDrag;
            ois.close();

            updateLiv();
            updateScore();
            updateInfoRuta();
            updateSkatter();
            updatePlayerTurn();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    

    private void setAllButtonsToStart() {
        for (int i = 0; i <10; i++){
            for (int j = 0; j < 10; j++){
                mainframe.getMainPanel().getLeftPanel().getButton(i,j).setBackground(Color.lightGray);
                mainframe.getMainPanel().getLeftPanel().getButton(i,j).setBorderPainted(true);
                mainframe.getMainPanel().getLeftPanel().getButton(i,j).setOpaque(false);
            }
        }
    }

    /*
    Metod för att Lägga in rutor på en spelplan. Hårdkodad spelplan.
     */
    public void setUpSpelplan1() {
        this.spelplan = new Spelplan();

        spelplan.setSkatt1(new Skatt(60, 0, 0, 1, 0, 2, 0, 0, 0));
        spelplan.addRuta(spelplan.getSkatt1().getIndexEttI(), spelplan.getSkatt1().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTvåI(), spelplan.getSkatt1().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTreI(), spelplan.getSkatt1().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexFyraI(), spelplan.getSkatt1().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt2(new Skatt(80, 9, 9, 0, -1, -1, -1, -1, -2));
        spelplan.addRuta(spelplan.getSkatt2().getIndexEttI(), spelplan.getSkatt2().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexTvåI(), spelplan.getSkatt2().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexTreI(), spelplan.getSkatt2().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexFyraI(), spelplan.getSkatt2().getIndexFyraJ(), new SkattRuta());


        spelplan.setSkatt3(new Skatt(80, 3, 3, 0, 1, 1, 1, 2, 1));
        spelplan.addRuta(spelplan.getSkatt3().getIndexEttI(), spelplan.getSkatt3().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexTvåI(), spelplan.getSkatt3().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexTreI(), spelplan.getSkatt3().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexFyraI(), spelplan.getSkatt3().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt4(new Skatt(40, 0, 8, 0, 1, 0, 0, 0, 0));
        spelplan.addRuta(spelplan.getSkatt4().getIndexEttI(), spelplan.getSkatt4().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexTvåI(), spelplan.getSkatt4().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexTreI(), spelplan.getSkatt4().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexFyraI(), spelplan.getSkatt4().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt5(new Skatt(80, 7, 0, 1, 0, 1, 1, 2, 1));
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

        /*
        for (int i = 0; i < spelplan.getSpelplan().length; i++) {
            for (int j = 0; j < spelplan.getSpelplan()[i].length; j++) {
                System.out.print(spelplan.getSpelplan()[i][j] + " ");
            }
            System.out.println();
        }
         */

    }

    /*
    En spelplan till.
     */
    public void setUpSpelplan2() {
        this.spelplan = new Spelplan();

        spelplan.setSkatt1(new Skatt(60, 3, 0, 1, 0, 2, 0, 0, 0));
        spelplan.addRuta(spelplan.getSkatt1().getIndexEttI(), spelplan.getSkatt1().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTvåI(), spelplan.getSkatt1().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTreI(), spelplan.getSkatt1().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexFyraI(), spelplan.getSkatt1().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt2(new Skatt(80, 9, 5, 0, -1, -1, -1, -1, -2));
        spelplan.addRuta(spelplan.getSkatt2().getIndexEttI(), spelplan.getSkatt2().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexTvåI(), spelplan.getSkatt2().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexTreI(), spelplan.getSkatt2().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt2().getIndexFyraI(), spelplan.getSkatt2().getIndexFyraJ(), new SkattRuta());


        spelplan.setSkatt3(new Skatt(80, 3, 3, 0, 1, 1, 1, 2, 1));
        spelplan.addRuta(spelplan.getSkatt3().getIndexEttI(), spelplan.getSkatt3().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexTvåI(), spelplan.getSkatt3().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexTreI(), spelplan.getSkatt3().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt3().getIndexFyraI(), spelplan.getSkatt3().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt4(new Skatt(80, 0, 3, 0, 1, 1, 1, 1, 0));
        spelplan.addRuta(spelplan.getSkatt4().getIndexEttI(), spelplan.getSkatt4().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexTvåI(), spelplan.getSkatt4().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexTreI(), spelplan.getSkatt4().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt4().getIndexFyraI(), spelplan.getSkatt4().getIndexFyraJ(), new SkattRuta());

        spelplan.setSkatt5(new Skatt(40, 4, 9, 0, -1, 0, 0, 0, 0));
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

        /*
        for (int i = 0; i < spelplan.getSpelplan().length; i++) {
            for (int j = 0; j < spelplan.getSpelplan()[i].length; j++) {
                System.out.print(spelplan.getSpelplan()[i][j] + " ");
            }
            System.out.println();
        }

         */
    }

    /*
    Metod för att slumpa spelplant. När man lägger till skatt nr5 bråkar den ibland.
     */
    public void NySlumpad() {
        this.spelplan = new Spelplan();
        SecureRandom slump = new SecureRandom();
        int slump1 = slump.nextInt(0, 7);
        int slump2 = slump.nextInt(0, 10);

        spelplan.setSkatt1(new Skatt(60, slump1, slump2, 1, 0, 2, 0, 0, 0));
        spelplan.addRuta(spelplan.getSkatt1().getIndexEttI(), spelplan.getSkatt1().getIndexEttJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTvåI(), spelplan.getSkatt1().getIndexTvåJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexTreI(), spelplan.getSkatt1().getIndexTreJ(), new SkattRuta());
        spelplan.addRuta(spelplan.getSkatt1().getIndexFyraI(), spelplan.getSkatt1().getIndexFyraJ(), new SkattRuta());

        testFyllgrannar();


        boolean lagttill = false;
        do {
            slump1 = slump.nextInt(1, 9);
            slump2 = slump.nextInt(2, 9);

            spelplan.setSkatt2(new Skatt(80, slump1, slump2, 0, -1, -1, -1, -1, -2));
            if (spelplan.getRuta(spelplan.getSkatt2().getIndexEttI(),spelplan.getSkatt2().getIndexEttJ()) == null &&
                    spelplan.getRuta(spelplan.getSkatt2().getIndexTvåI(),spelplan.getSkatt2().getIndexTvåJ()) == null &&
                    spelplan.getRuta(spelplan.getSkatt2().getIndexTreI(),spelplan.getSkatt2().getIndexTreJ()) == null &&
                    spelplan.getRuta(spelplan.getSkatt2().getIndexFyraI(),spelplan.getSkatt2().getIndexFyraJ()) == null)
            {
                spelplan.addRuta(spelplan.getSkatt2().getIndexEttI(), spelplan.getSkatt2().getIndexEttJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt2().getIndexTvåI(), spelplan.getSkatt2().getIndexTvåJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt2().getIndexTreI(), spelplan.getSkatt2().getIndexTreJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt2().getIndexFyraI(), spelplan.getSkatt2().getIndexFyraJ(), new SkattRuta());
                lagttill = true;

            }

        }
        while (!lagttill);

        testFyllgrannar();


        lagttill = false;
        do {
            slump1 = slump.nextInt(2, 9);
            slump2 = slump.nextInt(2, 9);
            spelplan.setSkatt3(new Skatt(80, slump1, slump2, 0, -1, -1, -1, -2, -1));

            if (spelplan.getRuta(spelplan.getSkatt3().getIndexEttI(),spelplan.getSkatt3().getIndexEttJ()) == null &&
                    spelplan.getRuta(spelplan.getSkatt3().getIndexTvåI(),spelplan.getSkatt3().getIndexTvåJ()) == null &&
                    spelplan.getRuta(spelplan.getSkatt3().getIndexTreI(),spelplan.getSkatt3().getIndexTreJ()) == null &&
                    spelplan.getRuta(spelplan.getSkatt3().getIndexFyraI(),spelplan.getSkatt3().getIndexFyraJ()) == null)
            {
                spelplan.addRuta(spelplan.getSkatt3().getIndexEttI(), spelplan.getSkatt3().getIndexEttJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt3().getIndexTvåI(), spelplan.getSkatt3().getIndexTvåJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt3().getIndexTreI(), spelplan.getSkatt3().getIndexTreJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt3().getIndexFyraI(), spelplan.getSkatt3().getIndexFyraJ(), new SkattRuta());
                lagttill = true;

            }

        }
        while (!lagttill);
        testFyllgrannar();


        lagttill = false;
        do {
            slump1 = slump.nextInt(1, 9);
            slump2 = slump.nextInt(2, 9);
            spelplan.setSkatt4(new Skatt(80, slump1, slump2, 0, 1, 1, 1, 1, 0));


            if (spelplan.getRuta(spelplan.getSkatt4().getIndexEttI(),spelplan.getSkatt4().getIndexEttJ()) == null &&
                    spelplan.getRuta(spelplan.getSkatt4().getIndexTvåI(),spelplan.getSkatt4 ().getIndexTvåJ()) == null &&
                    spelplan.getRuta(spelplan.getSkatt4().getIndexTreI(),spelplan.getSkatt4().getIndexTreJ()) == null &&
                    spelplan.getRuta(spelplan.getSkatt4().getIndexFyraI(),spelplan.getSkatt4().getIndexFyraJ()) == null)
            {
                spelplan.addRuta(spelplan.getSkatt4().getIndexEttI(), spelplan.getSkatt4().getIndexEttJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt4().getIndexTvåI(), spelplan.getSkatt4().getIndexTvåJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt4().getIndexTreI(), spelplan.getSkatt4().getIndexTreJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt4().getIndexFyraI(), spelplan.getSkatt4().getIndexFyraJ(), new SkattRuta());
                lagttill = true;


            }

        }
        while (!lagttill);
        testFyllgrannar();




        lagttill = false;
        do {
            slump1 = slump.nextInt(0, 10);
            slump2 = slump.nextInt(3, 10);
            spelplan.setSkatt5(new Skatt(40, slump1, slump2, 0, -1, 0, 0, 0, 0));

            if (spelplan.getRuta(spelplan.getSkatt5().getIndexEttI(),spelplan.getSkatt5().getIndexEttJ()) == null &&
                    spelplan.getRuta(spelplan.getSkatt5().getIndexTvåI(),spelplan.getSkatt5 ().getIndexTvåJ()) == null &&
                    spelplan.getRuta(spelplan.getSkatt5().getIndexTreI(),spelplan.getSkatt5().getIndexTreJ()) == null &&
                    spelplan.getRuta(spelplan.getSkatt5().getIndexFyraI(),spelplan.getSkatt5().getIndexFyraJ()) == null)
            {
                spelplan.addRuta(spelplan.getSkatt5().getIndexEttI(), spelplan.getSkatt5().getIndexEttJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt5().getIndexTvåI(), spelplan.getSkatt5().getIndexTvåJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt5().getIndexTreI(), spelplan.getSkatt5().getIndexTreJ(), new SkattRuta());
                spelplan.addRuta(spelplan.getSkatt5().getIndexFyraI(), spelplan.getSkatt5().getIndexFyraJ(), new SkattRuta());
                lagttill = true;


            }

        }
        while (!lagttill);

        lagttill = false;
        do {
            slump1 = slump.nextInt(10);
            slump2 = slump.nextInt(10);
            if (spelplan.getRuta(slump1, slump2) != null) {

            } else {
                spelplan.addRuta(slump1, slump2, new FällaRuta());
                lagttill = true;
            }
        } while (!lagttill);

        lagttill = false;
        do {
            slump1 = slump.nextInt(10);
            slump2 = slump.nextInt(10);
            if (spelplan.getRuta(slump1, slump2) != null) {

            } else {
                spelplan.addRuta(slump1, slump2, new FällaRuta());
                lagttill = true;
            }
        } while (!lagttill);


        lagttill = false;
        do {
            slump1 = slump.nextInt(10);
            slump2 = slump.nextInt(10);
            if (spelplan.getRuta(slump1, slump2) != null) {

            } else {
                spelplan.addRuta(slump1, slump2, new FällaRuta());
                lagttill = true;
            }
        } while (!lagttill);


        for (int i = 0; i < spelplan.getSpelplan().length; i++) {
            for (int j = 0; j < spelplan.getSpelplan()[i].length; j++) {
                if (spelplan.getSpelplan()[i][j] == null) {
                    spelplan.addRuta(i, j, new TomRuta());
                }
            }
        }

        /*
        for (int i = 0; i < spelplan.getSpelplan().length; i++) {
            for (int j = 0; j < spelplan.getSpelplan()[i].length; j++) {
                System.out.print(spelplan.getSpelplan()[i][j] + " ");
            }
            System.out.println();
        }

         */
    }

    public void testFyllgrannar() {
        for (int i = 0; i < spelplan.getSpelplan().length; i++) {
            for (int j = 0; j < spelplan.getSpelplan().length; j++) {
                if (spelplan.getRuta(i, j) instanceof SkattRuta) {
                    try {
                        if ((i - 1 > -1) && spelplan.getRuta(i - 1, j) == null) {
                            spelplan.addRuta(i - 1, j, new TomRuta());
                        }
                        if ((i + 1 < 10) && spelplan.getRuta(i + 1, j) == null) {
                            spelplan.addRuta(i + 1, j, new TomRuta());
                        }
                        if ((j + 1 < 10) && spelplan.getRuta(i, j + 1) == null) {
                            spelplan.addRuta(i, j + 1, new TomRuta());
                        }
                        if ((j - 1 > -1) && spelplan.getRuta(i, j - 1) == null) {
                            spelplan.addRuta(i, j - 1, new TomRuta());
                        }

                    } catch (Exception e) {
                        System.out.println("FEL");
                        //e.printStackTrace();
                    }
                }
            }
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

    /*
    kollar så att man inte försöker lägga in en skatt på en ruta som redan är en skattruta.
     */
    public boolean checkRutaförSkatt(Skatt skatt) {
        if (spelplan.getRuta(spelplan.getSkatt(skatt).getIndexEttI(), spelplan.getSkatt(skatt).getIndexEttJ()) instanceof SkattRuta
                || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTvåI(), spelplan.getSkatt(skatt).getIndexTvåJ()) instanceof SkattRuta
                || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTreI(), spelplan.getSkatt(skatt).getIndexTreJ()) instanceof SkattRuta
                || spelplan.getRuta(spelplan.getSkatt(skatt).getIndexFyraI(), spelplan.getSkatt(skatt).getIndexFyraJ()) instanceof SkattRuta) {
            return false;
        } else {
            return true;
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
        gjordaDrag.add(lastMove);
        newRound();

    }

    //Metoden som körs efter någon har tryckt på en spelknapp och lastmove har uppdaterats
    private void newRound() {
        infoRuta.clear();
        disableButton();
        updateFärgSpelplan();
        updateSkatter();
        checkIfFälla();
        checkIfLastSkattruta();
        updateScore();
        updateLiv();
        checkIfAllaSkatterHittade();
        checkIfDead();
        updateInfoRuta();
        round++;
        updatePlayerTurn();


    }

    private void checkIfDead() {
        if (player1.getLiv() == 0) {
            infoRuta.add("Spelare 1 förlorade sitt sista liv och har förlorat");
            gameOver();
        }
        if (player2.getLiv() == 0) {
            infoRuta.add("Spelare 2 förlorade sitt sista liv och har förlorat");
            gameOver();
        }
    }

    private void updateLiv() {
        mainframe.getMainPanel().getRightPanel().getPlayerLiv1().setText("Spelare 1 liv: " + player1.getLiv());
        mainframe.getMainPanel().getRightPanel().getPlayerLiv2().setText("Spelare 2 liv: " + player2.getLiv());
    }

    private void updateInfoRuta() {
        mainframe.getMainPanel().getRightPanel().getInfoFönster().setListData(infoRuta.toArray());
    }

    private void updatePlayerTurn() {
        if (round % 2 == 0) {
            mainframe.getMainPanel().getRightPanel().getCurrentPlayer().setText("Tur att trycka: Spelare 2");
        } else {
            mainframe.getMainPanel().getRightPanel().getCurrentPlayer().setText("Tur att trycka: Spelare 1");
        }
    }

    private void updateScore() {
        mainframe.getMainPanel().getRightPanel().getPlayer1().setText("Spelare 1 poäng: " + player1.getScore());
        mainframe.getMainPanel().getRightPanel().getPlayer2().setText("Spelare 2 poäng: " + player2.getScore());
    }

    private void printPlayerTurn() {
        System.out.println("-----------------------------------");
        if (round % 2 == 0) {
            System.out.println("Spelare 2 tur");
        } else {
            System.out.println("Spelare 1 tur");
        }
    }

    private void printScore() {
        System.out.println("Player 1 " + player1.getScore());
        System.out.println("Player 2 " + player2.getScore());
    }

    //Kollar om alla skatter är hittade och spelet är slut
    private void checkIfAllaSkatterHittade() {
        if (spelplan.getSkatt1().isAllaHittade() &&
                spelplan.getSkatt2().isAllaHittade() &&
                spelplan.getSkatt3().isAllaHittade() &&
                spelplan.getSkatt4().isAllaHittade() &&
                spelplan.getSkatt5().isAllaHittade()) {
            infoRuta.add("Alla skatter är hittade och spelet är slut");
            gameOver();

        }

    }

    private void gameOver() {
        disableAllSpelknapp();
        if (player1.getLiv() == 0) {
            JOptionPane.showMessageDialog(null, "Spelare2 vann matchen med " + player2.getScore() + " men poäng");
            if (hs.size() == 10 && hs.get(9).getPoäng() < player2.getScore()){
                deleteAndWriteHighscore(player2);
            }
            else {
                writeToHighscoreIfLessThan10(player2);
            }
        } else if (player2.getLiv() == 0) {
            JOptionPane.showMessageDialog(null, "Spelare1 vann matchen med " + player1.getScore() + " men poäng");
            if (hs.size() == 10 && hs.get(9).getPoäng() < player2.getScore()){
                deleteAndWriteHighscore(player2);
                System.out.println("nej");
            }
            else {
                writeToHighscoreIfLessThan10(player2);
            }
        } else if (player1.getScore() > player2.getScore()) {
            JOptionPane.showMessageDialog(null, "Spelare1 vann matchen med " + player1.getScore() + " men poäng");
            if (hs.size() == 10 && hs.get(9).getPoäng() < player1.getScore()){
                deleteAndWriteHighscore(player1);
            }
            else {
                writeToHighscoreIfLessThan10(player1);
            }
        } else if (player2.getScore() > player1.getScore()) {
            JOptionPane.showMessageDialog(null, "Spelare2 vann matchen med " + player2.getScore() + " men poäng");
            if (hs.size() == 10 && hs.get(9).getPoäng() < player2.getScore()){
                deleteAndWriteHighscore(player2);
            }
            else {
                writeToHighscoreIfLessThan10(player2);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Matchen slutade lika");

        }


        mainframe.getMainPanel().getRightPanel().getBtnNyttSpel().setEnabled(true);
    }

    //Stänger av en knapp. Används så man inte kan trycka på samma spelknapp två gånger
    private void disableButton() {
        mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setEnabled(false);
    }

    //Kollar om alla rutor i en skatt är hittade så att poäng kan delas ut
    private void checkIfLastSkattruta() {
        if (spelplan.getSkatt1().isB1() && spelplan.getSkatt1().isB2() && spelplan.getSkatt1().isB3() && spelplan.getSkatt1().isB4()) {
            spelplan.getSkatt1().setB1(false);
            spelplan.getSkatt1().setAllaHittade(true);
            gePoäng(1);
        }
        if (spelplan.getSkatt2().isB1() && spelplan.getSkatt2().isB2() && spelplan.getSkatt2().isB3() && spelplan.getSkatt2().isB4()) {
            spelplan.getSkatt2().setB1(false);
            spelplan.getSkatt2().setAllaHittade(true);
            gePoäng(2);
        }
        if (spelplan.getSkatt3().isB1() && spelplan.getSkatt3().isB2() && spelplan.getSkatt3().isB3() && spelplan.getSkatt3().isB4()) {
            spelplan.getSkatt3().setB1(false);
            spelplan.getSkatt3().setAllaHittade(true);
            gePoäng(3);
        }
        if (spelplan.getSkatt4().isB1() && spelplan.getSkatt4().isB2() && spelplan.getSkatt4().isB3() && spelplan.getSkatt4().isB4()) {
            spelplan.getSkatt4().setB1(false);
            spelplan.getSkatt4().setAllaHittade(true);
            gePoäng(4);
        }
        if (spelplan.getSkatt5().isB1() && spelplan.getSkatt5().isB2() && spelplan.getSkatt5().isB3() && spelplan.getSkatt5().isB4()) {
            spelplan.getSkatt5().setB1(false);
            spelplan.getSkatt5().setAllaHittade(true);
            gePoäng(5);
        }


    }

    //Metoden delar ut poängen och uppdaterar infoRuta med vilken poäng man fick
    private void gePoäng(int i) {
        int poäng = 0;
        if (i == 1) {
            poäng = spelplan.getSkatt1().getPoäng();
            infoRuta.add("Du grävde upp hela skatten och fick " + poäng + " poäng");
        }
        if (i == 2) {
            poäng = spelplan.getSkatt2().getPoäng();
            infoRuta.add("Du grävde upp hela skatten och fick " + poäng + " poäng");
        }
        if (i == 3) {
            poäng = spelplan.getSkatt3().getPoäng();
            infoRuta.add("Du grävde upp hela skatten och fick " + poäng + " poäng");
        }
        if (i == 4) {
            poäng = spelplan.getSkatt4().getPoäng();
            infoRuta.add("Du grävde upp hela skatten och fick " + poäng + " poäng");
        }
        if (i == 5) {
            poäng = spelplan.getSkatt5().getPoäng();
            infoRuta.add("Du grävde upp hela skatten och fick " + poäng + " poäng");
        }

        if (round % 2 == 0) {
            player2.setScore(player2.getScore() + poäng);
        }
        if (round % 2 != 0) {
            player1.setScore(player1.getScore() + poäng);
        }

    }

    //Metoden kollar om platsen är en fälla
    private void checkIfFälla() {
        if (spelplan.getTypeOfRuta(lastMove[0], lastMove[1]) instanceof FällaRuta) {
            gickIFälla();
        }
    }

    private void gickIFälla() {
        if (fällaCounter % 2 != 0) {
            if (round % 2 != 0) {
                player1.setLiv(player1.getLiv() - 1);
            } else {
                player2.setLiv(player2.getLiv() - 1);
            }
            infoRuta.add("Du förlorade ett liv ");
        } else {
            SecureRandom minslump = new SecureRandom();
            int slumpadPoäng = minslump.nextInt(10, 100);

            if (round % 2 != 0) {
                player1.setScore(player1.getScore() - slumpadPoäng);
            } else {
                player2.setScore(player2.getScore() - slumpadPoäng);
            }
            infoRuta.add("Du förlorade " + slumpadPoäng + " Poäng");


        }


        fällaCounter++;


    }

    //Kollar med alla skatter om draget hamnade på en av deras indexplatser. Om rätt ändrar boolean till att den är hittad
    private void updateSkatter() {
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

    //Uppdaterar färgen på knappen beroende på vilken sort det var OBS Lägger också till info till textrutan
    private void updateFärgSpelplan() {
        if (spelplan.getTypeOfRuta(lastMove[0], lastMove[1]) instanceof TomRuta) {
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBackground(Color.black);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setOpaque(true);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBorderPainted(true);
           //mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBorderPainted(false);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setForeground(Color.black);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setOpaque(true);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBorderPainted(true);
            //mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBorderPainted(false);
            infoRuta.add("Du tryckte på en tom ruta");
        }
        if (spelplan.getTypeOfRuta(lastMove[0], lastMove[1]) instanceof SkattRuta) {
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBackground(Color.yellow);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setOpaque(true);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBorderPainted(true);
           // mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBorderPainted(false);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setForeground(Color.yellow);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setOpaque(true);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBorderPainted(true);
            //mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBorderPainted(false);
            infoRuta.add("Grattis du tryckte på en skattruta");
        }
        if (spelplan.getTypeOfRuta(lastMove[0], lastMove[1]) instanceof FällaRuta) {
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBackground(Color.red);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setOpaque(true);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBorderPainted(true);
           // mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBorderPainted(false);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setForeground(Color.red);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setOpaque(true);
            mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBorderPainted(true);
            //mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setBorderPainted(false);
            infoRuta.add("Tyvärr du tryckte på en fälla");
        }



    }

    /*
    metod som används för att välja vilken spelplan man vill använda.
     */
    public void setupspelplan(int selectionindex) {
        switch (selectionindex) {
            case 0:
                setUpSpelplan1();
                break;
            case 1:
                setUpSpelplan2();
                break;
            case 2:
                NySlumpad();
                break;
        }
    }

    public static void createHighScore() {
        try {
            File highscore = new File("HighScore.txt");
            if (highscore.createNewFile()) {
                System.out.println("File created: " + highscore.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writehighscore(ArrayList<HighScore> test) {
        try {
            FileWriter myWriter = new FileWriter("highscore.txt");
            for (int i = 0; i < test.size(); i++) {
                String test1 = test.get(i).toString();
                myWriter.write(test1 + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void deleteAndWriteHighscore(Spelare spelare) {
        hs.remove(9);
        String namn = JOptionPane.showInputDialog("ange namn: ");
        hs.add(new HighScore(namn, spelare.getScore()));

        writehighscore(hs);
    }

    public void writeToHighscoreIfLessThan10(Spelare spelare) {
        if (hs.size() < 10) {
            System.out.println("Du kom med på highscorelistan");
            String namn = JOptionPane.showInputDialog("ange namn: ");
            hs.add(new HighScore(namn, spelare.getScore()));
            writehighscore(hs);
        }
    }

    private void showHighscore() {
        hs.sort(Comparator.comparingInt(HighScore::getPoäng).reversed());
        mainframe.getMainPanel().getRightPanel().getInfoFönster().setListData(hs.toArray());
    }

    public MainFrame getMainframe() {
        return mainframe;
    }

    public void setMainframe(MainFrame mainframe) {
        this.mainframe = mainframe;
    }

    public Spelplan getSpelplan() {
        return spelplan;
    }

    public void setSpelplan(Spelplan spelplan) {
        this.spelplan = spelplan;
    }

    public int[] getLastMove() {
        return lastMove;
    }

    public void setLastMove(int[] lastMove) {
        this.lastMove = lastMove;
    }

    public Spelare getPlayer1() {
        return player1;
    }

    public void setPlayer1(Spelare player1) {
        this.player1 = player1;
    }

    public Spelare getPlayer2() {
        return player2;
    }

    public void setPlayer2(Spelare player2) {
        this.player2 = player2;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public ArrayList<String> getInfoRuta() {
        return infoRuta;
    }

    public void setInfoRuta(ArrayList<String> infoRuta) {
        this.infoRuta = infoRuta;
    }

    public int getFällaCounter() {
        return fällaCounter;
    }

    public void setFällaCounter(int fällaCounter) {
        this.fällaCounter = fällaCounter;
    }

    public void disableSpeladeKnappar(ArrayList<int[]> gjordaDrag){

        for(int i=0; i<gjordaDrag.size(); i++){
            for (int j =0; j < lastMove.length; j++){
                lastMove=gjordaDrag.get(i);
                mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0],lastMove[1]).setEnabled(false);
                updateFärgSpelplan();
            }
        }

    }

}
