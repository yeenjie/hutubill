package gui.panel;

import gui.page.SpendPage;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class SpendPanel extends WorkingPanel{
    static {
        GUIUtil.useLNF();
    }
    SpendPage spendPage = new SpendService().getSpendPage();
    public static SpendPanel instance = new SpendPanel();
    JLabel lMonthSpend =new JLabel("本月消费");
    JLabel lTodaySpend = new JLabel("今日消费");
    JLabel lAvgSpendPerDay = new JLabel("日均消费");
    JLabel lMonthLeft = new JLabel("本月剩余");
    JLabel lDayAvgAvailable = new JLabel("日均可用");
    JLabel lMonthLeftDay = new JLabel("距离月末");
    JLabel vMonthSpend = new JLabel(spendPage.monthSpend);
    JLabel vTodaySpend = new JLabel(spendPage.todaySpend);
    JLabel vAvgSpendPerDay = new JLabel(spendPage.avgSpendPerDay);
    JLabel vMonthAvailable = new JLabel(spendPage.monthAvailable);
    JLabel vDayAvgAvailable = new JLabel(spendPage.dayAvgAvailable);
    JLabel vMonthLeftDay = new JLabel(spendPage.monthLeftDay);
    CircleProgressBar bar ;

    public SpendPanel(){
        this.setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.blueColor);
        bar.setProgress(spendPage.usagePercentage);

        GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
                lMonthLeftDay, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
        GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);

        vMonthSpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("微软雅黑", Font.BOLD, 23));

        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
    }

    @Override
    public void updateData() {
       SpendPage spendPage = new SpendService().getSpendPage();
        vMonthSpend.setText(spendPage.monthSpend);
         vTodaySpend.setText(spendPage.todaySpend);
         vAvgSpendPerDay.setText(spendPage.avgSpendPerDay);
         vMonthAvailable .setText(spendPage.monthAvailable);
         vDayAvgAvailable.setText(spendPage.dayAvgAvailable);
         vMonthLeftDay.setText(spendPage.monthLeftDay);
        bar.setProgress(spendPage.usagePercentage);
        if(spendPage.isOverSpend){
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);
        }else{
            vMonthSpend.setForeground(ColorUtil.blueColor);
            vTodaySpend.setForeground(ColorUtil.blueColor);
        }
        bar.setForegroundColor(ColorUtil.getByPercentage(spendPage.usagePercentage));


    }

    @Override
    public void addListener() {

    }

    private JPanel south(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,4));
        p.add(lAvgSpendPerDay);
        p.add(lMonthLeft);
        p.add(lDayAvgAvailable);
        p.add(lMonthLeftDay);
        p.add(vAvgSpendPerDay);
        p.add(vMonthAvailable);
        p.add(vDayAvgAvailable);
        p.add(vMonthLeftDay);
        return p;
    }

    private JPanel center(){
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(west(),BorderLayout.WEST);
        p.add(center2(),BorderLayout.CENTER);
        return p;
    }

    private JPanel west(){
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4,1));
        p.add(lMonthSpend);
        p.add(vMonthSpend);
        p.add(lTodaySpend);
        p.add(vTodaySpend);
        return p;
    }

    private JPanel center2(){
        return bar;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(SpendPanel.instance);
    }




}
