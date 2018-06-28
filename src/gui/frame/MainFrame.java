package gui.frame;

import gui.panel.MainPanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static MainFrame instance = new MainFrame();

    public MainFrame(){
        this.setSize(500,450);
        this.setTitle("记账本");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(MainPanel.instance);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        instance.setVisible(true);
    }


}
