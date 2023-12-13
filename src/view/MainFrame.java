package view;

import controller.Controller;

import javax.swing.*;

public class MainFrame extends JFrame {
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

    public void buttonPressed2(String test){
        controller.testmetod(test);
    }

}


