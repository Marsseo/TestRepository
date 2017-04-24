package ch17.exam21;

import com.sun.javafx.beans.event.AbstractNotifyListener;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;




public class RootController implements Initializable {

    @FXML
    private ListView<Phone> listView;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listView.setCellFactory(new Callback<ListView<Phone>, ListCell<Phone>>() {
            @Override
            public ListCell<Phone> call(ListView<Phone> param) {
                ListCell<Phone> listCell = new ListCell<Phone>(){
                    @Override
                    protected void updateItem(Phone item, boolean empty) {
                        
                        // id는 코드나 css에서 id값으로 객체를 찾을 때 사용 lookup("id")메소드 사용
                        
                        // fx:id는 컨트롤러에서 사용 @FXML로 변수 지정
                        
                        super.updateItem(item, empty);
                        //null인지 아닌지 검사한다.
                        if(empty) return;
                        try{
                                HBox hbox = (HBox) FXMLLoader.load(getClass().getResource("item.fxml"));
                                ImageView phoneImage = (ImageView)hbox.lookup("#image");
                                Label phoneName = (Label) hbox.lookup("#name");
                                Label phoneContent = (Label) hbox.lookup("#content");
                                
                                phoneImage.setImage(new Image(getClass().getResource("images/"+item.getImage()).toString()));
                                phoneName.setText(item.getName());
                                phoneContent.setText(item.getContent());
                                
                                //Cell의 내용으로 설정
                                setGraphic(hbox);
                            
                           }catch(Exception e){
                            e.printStackTrace();
                        }
                        
                    }
                    
                };
                return listCell;
            }
        });
        //데이타 세팅
        ObservableList<Phone> value = FXCollections.observableArrayList();
        value.add(new Phone("phone01.png","갤럭시s1","삼성의 최초 스마트폰입니다."));
        value.add(new Phone("phone02.png","갤럭시s2","삼성의 2nd 스마트폰입니다."));
        value.add(new Phone("phone03.png","갤럭시s3","삼성의 3rd 스마트폰입니다."));
        value.add(new Phone("phone04.png","갤럭시s4","삼성의 4th 스마트폰입니다."));
        listView.setItems(value);
        
        //선택 속성 감시
//        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {
//            @Override
//            public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
//                System.out.println(newValue.getName()+":"+newValue.getImage());
//            }
//            
//        });
        listView.setOnMouseClicked(e->{
            Phone phone = listView.getSelectionModel().getSelectedItem();
            System.out.println(phone.getName()+":"+phone.getImage());
        });
       
        
    }    
    
}
