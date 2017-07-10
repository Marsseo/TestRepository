package com.mycompany.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mycompany.myapplication.R;
import com.mycompany.myapplication.service.MqttSubscribeService;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText txtMessage;

    private Button btnPulish;
    private String serverURI = "tcp://192.168.3.109:1883";
    private String topic = "/devices/device1/temperature";
    private MqttAndroidClient mqttAndroidClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMessage = (EditText) findViewById(R.id.txtMessage);
        btnPulish = (Button) findViewById(R.id.btnPublish);

        mqttAndroidClient = new MqttAndroidClient(this, serverURI, MqttClient.generateClientId());

        try {
            mqttAndroidClient.connect().setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG, "MQTT 서버 연결 성공");
                    btnPulish.setEnabled(true);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i(TAG, "MQTT 서버 연결 실패: " + exception.toString());
                    btnPulish.setEnabled(false);
                }
            });
        } catch (MqttException e) {
            Log.i(TAG, "MQTT 서버 연결 실패: " + e.toString());
            btnPulish.setEnabled(false);
        }
    }


    public void handleBtnStartService(View v){
        Intent intent = new Intent(this, MqttSubscribeService.class);
        startService(intent);
    }

    public void handleBtnStopService(View v){
        Intent intent = new Intent(this, MqttSubscribeService.class);
        stopService(intent);
    }

    public void handleBtnPublish(View v){
        String message = txtMessage.getText().toString();
        try {
            byte[] encodePayload = message.getBytes("UTF-8");
            MqttMessage mqttMessage = new MqttMessage(encodePayload);
            mqttAndroidClient.publish(topic, mqttMessage).setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG, "MQTT 메시지 보내기 성공");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i(TAG, "MQTT 메시지 보내기 실패: " + exception.toString());
                }
            });
        } catch (Exception e) {
            Log.i(TAG, "MQTT 메세지 보내기 실패 :"+e.toString());
        }
    }
}
