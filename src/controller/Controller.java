package controller;
import Model.*;
import view.MainFrame;

public class Controller {
    private MainFrame mainframe;
    private Spelplan spelplan;

    public Controller() {
        this.mainframe = new MainFrame(1000, 550, this);
        this.spelplan = new Spelplan(); //test för att kolla att spelplanen har rätt storlek.

    }
}
