package gui.page;

public class SpendPage {
    //本月消费
    public String monthSpend;
    public String todaySpend;
    public String  avgSpendPerDay;
    public String monthAvailable;
    public String dayAvgAvailable;
    public String monthLeftDay;
    public int usagePercentage;
    public boolean isOverSpend = false;
    public SpendPage(int monthSpend ,int todaySpend,int avgSpendPerDay,int monthAvailable,int dayAvgAvailable,int monthLeftDay){
        this.monthSpend = "￥"+monthSpend;
        this.avgSpendPerDay = "￥"+avgSpendPerDay;
        this.dayAvgAvailable = "￥"+dayAvgAvailable;
        this.monthAvailable = "￥"+monthAvailable;
        this.monthLeftDay = "￥"+monthLeftDay;
        this.todaySpend = "￥"+todaySpend;
        if(monthAvailable < 0 )
            isOverSpend = true;

    }
}
