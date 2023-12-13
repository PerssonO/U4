package controller;


import view.*;


import Model.*;
import view.MainFrame;


public class Controller {
    private MainFrame mainframe;
    private Spelplan spelplan;

    public Controller() {
        this.mainframe = new MainFrame(1000, 550, this);
        this.spelplan = new Spelplan(); //test för att kolla att spelplanen har rätt storlek.

    }

    public void buttonPressed(ButtonType button) {

        switch (button) {
            case NyttSpel:{
            System.out.println("KNAPP NyttSpel");
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
}
