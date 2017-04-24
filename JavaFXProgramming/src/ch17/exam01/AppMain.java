package ch17.exam01;

import java.util.Map;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppMain  extends Application{
    
    public AppMain(){
        System.out.println("Constructor");
        System.out.println(Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        
        launch(args);
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.show();
        System.out.println("start");
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void init() throws Exception {
        System.out.println("init");
        System.out.println(Thread.currentThread().getName());
        Parameters params = getParameters();
        
        //잘 안쓰인다. 매개값을 일일히 줘야함
        /*        
        List<String> list = params.getRaw();
        for(String param : list){
            System.out.println(param);
        }*/
        
        //java Appmain --ip=192.168.3.17 --port=50001
        Map<String, String> map = params.getNamed();
        String ip = map.get("ip");
        String port = map.get("port");
        System.out.println(ip);
        System.out.println(port);
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop");
        System.out.println(Thread.currentThread().getName());
    }
    
    // init, constructor, stop 는 잘 작성하지 않음
    // 주로 start만 작성
}
