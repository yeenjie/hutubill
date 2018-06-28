package gui.panel;

import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

import javax.swing.JTextField;
public class ConfigPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }
    public static ConfigPanel instance = new ConfigPanel();
    JLabel lBudget = new JLabel("本月预算(￥)");
    public JTextField tfBudget = new JTextField("0");

    JLabel lMysql = new JLabel("Mysql安装目录");
    public JTextField tfMysqlPath = new JTextField("");

    JButton bSubmit = new JButton("更新");
    public ConfigPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lBudget, lMysql);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;
        pInput.setLayout(new GridLayout(4, 1, gap, gap));

        pInput.add(lBudget);
        pInput.add(tfBudget);
        pInput.add(lMysql);
        pInput.add(tfMysqlPath);

        pSubmit.add(bSubmit);

        this.setLayout(new BorderLayout());
        this.add(pInput, BorderLayout.NORTH);
        this.add(pSubmit, BorderLayout.CENTER);
        addListener();
    }

    @Override
    public void updateData() {
        String budget = new ConfigService().get(ConfigService.budget);
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        tfBudget.setText(budget);
        System.out.println("update"+ConfigService.budget +budget );
        tfMysqlPath.setText(mysqlPath);
        tfBudget.grabFocus();

    }

    public void addListener(){
        bSubmit.addActionListener(new ConfigListener());
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance);

    }
}
