package view;

import javax.swing.*;
import java.awt.*;

public class LeftPanel extends JPanel {
    private MainFrame mainframe;
    private int with;
    private int height;
    private JButton knapp;

    public LeftPanel(MainFrame mainframe, int with, int height) {
        this.mainframe =mainframe;
        this.with = with;
        this.height = height;
        this.setSize(with, height);
        setLocation(0,0);
        this.setLayout(new GridLayout(10,10));
        setBackground(Color.cyan);
        setup();

        
        


    }

    private void setup() {
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++) {
                knapp = new JButton(String.valueOf(i) + String.valueOf(j));
                knapp.setEnabled(true);
                knapp.setSize(50, 50);
                this.add(knapp);
            }
        }

    }
}
