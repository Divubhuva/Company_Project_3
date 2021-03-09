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

    @FXML
    void FullTimeRadioSelected(ActionEvent event) {
    	AnualSalaryText.setDisable(false);
    	AnualSalary.setDisable(false);
    	HourlyWorkText.setDisable(true);
    	HourlyWork.setDisable(true);
    	RateText.setDisable(true);
    	Rate.setDisable(true);
    	ManagerRadio.setDisable(true);
    	DepartmentHeadRadio.setDisable(true);
    	DirectorRadio.setDisable(true);
    }

    @FXML
    void ManagementRadioSelected(ActionEvent event) {
    	AnualSalaryText.setDisable(false);
    	AnualSalary.setDisable(false);
    	HourlyWorkText.setDisable(true);
    	HourlyWork.setDisable(true);
    	RateText.setDisable(true);
    	Rate.setDisable(true);
    	ManagerRadio.setDisable(false);
    	DepartmentHeadRadio.setDisable(false);
    	DirectorRadio.setDisable(false);
    }

    @FXML
    void PartTimeRadioSelected(ActionEvent event) {
    	AnualSalaryText.setStyle("#acaaaa");
    	AnualSalary.setDisable(true);
    	HourlyWorkText.setDisable(false);
    	HourlyWork.setDisable(false);
    	RateText.setDisable(false);
    	Rate.setDisable(false);
    	ManagerRadio.setDisable(true);
    	DepartmentHeadRadio.setDisable(true);
    	DirectorRadio.setDisable(true);
    }

    @FXML
    void addEmployeeButtonPressed(ActionEvent event) {
    	String name = EmployeeName.getText();
    	
    	if (name.isEmpty()) {
    		MessageOutput.appendText("Please eneter the Employee Name.\n");
    		return;
    	}
    	
    	if (!name.matches("^[a-zA-Z\s]*$")) {
    		MessageOutput.appendText("Employee name should contain a-z or A-Z.\n");
    		return;
    	}
    	
    	if (name.matches("^[\s]*$")) {
    		MessageOutput.appendText("Employee name should not contain only white space.\n");
    		return;
    	}
    	
    	
    	
    	
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {

    }

    @FXML
    void removeEmployeeButtonPressed(ActionEvent event) {

    }

    @FXML
    void setHoursButtonPressed(ActionEvent event) {

    }

}
