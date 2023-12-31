package controller;

import Model.*;
import view.ButtonType;
import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Controller klass som sköter logiken i spelet.
 * @author Ola Persson , Jonatan Tempel
 */



public class Controller implements Serializable {
    private final MainFrame mainframe;
    private Spelplan spelplan;

    private int[] lastMove;
    private Spelare player1;
    private Spelare player2;
    private int round = 1;
    private ArrayList<String> infoRuta;
    private ArrayList<int[]> gjordaDrag;

    private final transient ArrayList<HighScore> hs;

    int fällaCounter = 1;

    /**
     * Konstruktor för controllern. Körs från main när programet startas
     * @author Ola Persson Jonatan Tempel
     */
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

    /**
     * Metoden körs när spelet startas. Den läser in highscore listan från en txt fil och lägger till
     * värden i hs. Sen sorteras hs.
     * @author Ola Persson Jonatan Tempel
     */
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

    /**
     * Metoden tar in ett tryck från knapparna i programet
     * @param button vilken knapp trycks
     * @author Ola Persson Jonatan Tempel
     */
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

    /**
     * Metoden sparar spelet ner till filen test.dat
     * @author Ola Persson Jonatan Tempel
     */
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

    /**
     * Metoden laddar in det sparade spelet. Från test.dat.
     * @author Ola Persson Jonatan Tempel
     */
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

    /**
     * Metoden sätter all spelrutor till startvärdena vid start av nytt spel
     * @author Ola Persson Jonatan Tempel
     */
    private void setAllButtonsToStart() {
        for (int i = 0; i <10; i++){
            for (int j = 0; j < 10; j++){
                mainframe.getMainPanel().getLeftPanel().getButton(i,j).setBackground(Color.lightGray);
                mainframe.getMainPanel().getLeftPanel().getButton(i,j).setBorderPainted(true);
                mainframe.getMainPanel().getLeftPanel().getButton(i,j).setOpaque(false);
            }
        }
    }

    /**
     * Metod som används för att skapa spelplan1 vilket är en hårdkodad spelplan
     * @author Jonatan Tempel , Ola Persson
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



    }

    /**
     * Metod som används för att skapa spelplan2 vilket är en hårdkodad spelplan
     * @author Jonatan Tempel , Ola Persson
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


    }

    /**
     * Metod som används för att slumpa fram en spelplan
     * @author Ola Persson , Jonatan Tempel
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
            if ((spelplan.getRuta(slump1, slump2) == null) || (spelplan.getRuta(slump1, slump2) instanceof TomRuta )){
                spelplan.addRuta(slump1, slump2, new FällaRuta());
                lagttill = true;
            }
        } while (!lagttill);

        lagttill = false;
        do {
            slump1 = slump.nextInt(10);
            slump2 = slump.nextInt(10);
            if ((spelplan.getRuta(slump1, slump2) == null) || (spelplan.getRuta(slump1, slump2) instanceof TomRuta )){
                spelplan.addRuta(slump1, slump2, new FällaRuta());
                lagttill = true;
            }
        } while (!lagttill);


        lagttill = false;
        do {
            slump1 = slump.nextInt(10);
            slump2 = slump.nextInt(10);
            if ((spelplan.getRuta(slump1, slump2) == null) || (spelplan.getRuta(slump1, slump2) instanceof TomRuta )){
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


    }

    /**
     * Metoden används i metoden som slumpar fram en spelplan. Metoden fyller platserna runt en skatt
     * så att ny skatter inte kan placeras direkt brevid en skatt.
     * @author Ola Persson Jonatgan Tempel
     * */
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

