package gui;

import gui.panel.MainPanel;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static MainFrame instance = new MainFrame();

    public MainFrame(){
        this.setSize(500,450);
        this.setTitle("一本糊涂账");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(MainPanel.instance);


    }


    public static void main(String[] args) {
        instance.setVisible(true);
    }


}
