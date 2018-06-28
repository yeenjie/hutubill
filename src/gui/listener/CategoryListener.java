package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel p = CategoryPanel.instance;
        JButton b = (JButton) e.getSource();
        if(b == p.bAdd){
            String name = "";   
            name = JOptionPane.showInputDialog(null);
            if(0 == name.length()){
                JOptionPane.showMessageDialog(p,"分类名不能为空");
                return;
            }
            new CategoryService().add(name);
        }
        if(b == p.bEdit){
            Category c = p.getSelectedCategory();
            int id  = c.getId();
            String name = JOptionPane.showInputDialog("修改分类名称",c.getName());
            if(0 == name.length()){
                JOptionPane.showMessageDialog(p,"分类名称不能为空 ");
                return;
            }
            new CategoryService().update(id,name);
        }
        if(b == p.bDelete){
            Category c = p.getSelectedCategory();
            new CategoryService().delete(c.getId());
        }
        p.updateDate();
    }
}
