
import java.util.Calendar;

/**
 * This class defines the properties of a Date object.
 *
 * Date: 02/22/2021
 * @author Divya Bhuva, Dorothy Wu
 *
 */
public class Date implements Comparable<Date>
{	/**
	 * It is represent the Year of date object.
	 * */
    private int year;
    /**
	 * It is represent the month of date object.
	 * */
    private int month;
    /**
	 * It is represent the day of date object.
	 * */
    private int day;

    /**
     * MIN_YEAR is represent Date object is valid until 1900 Year.
     * */
    private static final int MIN_YEAR = 1900;
    /**
     * MONTH_OFFSET is Month  offset due to Calendar class.
     * */
    private static final int MONTH_OFFSET = 1;
    /**
     * DELIMS is separator to read month day and year. 
     * */
    private static final String DELIMS = "[/]";
    /**
     * MAX_DATE_SIZE is help to check number of require token in Date String. 
     * */
    private static final int MAX_DATE_SIZE = 3;
    /**
     * DEFAULT_VAL is default value of input of date string is not correct.  
     * */
    private static final int DEFAULT_VAL = -1;
    /**
     * QUADRENNIAL is help to figure out leap year. 
     * */
    public static final int QUADRENNIAL = 4;
    /**
     * CENTENNIAL is help to figure out leap year. 
     * */
    public static final int CENTENNIAL = 100;
    /**
     * QUATERCENTENNIAL is help to figure out leap year. 
     * */
    public static final int QUATERCENTENNIAL = 400;

    /**
     * Returns the year as an int.
     *
     * @return the year, which is type int.
     */
    public int getYear()
    {
        return year;
    }


    /**
     * Returns the month as an int.
     *
     * @return the month, which is type int.
     */
    public int getMonth()
    {
        return month;
    }


    /**
     * Returns the day as an int.
     *
     * @return the day, which is type int.
     */
    public int getDay()
    {
        return day;
    }

    /**
     * This constructor creates a Date object with a date String.
     * The constructor takes in a String, in format mm/dd/yyyy, and creates a Date object with the corresponding value.
     * Note that the months start with 0 for January (see calendar class).
     *
     * @param date is the date, which is type String, in the format mm/dd/yyyy.
     */
    public Date(String date)
    {
        String[] tokens = date.split(DELIMS);

        this.month = DEFAULT_VAL;
        this.day = DEFAULT_VAL;
        this.year = DEFAULT_VAL;

        if (tokens.length == MAX_DATE_SIZE)
        {
            try
            {
                this.month = Integer.parseInt(tokens[0]) - MONTH_OFFSET; //java starts with month jan as 0 so must subtract 1
                this.day = Integer.parseInt(tokens[1]);
                this.year = Integer.parseInt(tokens[2]);
            } catch (NumberFormatException e)
            {
                this.month = DEFAULT_VAL;
                this.day = DEFAULT_VAL;
                this.year = DEFAULT_VAL;
            }
        }
    }

    /**
     * This is the no-argument Date Constructor that creates a Date object with today’s date.
     * Note that the months start with 0 for January (see calendar class).
     */
    public Date()
    {
        Calendar rightNow = Calendar.getInstance();
        this.month = rightNow.get(Calendar.MONTH);
        this.day = rightNow.get(Calendar.DATE);
        this.year = rightNow.get(Calendar.YEAR);
    }


    /**
     * This checks if the date is of valid input.
     * It will test for the following:
     * A date with the year less than 1900 or a date beyond today's date is invalid.
     * The months January, March, May, July, August, October and December must have 31 days;
     * April, June, September and November must have 30 days;
     * February must be either 28 or 29 days, based on the leap year rules.
     *
     * @return TRUE if the Date's year, month, and day are within the range stated, FALSE otherwise.
     */
    public boolean isValid()
    {
        if (this.year < MIN_YEAR
                || (
                ((this.isBeforeAnotherDate(new Date()) == false)
                        && (this.isEqualToAnotherDate(new Date()) == false))
        )
        )
        {
            return false;
        }

        return isMonthAndDayValid();
    }

