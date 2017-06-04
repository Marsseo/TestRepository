package hardware.motor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import static hardware.motor.PCA9685.*;

public class DCMotor {
	
	private GpioPinDigitalOutput out1;
	private GpioPinDigitalOutput out2;
	private PCA9685 pca;
	private Pin pwm;
	
	public DCMotor(Pin out1, Pin out2, PCA9685 pca, Pin pwm){
		
		GpioController gpc = GpioFactory.getInstance();
		this.out1 = gpc.provisionDigitalOutputPin(out1, PinState.LOW);
		this.out2 = gpc.provisionDigitalOutputPin(out2, PinState.LOW);
		
		this.out1.setShutdownOptions(true, PinState.LOW);
		this.out2.setShutdownOptions(true, PinState.LOW);
		
		this.pca = pca;
		this.pwm = pwm;
	}
	public void setSpeed(int speed){
		pca.setStep(pwm, speed);
	}
	public void forward(){
		out1.low();
		out2.high();
	}
	public void backward(){
		out1.high();
		out2.low();
	}
	public void stop(){
		out1.low();
		out2.low();
		setSpeed(0);
	}
	
	public static void main(String[] args) throws Exception {
		PCA9685 pca = PCA9685.getInstance();
		
		DCMotor m1 = new DCMotor(RaspiPin.GPIO_00, RaspiPin.GPIO_01, pca, PCA9685.PWM_05);
		DCMotor m2 = new DCMotor(RaspiPin.GPIO_02, RaspiPin.GPIO_03, pca, PCA9685.PWM_04);
		
		m1.forward();
		m2.forward();
		
		m1.setSpeed(2500);
		m2.setSpeed(2500);
		Thread.sleep(500);
		pca.setDuration(PWM_00, 800);
		Thread.sleep(1000);
		
		
		m1.stop();
		m2.stop();
		Thread.sleep(1000);
		
		m1.backward();
		m2.backward();
		m1.setSpeed(2500);
		m2.setSpeed(2500);
		Thread.sleep(500);
		pca.setDuration(PWM_00, 2600);
		Thread.sleep(1000);
		
		m1.forward();
		m2.forward();
		pca.setDuration(PWM_00, 1680);
		pca.setDuration(PWM_01, 1750);
		Thread.sleep(2000);
	}
}
