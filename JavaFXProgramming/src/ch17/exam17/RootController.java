package ch17.exam17;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.fxml.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class RootController implements Initializable{
    
    @FXML private Circle circle;
    @FXML private AnchorPane root;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        circle.centerXProperty().bind(Bindings.divide(root.widthProperty(), 2));
        circle.centerYProperty().bind(Bindings.divide(root.heightProperty(), 2));
    }
}
