package ch17.exam16;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.fxml.*;
import javafx.scene.control.*;

public class RootController implements Initializable{
    
    @FXML private TextArea textArea1;
    @FXML private TextArea textArea2;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //단방향 바인딩
        //textArea2.textProperty().bind(textArea1.textProperty());
        
        //양방향 바인딩
        Bindings.bindBidirectional(textArea1.textProperty(), textArea2.textProperty());
        
    }
}
