package ch17.exam39;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


public class RootController implements Initializable {

	@FXML
	private ProgressBar progressBar;
	@FXML
	private Label label;
	@FXML
	private Label lblWorkDone;
	@FXML
	private Button btnStart;
	@FXML
	private Button btnStop;
	
	private Task<Integer> task;
	@FXML
	private Label lblResult;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnStart.setOnAction(e->handleStart(e));
		btnStop.setOnAction(e->handleStop(e));
	}	

	private void handleStart(ActionEvent e) {
		task = new Task<Integer>(){
			@Override
			protected Integer call() throws Exception {
				int result = 0;
				for(int i= 0;i<=100;i++){
					//0부터 더함
					result += i;
					//how1
					//task의 내부의 프로그레스를 변화시킴
//					updateProgress(i, 100);// 변화하는 값과 최대값
//					updateMessage(String.valueOf(i)+"%");
					//how2
					double value = i;
					Platform.runLater(()->{
						progressBar.setProgress(value/100);
						lblWorkDone.setText(String.valueOf(value));
					});
					if(isCancelled()){break;}
					try{Thread.sleep(10);}catch(Exception e){ break; }
				}
				return result;
			}

			@Override
			protected void succeeded() {
				int result = getValue();
				lblResult.setText(String.valueOf(result));
			}

			@Override
			protected void cancelled() {
				lblResult.setText("작업 취소");
			}

			@Override
			protected void failed() {
				lblResult.setText("작업 실패");
			}
			
			
		};
//		progressBar.progressProperty().bind(task.progressProperty());
//		lblWorkDone.textProperty().bind(task.messageProperty());
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}

	private void handleStop(ActionEvent e) {
		task.cancel();
	}
	
}
