

/**
 * This is the array-based container class that implements the employee database.
 *
 * date: 02/22/2021
 * @author Divya Bhuva, Dorothy Wu
 */
public class Company
{
	/**
	 * emplist uses to access the database.
	 * */
    private Employee[] emplist;
    /**
	 * numEmployee shows number of Employees in data base.
	 * */
    private int numEmployee;
    /**
     * INITIAL_CAPACITY shows initial capacity of container. 
     * */
    private static final int INITIAL_CAPACITY = 4;
    /**
     * GROW_RATE shows growing capacity of container. 
     * */
    private static final int GROW_RATE = 4;

    /**
     * Default constructor to create an empty list.
     * It sets numEmployee to 0 and books to INITIAL_CAPACITY.
     */
    public Company()
    {
        this.numEmployee = 0;

        emplist = new Employee[INITIAL_CAPACITY];
    }

    /**
     * A helper method to find an employee profile if it exists in the list.
     *
     * @param employee of type Employee
     * @return TRUE if employee is found; FALSE otherwise.
     */
    private int find(Employee employee)
    {
        int employeeIndex  = -1;

        if(this.numEmployee == 0)
        {
            return employeeIndex;
        }

        for( int index = 0; index < this.numEmployee;index++)
        {
            boolean found = emplist[index].getProfileInfo().equals(employee.getProfileInfo());
            if (found)
            {
                employeeIndex = index;
                break;
            }
        }
        return employeeIndex;
    }

    /**
     * This is a helper method to grow the list capacity by the GROW_RATE
     */
    private void grow()
    {
        Employee[] tempHolder = new Employee[this.numEmployee + GROW_RATE];
        for (int index = 0; index < this.numEmployee; index++)
        {
            tempHolder[index] = this.emplist[index];
        }
        this.emplist = tempHolder;
    }

    /**
     * This adds an employee to the list.
     * However, before adding an employee to the list it checks if the profile exists by calling the helper method find().
     * If the list is full it calls the helper method grow() to increase the list size, then adds the employee to the list.
     *
     * @param employee is of type Employee
     * @return TRUE if employee is added; otherwise, returns FALSE if profile already exists
     */
    public boolean add(Employee employee)
    {
        boolean empIsAdded = false;
        int notFoundIndex = -1;
        if (find(employee) != notFoundIndex)
        {
            return empIsAdded;
        }

        if(emplist.length == this.numEmployee)
        {
            grow();
        }
        this.emplist[this.numEmployee] = employee;
        this.numEmployee++;
        empIsAdded = true;
        return empIsAdded;
    }


    /**
     * This removes an employee from the list while maintaining the original sequence.
     * It calls the helper method find() and find the index of the employee to be removed.
     *
     * @param employee of type Employee.
     * @return TRUE if the employee was successfully removed from the list; otherwise returns FALSE.
     */
    public boolean remove(Employee employee)
    {

        int empIndex = find(employee);
        int notFoundIndex = -1;

        if(empIndex == notFoundIndex )
        {
            return false;
        }


        numEmployee--;
        for(int index = empIndex; index < numEmployee; index++)
        {
            emplist[index] = emplist[index+1];
        }
        return true;

    }

    /**
     * Sets the working hours for a part time employee if the employee exists
     *
     * @param employee of type Employee.
     * @return TRUE if the work hours are set; otherwise, FALSE if employee profile does not exists
     */
    public boolean setHours(Employee employee)
    {
        boolean workingHourSet = false;

        int empIndex = find(employee);
        int notFoundIndex = -1;

        if(empIndex == notFoundIndex )
        {
            return workingHourSet;
        }

        Parttime temp = (Parttime)emplist[empIndex];
        workingHourSet = temp.setWorkHours(((Parttime)employee).getWorkHours());

        if (workingHourSet)
        {
            emplist[empIndex] = temp;
        }

        return workingHourSet;
    }

    /**
     * This processes/computes payments for all employees.
     */
    public void processPayments()
    {

        for( int index = 0; index < this.numEmployee;index++)
        {
            emplist[index].calculatePayment();
        }
    }

    /**
     * This prints the earning statements for employee.
     * @param Int index of employee index database. 
     * @return String show earning statement of employees at particular index.
     */
    public String print(int index)
    {
    	String returnString = "";
        if (!isDataBaseEmpty())
        {
            
        	if (index >= 0 &&  index < this.numEmployee ) {
            	returnString = emplist[index].toString();
            } 
        }
        return returnString;
    }

    /**
     * This prints the earning statements of employees by department.
     * @param Int index of employee after sorting by department. 
     * @return String show earning statement of employees at particular index.
     */
    public String printByDepartment(int index)
    {
    	String returnString = "";
        if (!isDataBaseEmpty())
        {
            sort(true);
            
            if (index >= 0 &&  index < this.numEmployee ) {
            	returnString = emplist[index].toString();
            } 
        }
        return returnString;
    }

    /**
     * This prints the earning statements of  employees by date hired.
     * @param Int index of employee after sorting by date hired. 
     * @return String show earning statement of employees at particular index.
     */
    public String printByDate(int index)
    {
    	String returnString = "";
        if (!isDataBaseEmpty())
        {
            sort(false);
            if (index >= 0 &&  index < this.numEmployee ) {
            	returnString = emplist[index].toString();
            } 
        }
        return returnString;
    }

    /**
     * This method uses selection sort to sort by department or date hired.
     * @param byDepartment is a Boolean which if TRUE will sort by department.
     */
    public void sort(boolean byDepartment)
    {
        for (int i = 0; i < numEmployee-1; i++)
        {
            int minIdx = i;
            for (int j = i+1; j < numEmployee; j++)
            {
                if (byDepartment)
                {
                    String currSerialNum = emplist[j].getProfileInfo().getDepartment();
                    String minSerialNum = emplist[minIdx].getProfileInfo().getDepartment();

                    if (currSerialNum.compareTo(minSerialNum) < 0 )
                    {
                        minIdx = j;
                    }
                }
                else
                {
                    Date currDate = emplist[j].getProfileInfo().getDateHired();
                    Date minDate = emplist[minIdx].getProfileInfo().getDateHired();

                    if (currDate.compareTo(minDate) == 1)
                    {
                        minIdx = j;
                    }
                }
            }

            Employee temp = emplist[minIdx];
            emplist[minIdx] = emplist[i];
            emplist[i] = temp;
        }
    }

    /**
     *  Checks if the list is empty.
     * @return TRUE if list is empty; otherwise FALSE.
     */
    public boolean isDataBaseEmpty()
    {
        if(this.numEmployee == 0)
        {
            return true;
        }
        return false;
    }
    
    /**
     * return number of Employees in Data base.
     * @return int total number of employees in database.
     */
    public int getNumberOfEmployee(){
    	return this.numEmployee;
    }
    
    /**
     * 
     */
    public void exportDatabase() {
    	
    }
    
    /**
     * 
     */
    public void importDatabase() {
    	
    }
}