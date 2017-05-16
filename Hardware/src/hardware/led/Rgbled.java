package hardware.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class Rgbled {
	private GpioPinDigitalOutput redPin;
	private GpioPinDigitalOutput greenPin;
	private GpioPinDigitalOutput bluePin;
	
	//Constructor
	public Rgbled(Pin redPinNo, Pin greenPinNo, Pin bluePinNo){
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
	public void red(){
		redPin.low();
		greenPin.high();
		bluePin.high();
	}
	public void green(){
		redPin.high();
		greenPin.low();
		bluePin.high();		
	}
	public void blue(){
		redPin.high();
		greenPin.high();
		bluePin.low();
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		
		Rgbled test = new Rgbled(RaspiPin.GPIO_00, RaspiPin.GPIO_01, RaspiPin.GPIO_02);
		while(true){
			test.red();
			Thread.sleep(50);
			test.green();
			Thread.sleep(50);
			test.blue();
			Thread.sleep(50);
		}
	}
}
