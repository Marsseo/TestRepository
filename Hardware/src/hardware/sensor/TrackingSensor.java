package hardware.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.io.IOException;


public class TrackingSensor {
	
	
	GpioPinDigitalInput signal;
	
	public TrackingSensor(Pin signalNo){
		GpioController gpc = GpioFactory.getInstance();
		
		signal = gpc.provisionDigitalInputPin(signalNo);
		signal.setShutdownOptions(true);
		
	}
	public void setGpioPinListenerDigital(GpioPinListenerDigital gpioPin){
		signal.addListener(gpioPin);
	}
	
	public PinState getStatus(){
		return signal.getState();
	}
	
	public static void main(String[] args) throws IOException {
		
		TrackingSensor ts = new TrackingSensor(RaspiPin.GPIO_01);
		
		ts.setGpioPinListenerDigital(e->{
			if(e.getState()==PinState.HIGH) System.out.println("까망");
			else System.out.println("희멀건");
		});
		System.out.println("알류 레뒤");
		System.in.read();
		
	}
}
