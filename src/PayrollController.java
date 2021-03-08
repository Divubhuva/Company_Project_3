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
    private ToggleGroup EmployeeRole;

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

}

