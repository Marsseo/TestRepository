package hardware.button;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.io.IOException;


public class Button {
	
	private GpioPinDigitalInput btnInput;
	
	public Button(Pin buttonPinNo){
		GpioController gpioController = GpioFactory.getInstance();
		btnInput = gpioController.provisionDigitalInputPin(buttonPinNo);
		btnInput.setShutdownOptions(true);
	}
	
	public void setGpioPinListenerDigital(GpioPinListenerDigital listener){
		btnInput.addListener(listener);
	}
	public static void main(String[] args) throws IOException {
		Button button = new Button(RaspiPin.GPIO_00);
		button.setGpioPinListenerDigital(new GpioPinListenerDigital(){
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(event.getState()==PinState.HIGH){
					System.out.println("High");
				}else{
					System.out.println("Low");
				}
			}
									
		});
		System.out.println("Ready...");
		System.in.read();
	}
}
