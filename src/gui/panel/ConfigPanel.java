package gui.panel;

import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }
    public static ConfigPanel instance = new ConfigPanel();
    JLabel lBudget = new JLabel("本月预算");
    JTextField tfBudget = new JTextField("0");
    JLabel lMysql = new JLabel("Mysql安装目录");
    JTextField tfMysql = new JTextField();
    JButton bSubmit = new JButton("更新");
    public ConfigPanel(){
        GUIUtil.setColor(ColorUtil.grayColor,lBudget,lMysql);
        GUIUtil.setColor(ColorUtil.blueColor,bSubmit);
        int gra = 40;
        JPanel pInput = new JPanel();
        pInput.setLayout(new GridLayout(4,1,gra,gra));
        pInput.add(lBudget);
        pInput.add(tfBudget);
        pInput.add(lMysql);
        pInput.add(tfMysql);
        JPanel pSubmit = new JPanel();
        pSubmit.add(bSubmit);
        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
        this.add(pSubmit,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance);
    }
}
