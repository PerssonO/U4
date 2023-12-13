package view;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    private MainFrame mainframe;
    private int with;
    private int height;
    private JButton btnNyttSpel;
    private JButton btnSparaSpel;
    private JButton btnLaddaSpel;

    private JButton btnVisaHighScore;
    private JLabel player1;
    private JLabel player2;
    private JLabel currentPlayer;
    private JList<Object> infoFönster;





    public RightPanel(MainFrame mainframe, int with, int height) {
        this.mainframe =mainframe;
        this.with = with;
        this.height = height;
        this.setSize(with, height);
        setLocation(500,0);
        this.setLayout(null);
        setBackground(Color.cyan);
        setup();





    }

    private void setup() {
        player1 = new JLabel("Spelare 1 poäng: ");
        player1.setLocation(50, 0);
        player1.setSize(400, 20);
        this.add(player1);

        player2 = new JLabel("Spelare 2 poäng: ");
        player2.setLocation(50, 20);
        player2.setSize(400, 20);
        this.add(player2);

        currentPlayer = new JLabel("Vems tur: ");
        currentPlayer.setLocation(50, 40);
        currentPlayer.setSize(400, 20);
        this.add(currentPlayer);

        infoFönster = new JList<>();
        infoFönster.setLocation(50, 70);
        infoFönster.setSize(400,300);
        this.add(infoFönster);

        btnNyttSpel = new JButton("Nytt Spel");
        btnNyttSpel.setLocation(50, 450);
        btnNyttSpel.setSize(100,50);
        this.add(btnNyttSpel);

        btnLaddaSpel = new JButton("Ladda Spel");
        btnLaddaSpel.setLocation(155, 450);
        btnLaddaSpel.setSize(100,50);
        this.add(btnLaddaSpel);

        btnSparaSpel = new JButton("Spara Spel");
        btnSparaSpel.setLocation(260, 450);
        btnSparaSpel.setSize(100,50);
        this.add(btnSparaSpel);

        btnVisaHighScore = new JButton("Highscore");
        btnVisaHighScore.setLocation(365, 450);
        btnVisaHighScore.setSize(100,50);
        this.add(btnVisaHighScore);









    }
}