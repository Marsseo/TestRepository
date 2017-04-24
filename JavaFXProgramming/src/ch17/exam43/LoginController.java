package ch17.exam43;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LoginController implements Initializable {

	@FXML
	private BorderPane login;
	@FXML
	private Button btnMain;


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnMain.setOnAction(e->handleBtnMain(e));
	}	

	private void handleBtnMain(ActionEvent e) {

			//how1
			//RootController.rootPane.getChildren().remove(login);
			
			//how2
			StackPane rootPane= (StackPane)btnMain.getScene().getRoot();
			//int a = rootPane.getChildren().indexOf(login);
			
			login.setOpacity(1);
			
			KeyValue keyValue = new KeyValue(login.opacityProperty(), 0);
			KeyFrame keyFame = new KeyFrame(
							Duration.millis(300),
							event->{
								rootPane.getChildren().remove(login);
							},
							keyValue);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().add(keyFame);
			timeline.play();
	}
	
}
