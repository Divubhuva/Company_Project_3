package Proj2;

/**
 * This class defines the profile of an employee and encapsulates the data fields and methods of an employee.
 *
 * Date: 02/22/2021
 * @author Divya Bhuva, Dorothy Wu
 */
public class Profile
{
	/**
	 * name is represent the name of employee.
	 * */
    private String name; 
    
    /**
	 * department is represent the department of employee.
	 * */
    private String department; 
    /**
   	 * dateHired is represent hire date of employee.
   	 * */
    private Date dateHired;

    /**
     * This constructor creates a Profile object with the employee's name, department, and hiring date.
     *
     * @param _name an employee's name, which has type String.
     * @param _department an employee's department, which has type String.
     * @param _dateHired an employee's hiring date, which has type Date.
     */
    public Profile(String _name,String _department,Date _dateHired)
    {
        setName(_name);
        setDepartment(_department);
        setDateHired(_dateHired);
    }

    /**
     * Returns the employee's name as a String.
     * @return the employee's name as a String.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the employee's name as a String.
     * @param name is the employee's name, which is a String.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the department name as a String.
     * @return the department name as a String.
     */
    public String getDepartment()
    {
        return department;
    }

    /**
     * Sets the department name as a String.
     * @param department is the department name, which is a String.
     */
    public void setDepartment(String department)
    {
        this.department = department;
    }

    /**
     * Returns the date hired as a Date object.
     * @return the date hired as a Date object.
     */
    public Date getDateHired()
    {
        return dateHired;
    }

    /**
     * Sets the date hired as a Date object.
     * @param dateHired , the hired date, which is of type Date.
     */
    public void setDateHired(Date dateHired)
    {
        this.dateHired = dateHired;
    }

    /**
     * This method returns a String representation of a profile, in format [employee name]::[employee department]::[employee date hired]
     * @return a String representation of a profile in the following format: [employee name]::[employee department]::[employee date hired]
     */
    @Override
    public String toString()
    {
        String returnString = this.getName() +
                "::" + this.getDepartment() +
                "::" + this.getDateHired();
        return returnString;
    }

    /**
     * This method determines if the calling profile is equal to another profile.
     * It will compare name, department, and dateHired.
     *
     * @param obj , which should be of type Profile
     * @return TRUE if the name, department, and dateHired for the 2 profile objects are equal, and FALSE otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        boolean isEqual = false;
        if(!(obj instanceof Profile))
        {
            return isEqual;
        }

        Profile profileInfo = (Profile) obj;
        if (profileInfo.getName().equals (this.getName())
                &&
                profileInfo.getDepartment().equals (this.getDepartment())
                &&
                (profileInfo.getDateHired().compareTo(this.getDateHired()) == 0)) {
            isEqual = true;
        }
        return isEqual;
    }
}

