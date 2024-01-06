package view;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public class LeftPanel extends JPanel implements Serializable {
    private final MainFrame mainframe;
    private final int with;
    private final int height;
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
                array[i][j] = new JButton(String.valueOf(i) + j);
                array[i][j].setEnabled(true);
                array[i][j].setBackground(Color.lightGray);
                array[i][j].setFont(new Font(array[i][j].getFont().getName(), Font.PLAIN, 0));
                array[i][j].setSize(50, 50);
                //knapp = new JButton(String.valueOf(i) + String.valueOf(j));
               //knapp.setEnabled(true);
               //knapp.setSize(50, 50);

               //array[i][j].addActionListener(l -> mainframe.buttonPressed2(getComponents().toString()));
                int iDENNA = i;
                int jDENNA = j;
                int[] indexPlatser = new int[2];
                indexPlatser[0] = iDENNA;
                indexPlatser[1] = jDENNA;
                array[i][j].addActionListener(l -> mainframe.skickaIndexArray(indexPlatser));
                //array[i][j].addActionListener(l -> mainframe.buttonPressed(ButtonType.Spelknapp));
                this.add(array[i][j]);




            }
        }

    }

    public JButton getKnapp() {
        return knapp;
    }

    public void setKnapp(JButton knapp) {
        this.knapp = knapp;
    }

    public JButton getButton(int i, int j) {
        return array[i][j];
    }

    public void setArray(JButton[][] array) {
        this.array = array;
    }
}
