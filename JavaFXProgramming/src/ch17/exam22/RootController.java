package ch17.exam22;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;


public class RootController implements Initializable {

    @FXML
    private ListView<Food> listView;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setCellFactory(new Callback<ListView<Food>, ListCell<Food>>() {
            @Override
            public ListCell<Food> call(ListView<Food> param) {
               ListCell<Food> listCell = new ListCell<Food>(){
                   @Override
                   protected void updateItem(Food item, boolean empty) {
                     
                       
                       super.updateItem(item, empty);
                       if(empty) return;
                       try {
                           
                           HBox hbox = (HBox) FXMLLoader.load(getClass().getResource("item.fxml"));
                           ImageView foodImage = (ImageView)hbox.lookup("#image");
                           Label foodName = (Label) hbox.lookup("#name");
                           ImageView starImage = (ImageView) hbox.lookup("#star");
                           Label fooddescription = (Label) hbox.lookup("#description");
                           
                           foodImage.setImage(new Image(getClass().getResource("images/"+item.getImage()+".png").toString()));
                           foodName.setText(item.getName());
                           starImage.setImage(new Image(getClass().getResource("images/star"+item.getScore()+".png").toString()));
                           fooddescription.setText(item.getDescription());
                           
                           setGraphic(hbox);
                       } catch (IOException ex) {
                           ex.printStackTrace();
                       }
                   }
                 
               };
                return listCell;
            }
        });
        ObservableList<Food> value = FXCollections.observableArrayList();
        value.add(new Food("food01","삼겹살", 5,"고기는 그냥 맛있어요"));
        value.add(new Food("food02","양념장어", 7,"정력에도 좋고 맛도 좋고"));
        value.add(new Food("food03","장어구이", 10,"정력에 좋아요"));
        value.add(new Food("food04","비빔밥", 3,"그냥 평범해요"));
        value.add(new Food("food05","새우볶음밥", 2,"누구나 만들 수 있어요"));
        listView.setItems(value);
        
        //선택 속성 감시
       
        listView.setOnMousePressed(new EventHandler(){
            @Override
            public void handle(Event event) {
                listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Food>() {
            @Override
            public void changed(ObservableValue<? extends Food> observable, Food oldValue, Food newValue) {
                System.out.println(newValue.getName()+":"+newValue.getImage());
            }
        });
            }
            
        });
        
    }    
    
}
