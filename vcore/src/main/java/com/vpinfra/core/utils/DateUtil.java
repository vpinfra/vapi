package com.vpinfra.core.utils;

import com.vpinfra.core.model.KeyValueBean;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 *
 * @author 尹俊峰
 * @date 2016年8月22日
 * @since 2.1.1
 */
public final class DateUtil {

    private static final LogUtil logger = LogUtil.newInstance(DateUtil.class);

    private static final String TIME_PATTERN = "HH:mm";

    private static final String DATE_TIME_PATTREN_CN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTREN_CN = "yyyy-MM-dd";
    private static final String TIME_PATTREN_CN = "HH:mm:ss";

    private DateUtil() {
    }

    /**
     * 将目标字符串按照传入的格式转换成为日期.
     *
     * @param pattern 日期格式
     * @param strDate 目标日期
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(final String pattern, final String strDate) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat(pattern);

        logger.debug(String.format("converting '%s' to date with pattern '%s'", pattern, strDate));

        return df.parse(strDate);
    }

    /**
     * 获取日期字符串.
     *
     * @param theTime 目标日期
     * @return
     */
    public static String getTimeString(final Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    /**
     * 将转换成指定格式的字符串.
     *
     * @param pattern 日期格式
     * @param aDate 目标日期
     * @return
     */
    public static String getDateTime(final String pattern, final Date aDate) {
        String returnValue = "";

        if (aDate == null) {
            logger.warn("aDate is null!");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            returnValue = sdf.format(aDate);
        }

        return returnValue;
    }

    /**
     * 将时间转换成指定格式的字符串.
     *
     * @param pattern 日期格式
     * @param aDate 目标日期
     * @return
     */
    public static String convertDateToString(final String pattern, final Date aDate) {
        return getDateTime(pattern, aDate);
    }

    /**
     * 获取当前的日期时间字符串(yyyy-MM-dd HH:mm:ss).
     *
     * @return 返回当前日期时间字符串
     */
    public static String getCurrentTime() {
        return getCurrentTime(DATE_TIME_PATTREN_CN);
    }

    /**
     * 获取当前的日期时间字符串.
     *
     * @param dateFormat 目标日期
     * @return 返回当前日期时间字符串(指定格式 "yyyy-MM-dd HH:mm:ss")
     */
    public static String getCurrentTime(final String dateFormat) {
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat theDate = new SimpleDateFormat(dateFormat);

        return theDate.format(cal.getTime());
    }

    /**
     * 获取当前的日期字符串(yyyy-MM-dd).
     *
     * @return 返回当前日期字符串
     */
    public static String getCurrentDate() {
        return getCurrentTime(DATE_PATTREN_CN);
    }

    /**
     * 获取指定的日期时间字符串
     *
     * @param time 目标时间
     * @return 返回当前日期时间字符串(指定格式 "yyyy-MM-dd HH:mm:ss")
     */
    public static String getAppointTime(final long time) {
        SimpleDateFormat theDate = new SimpleDateFormat(DATE_TIME_PATTREN_CN);

        return theDate.format(time);
    }

    /**
     * 获取指定的日期时间字符串.
     *
     * @param time 目标时间
     * @param pattern 目标格式
     * @return 返回当前日期时间字符串
     */
    public static String getAppointTime(final long time, final String pattern) {
        SimpleDateFormat theDate = new SimpleDateFormat(pattern);

        return theDate.format(time);
    }

    /**
     * 将字符串日期转为java.util.Date.
     *
     * @param aDate 需要转换的字符型日期
     * @param formatCode 传入日期的格式
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(final String aDate, final String formatCode) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(formatCode);
        return format.parse(aDate);
    }

    /**
     * 修改指定的时间，将时间向后延期指定的分钟数.
     *
     * @param beginDate 开始时间
     * @param minite 延后的分钟数
     * @return
     */
    public static String minuteAdd(final Date beginDate, final int minite) {
        DateFormat df = new SimpleDateFormat(DATE_TIME_PATTREN_CN);
        GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(beginDate);
        calendar.add(Calendar.MINUTE, minite);

        Date date = calendar.getTime();

        return df.format(date);
    }

    /**
     * 修改指定的时间，将时间向后延期指定的天数.
     *
     * @param beginDate 开始日期
     * @param day 延后的天数
     * @return
     */
    public static String dayAddStr(final Date beginDate, final int day) {
        DateFormat df = new SimpleDateFormat(DATE_TIME_PATTREN_CN);
        Date date = dayAdd(beginDate, day);

        return df.format(date);
    }

    /**
     * 修改指定的时间，将时间向后延期指定的天数.
     *
     * @param beginDate 开始日期
     * @param day 延后的天数
     * @return
     */
    public static Date dayAdd(final Date beginDate, final int day) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(beginDate);
        calendar.add(Calendar.DATE, day);

        Date date = calendar.getTime();

        return date;
    }

