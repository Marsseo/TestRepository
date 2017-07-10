package mqtt.exam01;

import java.util.Date;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MqttSubscriber {
	
	private MqttClient mqttClient;
	
	private MqttCallback mqttCallback = new MqttCallback(){
		@Override
		public void connectionLost(Throwable thrwbl) {
			
		}

		@Override
		public void messageArrived(String string, MqttMessage mm) throws Exception {
			String text = new String(mm.getPayload());
			System.out.println("message Arrived: "+ text);
		}

		@Override
		public void deliveryComplete(IMqttDeliveryToken imdt) {
			System.out.println("Delivery Complete! when :"+ new Date()
			);
		}
		
	};

	public MqttSubscriber() throws MqttException {
		// MQTT 클라이언트 생성
		mqttClient = new MqttClient("tcp://192.168.3.109:1883", MqttClient.generateClientId());
		
		//통신 결과에 따라 실행할 콜백 개체 생성
		mqttClient.setCallback(mqttCallback);
		
		MqttConnectOptions conOpt = new MqttConnectOptions();
		conOpt.setCleanSession(true);
		
		mqttClient.connect(conOpt);
	}
	
	public void subscribe() throws MqttException{
		//지정한 토픽의 구독자로 신청
		mqttClient.subscribe("/devices/device1/temperature");
	}
	
	public void shutdown() throws MqttException{
		// MQTT 브로커와 연결 끊기
		mqttClient.disconnect();
		// MqttClient가 사용한 리소스(메모리)를 해제
		mqttClient.close();
	}
	
	public static void main(String[] args) throws Exception {
		MqttSubscriber subscriber = new MqttSubscriber();
				
		
		subscriber.subscribe();
		System.out.println("start subscriber");
		
		System.out.println("press any keys...");
		System.in.read();
		Thread.sleep(1000);
	}

	
}
