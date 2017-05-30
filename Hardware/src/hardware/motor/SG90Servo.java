package hardware.motor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;


public class SG90Servo {
	
	private GpioPinPwmOutput pin;
	private int angle;
	private double minStep;
	private double maxStep;
	
	public SG90Servo(Pin pinNo){
		this(pinNo, 8, 27);
	}
	
	public SG90Servo(Pin pinNo, double minStep, double maxStep){
		this.minStep = minStep;
		this.maxStep = maxStep;
		GpioController gpc = GpioFactory.getInstance();
		pin = gpc.provisionPwmOutputPin(pinNo);
		
		Gpio.pwmSetMode(Gpio.PWM_MODE_MS); // 마크 스페이스 로 설정
		Gpio.pwmSetClock(1920); // 클락 설정
		Gpio.pwmSetRange(200); // 단계 설정
				
	}

	public void setAngle(int angle) {
		if(angle<0) angle = 0; // 0보다 작으면 0
		else if(angle >180) angle = 180; // 180보다 크면 무조건 180
		
		this.angle = angle;
		// step을 구하기 위한 공식 각도에 따라 일정비율 커지고 minStep과 maxStep 사이의 값이 된다.
		int step = (int)(minStep + (angle*(maxStep-minStep)/180.0)); 
		pin.setPwm(step);
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		SG90Servo mt = new SG90Servo(RaspiPin.GPIO_01);
		
//		for(int i =0 ;i<180;i+=10){ // 10도씩 각도가 바뀌면서 테스트
//			mt.setAngle(i);
//			Thread.sleep(500);
//		}
		
		mt.setAngle(90);
		Thread.sleep(500);
	}
	
}
