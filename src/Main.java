import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * This launches Payroll Controller Application.
 *
 * Date: 03/12/2021
 * @author Divya Bhuva, Dorothy Wu
 */
public class Main extends Application {
	/**
	 * The main entry point for all JavaFX applications.
	 * The start method is called after the init method has returned, and after the system is ready for the application to begin running.
	 * @param primaryStage stage onto which the application can be set.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("PayrollView.fxml"));
			final int width = 600;
			final int height = 400;
			Scene scene = new Scene(root,width,height);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Payroll Processing Tool");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This creates an instance of Application and calls the start method.
	 * @param args the command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}