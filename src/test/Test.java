package test;


import javax.swing.*;

import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Test {
    public static void main(String[] args){

        GUIUtil.useLNF();
        JPanel p = new JPanel();
        CircleProgressBar circleProgressBar = new CircleProgressBar();
        circleProgressBar.setBackgroundColor(ColorUtil.blueColor);
        circleProgressBar.setProgress(0);
        //按钮
        JButton b = new JButton("点击");
        p.setLayout(new BorderLayout());
        p.add(circleProgressBar,BorderLayout.CENTER);
        p.add(b,BorderLayout.SOUTH);
        //显示面板
        GUIUtil.showPanel(p);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SwingWorker(){
                    @Override
                    protected Object doInBackground() {
                        for (int i = 1; i <= 100 ; i++) {
                            circleProgressBar.setProgress(i);
                            circleProgressBar.setForegroundColor(ColorUtil.getByPercentage(i));
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                        return null;
                    }
                }.execute();
            }
        });
    }
}
