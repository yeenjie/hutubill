package gui.panel;

import gui.model.CategoryTableModel;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }
    public static CategoryPanel instance =new CategoryPanel();
    JButton bAdd = new JButton("新增");
    JButton bEdit = new JButton("编辑");
    JButton bDelete = new JButton("删除");
    String columnNames[] = new  String[]{"分类名称","消费次数"};
    public CategoryTableModel categoryTableModel = new CategoryTableModel();
    public JTable table= new  JTable(categoryTableModel);
    public  CategoryPanel(){
        GUIUtil.setColor(ColorUtil.blueColor,bAdd,bEdit,bDelete);
        JScrollPane ps = new JScrollPane(table);
        JPanel psubmit = new JPanel();
        psubmit.add(bAdd);
        psubmit.add(bEdit);
        psubmit.add(bDelete);
        this.setLayout(new BorderLayout());
        this.add(psubmit,BorderLayout.SOUTH);
        this.add(ps,BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }
}
