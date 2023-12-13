package controller;

import view.*;


public class Controller {
    private MainFrame mainframe;

    public Controller() {
        this.mainframe = new MainFrame(1000, 550, this);




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
