package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LeftPanel extends JPanel {
    private MainFrame mainframe;
    private int with;
    private int height;
    private JButton knapp;
    private JButton[][] array;

   // private ArrayList <A>



    public LeftPanel(MainFrame mainframe, int with, int height) {
        this.mainframe =mainframe;
        this.with = with;
        this.height = height;
        this.setSize(with, height);
        setLocation(0,0);
        this.setLayout(new GridLayout(10,10));
        setBackground(Color.cyan);
        array = new JButton[10][10];
        setup();

        
        


    }

    private void setup() {
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++) {
               // array[i][j] = new JButton(String.valueOf(i) + String.valueOf(j));
               // array[i][j].setEnabled(true);
               // array[i][j].setSize(50, 50);
                knapp = new JButton(String.valueOf(i) + String.valueOf(j));
               knapp.setEnabled(true);
               knapp.setSize(50, 50);

               //array[i][j].addActionListener(l -> mainframe.buttonPressed2(getComponents().toString()));
                int iDENNA = i;
                int jDENNA = j;
                knapp.addActionListener(l -> mainframe.buttonPressed2(String.valueOf(iDENNA)+","+String.valueOf(jDENNA)));
                this.add(knapp);




            }
        }

    }
}
