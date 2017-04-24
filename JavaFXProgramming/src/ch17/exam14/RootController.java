package ch17.exam14;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.*;
import javafx.scene.control.*;

public class RootController implements Initializable{
    
    @FXML private Button bt1;
    @FXML private Button bt2;
    @FXML private Button bt3;
    @FXML private Button nbt1;
    @FXML private Button nbt2;
    @FXML private Button nbt3;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //how1
        bt1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("버튼1이 클릭됨");
            }
        });
        //how2
        bt2.setOnAction(event->System.out.println("버튼2이 클릭됨"));
        
        nbt1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("새로운 버튼1이 클릭됨");
            }
        });
        
        nbt2.setOnAction(event -> System.out.println("새로운 버튼2이 클릭됨") );
        
        
    }
    //how3
    public void bt3Handle(ActionEvent event){
        System.out.println("버튼3이 클릭됨");
    }
    public void nbt3Handle(ActionEvent e){
        System.out.println("새로운 버튼3이 클릭됨");
    }
        
}
