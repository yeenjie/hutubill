package service;

import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.Date;

public class RecordService {
    public void add(int spend, Category category,String comment,Date date){
        RecordDAO dao = new RecordDAO();
        Record record = new Record();
        record.setSpend(spend);
        record.setCid(category.getId());
        record.setComment(comment);
        record.setDate(date);
        dao.add(record);
    }
}
