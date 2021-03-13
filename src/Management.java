

/**
 * This class extends the Fulltime class and includes specific data and operations in regards to a full-time employee with a management role.
 *
 * date: 02/22/2021
 * @author Divya Bhuva, Dorothy Wu
 */
public class Management extends Fulltime
{

    /**
     * manager is represent the type of role is Manager.
     * */
    private static final int MANAGER = 1;
    /**
     * departmentHead is represent the type of role is Department Head.
     * */
    private static final int DEPARTMENT_HEAD = 2;
    /**
     * director is represent the type of role is Director.
     * */
    private static final int DIRECTOR = 3;
    /**
     * typeOfManagementRole shows type of management role of Management full time employes.
     * */
    private int typeOfManagementRole;

    /**
     * MANAGER_COMPENSATION represents the manager's pay
     */
    private static final double MANAGER_COMPENSATION = 5000.00;

    /**
     * DEPARTMENT_HEAD_COMPENSATION represents the department head's pay
     */
    private static final double DEPARTMENT_HEAD_COMPENSATION = 9500.00;

    /**
     * DIRECTOR_COMPENSATION represents the director's pay
     */
    private static final double DIRECTOR_COMPENSATION = 12000.00;


    /**
     * Creates an instance of Management using name, department, hiring date, annual salary, and manager type.
     *
     * @param inputName an employee's name, which is of type String.
     * @param inputDepartment an employee's department, which is of type String.
     * @param inputDateHired an employee's hiring date, which is of type Date.
     * @param inputAnnualSalary an employee's annual salary, which is of type double.
     * @param roleType an employee's management type, which is of type int.
     */
    public Management(String inputName, String inputDepartment, Date inputDateHired, double inputAnnualSalary, int roleType)
    {
        super(inputName, inputDepartment, inputDateHired, inputAnnualSalary);
        typeOfManagementRole = roleType;
        super.setCompensation(calculateCompensation());
    }

    /**
     * This method returns a String representation of a Fulltime Employee with a Management role.
     * @return the String representation of a Fulltime Employee with a Management role.
     */
    @Override
    public String toString()
    {
        String roleType = "";
        if (typeOfManagementRole == MANAGER)
        {
            roleType = "Manager";
        }
        if (typeOfManagementRole == DEPARTMENT_HEAD)
        {
            roleType = "Department Head";
        }
        if (typeOfManagementRole == DIRECTOR)
        {
            roleType = "Director";
        }

        String returnString = super.toString() + "::"+ roleType + " Compensation $" + super.getTwoUpToTwoDecimalPoint(super.getCompensation());
        return returnString;
    }

    /**
     * This method returns if the calling Object is of type Management.
     * @param obj , which should be of type Object.
     * @return TRUE if the Object is of type Management; otherwise FALSE.
     */
    @Override
    public boolean equals(Object obj)
    {

        boolean isEqual = false;
        if(!(obj instanceof Management))
        {
            return isEqual;
        }
        isEqual = super.equals(obj);
        return isEqual;

    }

    /**
     * This calculates the payment of a manager including the additional compensation according to the Management role.
     */
    @Override
    public void calculatePayment()
    {
        super.setCompensation(calculateCompensation());
        super.calculatePayment();
    }

    /**
     * This returns the amount of additional compensation a manager receives based on their Management role.
     * @return the amount of additional compensation a manager receives based on their Management role, which is of type double.
     */
    private double  calculateCompensation()
    {
        double annuallyCompensation  = 0.0;
        double Compensation;
        if (typeOfManagementRole == MANAGER)
        {
            annuallyCompensation = MANAGER_COMPENSATION;
        }
        if (typeOfManagementRole == DEPARTMENT_HEAD)
        {
            annuallyCompensation = DEPARTMENT_HEAD_COMPENSATION;
        }
        if (typeOfManagementRole == DIRECTOR)
        {
            annuallyCompensation = DIRECTOR_COMPENSATION;
        }
        Compensation = annuallyCompensation/super.getNumberOfPayPeriod();
        return Compensation;
    }

}