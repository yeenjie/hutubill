package util;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import gui.panel.WorkingPanel;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends  JPanel {
    private double rate;//拉伸比例
    private JComponent c;//显示的组件
    private boolean strech;//是否拉伸
    public CenterPanel(double rate,boolean strech){
        this.setLayout(null);
        this.rate = rate;
        this. strech =strech;
    }
    public CenterPanel(double rate){
        this(rate,true);
    }

    public void repaint(){
        if(null!=c){
            Dimension containerSize = this.getSize();
            Dimension componentSize = c.getPreferredSize();
            if(strech){
               c.setSize((int)(containerSize.width*rate),(int)(containerSize.height*rate));
            }else{
                c.setSize(componentSize);
            }
            c.setLocation(containerSize.width/2-c.getSize().width/2,containerSize.height/2-c.getSize().height/2);
            super.repaint();
        }
    }

    public void show(JComponent p){
        this.c = p;
        Component[] cs = getComponents();
        for(Component c : cs){
            remove(c);
        }
        add(p);
        if(p instanceof WorkingPanel){
            ((WorkingPanel)p).updateData();
        }
        this.updateUI();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(500,200);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(0.85,false);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        JButton b = new JButton("abc");
        cp.show(b);
    }
}
