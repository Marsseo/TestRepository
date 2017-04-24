package ch17.exam41;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class RootController implements Initializable {

	@FXML
	private Button btnLogin;



	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnLogin.setOnAction(e->handleLogin(e));
	}	

	private void handleLogin(ActionEvent e) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(parent);
			Stage primaryStage = (Stage)btnLogin.getScene().getWindow();
			primaryStage.setScene(scene);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
