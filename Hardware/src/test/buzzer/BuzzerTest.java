package test.buzzer;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import hardware.button.Button;
import hardware.buzzer.ActiveBuzzer;
import java.io.IOException;


public class BuzzerTest {
	
	public static void main(String[] args) throws IOException {
		
		Button btn = new Button(RaspiPin.GPIO_01);
		ActiveBuzzer ab = new ActiveBuzzer(RaspiPin.GPIO_00);
		
		btn.setGpioPinListenerDigital(e->{
			if(e.getState()==PinState.LOW) ab.on();
			else ab.off();
		});
		System.out.println("Read...");
		System.in.read();
	}
}
