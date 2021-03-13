
/**
 * This class extends the Employee class and includes specific data and operations in regards to a part-time employee..
 *
 * date: 02/22/2021
 * @author Divya Bhuva, Dorothy Wu
 */
public class Parttime extends Employee
{
	/**
	 * hourlyRate is show hourly rate for part time Employee. 
	 * */
    private double hourlyRate = 0.0;
    /**
	 * hoursWork is show work hour of Employee. 
	 * */
    private double hoursWork = 0.0;

    /**
     * Creates an instance of Parttime using name, department,and hiring date.
     *
     * @param inputName an employee's name, which is of type String.
     * @param inputDepartment an employee's department, which is of type String.
     * @param inputDateHired an employee's hiring date, which is of type Date.
     */
    public Parttime(String inputName, String inputDepartment, Date inputDateHired)
    {
        super(inputName, inputDepartment, inputDateHired);
    }

    /**
     * Creates an instance of Parttime using name, department, hiring date, and hourly rate.
     *
     * @param inputName an employee's name, which is of type String.
     * @param inputDepartment an employee's department, which is of type String.
     * @param inputDateHired an employee's hiring date, which is of type Date.
     * @param inputHourlyRate an employee's hourly rate, which is of type double.
     */
    public Parttime(String inputName, String inputDepartment, Date inputDateHired, double inputHourlyRate)
    {
        super(inputName, inputDepartment, inputDateHired);
        hourlyRate = inputHourlyRate;
    }

    /**
     * This method returns a String representation of a Parttime Employee.
     * @return a String representation of a Parttime Employee.
     */
    @Override
    public String toString()
    {
        String returnString = super.toString() + "::PART TIME::Hourly Rate $"+super.getTwoUpToTwoDecimalPoint(hourlyRate)+"::Hours worked this period: "+(int)hoursWork;
        return returnString;
    }

    /**
     * This method returns if the calling Object is of type Parttime.
     * @param obj , which should be of type Object.
     * @return TRUE if the Object is of type Parttime; otherwise FALSE.
     */
    @Override
    public boolean equals(Object obj)
    {
        boolean isEqual = false;
        if(!(obj instanceof Parttime))
        {
            return isEqual;
        }
        isEqual = super.equals(obj);

        return isEqual;

    }

    /**
     * Calculates the payment for a Parttime Employee.
     *
     */
    @Override
    public void calculatePayment()
    {
        final double regularPayRateHour = 80;
        if (hoursWork <= regularPayRateHour)
        {
            super.setPayment(hourlyRate * hoursWork);
        }
        else
        {
            double payment = hourlyRate * regularPayRateHour;
            double extraHour = hoursWork - regularPayRateHour;
            double HourlyRateIncereaseFactor = 1.5;
            double newHourlyRate = hourlyRate * HourlyRateIncereaseFactor;
            payment = payment + (newHourlyRate*extraHour);
            super.setPayment(payment);
        }
    }

    /**
     * Sets the work hours for a Parttime Employee.
     * The hours can only be set if the work hours are less than or equal to the maximum allowable work hours in a pay period.
     *
     * @param workingHours the work hours of a Parttime Employee, which has type double.
     * @return FALSE if the work hours are greater than the maximum allowable work hours in a pay period; otherwise TRUE.
     */
    public boolean setWorkHours(double workingHours)
    {
        boolean set = false;
        final double MaxHour = 100;
        if (workingHours <= MaxHour)
        {
            hoursWork = workingHours;
            set= true;
        }
        return set;
    }

    /**
     * Returns the work hours of a Parttime Employee.
     * @return the work hours of a Parttime Employee as a double.
     */
    public double getWorkHours()
    {
        return hoursWork;
    }
}
