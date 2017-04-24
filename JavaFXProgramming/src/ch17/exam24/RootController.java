package ch17.exam24;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class RootController implements Initializable {

    @FXML
    private ListView<String> listView;
    @FXML
    private TableView<Phone> tableView;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btOk;
    @FXML
    private Button btCancle;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btOk.setOnAction(e->System.out.println("선택하셨습니다!"));
        btCancle.setOnAction(e->Platform.exit());
        ObservableList<String> data1 = FXCollections.observableArrayList();
        data1.add("갤럭시s");
        data1.add("갤럭시s2");
        data1.add("갤럭시s3");
        data1.add("갤럭시s4");
        data1.add("갤럭시s5");
        data1.add("갤럭시s6");
        data1.add("갤럭시s7");
        listView.setItems(data1);
        
       TableColumn tc0 = tableView.getColumns().get(0);
       TableColumn tc1 = tableView.getColumns().get(1);
       
       tc0.setCellValueFactory(new PropertyValueFactory<Phone, String>("name"));
       tc1.setCellValueFactory(new PropertyValueFactory<Phone, String>("image"));
       
       ObservableList<Phone> data2 = FXCollections.observableArrayList();
       data2.add(new Phone("phone01.png","갤럭시s","삼성의 1st 스마트폰입니다."));
       data2.add(new Phone("phone02.png","갤럭시s2","삼성의 2nd 스마트폰입니다."));
       data2.add(new Phone("phone03.png","갤럭시s3","삼성의 3rd 스마트폰입니다."));
       data2.add(new Phone("phone04.png","갤럭시s4","삼성의 4th 스마트폰입니다."));
       data2.add(new Phone("phone05.png","갤럭시s5","삼성의 5th 스마트폰입니다."));
       data2.add(new Phone("phone06.png","갤럭시s6","삼성의 6th 스마트폰입니다."));
       data2.add(new Phone("phone07.png","갤럭시s7","삼성의 7th 스마트폰입니다."));
       tableView.setItems(data2);
       
       listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
               tableView.getSelectionModel().select(newValue.intValue());
               tableView.scrollTo(newValue.intValue());
            }
        });
       tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>(){
            @Override
            public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
                imageView.setImage(new Image(getClass().getResource("images/"+newValue.getImage()).toString()));
            }
        });    
    }
}
