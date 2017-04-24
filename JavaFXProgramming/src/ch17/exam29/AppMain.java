package ch17.exam29;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application{
           
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent parent = FXMLLoader.load(getClass().getResource("root.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("root.fxml"));
        Parent root = loader.load();
        RootController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("다이얼로그yop");
        primaryStage.setOnCloseRequest(event->System.out.println("다음에 또 오시오"));        
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
