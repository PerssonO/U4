package view;

import controller.Controller;

import javax.swing.*;
import java.io.Serializable;

public class MainFrame extends JFrame implements Serializable {
    private MainPanel mainPanel;
    private Controller controller;

    public MainFrame(int with, int hight, Controller controller){
        this.controller = controller;
        this.setResizable(false);
        this.setSize(with,hight);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainPanel = new MainPanel(with, hight - 50, this);
        this.setContentPane(mainPanel);



    }

    public void buttonPressed(ButtonType pressedButton){
        controller.buttonPressed(pressedButton);
    }




    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void skickaIndexArray(int[] indexPlatser) {
        controller.updateLastMove(indexPlatser);
    }
}


