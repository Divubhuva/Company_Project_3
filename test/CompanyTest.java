

import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyTest
{

    @Test
    public void add()
    {
        Company testCompany = new Company();
        Employee emp1 = new Employee("Doe,Jane","CS",new Date("7/1/2020"));
        Employee emp2 = new Employee("Doe,Jane","ECE",new Date("1/1/2005"));
        Employee emp3 = new Employee("Doe,Jane","IT",new Date("2/28/2012"));
        Employee emp4 = new Employee("Doe,Jane","ECE",new Date("8/1/2020"));
        Employee emp5 = new Employee("Doe,Jane","CS",new Date("3/31/2020"));

        assertTrue(testCompany.add(emp1)); //test case #1, adding a new employee
        assertFalse(testCompany.add(emp1)); //test case #2, adding an existing employee

        testCompany.add(emp2);
        testCompany.add(emp3);
        testCompany.add(emp4);

        assertTrue(testCompany.add(emp5)); //test case #3, adding an employee that exceeds the bounds of the array and calls the grow() method
    }

    @Test
    public void remove()
    {
        Company testCompany = new Company();
        Employee emp1 = new Employee("Doe,Jane","CS",new Date("7/1/2020"));

        assertFalse(testCompany.remove(emp1)); //test case #4, removing an employee from an empty list

        testCompany.add(emp1);

        assertTrue(testCompany.remove(emp1)); //test case #5, removing an employee on the list
        assertFalse(testCompany.remove(emp1)); //test case #6, removing an employee not on the list
    }

    @Test
    public void setHours()
    {
        Company testCompany = new Company();
        Parttime emp1 = new Parttime("Doe,Jane","CS",new Date("7/1/2020"));

        assertFalse(testCompany.setHours(emp1)); //test case #7, set hours for a parttime employee that is not on the list

        testCompany.add(emp1);

        assertTrue(testCompany.setHours(emp1)); //test case #8, set hours for a parttime employee on the list
    }
}