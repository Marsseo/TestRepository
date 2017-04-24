package ch17.exam19;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class RootController implements Initializable {

    @FXML
    private TextField txtTitle;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ComboBox<String> comboPublic;
    @FXML
    private TextArea txtContent;
    @FXML
    private Button btnReg;
    @FXML
    private Button btnCancel;
    @FXML
    private DatePicker dateExit;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> value = FXCollections.observableArrayList();
        value.add("공개");
        value.add("비공개");
       comboPublic.setItems(value);
       
       btnCancel.setOnAction(e->Platform.exit());
    }    

    @FXML
    private void btnRegHandle(ActionEvent event) {
        String title = txtTitle.getText();
        String password = txtPassword.getText();
        String strPublic = comboPublic.getValue();
        LocalDate date = dateExit.getValue();
        String content = txtContent.getText();
        
        System.out.println("제목: "+title);
        System.out.println("비밀번호: "+password);
        System.out.println("공개/비공개: "+strPublic);
        System.out.println("날짜: "+date);
        System.out.println("내용: "+content);
    }
    
}
