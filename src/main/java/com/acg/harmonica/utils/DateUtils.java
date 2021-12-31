package com.acg.harmonica.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期处理类
 */
public class DateUtils {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final String DATA_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期转换为"yyyy-MM-dd"格式字符串
     *
     * @param date 需要转换的日期
     * @return String 转换后的日期串
     */
    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        return df.format(date);
    }

    /**
     * 日期转换为"yyyy-MM-dd HH:mm:ss"格式字符串
     *
     * @param date 需要转换的日期
     * @return 转换后的日期串
     */
    public static String dateToStringAll(Date date) {
        DateFormat df = new SimpleDateFormat(DATA_TIME_FORMAT);
        return df.format(date);
    }

    /**
     * "yyyy-MM-dd"格式字符串转换为日期
     *
     * @param dateStr 需要转换的日期串
     * @return Date 转换后的日期
     */
    public static Date stringToDate(String dateStr) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        try {
            return df.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * "yyyy-MM-dd HH:mm:ss"格式的字符串转换为时间
     *
     * @param dateStr 需要转换的日期串
     * @return Date 转换后的日期
     */
    public static Date stringToDateAll(String dateStr) {
        DateFormat df = new SimpleDateFormat(DATA_TIME_FORMAT);
        try {
            return df.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 自定义格式的日期转换为字符串的方法
     *
     * @param date    需要转换的日期
     * @param pattern 目标格式
     * @return 字符串日期
     */
    public static String dateToStringOfPattern(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 自定义格式的字符串转换为日期的方法
     *
     * @param dateStr 需要转换的日期串
     * @param parrern 转换格式
     * @return 转换后的日期
     */
    public static Date stringToDateOfPattern(String dateStr, String parrern) {
        DateFormat df = new SimpleDateFormat(parrern);
        try {
            return df.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取入参日期的年份
     */
    public static int getYear(Date date) {
        Calendar cd = new GregorianCalendar();
        cd.setTime(date);
        return cd.get(Calendar.YEAR);
    }

    /**
     * 获取入参日期的月份，从0开始计
     */
    public static int getMonth(Date date) {
        Calendar cd = new GregorianCalendar();
        cd.setTime(date);
        return cd.get(Calendar.MONTH);
    }

    /**
     * 获取入参日期的日值
     */
    public static int getDay(Date date) {
        Calendar cd = new GregorianCalendar();
        cd.setTime(date);
        return cd.get(Calendar.DATE);
    }

    /**
     * 获取入参日期中小时的24小时制小时数
     */
    public static int getHourOfDay(Date date) {
        Calendar cd = new GregorianCalendar();
        cd.setTime(date);
        return cd.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取入参日期中小时的12小时制小时数
     */
    public static int getHour(Date date) {
        Calendar cd = new GregorianCalendar();
        cd.setTime(date);
        return cd.get(Calendar.HOUR);
    }

    /**
     * 获取入参日期在当前月中属于多少周
     */
    public static int getWeekOfMonth(Date date) {
        Calendar cd = new GregorianCalendar();
        cd.setTime(date);
        return cd.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取入参日期在当前周中属于第几天，从上一周的星期天开始计
     */
    public static int getDayOfWeek(Date date) {
        Calendar cd = new GregorianCalendar();
        cd.setTime(date);
        return cd.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取入参日期所在的周为当前年的多少周
     */
    public static int getWeekOfYear(Date date) {
        Calendar cd = new GregorianCalendar();
        cd.setTime(date);
        return cd.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取入参日期在当前年中属于第几天
     */
    public static int getDayOfYear(Date date) {
        Calendar cd = new GregorianCalendar();
        cd.setTime(date);
        return cd.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 返回指定日期的本周第一天日期
     */
    public static Date getFirstDateOfWeek(Date date) {
        Calendar cd = new GregorianCalendar();
        cd.setTime(date);
        cd.set(Calendar.DAY_OF_WEEK, 1);
        return cd.getTime();
    }

    /**
     * 返回指定日期的本月第一天日期
     */
    public static Date getFirstDateOfMonth(Date date) {
        Calendar cd = new GregorianCalendar();
        cd.setTime(date);
        cd.set(Calendar.DAY_OF_MONTH, 1);
        return cd.getTime();
    }

    /**
     * 返回当前时间的上下午的判断值
     *
     * @return 0为上午，1为下午
     */
    public static int getAMorPM() {
        Calendar cd = new GregorianCalendar();
        return cd.get(GregorianCalendar.AM_PM);
    }



    /**
     * 在原日期的基础上增加小时数
     * @param date
     * @param hour
     * @return
     */
    public static Date addHourOfDate(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hour);
        Date newDate = c.getTime();
        return newDate;
    }


    /**
     * 在原日期的基础上增加天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDayOfDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        Date newDate = calendar.getTime();
        return newDate;
    }


}