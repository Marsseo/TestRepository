package ch17.exam43;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;



public class RootController implements Initializable {

	@FXML
	private Button btnLogin;
	@FXML
	private StackPane stackPane;
	
	public static StackPane rootPane;


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//rootPane = stackPane;
		btnLogin.setOnAction(e->handleLogin(e));
	}	

	private void handleLogin(ActionEvent e) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
			stackPane.getChildren().add(parent);
			parent.setTranslateX(350);
			
			KeyValue keyValue = new KeyValue(parent.translateXProperty(), 0);
			KeyFrame keyFame = new KeyFrame(Duration.millis(1000), keyValue);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().add(keyFame);
			timeline.play();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
