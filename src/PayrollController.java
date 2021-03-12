import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PayrollController {

    @FXML
    private TextField EmployeeName;

    @FXML
    private RadioButton CSRadio;

    @FXML
    private ToggleGroup Department;

    @FXML
    private RadioButton ITRadio;

    @FXML
    private RadioButton ECERadio;

    @FXML
    private DatePicker SelectedDate;

    @FXML
    private RadioButton FullTimeRadio;

    @FXML
    private ToggleGroup EmployeeType;

    @FXML
    private RadioButton PartTimeRadio;

    @FXML
    private RadioButton ManagementRadio;

    @FXML
    private Text AnualSalaryText;

    @FXML
    private TextField AnualSalary;

    @FXML
    private Text HourlyWorkText;

    @FXML
    private TextField HourlyWork;

    @FXML
    private Text RateText;

    @FXML
    private TextField Rate;

    @FXML
    private RadioButton ManagerRadio;

    @FXML
    private ToggleGroup ManagementRole;

    @FXML
    private RadioButton DepartmentHeadRadio;

    @FXML
    private RadioButton DirectorRadio;

    @FXML
    private Button ClearButton;

    @FXML
    private Button AddEmployeeButton;

    @FXML
    private Button RemoveEmployeeButton;

    @FXML
    private Button SetHoursButton;

    @FXML
    private TextArea MessageOutput;

    @FXML
    private MenuItem ImportItem;

    @FXML
    private MenuItem ExportItem;

    @FXML
    private MenuItem AllEmployePrintItem;

    @FXML
    private MenuItem ByDepartmentPrintItem;

    @FXML
    private MenuItem ByDateHiredItem;

    @FXML
    private MenuItem ComputeItem;

    @FXML
    private TextArea OutputLog;

    
    /**
     * companyDataBaseAccess is provide interface with command.
     */
    private Company companyDataBaseAccess = new Company();
    
    
    
    
    @FXML
    void FullTimeRadioSelected(ActionEvent event) {
    	AnualSalary.setDisable(false);
    	HourlyWork.setDisable(true);
    	SetHoursButton.setDisable(true);
    	Rate.setDisable(true);
    	ManagerRadio.setDisable(true);
    	DepartmentHeadRadio.setDisable(true);
    	DirectorRadio.setDisable(true);
    	
    }

    @FXML
    void ManagementRadioSelected(ActionEvent event) {
    	AnualSalary.setDisable(false);
    	HourlyWork.setDisable(true);
    	SetHoursButton.setDisable(true);
    	Rate.setDisable(true);
    	ManagerRadio.setDisable(false);
    	DepartmentHeadRadio.setDisable(false);
    	DirectorRadio.setDisable(false);
    	
    }

    @FXML
    void PartTimeRadioSelected(ActionEvent event) {
    	AnualSalary.setDisable(true);
    	HourlyWork.setDisable(false);
    	Rate.setDisable(false);
    	ManagerRadio.setDisable(true);
    	DepartmentHeadRadio.setDisable(true);
    	DirectorRadio.setDisable(true);
    	SetHoursButton.setDisable(false);
    }

    @FXML
    void addEmployeeButtonPressed(ActionEvent event) {
    	
    	
    	final String employName = ReadEmployeeName();
    	if (employName.isEmpty()) {
    		return;
    	}
    	
    	final String dateString = ReadHiredDate();
    	if (dateString.isEmpty()) {
    		return;
    	}
    	
    	final Date heiredDate = new Date(dateString);
        if (!heiredDate.isValid()) {
        	MessageOutput.appendText(heiredDate.toString()+ " is not a valid date!\n");
            return;
        }
    	
      	final RadioButton  SelectedDepartment = (RadioButton) Department.getSelectedToggle();
      	final String department = SelectedDepartment.getText();
      	
        
      	final RadioButton  selectedRadioButton = (RadioButton) EmployeeType.getSelectedToggle();
      	final String employeePosition = selectedRadioButton.getText();
      	
      	
      	boolean added = false;
      	final String fullTime = FullTimeRadio.getText();
      	final String partTime = PartTimeRadio.getText();
      	final String management = ManagementRadio.getText();
      	final Double inValidAmount = -1.0;
      	
      	if (fullTime.equals(employeePosition)) {
      		final Double salary = ReadAnualSalary();
      		if (Double.compare(salary, inValidAmount) == 0) {
      			return;
      		}
      		added = companyDataBaseAccess.add(new Fulltime(employName,department,heiredDate, salary));
            
      	}	
      	else if(partTime.equals(employeePosition)) {
      		
      		final Double rate = ReadRate();
      		if (Double.compare(rate, inValidAmount) == 0) {
      			return;
      		}
      		added = companyDataBaseAccess.add(new Parttime(employName,department,heiredDate, rate));
      	}
      	else if(management.equals(employeePosition)) {
      		final Double salary = ReadAnualSalary();
      		if (Double.compare(salary, inValidAmount) == 0) {
      			return;
      		}

      		final RadioButton  roleRadio = (RadioButton) ManagementRole.getSelectedToggle();
            String roleString = roleRadio.getText();
      		int departmentCode;
            
      		final String manager = ManagerRadio.getText();
      		final String departmentHead = DepartmentHeadRadio.getText();
      		final String director = DirectorRadio.getText();
      		
      		if (manager.equals(roleString)) {
      			departmentCode = 1;
      		}
      		else if(departmentHead.equals(roleString)) {
      			departmentCode = 2;
      		}
      		else if(director.equals(roleString)) {
      			departmentCode = 3;
      		}
      		else {
      			departmentCode = -1;
      		}
      		
      		if (departmentCode <= 0 || departmentCode > 3)
            {
      			MessageOutput.appendText("Invalid management code.");
      			return;
            }
      		
      		added = companyDataBaseAccess.add(new Management(employName,department,heiredDate, salary,departmentCode));
      	}
      	else {
      		MessageOutput.appendText("Please select the valid Employeeyype. Full Time or Part Time or Management\n");
      		return;
      	}
      	
      	if (added) {
        	MessageOutput.appendText("Employee added.\n");
        }
        else {
        	MessageOutput.appendText("Employee is already in the list.\n");
        }	
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
    	EmployeeName.clear();
    	AnualSalary.clear();
    	HourlyWork.clear();
    	Rate.clear();
    	MessageOutput.clear();
    	final TextField dateEditor = SelectedDate.getEditor();
    	dateEditor.clear();
    	SelectedDate.setValue(null);
    }

    @FXML
    void removeEmployeeButtonPressed(ActionEvent event) {
    	if (companyDataBaseAccess.isDataBaseEmpty()) {
    		MessageOutput.appendText("Employee database is empty.\n");
            return;
        }
    	
    	final String employName = ReadEmployeeName();
    	if (employName.isEmpty()) {
    		return;
    	}
    	
    	final String dateString = ReadHiredDate();
    	if (dateString.isEmpty()) {
    		return;
    	}
    	
    	final Date heiredDate = new Date(dateString);
        if (!heiredDate.isValid()) {
        	MessageOutput.appendText(heiredDate.toString()+ " is not a valid date!\n");
            return;
        }
    	
      	final RadioButton  SelectedDepartment = (RadioButton) Department.getSelectedToggle();
      	final String department = SelectedDepartment.getText();
    	
    	boolean removed = companyDataBaseAccess.remove(new Employee(employName,department,heiredDate));
        if (removed)
        {
        	MessageOutput.appendText("Employee removed.\n");
        }
        else
        {
        	MessageOutput.appendText("Employee does not exist.\n");
        }
    }

    @FXML
    void setHoursButtonPressed(ActionEvent event) {
    	if (companyDataBaseAccess.isDataBaseEmpty()) {
    		MessageOutput.appendText("Employee database is empty.\n");
            return;
        }
    	
    	final String employName = ReadEmployeeName();
    	if (employName.isEmpty()) {
    		return;
    	}
    	
    	final String dateString = ReadHiredDate();
    	if (dateString.isEmpty()) {
    		return;
    	}
    	
    	final Date heiredDate = new Date(dateString);
        if (!heiredDate.isValid()) {
        	MessageOutput.appendText(heiredDate.toString()+ " is not a valid date!\n");
            return;
        }
    	
        final RadioButton  SelectedDepartment = (RadioButton) Department.getSelectedToggle();
      	final String department = SelectedDepartment.getText();
    	
      	final Double workhour = ReadWorkHour();
    	final Double inValidAmount = -1.0; 
    	if (Double.compare(workhour, inValidAmount) == 0) {
  			return;
  		}
      	
      	
    	Parttime pEmp = new Parttime(employName,department,heiredDate);
        pEmp.setWorkHours(workhour);
        boolean setHour = companyDataBaseAccess.setHours(pEmp);
        if (setHour)
        {
        	MessageOutput.appendText("Working hours set.\n");  
        }
        else
        {
        	MessageOutput.appendText("Employee does not exist.\n");
        }
    }
    @FXML
    void ComputePayment(ActionEvent event) {
    	if (companyDataBaseAccess.isDataBaseEmpty()) {
    		OutputLog.appendText("Employee database is empty.\n");
            return;
        }
    	
    	companyDataBaseAccess.processPayments();
    	OutputLog.appendText("Calutlation of employee payments is done.\n");
    }
    
    @FXML
    void PrintAllEmployes(ActionEvent event) {
    	if (companyDataBaseAccess.isDataBaseEmpty()) {
    		OutputLog.appendText("Employee database is empty.\n");
            return;
        }
    	
    	OutputLog.appendText("--Printing earning statements for all employees--\n");
    	for (int index = 0; index <companyDataBaseAccess.getNumberOfEmployee();index++) {
    		OutputLog.appendText(companyDataBaseAccess.print(index)+"\n");
    	}
    }

    @FXML
    void PrintByDatehired(ActionEvent event) {
    	if (companyDataBaseAccess.isDataBaseEmpty()) {
    		OutputLog.appendText("Employee database is empty.\n");
            return;
        }
    	
    	OutputLog.appendText("--Printing earning statements by date hired--\n");
    	for (int index = 0; index <companyDataBaseAccess.getNumberOfEmployee();index++) {
    		OutputLog.appendText(companyDataBaseAccess.printByDate(index)+"\n");
    	}
    }

    @FXML
    void PrintByDepartment(ActionEvent event) {
    	if (companyDataBaseAccess.isDataBaseEmpty()) {
    		OutputLog.appendText("Employee database is empty.\n");
            return;
        }
    	OutputLog.appendText("--Printing earning statements by department--\n");
    	for (int index = 0; index <companyDataBaseAccess.getNumberOfEmployee();index++) {
    		OutputLog.appendText(companyDataBaseAccess.printByDepartment(index)+"\n");
    	}
    }
    
    
    @FXML
    void ImportFile(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
    	chooser.setTitle("Open Source File for Import");
    	chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files","*.txt"),
    			new ExtensionFilter("All Files","*.*"));
    	Stage stage = new Stage();
    	File sourceFile = chooser.showOpenDialog(stage);
    	
    	if (sourceFile == null) {
    		OutputLog.appendText("File is not selected. \n");
    		return;
    	}
    	
    	if (!sourceFile.exists()) {
    		OutputLog.appendText("File is not exist. \n");
    		return;
    	}
    	
    	if (sourceFile.isHidden()) {
    		OutputLog.appendText("File is not hidden. Hidden file not read by application. \n");
    		return;
    	}
    	
    	try {
			Scanner src = new Scanner(sourceFile);
			while(src.hasNextLine()){
				 String inputFromUser = src.nextLine();
				 if (inputFromUser.isBlank() || inputFromUser.isEmpty()) {
					 OutputLog.appendText(" Empty line is skip from file.\n");
		             continue;
		         }
				 
		         String separator =",";
		         StringTokenizer input = new StringTokenizer(inputFromUser,separator,false);
		         int numberOfToken = input.countTokens();
		         
		         String command;
		            if (numberOfToken > 0)
		            {
		                command = input.nextToken();
		            }
		            else
		            {
		                command = input.toString();
		            }


		            if (!command.matches("(P|F|M)"))
		            {
		            	OutputLog.appendText("Unknow command is found in selected file. \n");
		                continue;
		            }
		            
		            int neededToken = 5;
		            
		            if (command.equals("M")) {
		            	neededToken++;
		            }
		            
               	 	if(numberOfToken == neededToken) {
               	 		String employName = input.nextToken();
               	 		String department = input.nextToken();
               	 		if (!department.matches("(CS|ECE|IT)"))
               	 		{
               	 			OutputLog.appendText("'"+department + "' is not a valid department code.\n");
               	 			continue;
               	 		}
                     
               	 		Date heiredDate = 	new Date(input.nextToken());

               	 		if (!heiredDate.isValid())
               	 		{
               	 			OutputLog.appendText(heiredDate.toString()+ " is not a valid date!\n");
               	 			continue;
               	 		}
                     
               	 		String amount = input.nextToken();
               	 		Double salaryOrARateOrHour = Double.parseDouble(amount);
               	 		
               	 	switch(command) {
	                case "P":{
	                	if (salaryOrARateOrHour < 0)
                        {
	                		OutputLog.appendText("Pay rate cannot be negative.");
                            continue;
                        }
                        else
                        {
                            boolean added = companyDataBaseAccess.add(new Parttime(employName,department,heiredDate, salaryOrARateOrHour));
                            if (!added) {
                            	OutputLog.appendText(employName +"is not added to data base because Employee is already in the list.");
                            }
                            
                        }
	                }
	                break;
	                case "F":{
	                	if (salaryOrARateOrHour < 0)
                        {
	                		OutputLog.appendText("Salary cannot be negative.");
                            continue;

                        }
                        else
                        {
                            boolean added = companyDataBaseAccess.add(new Fulltime(employName,department,heiredDate, salaryOrARateOrHour));
                            if (!added)
                            {
                            	OutputLog.appendText(employName +"is not added to data base because Employee is already in the list.");
                            }
                        }
	                	}
	                break;
	                case "M":{
	                	if (salaryOrARateOrHour < 0)
                        {
	                		OutputLog.appendText("Salary cannot be negative.");
                        }
                        else
                        {
                            int departmentCode = Integer.parseInt(input.nextToken());

                            if (departmentCode <= 0 || departmentCode > 3)
                            {
                            	OutputLog.appendText("Invalid management code.");
                                continue;
                            }
                            else
                            {
                                boolean added = companyDataBaseAccess.add(new Management(employName,department,heiredDate, salaryOrARateOrHour,departmentCode));
                                if (!added)
                                {
                                	OutputLog.appendText(employName +"is not added to data base because Employee is already in the list.");
                                }
                            }
                        }
                		}
	                break;
	                default:
	                	OutputLog.appendText("Command is not identify. \n");
		            }
                 }
			}
		OutputLog.appendText("Selected file is import finished. \n");
		} catch (FileNotFoundException e) {
			OutputLog.appendText("Exception is generated while reading file. \n");
		}
    	
    }
    
    @FXML
    void ExportFile(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
    	chooser.setTitle("Save Target File for Export");
    	chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files","*.txt"),
    			new ExtensionFilter("All Files","*.*"));
    	Stage stage = new Stage();
    	File targetFile = chooser.showSaveDialog(stage);
    	
    	if (targetFile == null) {
    		return;
    	}

    	if (!targetFile.exists()) {
    		try {
    			targetFile.createNewFile();
    		} catch (IOException e) {
				OutputLog.appendText("File is not found exception generated. While creating new file.\n");
			}
    	}
    	WriteDateBaseToFile(targetFile.getPath());
    }

    private void WriteDateBaseToFile(String filePath) {      
        PrintWriter fw = null;
        BufferedWriter bw  = null;
        try {
            fw = new PrintWriter(filePath);
            bw = new BufferedWriter(fw);
            if (!companyDataBaseAccess.exportDatabase().isEmpty()) {
            	bw.write(companyDataBaseAccess.exportDatabase());
            	OutputLog.appendText("Database is write successful in file.\n");
            }
            else {
            	bw.write("Data Base is empty.");
            	OutputLog.appendText("Data Base is empty. Empty file is generated.\n");
            }
        } catch (IOException e) {
            OutputLog.appendText("Error is generated while wrtting the file.\n");
        }
        finally
    	{ 
    	   try{
    	      if(bw!=null) {
    	    	  bw.close();
    	      }
    	      if (fw != null) {
    	    	  fw.close();
    	      }
    	   }catch(Exception ex){
    		   OutputLog.appendText("Error in closing the BufferedWriter.\n");
    	    }
    	}
    }

    private String ReadEmployeeName() {
    	String returnName = "";
    	final String name = EmployeeName.getText();
    	
    	if (name.isEmpty()) {
    		MessageOutput.appendText("Please eneter the Employee Name.\n");
    		return returnName;
    	}
    	
    	if (!name.matches("^[a-zA-Z\s]*$")) {
    		MessageOutput.appendText("Employee name should contain a-z or A-Z.\n");
    		return returnName;
    	}
    	
    	if (name.matches("^[\s]*$")) {
    		MessageOutput.appendText("Employee name should not contain only white space.\n");
    		return returnName;
    	}
    	returnName = name;
    	return returnName;
    }
   
    private String ReadHiredDate() {
    	String returnDate = "";
    	final TextField dateEditor = SelectedDate.getEditor();
    	final String dateValue = dateEditor.getText();
    	
    	if (dateValue.isEmpty()) {
    		MessageOutput.appendText("Please enter the date.\n");
    		return returnDate;
    	}
    	
    	if (dateValue.matches("^[\s]*$")) {
    		MessageOutput.appendText("Date should not contain only white space.\n");
    		return returnDate;
    	}
    	
    	if (!dateValue.matches("^[^a-z^A-Z]*$")) {
    		MessageOutput.appendText("Date should not contain A to Z \n");
    		return returnDate;
    	}
    	
      	if (!dateValue.matches("\\d{1,2}(\\/)\\d{1,2}\\1\\d{4}$")) {
    		MessageOutput.appendText("Date format is invalid. Valid format for date is MM/DD/YYYY.\n");
    		return returnDate;
    	}
    	
      	returnDate = dateValue;
      	return returnDate;
    }
    
    private Double ReadAnualSalary() {
    	Double returnSalary = -1.0;
    	final String amount = AnualSalary.getText();
    	if (amount.isEmpty()) {
    		MessageOutput.appendText("Please enter the Salary.\n");
    		return returnSalary;
    	}
    	
    	if (amount.matches("^[\s]*$")) {
    		MessageOutput.appendText("Salary should not contain only white space.\n");
    		return returnSalary;
    	}
    	
    	if (!amount.matches("^[^a-z^A-Z]*$")) {
    		MessageOutput.appendText("Salary should not contain A to Z \n");
    		return returnSalary;
    	}
    	
  		if (!amount.matches("-?\\d{1,15}\\.?\\d{0,10}?$")) {
    		MessageOutput.appendText("AnualSalary should contain only digits with single decimal point.\n");
    		return returnSalary;
    	}
  		final Double salary = Double.parseDouble(amount);
  		
  		if(salary < 0 ) {
  			MessageOutput.appendText("Salary cannot be negative.\n");
  			return returnSalary;
  		}
    	
  		returnSalary = salary;
  		return returnSalary;
    }
    
    private Double ReadRate() {
    	Double returnRate = -1.0;
    	final String amount = Rate.getText();
    	if (amount.isEmpty()) {
    		MessageOutput.appendText("Please enter the rate.\n");
    		return returnRate;
    	}
    	
    	if (amount.matches("^[\s]*$")) {
    		MessageOutput.appendText("Rate should not contain only white space.\n");
    		return returnRate;
    	}
    	
    	if (!amount.matches("^[^a-z^A-Z]*$")) {
    		MessageOutput.appendText("Rate should not contain A to Z \n");
    		return returnRate;
    	}
    	
    	
  		if (!amount.matches("-?\\d{1,15}\\.?\\d{0,10}?$")) {
    		MessageOutput.appendText("Hourly rate should contain only digits with single decimal point.\n");
    		return returnRate;
    	}
  		
  		final Double salary = Double.parseDouble(amount);
  		
  		if(salary < 0 ) {
  			MessageOutput.appendText("Pay rate cannot be negative.\n");
  			return returnRate;
  		}
    	
  		returnRate = salary;
  		return returnRate;
    }
    
    private Double ReadWorkHour() {
    	Double returnHour = -1.0;
    	final String workHour = HourlyWork.getText();
    	if (workHour.isEmpty()) {
    		MessageOutput.appendText("Please enter the workHour.\n");
    		return returnHour;
    	}
    	
    	if (workHour.matches("^[\s]*$")) {
    		MessageOutput.appendText("WorkHour should not contain only white space.\n");
    		return returnHour;
    	}
    	
    	if (!workHour.matches("^[^a-z^A-Z]*$")) {
    		MessageOutput.appendText("workHour should not contain A to Z \n");
    		return returnHour;
    	}
    	
    	
  		if (!workHour.matches("-?\\d{1,15}\\.?\\d{0,10}?$")) {
    		MessageOutput.appendText("Hourly rate should contain only digits. \n");
    		return returnHour;
    	}
  		
  		final Double Hour = Double.parseDouble(workHour);
  		
  		if(Hour < 0 ) {
  			MessageOutput.appendText("Work hour cannot be negative.\n");
  			return returnHour;
  		}
  		
  		if (Hour> 100) {
  			MessageOutput.appendText("Invalid Work hour over 100. \n");
  			return returnHour;
  		}
    	
  		returnHour = Hour;
    	return returnHour;
    }

}
