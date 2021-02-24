package Proj2;
import java.text.DecimalFormat;

/**
 * This class defines the common data and operations for all employee type; each employee has a profile that uniquely identifies the employee.
 *
 * date: 02/22/2021
 * @author Divya Bhuva, Dorothy Wu
 */
class Employee
{
	/**
	 * empProfile shows information about Employee profile
	 * */
    private Profile empProfile;


    /**
     * payment is show amount of payment.
     * */
    private double payment = 0.0;

    /**
     * This creates an Employee object with the employee name, department, and date hired.
     *
     * @param inputName an employee's name, which has type String.
     * @param inputDepartment an employee's department, which has type String.
     * @param inputDateHired an employee's hiring date, which has type Date.
     */
    public Employee(String inputName, String inputDepartment, Date inputDateHired)
    {
        empProfile = new Profile(inputName, inputDepartment, inputDateHired);
    }

    /**
     * This method returns a String representation of an employee.
     * @return a String representation of an employee.
     */
    @Override
    public String toString()
    {
        String returnString = empProfile.toString() + "::Payment $"+ getTwoUpToTwoDecimalPoint(getPayment());
        return returnString;
    }

    /**
     * This method determines if the calling Object is of type Employee
     * @param obj , which should be of type Object.
     * @return TRUE if the Object is of type Employee; otherwise, FALSE.
     */
    @Override
    public boolean equals(Object obj)
    {
        boolean isEqual = false;
        if(!(obj instanceof Employee))
        {
            return isEqual;
        }
        Employee emp = (Employee) obj;

        if (emp.empProfile == null)
        {
            return isEqual;
        }

        isEqual = empProfile.equals(emp.empProfile);
        return isEqual;
    }

    /** 
     * This is the calculatePayment method template.
     * It is meant to be overwritten.
     */
    public void calculatePayment()
    {
    }

    /**
     * Sets the employee's payment as a double.
     * @param empPayment is the employee's payment, which has type double.
     */
    protected void setPayment(double empPayment)
    {
        payment = empPayment;
    }
    
    /**
     * Returns the employee's payment.
     * @return the employee's payment.
     */
    public double getPayment()
    {
        return payment;
    }


    /**
     * Returns the employee's profile as a String.
     * @return the employee's profile as a String.
     */
    public Profile getProfileInfo()
    {
        return empProfile;
    }

    /**
     * This formats the payment to have two decimal places.
     * @param val , which should be of type Double.
     * @return formatted value with two decimal places.
     */
    public String getTwoUpToTwoDecimalPoint(double val)
    {
        DecimalFormat df2 = new DecimalFormat("###,###,##0.00");
        return df2.format(val);
    }

}