package ch17.exam15;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;

public class RootController implements Initializable{
    
    @FXML private Label label;
    @FXML private Slider slider;
    @FXML private TextField txt1;
    @FXML private TextField txt2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                label.setFont(new Font(newValue.doubleValue()));
            }
        });
        
        txt1.textProperty().addListener( (bservable, oldValue, newValue)->{txt2.setText("변경된 값:"+newValue);});
    
   
    }
}
