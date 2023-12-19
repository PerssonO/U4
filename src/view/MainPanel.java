package view;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class MainPanel extends JPanel implements Serializable {
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

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(LeftPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }

    public void setRightPanel(RightPanel rightPanel) {
        this.rightPanel = rightPanel;
    }
}
