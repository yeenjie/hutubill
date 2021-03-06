package gui.model;

import dao.CategoryDAO;
import entity.Category;
import service.CategoryService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

public class CategoryTableModel implements TableModel {

    String[] columnNames = new String[]{"分类名称", "消费次数"};
//    List<String> cs = new ArrayList();

    public List<Category> cs = new CategoryService().list();

    public CategoryTableModel(){
//        cs.add("餐饮");
//        cs.add("交通");
//        cs.add("住宿");
//        cs.add("话费");
    }

    @Override
    public int getRowCount() {
        return cs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category row = cs.get(rowIndex);
        if(0==columnIndex)
            return row.getName();
        if(1==columnIndex){
            return row.getRecordNumber();
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }

}
