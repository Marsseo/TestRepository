package hardware.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class UltrasonicSensor {
	
	private GpioPinDigitalOutput trigPin;
	private GpioPinDigitalInput echoPin;
	
	private boolean again;
	private int count;
	private int previousDistance;
	
	public UltrasonicSensor(Pin trigPinNo,Pin echoPinNo){
		GpioController gpioCont = GpioFactory.getInstance();
		
		trigPin = gpioCont.provisionDigitalOutputPin(trigPinNo);
		trigPin.setShutdownOptions(true, PinState.LOW);
		
		echoPin = gpioCont.provisionDigitalInputPin(echoPinNo);
		echoPin.setShutdownOptions(true, PinState.LOW);
	}
	public int getDistance(){
		double start = 0;
		double end = 0;

		trigPin.high();
		try {
			Thread.sleep(0, 10000);
		} catch (InterruptedException ex) {}
		trigPin.low();
		count = 0;
		while(echoPin.isLow()){
			count++;
			if(count>50000) return getDistance();
		}
		start = System.nanoTime();
		while(echoPin.isHigh()){
			count++;
			if(count>50000) return getDistance();
		}
		end = System.nanoTime();
		double second = (end - start)/100000000/2;
		//거리 (cm)
		int distance = (int)(second*3400);
		if(again==false && Math.abs(previousDistance-distance)>100){
			again = true;
			getDistance();
			distance = getDistance();
		}else{
			again = false;
		}
		
		if(distance < 2){
			distance = 2;
		}else if(distance>400){
			distance = 400;
		}
		
		previousDistance = distance;
		
		return distance;
	}
	public static void main(String[] args) throws InterruptedException {
		
		UltrasonicSensor uls = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
		
		while(true){
			int distance = uls.getDistance();
			System.out.println("거리(cm): "+distance);
			Thread.sleep(1000);
		}
	}
}
