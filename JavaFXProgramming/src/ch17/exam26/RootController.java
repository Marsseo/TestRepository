package ch17.exam26;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class RootController implements Initializable {

    @FXML
    private MediaView mediaView;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private Button btnStop;
    @FXML
    private Slider sliderVolume;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Label labelTime;
    
    private boolean endOfMedia;
    @FXML
    private Slider slider;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //미디어 객체 생성
        Media media = new Media(getClass().getResource("media/video.m4v").toString());
        //Media media = new Media(getClass().getResource("media/audio.wav").toString());
        //미디어 플레이어 객체 생성
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        
        //미디어뷰에 미디어플레이어 삽입
        mediaView.setMediaPlayer(mediaPlayer);
        //레디 상태를 세팅
        mediaPlayer.setOnReady(()->{
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);
            labelTime.setText(0+"/"+(int)mediaPlayer.getTotalDuration().toSeconds()+"sec");
        });
        //플레이중 상태를 세팅
        mediaPlayer.setOnPlaying(()->{
            btnPlay.setDisable(true);
            btnPause.setDisable(false);
            btnStop.setDisable(false);
        });
        //일시정지 상태 세팅
        mediaPlayer.setOnPaused(()->{
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(false);
        });
        // 정지 상태
        mediaPlayer.setOnStopped(()->{
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);
        });
        // 미디어 재생 완료후
        mediaPlayer.setOnEndOfMedia(()->{
            endOfMedia = true;
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);
            progressBar.setProgress(1.0);
            progressIndicator.setProgress(1.0);
        });
        
        btnPlay.setOnAction(e-> {
            // 미디어의 완전 재생후 다시 실행을 위해서
            if(endOfMedia){
                mediaPlayer.stop();
                mediaPlayer.seek(mediaPlayer.getStartTime());
            }
            mediaPlayer.play();
            endOfMedia = false; // 플레이 상태에 넣어줘야 함
         });
        btnPause.setOnAction(e->mediaPlayer.pause());
        btnStop.setOnAction(e->mediaPlayer.stop());
        
        //볼륨 속성 검사하여 세팅
        sliderVolume.valueProperty().addListener(new ChangeListener<Number> () {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                mediaPlayer.setVolume(newValue.doubleValue()/100.0);
            }
        });
        
        sliderVolume.setValue(50);
        //현재 재생시간
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                double progress = newValue.toSeconds() / mediaPlayer.getTotalDuration().toSeconds();
                progressBar.setProgress(progress);
                progressIndicator.setProgress(progress);
                labelTime.setText((int)newValue.toSeconds()+"/"+(int)mediaPlayer.getTotalDuration().toSeconds()+"sec");
                slider.setValue(newValue.toSeconds()/mediaPlayer.getTotalDuration().toSeconds()*100);
            }
        });
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    Duration d = Duration.seconds(mediaPlayer.getTotalDuration().toSeconds()/100*newValue.doubleValue());
                    if(slider.isValueChanging()){
                        mediaPlayer.seek(d);
                    }else{
                        if(Math.abs(oldValue.doubleValue() - newValue.doubleValue())>0.5){
                            mediaPlayer.seek(d);
                        }
                    }
            }
        });
       
    }    
    
}
