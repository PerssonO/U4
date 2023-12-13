package view;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private LeftPanel leftPanel;
    private RightPanel rightPanel;

    public MainPanel(int with, int height, MainFrame mainFrame) {
        super(null);
        this.setSize(with, height);

        this.leftPanel = new LeftPanel(mainFrame, with/2, height);
        add(leftPanel);
        setBackground(Color.green);
        this.rightPanel = new RightPanel(mainFrame, with/2, height);
        add(rightPanel);



    }
}