    /**
     * Metod som gör att alla spelknappar blir disabled.
     * @author Jonatan Tempel
     */
    public void disableAllSpelknapp() {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                mainframe.getMainPanel().getLeftPanel().getButton(i, j).setEnabled(false);
            }
        }
    }

    /**
     * Metod som används för att kolla om det ligger en skatt på en ruta
     * @param skatt man anger den skatten man vill kontrollera
     * @return boolean som antingen är true eller false beroende på om det finns en skatt eller inte på rutan
     * @author Jonatan Tempel
     */
    public boolean checkRutaförSkatt(Skatt skatt) {
        return !(spelplan.getRuta(spelplan.getSkatt(skatt).getIndexEttI(), spelplan.getSkatt(skatt).getIndexEttJ()) instanceof SkattRuta)
                && !(spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTvåI(), spelplan.getSkatt(skatt).getIndexTvåJ()) instanceof SkattRuta)
                && !(spelplan.getRuta(spelplan.getSkatt(skatt).getIndexTreI(), spelplan.getSkatt(skatt).getIndexTreJ()) instanceof SkattRuta)
                && !(spelplan.getRuta(spelplan.getSkatt(skatt).getIndexFyraI(), spelplan.getSkatt(skatt).getIndexFyraJ()) instanceof SkattRuta);
    }

    /**
     * Metod som gör alla spelknappar enabled
     * @author Jonatan Tempel
     */
    public void enableAllSpelknapp() {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                mainframe.getMainPanel().getLeftPanel().getButton(i, j).setEnabled(true);
            }
        }

    }

    //Metoden tar emot värdet från knappen på spelplanen och uppdaterar last move

    /**
     * Metoden tar in en int array med två värden vilket är indexplatserna på den spelknapp
     * spelaren tryckt på. Sen uppdaterar den lastMove samt lägger in det i gjorda drag
     * @param indexPlatser int[] med indexplatserna för senaste gjorda drag
     * @author Ola Persson Jonatan Tempel
     */
    public void updateLastMove(int[] indexPlatser) {
        lastMove = indexPlatser;
        gjordaDrag.add(lastMove);
        newRound();

    }

    /**
     * Metoden som körs efter någon har tryckt på en spelknapp och lastmove har uppdaterats
     * Samlar ihop alla metoder som måste köras efter ett tryck på spelknapp.
     * @Author Ola Persson Jonatan Tempel
     *
     */

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

    /**
     * Metoden körs efter varje runda och kontrolerar om någon spelare
     * har förlorat alla liv. Om det skett körs metoden game over
     * @author Ola Persson
     */
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

    /**
     * Metoden uppdaterar panelen som visar antalet liv för varje spelare
     * @author Ola Persson
     */
    private void updateLiv() {
        mainframe.getMainPanel().getRightPanel().getPlayerLiv1().setText("Spelare 1 liv: " + player1.getLiv());
        mainframe.getMainPanel().getRightPanel().getPlayerLiv2().setText("Spelare 2 liv: " + player2.getLiv());
    }
    /**
     * Metoden uppdaterar panelen som visar inforutan
     * @author Ola Persson
     */
    private void updateInfoRuta() {
        mainframe.getMainPanel().getRightPanel().getInfoFönster().setListData(infoRuta.toArray());
    }
    /**
     * Metoden uppdaterar rutan som visar vilken spelares tur det är
     * @author Ola Persson
     */
    private void updatePlayerTurn() {
        if (round % 2 == 0) {
            mainframe.getMainPanel().getRightPanel().getCurrentPlayer().setText("Tur att trycka: Spelare 2");
        } else {
            mainframe.getMainPanel().getRightPanel().getCurrentPlayer().setText("Tur att trycka: Spelare 1");
        }
    }
    /**
     * Metoden uppdaterar rutan som visar spelarnas poäng
     * @author Ola Persson
     */
    private void updateScore() {
        mainframe.getMainPanel().getRightPanel().getPlayer1().setText("Spelare 1 poäng: " + player1.getScore());
        mainframe.getMainPanel().getRightPanel().getPlayer2().setText("Spelare 2 poäng: " + player2.getScore());
    }



    /**
     * Metoden kontrolerar om alla skattrutor är hittade och i så fall avslutas spelet
     * @author Ola Persson
     */
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

    /**
     * Metod som används när spelet är slut. Används för att få reda på vilken spelare som vann matchen och om deras poäng är tillräckligt
     * hög för att skrivas in på highscore listan
     * @author Jonatan Tempel , Ola Persson
     */
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
            if (hs.size() == 10 && hs.get(9).getPoäng() < player1.getScore()){
                deleteAndWriteHighscore(player1);
            }
            else {
                writeToHighscoreIfLessThan10(player1);
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

    /**
     * Metoden disablar den spelruta som tryckts på så att det inte går att trycka på samma ruta två
     * gånger i samma spel
     * @author Ola Persson
     */
    private void disableButton() {
        mainframe.getMainPanel().getLeftPanel().getButton(lastMove[0], lastMove[1]).setEnabled(false);
    }
    /**
     * Kollar om alla rutor i en skatt är hittade så att poäng kan delas ut
     * kan det delas ut körs metoden gePoäng
     * @author Ola Persson
     */
    //
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
    /**
     * Metoden delar ut poängen och uppdaterar infoRuta med vilken poäng man fick
     *
     * @author Ola Persson
     */

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

    /**
     * Metoden kollar om platsen är en fälla
     * @author Ola Persson
     */
    private void checkIfFälla() {
        if (spelplan.getTypeOfRuta(lastMove[0], lastMove[1]) instanceof FällaRuta) {
            gickIFälla();
        }
    }
    /**
     * Metoden körs om en splare gått i fälla. Ojämna fällor ger minus liv och jämna minus
     * en slumpad poäng
     * @author Ola Persson
     */
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
    /**
     * Kollar med alla skatter om draget hamnade på en av deras indexplatser.
     * Om rätt ändrar boolean till att den är hittad
     * @author Ola Persson
     */

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
    /**
     * Uppdaterar färgen på knappen beroende på vilken sort det var
     * Lägger också till info till textrutan
     * @author Ola Persson
     */

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

    /**
     * Metod som används för att skapa en spelplan beroende på vilket val användaren gör.
     * @param selectionindex int som användaren väljer för att skapa ett nytt spel
     * @author Jonatan Tempel
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

    /**
     * Metod som skapar en textfil med namnet HighScore.
     * @author Jonatan Tempel
     */
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

    /**
     * Metod som används för att skriva en värdena som finns i en arraylist till highscore.txt filen
     * @param hs arraylist med värden som ska skrivas in i highscore textfilen
     * @author Jonatan Tempel
     */
    public void writehighscore(ArrayList<HighScore> hs) {
        try {
            FileWriter myWriter = new FileWriter("highscore.txt");
            for (int i = 0; i < hs.size(); i++) {
                String test1 = hs.get(i).toString();
                myWriter.write(test1 + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Metod som tar bort den lägsta poängen från highscore arrayListan och lägger till den spelaren som finns i parametern
     * @param spelare vilken spelare som ska skrivas in på highscore listan
     * @author Jonatan Tempel
     */
    public void deleteAndWriteHighscore(Spelare spelare) {
        hs.remove(9);
        String namn = JOptionPane.showInputDialog("Du kom med på highscorelistan. \nAnge namn: ");
        if (namn == null){
            namn = "Anonym";
        }
        hs.add(new HighScore(namn, spelare.getScore()));

        writehighscore(hs);
    }

    /**
     * Metod som kollar om highscore arraylistan är mindre än 10 och skriver in den aktuella spelaren på highscorelistan
     * @param spelare den aktuella spelaren
     * @author Jonatan Tempel
     */
    public void writeToHighscoreIfLessThan10(Spelare spelare) {
        if (hs.size() < 10) {
            String namn = JOptionPane.showInputDialog("Du kom med på highscorelistan. \nAnge namn: ");
            if (namn == null){
                namn = "Anonym";
            }
            hs.add(new HighScore(namn, spelare.getScore()));
            writehighscore(hs);
        }
    }

    /**
     * Metod som visar highscorelistan i GUI
     * @author Jonatan Tempel
     */
    private void showHighscore() {
        hs.sort(Comparator.comparingInt(HighScore::getPoäng).reversed());
        mainframe.getMainPanel().getRightPanel().getInfoFönster().setListData(hs.toArray());
    }



    /**
     * Metod som används för att göra de spelknappar som har används blir disabled när man laddar ett spel
     * @param gjordaDrag arraylista som innehåller de rutor som man grävt på under spelets gång
     * @author Jonatan Tempel
     */
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