    /**
     * Checks if month and day are valid.
     * First, test to see if the month is between the ranges of Calendar.January and Calendar.December.
     * If it is a valid month, check if the day is between 1 and maxDay, based on the month.
     *
     * @return TRUE if the month is between Calendar.January and Calendar.December and between minDay and maxDay for given valid month; FALSE otherwise
     */
    private boolean isMonthAndDayValid()
    {
        boolean isValidMonthDay = false;
        boolean isLeapYear = checkLeapYear(this.year);

        int maxDay = 0;
        int minDay = 0;
        // this checks the month range
        if (this.month < Calendar.JANUARY || this.month > Calendar.DECEMBER)
        {
            return false;
        }

        // set the max day that a month can be
        switch (this.month)
        {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                maxDay = 31;
                break;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                maxDay = 30;
                break;
            case Calendar.FEBRUARY:
                if (isLeapYear) maxDay = 29;
                else maxDay = 28;
                break;

        }

        if (this.day > minDay && this.day <= maxDay)
        {
            isValidMonthDay = true;
        }
        return isValidMonthDay;
    }

    /**
     * This method checks if a year is a leap year.
     * It checks for leap year based on the following rules:
     * Step 1. If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
     * Step 2. If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
     * Step 3. If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
     * Step 4. The year is a leap year.
     * Step 5. The year is not a leap year.
     *
     * @param testYear is an int.
     * @return TRUE if the year is a leap year, FALSE otherwise.
     */
    private boolean checkLeapYear(int testYear)
    {

        boolean isLeapYear = false;

        if (testYear % QUADRENNIAL == 0)
        {
            if (testYear % CENTENNIAL == 0)
            {
                if (testYear % QUATERCENTENNIAL == 0)
                {
                    isLeapYear = true;
                }
            }
            else
            {
                isLeapYear = true;
            }
        }
        return isLeapYear;
    }

    /**
     * Returns the String representation of the Date object.
     * Note that it adjusts for the month offset from the Calendar class.
     * January will be returned as 1 instead of 0, and so on.
     *
     * @return the String representation of the Date object, in mm/dd/yyyy format
     */
    @Override
    public String toString()
    {
        return String.valueOf(this.month + MONTH_OFFSET) + "/" + String.valueOf(this.day) + "/" + String.valueOf(this.year);
    }

    /**
     * This method compares the current Date with another Date object.
     * It determines if the calling Date object is equal to the input Date.
     *
     * @param compareDate is a Date object.
     * @return TRUE if the calling Date is equal to the input Date; otherwise, returns FALSE.
     */
    public boolean isEqualToAnotherDate(Date compareDate)
    {
        if (this.year == compareDate.getYear() && this.month == compareDate.getMonth() && this.day == compareDate.getDay())
        {
            return true;
        }
        return false;
    }

    /**
     * This method compares the current Date with another Date object.
     * It determines if the calling Date object is set before the input Date.
     *
     * @param compareDate is a Date object.
     * @return TRUE if the calling Date is before the input Date; otherwise, returns FALSE.
     */
    public boolean isBeforeAnotherDate(Date compareDate)
    {
        if (this.year < compareDate.getYear()
                || (this.year == compareDate.getYear() && this.month < compareDate.getMonth())
                || (this.year == compareDate.getYear() && this.month == compareDate.getMonth() && this.day < compareDate.getDay()))
        {
            return true;
        }
        return false;
    }


    /**
     * This will compare dates.
     * If the calling date is equal to the input date it returns a value of 0.
     * Else if the calling date is before the function returns a value of 1.
     * Else the date is after and the function returns a value of -1.
     * @param date of type Date.
     * @return 0 if the dates are equal; 1 if the date is before; -1 if the date is after
     */
    @Override
    public int compareTo(Date date)
    {

        int returnValue;
        if (isEqualToAnotherDate(date))
        {
            returnValue = 0;
        }
        else if (isBeforeAnotherDate(date))
        {
            returnValue = 1;
        }
        else
        {
            returnValue = -1;
        }

        return returnValue;
    }

}