package hardware.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class DualColorLed {
	
	//Field
	private GpioPinDigitalOutput redPin;
	private GpioPinDigitalOutput greenPin;
	
	//Constructor
	public DualColorLed(Pin redPinNo, Pin greenPinNo){
		//싱글톤인 GpioController 생성하여 핀 세팅
		GpioController gpioController = GpioFactory.getInstance();
		redPin = gpioController.provisionDigitalOutputPin(redPinNo);
		//어플리케이션이 종료될 때 출력 모드 해제하고 핀 출력을 LOW
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
		
		
		DualColorLed test = new DualColorLed(RaspiPin.GPIO_21, RaspiPin.GPIO_22);
		while(true){
			for(int i =0;i<3;i++){
				test.green();
			}
			Thread.sleep(3000);
			for(int i =0;i<3;i++){
				test.red();
			}
			Thread.sleep(1000);
			
		}
	}
}
