package hardware.motor;

import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import java.math.BigDecimal;


public class PCA9685 {
	
	private static PCA9685 singleton;
	public static PCA9685 getInstance() throws Exception{
		if(singleton == null){
			singleton = new PCA9685();
		}
		return singleton;
	}
	private PCA9685GpioProvider gpioProvider;
	
	public static final Pin PWM_00 = PCA9685Pin.PWM_00;
	public static final Pin PWM_01 = PCA9685Pin.PWM_01;
	public static final Pin PWM_02 = PCA9685Pin.PWM_02;
	public static final Pin PWM_03 = PCA9685Pin.PWM_03;
	public static final Pin PWM_04 = PCA9685Pin.PWM_04;
	public static final Pin PWM_05 = PCA9685Pin.PWM_05;
	public static final Pin PWM_06 = PCA9685Pin.PWM_06;
	public static final Pin PWM_07 = PCA9685Pin.PWM_07;
	public static final Pin PWM_08 = PCA9685Pin.PWM_08;
	public static final Pin PWM_09 = PCA9685Pin.PWM_09;
	public static final Pin PWM_10 = PCA9685Pin.PWM_10;
	public static final Pin PWM_11 = PCA9685Pin.PWM_11;
	public static final Pin PWM_12 = PCA9685Pin.PWM_12;
	public static final Pin PWM_13 = PCA9685Pin.PWM_13;
	public static final Pin PWM_14 = PCA9685Pin.PWM_14;
	public static final Pin PWM_15 = PCA9685Pin.PWM_15;
	
	private int period;
	
	private PCA9685() throws Exception{
		I2CBus i2cBus = I2CFactory.getInstance(I2CBus.BUS_1);
		// PWM 주파수를 50Hz로 설정(SG90 서브모터의 주파수가 50이기 때문)
		gpioProvider = new PCA9685GpioProvider(i2cBus, 0x40, new BigDecimal(50));
		period = gpioProvider.getPeriodDurationMicros();
		
		GpioController gpc = GpioFactory.getInstance();
		
		gpc.provisionPwmOutputPin(gpioProvider, PWM_00);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_01);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_02);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_03);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_04);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_05);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_06);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_07);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_08);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_09);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_10);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_11);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_12);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_13);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_14);
		gpc.provisionPwmOutputPin(gpioProvider, PWM_15);		
		
		gpioProvider.reset();
	}
	
	public void setDuration(Pin pin, int duration){
		if(duration>=period){
			duration = period-1;
		}else if(duration<0){
			duration = 0;
		}
		if(duration !=0){
			gpioProvider.setPwm(pin, duration);
		}else{
			gpioProvider.setAlwaysOff(pin);
		}
	}
	
	public void setStep(Pin pin, int step){
		//step 0~4095
		if(step>4095) step = 4095;
		else if(step<0) step = 0;
		
		if(step !=0){
			gpioProvider.setPwm(pin, 0, step);
		}else{
			gpioProvider.setAlwaysOff(pin);
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		PCA9685 pca9685 = PCA9685.getInstance();
		while(true){
			pca9685.setDuration(PWM_00, 800);	// 0도
			pca9685.setDuration(PWM_01, 800);
			pca9685.setDuration(PWM_00, 800);
			Thread.sleep(1000);
			pca9685.setDuration(PWM_00, 2600);	// 180도
			pca9685.setDuration(PWM_01, 2600);
			pca9685.setDuration(PWM_02, 2600);
			Thread.sleep(1000);
			pca9685.setDuration(PWM_00, 1750);	// 90도
			pca9685.setDuration(PWM_01, 1750);
			pca9685.setDuration(PWM_02, 1750);
			Thread.sleep(3000);
			
			//step 으로 제어
			pca9685.setStep(PWM_00, 164);	// 0도
			pca9685.setStep(PWM_01, 164);
			pca9685.setStep(PWM_02, 164);
			Thread.sleep(1000);
			pca9685.setStep(PWM_00, 552);	// 180도
			pca9685.setStep(PWM_01, 552);
			pca9685.setStep(PWM_02, 552);
			Thread.sleep(1000);
			pca9685.setStep(PWM_00, 358);	// 90도
			pca9685.setStep(PWM_01, 358);
			pca9685.setStep(PWM_02, 358);
			Thread.sleep(1000);
		}
		
	}
}
