package cn.com.eju.deal.common.util;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


public class DateUtils {
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YY_MM_DD = "yy-MM-dd";
    public static final String FORMAT_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_MM = "yyyy-MM";

    private static Pattern YYYY_MM_DD_REG = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\-\\s]?((((0?" + "[13578])|(1[02]))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))" + "|(((0?[469])|(11))[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|" + "(0?2[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12" + "35679])|([13579][01345789]))[\\-\\-\\s]?((((0?[13578])|(1[02]))" + "[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))" + "[\\-\\-\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\-\\s]?((0?[" + "1-9])|(1[0-9])|(2[0-8]))))))");

    public static boolean checkDateStrAsYYYYMMDD(String dateStr) {
        return YYYY_MM_DD_REG.matcher(dateStr).matches();
    }

    public static long getDateDiffByDay(String time1, String time2) {
        long quot = 0;
        SimpleDateFormat ft = new SimpleDateFormat(YYYY_MM_DD);
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            quot = date1.getTime() - date2.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return quot;
    }

    public static Integer getDateDiffByMonth(String start, String end) {
        SimpleDateFormat ft = new SimpleDateFormat(YYYY_MM_DD);
        try {
            Date startDate = ft.parse(start);
            Date endDate = ft.parse(end);
            Calendar instance = Calendar.getInstance();
            instance.setTime(startDate);
            int startYear = instance.get(Calendar.YEAR);
            int startMonth = instance.get(Calendar.MONTH);
            instance.setTime(endDate);
            int endYear = instance.get(Calendar.YEAR);
            int endMonth = instance.get(Calendar.MONTH);
            return (endYear - startYear) * 12 + endMonth - startMonth;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDateByDiffMonths(int diffMonths, String time) {
        SimpleDateFormat ft = new SimpleDateFormat(YYYY_MM_DD);
        try {
            Date date = ft.parse(time);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(Calendar.MONTH, diffMonths);
            int year = instance.get(Calendar.YEAR);
            int month = instance.get(Calendar.MONTH) + 1;
            return year + (month < 10 ? "-0" + month : "-" + month) + "-01";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDateDiffNDay(String strDate, int n) {
        SimpleDateFormat ft = new SimpleDateFormat(YYYY_MM_DD);
        try {
            Date dDate = ft.parse(strDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + n);
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String sDate, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        if (StringUtils.isNotEmpty(sDate)) {
            try {
                return format.parse(sDate);
            } catch (ParseException e) {
                e.printStackTrace(); // To change body of catch statement use
                // File | Settings | File Templates.
                return null;
            }
        }
        return null;
    }

    public static Date parseDate(String sDate) {
        return parseDate(sDate, YYYY_MM_DD);
    }

    public static String formatDate(Date sDate) {
        DateFormat format = new SimpleDateFormat(YYYY_MM_DD);
        return format.format(sDate);
    }

    public static String formatDateSimpleYear(Date sDate) {
        DateFormat format = new SimpleDateFormat(YY_MM_DD);
        return format.format(sDate);
    }

    public static String formatDate(Date sDate, String formatStr) {
        if (sDate == null)
            return "";
        DateFormat format = new SimpleDateFormat(formatStr);
        return format.format(sDate);
    }

    public static String defaultFormatDate(Date sDate) {
        return formatDate(sDate, FORMAT_STR);
    }

    public static boolean isValidDate(String dateStr, String pattern) {
        return formatDate(parseDate(dateStr, pattern), pattern).equals(dateStr);
    }

    public static String getStringCurrentDateTime() {
        Calendar rightNow = Calendar.getInstance();
        int intYear = rightNow.get(Calendar.YEAR);

        int intMonth = rightNow.get(Calendar.MONTH) + 1;
        String strMonth = null;
        if (intMonth < 10) {
            strMonth = "0" + intMonth;
        } else {
            strMonth = "" + intMonth;
        }

        int intDate = rightNow.get(Calendar.DATE);
        String strDate = null;
        if (intDate < 10) {
            strDate = "0" + intDate;
        } else {
            strDate = "" + intDate;
        }

        int intHour = rightNow.get(Calendar.HOUR_OF_DAY);
        String strHour = null;
        if (intHour < 10) {
            strHour = "0" + intHour;
        } else {
            strHour = "" + intHour;
        }

        int intMinute = rightNow.get(Calendar.MINUTE);
        String strMinute = null;
        if (intMinute < 10) {
            strMinute = "0" + intMinute;
        } else {
            strMinute = "" + intMinute;
        }

        int intSecond = rightNow.get(Calendar.SECOND);
        String strSecond = null;
        if (intSecond < 10) {
            strSecond = "0" + intSecond;
        } else {
            strSecond = "" + intSecond;
        }

        return intYear + "-" + strMonth + "-" + strDate + " " + strHour + ":"
                + strMinute + ":" + strSecond;

    }

    public static int getDateInQuarter(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        if (month <= 3) {
            return 1;
        } else if (month <= 6) {
            return 2;
        } else if (month <= 9) {
            return 3;
        } else {
            return 4;
        }
    }

    public static Date getThisDayFirstDayInQuarter(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        String firstMonth = "";
        if (month <= 3) {
            firstMonth = "01";
        } else if (month <= 6) {
            firstMonth = "04";
        } else if (month <= 9) {
            firstMonth = "07";
        } else {
            firstMonth = "10";
        }
        int year = c.get(Calendar.YEAR);
        String dateStr = year + "-" + firstMonth + "-01";
        return parseDate(dateStr);
    }

    public static String getDateInQuarterStr(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        month = month + 1;
        if (month <= 3) {
            return "一季度";
        } else if (month <= 6) {
            return "二季度";
        } else if (month <= 9) {
            return "三季度";
        } else {
            return "四季度";
        }
    }

    public static long subtractionDateForDay(Date subtrahend, Date minuend) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = sdf.parse(sdf.format(subtrahend));
        Date end = sdf.parse(sdf.format(minuend));
        return (begin.getTime() - end.getTime()) / (1000 * 60 * 60 * 24);
    }

    public static String convertDateFormat(String dateString,
                                           String sourceDateFormat, String destDateFormat)
            throws ParseException {
        DateFormat dfOne = new SimpleDateFormat(sourceDateFormat);
        DateFormat dfTwo = new SimpleDateFormat(destDateFormat);
        Date date = dfOne.parse(dateString);
        return dfTwo.format(date);
    }
    
    /**
     * zhenggang.Huang
     * date 2018-11-13 
     * desc : 对比两个字符时间(年月)大小
     * @param beginDate yyyy-MM-dd HH:mm:ss
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static String contrastDate(String beginDate, String endDate,String pattern) throws ParseException{
//    	String begDate = beginDate.substring(0, beginDate.lastIndexOf("-"));
//    	String enDate = endDate.substring(0, endDate.lastIndexOf("-"));
    	DateFormat df = new SimpleDateFormat(pattern);
    	if(df.parse(beginDate).getTime() >= df.parse(endDate).getTime()) {
    		return "1";
    	}else {
    		return "0";
    	}
    }
    
    public static void main(String[] args) throws ParseException {
    	String begDate = "2018-12-11 01:00:00";
    	String enDate = "2018-12-11 02:00:00";
    	String s = contrastDate(begDate,enDate,FORMAT_STR);
    	System.out.println(s);
	}
}
