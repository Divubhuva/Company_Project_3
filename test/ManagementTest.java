package Proj2;

import org.junit.Test;

import static org.junit.Assert.*;

public class ManagementTest
{

    @Test
    public void calculatePayment()
    {
        Management m1 = new Management("Doe,Jane","CS",new Date("2/28/2012"),85000,1);
        Management m2 = new Management("Doe,John","CS",new Date("2/28/2012"),85000,2);
        Management m3 = new Management("Doe,John","ECE",new Date("2/28/2012"),85000,3);

        assertEquals(192.31,m1.getCompensation(),.01); //test case #1, test compensation for management role: Manager
        assertEquals(365.38,m2.getCompensation(),.01); //test case #2, test compensation for management role: Department Head
        assertEquals(461.54,m3.getCompensation(),.01); //test case #3, test compensation for management role: Director

        m1.calculatePayment();
        m2.calculatePayment();
        m3.calculatePayment();

        assertEquals(3461.54,m1.getPayment(),0.01); //test case #4, calculate payment for management role: Manager
        assertEquals(3634.61,m2.getPayment(),0.01); //test case #5, calculate payment for management role: Department Head
        assertEquals(3730.77,m3.getPayment(),0.01); //test case #6, calculate payment for management role: Director
    }
}