package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * Main class to start the application
 * 
 */
public class Main extends Application {
	
	/**
	 * Start method to start the application by loading the login.fxml file and setting the database
	 * 
	 * @param stage
	 * @see Database
	 */
	@Override
	public void start(Stage stage) {
		try {
			Database.createAdminTable();
			Database.adminDataExists();
			Database.createItemTable();
			Database.itemDataExists();
			Database.createCustomerTable();
			Database.customerDataExists();
			Database.createOrdersTable();
			Database.createOrdersTable();
			Database.ordersDataExists();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
		    Parent root = loader.load();
		    Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Login");
			stage.show();
			
			stage.setOnCloseRequest(event -> {
				event.consume();
				try {
					logout(stage);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main method to launch the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Method to close the application
	 * 
	 * @param stage
	 * @throws IOException
	 */
	public void logout(Stage stage) throws IOException {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit Application");
		alert.setHeaderText("You're about to close the Application!");
		alert.setContentText("Are you sure?");
		
		if(alert.showAndWait().get() == ButtonType.OK) {
			stage.close();
		}
	}
}