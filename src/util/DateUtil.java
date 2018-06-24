package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    static long millisecondsOfOneDay = 1000*60*60*24;

    public static java.sql.Date util2sql(java.util.Date d){
        return new java.sql.Date(d.getTime());
    }

    /**
     * 获得浸提，设置分秒为0是因为日期控件没有时分秒
     * @return
     */
    public static Date today(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        return c.getTime();
    }

    public static Date monthBegin(){
        Calendar c =Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DATE,1);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);

        return c.getTime();
    }

    public static Date monthEnd(){
        Calendar c= Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND,0);
        c.set(Calendar.DATE,0);
        c.add(Calendar.MONTH,1);
//        c.add(Calendar.DATE,-1);
        return c.getTime();
    }

    public static int thisMonthLeftDay(){
        long lastDayMilliSeconds = monthEnd().getTime();
        long toDayMilliSeconds = today().getTime();
        return (int) ((lastDayMilliSeconds-toDayMilliSeconds)/millisecondsOfOneDay)+1;
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.thisMonthLeftDay());
    }

}
