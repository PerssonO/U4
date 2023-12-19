package view;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

import controller.*;

public class RightPanel extends JPanel implements Serializable {
    private MainFrame mainframe;
    private int with;
    private int height;
    private JButton btnNyttSpel;
    private JButton btnSparaSpel;
    private JButton btnLaddaSpel;

    private JButton btnVisaHighScore;
    private JLabel player1;
    private JLabel player2;
    private JLabel playerLiv1;
    private JLabel playerLiv2;
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
        player1.setSize(150, 20);
        this.add(player1);

        player2 = new JLabel("Spelare 2 poäng: ");
        player2.setLocation(50, 20);
        player2.setSize(150, 20);
        this.add(player2);

        playerLiv1 = new JLabel("Spelare 1 liv: ");
        playerLiv1.setLocation(200, 0);
        playerLiv1.setSize(150, 20);
        this.add(playerLiv1);

        playerLiv2 = new JLabel("Spelare 2 liv: ");
        playerLiv2.setLocation(200, 20);
        playerLiv2.setSize(150, 20);
        this.add(playerLiv2);

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
        btnNyttSpel.addActionListener(l -> mainframe.buttonPressed(ButtonType.NyttSpel));

        btnLaddaSpel = new JButton("Ladda Spel");
        btnLaddaSpel.setLocation(155, 450);
        btnLaddaSpel.setSize(100,50);
        this.add(btnLaddaSpel);
        btnLaddaSpel.addActionListener(l -> mainframe.buttonPressed(ButtonType.LaddaSpel));

        btnSparaSpel = new JButton("Spara Spel");
        btnSparaSpel.setLocation(260, 450);
        btnSparaSpel.setSize(100,50);
        this.add(btnSparaSpel);
        btnSparaSpel.addActionListener(l -> mainframe.buttonPressed(ButtonType.SparaSpel));

        btnVisaHighScore = new JButton("Highscore");
        btnVisaHighScore.setLocation(365, 450);
        btnVisaHighScore.setSize(100,50);
        this.add(btnVisaHighScore);
        btnVisaHighScore.addActionListener(l -> mainframe.buttonPressed(ButtonType.VisaHigh));


    }

    public MainFrame getMainframe() {
        return mainframe;
    }

    public void setMainframe(MainFrame mainframe) {
        this.mainframe = mainframe;
    }

    public int getWith() {
        return with;
    }

    public void setWith(int with) {
        this.with = with;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public JButton getBtnNyttSpel() {
        return btnNyttSpel;
    }

    public void setBtnNyttSpel(JButton btnNyttSpel) {
        this.btnNyttSpel = btnNyttSpel;
    }

    public JButton getBtnSparaSpel() {
        return btnSparaSpel;
    }

    public void setBtnSparaSpel(JButton btnSparaSpel) {
        this.btnSparaSpel = btnSparaSpel;
    }

    public JButton getBtnLaddaSpel() {
        return btnLaddaSpel;
    }

    public void setBtnLaddaSpel(JButton btnLaddaSpel) {
        this.btnLaddaSpel = btnLaddaSpel;
    }

    public JButton getBtnVisaHighScore() {
        return btnVisaHighScore;
    }

    public void setBtnVisaHighScore(JButton btnVisaHighScore) {
        this.btnVisaHighScore = btnVisaHighScore;
    }

    public JLabel getPlayer1() {
        return player1;
    }

    public void setPlayer1(JLabel player1) {
        this.player1 = player1;
    }

    public JLabel getPlayer2() {
        return player2;
    }

    public void setPlayer2(JLabel player2) {
        this.player2 = player2;
    }

    public JLabel getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(JLabel currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public JList<Object> getInfoFönster() {
        return infoFönster;
    }

    public void setInfoFönster(JList<Object> infoFönster) {
        this.infoFönster = infoFönster;
    }

    public JLabel getPlayerLiv1() {
        return playerLiv1;
    }

    public void setPlayerLiv1(JLabel playerLiv1) {
        this.playerLiv1 = playerLiv1;
    }

    public JLabel getPlayerLiv2() {
        return playerLiv2;
    }

    public void setPlayerLiv2(JLabel playerLiv2) {
        this.playerLiv2 = playerLiv2;
    }
}