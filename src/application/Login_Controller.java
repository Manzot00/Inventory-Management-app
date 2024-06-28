package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The class {@code Login_Controller} is a controller for the login.fxml file.
 * 
 */
public class Login_Controller {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Label errorLabel;
	
	/**
	 * Logs in the user if the username and password are correct and loads the dashboard.fxml file.
	 * 
	 * @param e the ActionEvent that triggered this method
	 * @see Dashboard_Controller
	 */
	public void login(ActionEvent e) {
		String user = username.getText();
        String pass = password.getText();
		if (user.isEmpty() || pass.isEmpty()) {
			errorLabel.setText("Username or Password\ncannot be empty");
			return;
		}
        
        try {
            conn = Database.connectDb();
			
            ps = conn.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?");
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            
            if (rs.next()) {
            	FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
        		Parent  root = loader.load();
                
                Dashboard_Controller dc = loader.getController();
                dc.setUserLabel(user);
                
                Stage stage = new Stage();
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Inventory Management System");
                stage.show();
            } else {
            	errorLabel.setText("Invalid Username or Password");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
   }

}
