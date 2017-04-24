package ch17.exam26;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("root.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("동영상 실행");
        primaryStage.setOnCloseRequest(event->System.out.println("에궁 다음 기회에..."));        
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
