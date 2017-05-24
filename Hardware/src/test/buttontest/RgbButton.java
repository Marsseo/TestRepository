package test.buttontest;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import hardware.button.Button;
import hardware.led.RgbLedDigital;
import java.io.IOException;


public class RgbButton {
	
	public static void main(String[] args) throws IOException {
		
		Button btn1 = new Button(RaspiPin.GPIO_00);
		Button btn2 = new Button(RaspiPin.GPIO_01);
		Button btn3 = new Button(RaspiPin.GPIO_02);
		
		RgbLedDigital rgbled = new RgbLedDigital(RaspiPin.GPIO_27, RaspiPin.GPIO_28, RaspiPin.GPIO_29);
		
		btn1.setGpioPinListenerDigital((e)->{
			if(e.getState()==PinState.LOW){
				rgbled.red(true);
			}
			else{
				rgbled.red(false);
			}
		});
		btn2.setGpioPinListenerDigital((e)->{
			if(e.getState()==PinState.LOW){
				rgbled.green(true);
			}
			else{
				rgbled.green(false);
			}
		});
		btn3.setGpioPinListenerDigital((e)->{
			if(e.getState()==PinState.LOW){
				rgbled.blue(true);
			}
			else{
				rgbled.blue(false);
			}
		});
		System.out.println("Read...");
		System.in.read();
	}
}
