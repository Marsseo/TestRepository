package mqtt;

import hardware.convertor.PCF8591;
import hardware.sensor.ThermistorSensor;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MqttPublisher {
	
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

	public MqttPublisher() throws MqttException {
		// MQTT 클라이언트 생성
		mqttClient = new MqttClient("tcp://192.168.3.109:1883", MqttClient.generateClientId());
		
		//통신 결과에 따라 실행할 콜백 개체 생성
		mqttClient.setCallback(mqttCallback);
		
		MqttConnectOptions conOpt = new MqttConnectOptions();
		conOpt.setCleanSession(true);
		mqttClient.connect(conOpt);
	}
	
	public void publish(String text) throws MqttException{
		// MQTT 브로커로 보내는 메세지 생성
		MqttMessage message = new MqttMessage(text.getBytes());
		// 지정한 MQTT 브로커로 메세지 보냄
		mqttClient.publish("devices/device1/temparature", message);
	}
	
	public void shutdown() throws MqttException{
		// MQTT 브로커와 연결 끊기
		mqttClient.disconnect();
		// MqttClient가 사용한 리소스(메모리)를 해제
		mqttClient.close();
	}
	
	public static void main(String[] args) throws Exception {
		MqttPublisher publisher = new MqttPublisher();
		// 매 1초 단위로 온도 메세지를 보냄
		
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN1);
		ThermistorSensor ts = new ThermistorSensor(pcf8591);
		
		for(int i =1;i<=5;i++){
			publisher.publish("temperature"+ts.getValue());
			Thread.sleep(1000);
		}
	}
}
