package gui.panel;
import entity.Category;
import gui.listener.CategoryListener;
import gui.listener.ConfigListener;
import gui.model.CategoryTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CategoryPanel extends JPanel {
    static {
        GUIUtil.useLNF();
    }
    public static CategoryPanel instance =new CategoryPanel();
    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");
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
        addListener();

    }

    public Category getSelectedCategory(){
        int index  = table.getSelectedRow();
        return categoryTableModel.cs.get(index);
    }

    public void updateDate(){
        categoryTableModel.cs = new CategoryService().list();
        table = new JTable(categoryTableModel);
        table.updateUI();
        table.getSelectionModel().setSelectionInterval(0,0);
        if(0 == categoryTableModel.cs.size()){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }
        else{
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }

    }

    public void addListener(){
        ActionListener actionListener = new CategoryListener();
        bEdit.addActionListener(actionListener);
        bAdd.addActionListener(actionListener);
        bDelete.addActionListener(actionListener);
    }
    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }
}
