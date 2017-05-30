package test;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.convertor.PCF8591;
import hardware.led.DualColorLed;
import hardware.motor.SG90Servo;
import hardware.sensor.GasSensor;


public class GasSensorBuzzerDualLedServoMotorTest {
	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN1);
		GasSensor ts = new GasSensor(pcf8591, RaspiPin.GPIO_04);
		DualColorLed led = new DualColorLed(RaspiPin.GPIO_02, RaspiPin.GPIO_00);
		ActiveBuzzer ab = new ActiveBuzzer(RaspiPin.GPIO_03);
		SG90Servo mt = new SG90Servo(RaspiPin.GPIO_01);
		
		ts.setGpioPinListenerDigital(e->{
			if(e.getState()==PinState.LOW){
				mt.setAngle(180);
				led.red();
				ab.on();
			}else{
				mt.setAngle(0);
				led.green();
				ab.off();
			}
		});
		
		while(true){
			double value = ts.getValue();
			System.out.print("값: "+value);
			if(value<100){}			
			Thread.sleep(1000);
		}
	}
}
