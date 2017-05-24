package hardware.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class RgbLedDigital {
	private GpioPinDigitalOutput redPin;
	private GpioPinDigitalOutput greenPin;
	private GpioPinDigitalOutput bluePin;
	
	//Constructor
	public RgbLedDigital(Pin redPinNo, Pin greenPinNo, Pin bluePinNo){
		//싱글톤인 GpioController 생성하여 핀 세팅
		GpioController gpioController = GpioFactory.getInstance();
		redPin = gpioController.provisionDigitalOutputPin(redPinNo, PinState.HIGH);
		//어플리케이션이 종료될 때 출력 모드 해제하고 핀 출력을 LOW
		redPin.setShutdownOptions(true, PinState.LOW);

		
		greenPin = gpioController.provisionDigitalOutputPin(greenPinNo, PinState.HIGH);
		greenPin.setShutdownOptions(true, PinState.LOW);
		
		bluePin = gpioController.provisionDigitalOutputPin(bluePinNo, PinState.HIGH);
		bluePin.setShutdownOptions(true, PinState.LOW);
		
	}
	//Method
	
	public void red(boolean a){
		if(a) redPin.low();
		else redPin.high();
	}
	public void green(boolean a){
		if(a) greenPin.low();
		else greenPin.high();
	}
	public void blue(boolean a){
		if(a) bluePin.low();
		else bluePin.high();
	}
	public void rgb(boolean red, boolean green, boolean blue){
		if(red) redPin.low();
		else redPin.high();
		
		if(green) greenPin.low();
		else greenPin.high();
		
		if(blue) bluePin.low();
		else bluePin.high();
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		
		RgbLedDigital test = new RgbLedDigital(RaspiPin.GPIO_00, RaspiPin.GPIO_01, RaspiPin.GPIO_02);
		while(true){
			test.rgb(true, false, false);
			Thread.sleep(50);
			test.rgb(false, true, false);
			Thread.sleep(50);
			test.rgb(false, false, true);
			Thread.sleep(50);
		}
	}
}
