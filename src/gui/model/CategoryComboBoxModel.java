package gui.model;

import entity.Category;
import service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public class CategoryComboBoxModel implements ComboBoxModel<Category> {

//    public List<String> cs = new ArrayList();
//    String c; //表示选中的标签
    public List<Category> cs = new CategoryService().list();
    public Category c = null; //表示选中的标签
    public CategoryComboBoxModel(){
//        cs.add("餐饮");
//        cs.add("交通");
//        cs.add("住宿");
//        cs.add("花费");
//        c = cs.get(0);
    }
    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    @Override
    public Category getSelectedItem() {
        return c;
    }

    @Override
    public int getSize() {
        return cs.size();
    }

    @Override
    public Category getElementAt(int index) {
        return cs.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }

}
