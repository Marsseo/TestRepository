package hardware.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DualColorLed {
	
	//Field
	private GpioPinDigitalOutput redPin;
	private GpioPinDigitalOutput greenPin;
	
	//Constructor
	public DualColorLed(Pin redPinNo, Pin greenPinNo){
		GpioController gpioController = GpioFactory.getInstance();
		redPin = gpioController.provisionDigitalOutputPin(redPinNo);
		redPin.setShutdownOptions(true, PinState.LOW);
		
		greenPin = gpioController.provisionDigitalOutputPin(greenPinNo);
		greenPin.setShutdownOptions(true, PinState.LOW);
		
	}
	//Method
	public void red(){
		redPin.high();
		greenPin.low();
	}
	public void green(){
		greenPin.high();
		redPin.low();
	}
	
	public static void main(String[] args) throws InterruptedException {
					
			DualColorLed test = new DualColorLed(RaspiPin.GPIO_00, RaspiPin.GPIO_01);
			for(int i =0;i<3;i++){
				test.red();
			}
			Thread.sleep(2000);
			for(int i =0;i<3;i++){
				test.green();
			}
			Thread.sleep(2000);
			
	
	}
}
