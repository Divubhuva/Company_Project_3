//
//import java.util.Scanner;
//import java.util.StringTokenizer;
//
///**
// * This is the user interface class that reads/writes from/to the console.
// * This class handles all exceptions and invalid data before it calls the methods in Company class to complete the associated commands
// *
// * Date: 02/22/2021
// * @author Divya Bhuva, Dorothy Wu
// */
//public class PayrollProcessing
//{
//    /**
//     * companyDataBaseAccess is provide interface with command.
//     */
//    private Company companyDataBaseAccess = new Company();
//
//
//    /**
//     * endSession is help to identify session is end or not.
//     */
//    boolean endSession = false;
//
//    /**
//     * This methods starts the Payroll Processing.
//     * The PayrollProcessing class can add an employee, remove an employee, and calculate the payments for all employees.
//     * It can also set the hours for part time employees.
//     * It can also print the earning statements as is, sorted by date hired, or grouped by department.
//     */
//    public void run()
//    {
//        System.out.print("Payroll Processing starts.\n");
//        Scanner src = new Scanner(System.in);
//
//        while(true)
//        {
//            String inputFromUser = src.nextLine();
//            if (inputFromUser.isBlank() || endSession)
//            {
//                continue;
//            }
//
//            /* Check Command is Valid or not*/
//            String separator =" \n\t";
//            StringTokenizer input = new StringTokenizer(inputFromUser,separator,false);
//            int numberOfToken = input.countTokens();
//
//            String command;
//            if (numberOfToken > 0)
//            {
//                command = input.nextToken();
//            }
//            else
//            {
//                command = input.toString();
//            }
//
//
//            if (!command.matches("(AP|AF|AM|R|C|S|Q|PA|PH|PD)"))
//            {
//                System.out.print("Command '"+command+"' not supported!\n");
//                continue;
//            }
//
//            if (command.matches("(R|C|S|PA|PH|PD)") && companyDataBaseAccess.isDataBaseEmpty())
//            {
//                System.out.println("Employee database is empty.");
//                continue;
//            }
//
//            /* Check Command has enough input value*/
//
//            boolean executeCommand = false;
//
//            int neededToken = 1;
//
//            switch(command)
//            {
//                case "AP":
//                case "AF":
//                case "AM":
//                case "S":
//                case "R":
//
//                    neededToken = 5;
//                    if (command.matches("(AM)"))
//                    {
//                        neededToken++;
//                    }
//                    if (command.matches("(R)"))
//                    {
//                        neededToken--;
//                    }
//
//                    if(numberOfToken == neededToken)
//                    {
//
//                        String employName = input.nextToken();
//                        String department = input.nextToken();
//
//                        if (!department.matches("(CS|ECE|IT)"))
//                        {
//                            System.out.print("'"+department + "' is not a valid department code.\n");
//                            continue;
//                        }
//
//                        Date heiredDate = 	new Date(input.nextToken());
//
//                        if (!heiredDate.isValid())
//                        {
//                            System.out.print(heiredDate.toString()+ " is not a valid date!\n");
//                            continue;
//                        }
//
//                        if (command.matches("(R)"))
//                        {
//
//                            boolean removed = companyDataBaseAccess.remove(new Employee(employName,department,heiredDate));
//                            if (removed)
//                            {
//                                System.out.println("Employee removed.");
//                            }
//                            else
//                            {
//                                System.out.println("Employee does not exist.");
//                            }
//                            executeCommand = true;
//                        }
//
//                        if (command.matches("(AF|AM|AP|S|AF)"))
//                        {
//                            String amount = input.nextToken();
//                            Double salaryOrARateOrHour = Double.parseDouble(amount);
//
//
//                            switch(command)
//                            {
//                                case "AP":{
//
//                                    if (salaryOrARateOrHour < 0)
//                                    {
//                                        System.out.println("Pay rate cannot be negative.");
//                                        continue;
//                                    }
//                                    else
//                                    {
//                                        boolean added = companyDataBaseAccess.add(new Parttime(employName,department,heiredDate, salaryOrARateOrHour));
//                                        if (added)
//                                        {
//                                            System.out.println("Employee added.");
//                                            executeCommand = true;
//                                        }
//                                        else
//                                        {
//                                            System.out.println("Employee is already in the list.");
//                                            continue;
//                                        }
//                                    }
//
//                                }
//                                break;
//                                case "AF":
//                                    {
//                                    if (salaryOrARateOrHour < 0)
//                                    {
//                                        System.out.println("Salary cannot be negative.");
//                                        continue;
//
//                                    }
//                                    else
//                                    {
//                                        boolean added = companyDataBaseAccess.add(new Fulltime(employName,department,heiredDate, salaryOrARateOrHour));
//                                        if (added)
//                                        {
//                                            System.out.println("Employee added.");
//                                            executeCommand = true;
//                                        }
//                                        else
//                                        {
//                                            System.out.println("Employee is already in the list.");
//                                            continue;
//                                        }
//                                    }
//                                }
//                                break;
//
//                                case "AM":
//                                    {
//                                    if (salaryOrARateOrHour < 0)
//                                    {
//                                        System.out.println("Salary cannot be negative.");
//                                    }
//                                    else
//                                    {
//
//                                        int departmentCode = Integer.parseInt(input.nextToken());
//
//                                        if (departmentCode <= 0 || departmentCode > 3)
//                                        {
//                                            System.out.println("Invalid management code.");
//                                            continue;
//                                        }
//                                        else
//                                        {
//                                            boolean added = companyDataBaseAccess.add(new Management(employName,department,heiredDate, salaryOrARateOrHour,departmentCode));
//                                            if (added)
//                                            {
//                                                System.out.println("Employee added.");
//                                                executeCommand = true;
//                                            }
//                                            else
//                                            {
//                                                System.out.println("Employee is already in the list.");
//                                                continue;
//                                            }
//                                        }
//                                    }
//
//                                }
//                                break;
//
//                                case "S":
//                                    {
//
//                                    if (salaryOrARateOrHour < 0)
//                                    {
//                                        System.out.println("Working hours cannot be negative.");
//                                        continue;
//
//                                    }
//                                    else
//                                    {
//                                        Parttime pEmp = new Parttime(employName,department,heiredDate);
//                                        pEmp.setWorkHours(salaryOrARateOrHour);
//                                        boolean added = companyDataBaseAccess.setHours(pEmp);
//                                        if (added)
//                                        {
//                                            System.out.println("Working hours set.");
//                                            executeCommand = true;
//                                        }
//                                        else
//                                        {
//                                            System.out.println("Invalid Hours: over 100.");
//                                            continue;
//
//                                        }
//                                    }
//
//                                }
//                                break;
//
//                                default:
//
//                            }
//
//
//                        }
//                    }
//                    break;
//                case "C":
//                    neededToken = 1;
//                    if(numberOfToken == neededToken)
//                    {
//                        companyDataBaseAccess.processPayments();
//                        System.out.println("Calutlation of employee payments is done.");
//                        executeCommand = true;
//                    }
//                    break;
//                case "Q":
//                    neededToken = 1;
//                    if(numberOfToken == neededToken)
//                    {
//                        endSession = true;
//                        executeCommand = true;
//
//                    }
//                    break;
//                case "PA":
//                    neededToken = 1;
//                    if(numberOfToken == neededToken)
//                    {
//                        companyDataBaseAccess.print();
//                        executeCommand = true;
//                    }
//                    break;
//                case "PD":
//                    neededToken = 1;
//                    if(numberOfToken == neededToken)
//                    {
//                        companyDataBaseAccess.printByDepartment();
//                        executeCommand = true;
//                    }
//                    break;
//                case "PH":
//                    neededToken = 1;
//                    if(numberOfToken == neededToken)
//                    {
//                        companyDataBaseAccess.printByDate();
//                        executeCommand = true;
//                    }
//                    break;
//                default:
//
//            }
//
//            if (!executeCommand)
//            {
//                System.out.print("Command '"+command+"' not supported!\n");
//                continue;
//            }
//
//            if (endSession)
//            {
//                System.out.print("Payroll Processing completed.\n");
//            }
//
//        }
//
//    }
//}
