package ch17.exam38;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class RootController implements Initializable {

    @FXML
    private Label lblTime;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnStop;

    private boolean stop;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnStart.setOnAction(e->handleBtnStart(e));
        btnStop.setOnAction(e->handleBtnStop(e));
    }    

    private void handleBtnStart(ActionEvent e) {
        stop = false;
        Thread thread = new Thread(){
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                while(!stop){
                    String strTime = sdf.format(new Date());
                    //lblTime.setText(strTime) 에러 발생
                    Platform.runLater(()->lblTime.setText(strTime));
                    try {Thread.sleep(100); } catch (InterruptedException ex) {
                        Logger.getLogger(RootController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        
        };
        thread.setDaemon(true);
        thread.start();
    }

    private void handleBtnStop(ActionEvent e) {
        stop = true;
    }
    
}
