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
    public SpendPage(int monthSpend ,int todaySpend,int avgSpendPerDay,int monthAvailable,int dayAvgAvailable,int monthLeftDay,int usagePercentage){
        this.monthSpend = "￥"+monthSpend;
        this.avgSpendPerDay = "￥"+avgSpendPerDay;
        this.todaySpend = "￥"+todaySpend;
        this.monthLeftDay = monthLeftDay +"天";
        this.usagePercentage = usagePercentage;
        if(monthAvailable < 0 )
            isOverSpend = true;
        if(!isOverSpend){
            this.dayAvgAvailable = "￥"+dayAvgAvailable;
            this.monthAvailable = "￥"+monthAvailable;
        }else{
            this.dayAvgAvailable = "￥"+ 0;
            this.monthAvailable = "￥"+ 0;
        }


    }
}
