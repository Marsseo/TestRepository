package ch17.exam25;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class RootController implements Initializable {

    @FXML
    private MediaView mediaView;
    @FXML
    private Button btnPlay;
    @FXML
    private Button btnPause;
    @FXML
    private Button btnStop;

    private boolean endOfMedia;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //미디어 객체 생성
        Media media = new Media(getClass().getResource("media/video.m4v").toString());
        //미디어 플레이어 객체 생성
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        
        //미디어뷰에 미디어플레이어 삽입
        mediaView.setMediaPlayer(mediaPlayer);
        //레디 상태를 세팅
        mediaPlayer.setOnReady(()->{
            btnPlay.setDisable(false);
            btnPause.setDisable(true);
            btnStop.setDisable(true);
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
        
        
    }    
    
}
