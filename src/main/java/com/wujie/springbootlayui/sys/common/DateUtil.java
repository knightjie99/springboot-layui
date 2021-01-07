package com.wujie.springbootlayui.sys.common;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    /**
     * 获取当前月份的第一天时间
     *
     * @return
     */
    public static Date getFirstDayOfMonth() {
        // 获取当前年份、月份、日期
        Calendar cale = null;
        cale = Calendar.getInstance();
        // 获取当月第一天和最后一天
        SimpleDateFormat formatTemp = new SimpleDateFormat("yyyy-MM-dd");
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        String firstday = formatTemp.format(cale.getTime()) + " 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(firstday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * 获取当前月份最后一天时间
     *
     * @return
     */
    public static Date getLastDayOfMonth() {
        // 获取当前年份、月份、日期
        Calendar cale = null;
        cale = Calendar.getInstance();
        // 获取当月第一天和最后一天
        SimpleDateFormat formatTemp = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        String lastday = formatTemp.format(cale.getTime()) + " 23:59:59";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(lastday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * 将字符串日期转Date日期
     *
     * @param time
     * @return
     */
    public static Date parseStringDayToDate(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * 将Date转日期字符串
     */
    public static String parseDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = sdf.format(date);
        return datetime;
    }

    /**
     * 获取一天的结束时间
     */
    public static Date getDayOfEndTime(Date date) {
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(date);
        dateEnd.set(Calendar.HOUR_OF_DAY, 23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        return dateEnd.getTime();
    }

    /**
     * 拼接当前时间转Date
     */
    public static Date pingStringTimeToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = sdf.format(new Date());
        String result = datetime + " " + time;
        Date date = parseStringDayToDate(result);
        return date;
    }


    /**
     * 获取一天的开始时间
     *
     * @param args
     */
    public static Date getDayOfStartTime(Date date) {
        Calendar dateStart = Calendar.getInstance();
        dateStart.setTime(date);
        dateStart.set(Calendar.HOUR_OF_DAY, 0);
        dateStart.set(Calendar.MINUTE, 0);
        dateStart.set(Calendar.SECOND, 0);
        return dateStart.getTime();
    }

    /**
     * 计算两个Date之间查多少小时
     *
     * @param args
     */
    public static double getTwoDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long diff = endDate.getTime() - nowDate.getTime();
        long hour = diff % nd / nh;
        long min = diff % nd % nh / nm;
        double dd = (double) (int) min / 60;
        DecimalFormat df = new DecimalFormat(".0");
        String str = hour + df.format(dd);
        double result = Double.parseDouble(str);
        return result;
    }

    /**
     * 将yyyyMMddHHmmss格式转为日期
     *
     * @return
     */
    public static String parseStrToDate(String datetime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime ldt = LocalDateTime.parse(datetime, dtf);
        DateTimeFormatter fa = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datetime2 = ldt.format(fa);
        return datetime2;
    }

    /**
     * 计算两个日期之间相差秒
     *
     * @param date1
     * @param date2
     * @return
     */
    public static final int secBetween(String sdate1, String sdate2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = df.parse(sdate1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = df.parse(sdate2);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Calendar time1 = Calendar.getInstance();
        Calendar time2 = Calendar.getInstance();
        time1.setTime(date1);
        time2.setTime(date2);

        int sec = 0;
        if (time1.getTime().getTime() >= time2.getTime().getTime()) {
            sec = ((int) (time1.getTime().getTime() / 1000) - (int) (time2.getTime().getTime() / 1000));
        } else {
            sec = ((int) (time2.getTime().getTime() / 1000) - (int) (time1.getTime().getTime() / 1000));
        }
        return sec;
    }


    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();
        String gateName = StringUtils.join(names, ",");
        System.out.println(gateName);
    }

    private static final int FIRST_DAY = Calendar.MONDAY;

    public static final List<String> printWeekdays() {
        Calendar calendar = Calendar.getInstance();
        setToFirstDay(calendar);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            list.add(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }

        return list;
    }

    private static void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }

    public String getWeek(String dateTime) {
        String week = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateTime);
            SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
            week = dateFm.format(date);
            week = week.replaceAll("星期", "周");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return week;
    }

    /**
     * 获取过去7天内的日期数组
     *
     * @param intervals intervals天内
     * @return 日期数组
     */
    public static ArrayList<String> getDays(int intervals) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = intervals - 1; i >= 0; i--) {
            pastDaysList.add(getPastDate(i));
        }
        return pastDaysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    public static Integer getWeekOfDate(Date date) {
        Integer[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static int getDaysOfTheMonth(Date date){//获取当月天数
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date); // 要计算你想要的月份，改变这里即可
//        int days = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
        int days = rightNow.get(Calendar.DAY_OF_MONTH);
        return days;
    }

    public static List<Integer> getSundays(Date dat) {
        int d1=0;
        int d2=0;
        int d3=0;
        int d4=0;
        int d5=0;
        int d6=0;
        int d7=0;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Calendar setDate = Calendar.getInstance();
        int day;
        for (day = 1; day <= getDaysOfTheMonth(dat); day++) {
            setDate.set(Calendar.DATE, day);
            String str = sdf.format(setDate.getTime());
            if (str.equals("星期日")) {
                d7++;
            }
            if (str.equals("星期六")) {
                d6++;
            }
            if (str.equals("星期五")) {
                d5++;
            }
            if (str.equals("星期四")) {
                d4++;
            }
            if (str.equals("星期三")) {
                d3++;
            }
            if (str.equals("星期二")) {
                d2++;
            }
            if (str.equals("星期一")) {
                d1++;
            }

        }

        List<Integer> list = new ArrayList<>();
        list.add(d1);
        list.add(d2);
        list.add(d3);
        list.add(d4);
        list.add(d5);
        list.add(d6);
        list.add(d7);


        return list;
    }

}