    /**
     * 修改指定的时间，将时间向后延期指定的月数.
     *
     * @param beginDate 开始日期
     * @param month 延后的月数
     * @return
     */
    public static Date monthAdd(final Date beginDate, final int month) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(beginDate);
        calendar.add(Calendar.MONTH, month);

        return calendar.getTime();
    }


    /**
     * 将字符串转换为日期类型.
     *
     * <p>
     * 转换方法能够自动识别的格式包括：
     * <li>yyyy-MM-dd
     * <li>yyyy-MM-dd HH:mm:ss
     * <li>yy年MM月dd日
     * <li>yyyy年MM月dd日
     * <li>HH:mm:ss
     * <li>HH时mm分ss秒
     * <li>yyyy年MM月dd日 HH:mm:ss
     * </p>
     *
     * @param value 目标时间
     * @return
     * @throws ParseException
     */
    public static Date converToDate(final String value) throws ParseException {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        List<KeyValueBean> regs = new ArrayList<KeyValueBean>();

        regs.add(new KeyValueBean("^\\d{4}-\\d{2}-\\d{2}$", DATE_PATTREN_CN));
        regs.add(new KeyValueBean("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", DATE_TIME_PATTREN_CN));
        regs.add(new KeyValueBean("^\\d{2}年\\d{2}月\\d{2}日$", "yy年MM月dd日"));
        regs.add(new KeyValueBean("^\\d{4}年\\d{2}月\\d{2}日$", "yyyy年MM月dd日"));
        regs.add(new KeyValueBean("^\\d{2}:\\d{2}:\\d{2}$", TIME_PATTREN_CN));
        regs.add(new KeyValueBean("^\\d{2}:\\d{2}$", TIME_PATTERN));
        regs.add(new KeyValueBean("^\\d{2}时\\d{2}分\\d{2}秒$", "HH时mm分ss秒"));
        regs.add(new KeyValueBean("^\\d{4}年\\d{2}月\\d{2}日 \\d{2}时\\d{2}分\\d{2}秒$", "yyyy年MM月dd日 HH:mm:ss"));
        for (KeyValueBean reg : regs) {
            Pattern p = Pattern.compile(reg.getKey());
            Matcher m = p.matcher(value);
            if (m.find()) {
                return convertStringToDate(reg.getValue(), value);
            }
        }
        return null;
    }

    /**
     * 获取从开始日期到结束日期间的工作日.
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param workDaysOfWeek 每周工作日标识
     * @param holiday 节假日标识
     * @param workDays 工作日标识
     * @return
     */
    public static List<Date> findWorkDays(final Date startDate, final Date endDate, 
            final String workDaysOfWeek,final List<String> holiday, final List<String> workDays) {

        List<Date> workDateList = new ArrayList<>();
        SimpleDateFormat dateFormater = new SimpleDateFormat(DATE_PATTREN_CN);

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        // 时分秒置0
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        while (endCalendar.after(startCalendar)) {
            String dayOfWeek = String.valueOf(startCalendar.get(Calendar.DAY_OF_WEEK));
            Date date = startCalendar.getTime();
            String dateStr = dateFormater.format(date);

            if ((workDaysOfWeek.contains(dayOfWeek) && !holiday.contains(dateStr)) || workDays.contains(dateStr)) {
                workDateList.add(date);
            }

            startCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return workDateList;
    }

    /**
     * 获取当前时间所在年的周数.
     *
     * @param date 目标日期
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);

        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取当前时间所在年的最大周数.
     *
     * @param year 目标年份
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

        return getWeekOfYear(c.getTime());
    }

    /**
     * 获取某年的第几周的开始日期.
     *
     * @param year 目标年份
     * @param week 周数
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 获取某年的第几周的结束日期.
     *
     * @param year 目标年份
     * @param week 周数
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 获取当前时间所在周的开始日期.
     *
     * @param date 目标日期
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }

    /**
     * 获取当前时间所在周的结束日期.
     *
     * @param date 目标日期
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        return c.getTime();
    }

    /**
     * 获取当月第一天,格式为XXXX-XX-XX 00:00:00.
     *
     * @param date 目标日期
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取当月最后一天，格式为XXXX-XX-XX 23:59:59.
     *
     * @param date 目标日期
     * @return
     * @throws ParseException
     */
    public static Date getLastDayOfMonth(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return sdf.parse(sdf.format(calendar.getTime()));
    }
}
