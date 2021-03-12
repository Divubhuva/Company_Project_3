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
    	OutputLog.appendText("Calutlation of employee payments is done.");
    }
    
    @FXML
    void PrintAllEmployes(ActionEvent event) {
    	if (companyDataBaseAccess.isDataBaseEmpty()) {
    		OutputLog.appendText("Employee database is empty.\n");
            return;
        }
    }

    @FXML
    void PrintByDatehired(ActionEvent event) {
    	if (companyDataBaseAccess.isDataBaseEmpty()) {
    		OutputLog.appendText("Employee database is empty.\n");
            return;
        }
    }

    @FXML
    void PrintByDepartment(ActionEvent event) {
    	if (companyDataBaseAccess.isDataBaseEmpty()) {
    		OutputLog.appendText("Employee database is empty.\n");
            return;
        }
    }
    
    @FXML
    void ExportFile(ActionEvent event) {

    }

    @FXML
    void ImportFile(ActionEvent event) {

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
    		MessageOutput.appendText("Please eneter the Salary.\n");
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
    		MessageOutput.appendText("Please eneter the rate.\n");
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
    		MessageOutput.appendText("Please eneter the workHour.\n");
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
