package service;

import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import util.DateUtil;

import java.util.Date;
import java.util.List;

public class SpendService {
    public SpendPage getSpendPage(){
        RecordDAO dao = new RecordDAO();
        List<Record> monthRecords = dao.listThisMonth();
        List<Record> dayRecords = dao.listToday();
        int thisMonthTotalDay = DateUtil.thisMonthTotalDay();
        int monthBudget =0;
        int todaySpend =0;
        int avgSpendPerDay=0;
        int monthLeftDay = 0;
        int monthSpend = 0;
        int dayAvgAvailable =0;
        int monthAvailable =0;
        int usagePercentTage = 0;

        monthBudget = new ConfigService().getIntBudget();
        for(Record record : dayRecords){
            todaySpend += record.getSpend();
        }
        for(Record record : monthRecords){
            monthSpend += record.getSpend();
        }
        monthLeftDay = DateUtil.thisMonthLeftDay();
        System.out.println("this mont total "+thisMonthTotalDay);
        System.out.println("left day "+monthLeftDay);
        avgSpendPerDay = monthSpend / (thisMonthTotalDay-monthLeftDay);

        dayAvgAvailable = (monthBudget - monthSpend)/monthLeftDay;
        monthAvailable = monthBudget - monthSpend;
        usagePercentTage = monthSpend*100/monthBudget;
        return new SpendPage(monthSpend,todaySpend,avgSpendPerDay,monthAvailable,dayAvgAvailable,monthLeftDay,usagePercentTage);

    }

}
