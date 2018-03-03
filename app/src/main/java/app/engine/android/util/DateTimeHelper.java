package app.engine.android.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateTimeHelper {

    public DateTimeHelper() {

    }

    /**
     * Parsing string into Dates
     * @param dateStr >> Date String
     * @param patternStr >> Pattern String
     * @return date
     * Example: stringToDate("09/12/2017", "dd/MM/yyyy");
     */
    public Date stringToDate(String dateStr, String patternStr){
        Date date = null;
        try{
            DateFormat format = new SimpleDateFormat(patternStr, Locale.ENGLISH);
            date = format.parse(dateStr);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }//end of stringToDate method


    /**
     * Converts Date to String
     * default Date pattern use dd/MM/yyyy
     * @param date Date parameter
     * @param patternStr pattern String
     * @return String
     */
    public String dateToString(Date date, String patternStr){
        String dateStr;
        String pattern = patternStr.length() < 1 ? "dd/MM/yyyy" : patternStr;
        DateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        dateStr = format.format(date);
        return dateStr;
    }//end of dateToString method

    /**
     * Get year from a Date
     * @param date Date param
     * @return int
     */
    public int getYearFromDate(Date date){
        int year;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        year = calendar.get(Calendar.YEAR);
        return year;
    }//end of getYearFromDate method

    /**
     * Get month from a Date
     * @param date Date param
     * @return int
     */
    public int getMonthFromDate(Date date){
        int month;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }//end of getMonthFromDate method


    /**
     * Get day from a Date
     * @param date date param
     * @return int
     */
    public int getDayFromDate(Date date){
        int day;
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }//end of getDayFromDate method


    /**
     * Get Month name from a date
     * @param date date param
     * @return String
     */
    public String getMonthNameFromDate(Date date){
        Format formatter = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        return formatter.format(date);
    }//end of getMonthNameFromDate method

    public String getFormattedDateForAvailableFlight(String dateStr, String patternStr){
        DateTimeHelper dateTimeHelper = new DateTimeHelper();
        Date date = dateTimeHelper.stringToDate(dateStr, patternStr);

        Format formatter1 = new SimpleDateFormat("MMM", Locale.ENGLISH);
        String monthName = formatter1.format(date);

        Format formatter2 = new SimpleDateFormat("dd", Locale.ENGLISH);
        String dayNum = formatter2.format(date);

        String day = new SimpleDateFormat("E", Locale.ENGLISH).format(dateTimeHelper.stringToDate(dateStr, patternStr));
        return day + " " + monthName + " " + dayNum;
    }


}//end of DateTimeHelper class
