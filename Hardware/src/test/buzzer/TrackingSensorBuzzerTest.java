package test.buzzer;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.sensor.TrackingSensor;
import java.io.IOException;


public class TrackingSensorBuzzerTest {
	
	public static void main(String[] args) throws IOException {
		
		TrackingSensor ts = new TrackingSensor(RaspiPin.GPIO_01);
		ActiveBuzzer bz = new ActiveBuzzer(RaspiPin.GPIO_00);
	
		ts.setGpioPinListenerDigital(e->{
			if(e.getState()==PinState.HIGH) bz.off();
			else bz.on();
		});
		System.out.println("알류 레뒤");
		System.in.read();
	}
	
}
