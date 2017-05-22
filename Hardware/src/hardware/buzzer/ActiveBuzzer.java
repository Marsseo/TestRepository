package hardware.buzzer;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class ActiveBuzzer {
	
	private GpioPinDigitalOutput buzzerPin;
	private String status = "off";
	
	public ActiveBuzzer(Pin BuzzerPinNo){
		GpioController gpioCont = GpioFactory.getInstance();
		buzzerPin = gpioCont.provisionDigitalOutputPin(BuzzerPinNo);
		buzzerPin.setShutdownOptions(false, PinState.HIGH);
	}
	
	public void on(){
		buzzerPin.low();
		status = "off";
	}
	
	public void off(){
		buzzerPin.high();
		status = "on";
	}

	public String getStatus() {
		return status;
	}
	public static void main(String[] args) throws InterruptedException {
		ActiveBuzzer test = new ActiveBuzzer(RaspiPin.GPIO_00);
		for(int i=0;i<100;i++){
			test.on();
			Thread.sleep(300);
			test.off();
			Thread.sleep(300);
		}
	}
}
