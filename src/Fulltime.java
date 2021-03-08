

/**
 * This class extends the Employee class and includes specific data and operations in regards to a full-time employee.
 *
 * date: 02/22/2021
 * @author Divya Bhuva, Dorothy Wu
 */
public class Fulltime extends Employee
{
	/**
	 * annualSalary is show salary of fulltime Employee 
	 * */
    private double annualSalary = 0.0;
    /**
	 * numberOfPayPeriod is show  number of pay period of salary.
	 * */
    private double numberOfPayPeriod = 26.00;
    /**
     * Compensation is help to represent the Compensation of management role Employee.
     * */
    private double Compensation = 0.0;

    /**
     * Creates an instance of Fulltime using name, department,hiring date, and annual salary.
     *
     * @param inputName an employee's name, which has type String.
     * @param inputDepartment an employee's department, which has type String.
     * @param inputDateHired an employee's hiring date, which has type Date.
     * @param inputAnnualSalary an employee's annual salary, which has type double.
     */
    public Fulltime(String inputName, String inputDepartment, Date inputDateHired, double inputAnnualSalary)
    {
        super(inputName, inputDepartment, inputDateHired);
        annualSalary = inputAnnualSalary;
    }

    /**
     * This method returns a String representation of a Fulltime Employee.
     * @return the string representation of a Fulltime Employee.
     */
    @Override
    public String toString()
    {
        String returnString = super.toString() + "::FULL TIME::Annual Salary $" + super.getTwoUpToTwoDecimalPoint(annualSalary);
        return returnString;
    }

    /**
     * This method returns if the calling Object is of type Fulltime.
     * @param obj , which should be of type Object.
     * @return TRUE if the Object is of type Fulltime; otherwise, FALSE.
     */
    @Override
    public boolean equals(Object obj)
    {

        boolean isEqual = false;
        if(!(obj instanceof Fulltime))
        {
            return isEqual;
        }
        isEqual = super.equals(obj);
        return isEqual;

    }

    /**
     * This calculates the payment given each pay period.
     */
    @Override
    public void calculatePayment()
    {
        super.setPayment((annualSalary /getNumberOfPayPeriod())+ Compensation);
    }

    /**
     * Returns the pay period as a double.
     * @return the pay period as a double.
     */
    public double getNumberOfPayPeriod()
    {
        return numberOfPayPeriod;
    }

    /**
     * Returns the compensation as a double.
     * @return the compensation as a double.
     */
    public double getCompensation()
    {
        return Compensation;
    }

    /**
     * Sets the compensation as a double.
     * @param val is the compensation, which has type double.
     */
    public void setCompensation(double val)
    {
        Compensation = val;
    }
}
