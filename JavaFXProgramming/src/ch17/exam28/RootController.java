
package ch17.exam28;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class RootController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void menuItemNew(ActionEvent event) {
        System.out.println("새로만들기 클릭됨");
    }

    @FXML
    private void newfile(ActionEvent event) {
        System.out.println("새로 열기!!! 빠밤");
    }
    
}
