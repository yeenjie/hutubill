package service;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;
import gui.model.CategoryTableModel;

import javax.swing.text.Caret;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CategoryService {
    CategoryDAO categoryDAO = new CategoryDAO();
    RecordDAO recordDAO = new RecordDAO();

    public List<Category> list(){

        List<Category> cs = categoryDAO.list();

        for(Category c : cs){
            List<Record> rs = recordDAO.list(c.getId());
            c.setRecordNumber(rs.size());
        }

        Collections.sort(cs,(c1,c2)->c2.getRecordNumber()-c1.getRecordNumber());;

        return cs;
    }

    public void add(String name){
        Category c = new Category();
        c.setName(name);
        categoryDAO.add(c);
    }

    public void update(int id,String name){
        Category c = new Category();
        c.setName(name);
        c.setId(id);
        categoryDAO.update(c);
    }

    public void delete(int id){
        categoryDAO.delete(id);
    }

}
