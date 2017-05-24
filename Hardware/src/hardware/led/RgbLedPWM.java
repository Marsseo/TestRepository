package hardware.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.IOException;


public class RgbLedPWM {
	
	private GpioPinPwmOutput redPin;
	private GpioPinPwmOutput greenPin;
	private GpioPinPwmOutput bluePin;
	private int[] curColorSet = new int[3];

	public int[] getCurColorSet() {
		return curColorSet;
	}
	
	public RgbLedPWM(Pin redPinNo, Pin greenPinNo, Pin bluePinNo){ // 각각의 핀 번호를 r g b에 세팅
		GpioController gpc = GpioFactory.getInstance();
		redPin = gpc.provisionSoftPwmOutputPin(redPinNo);
		greenPin = gpc.provisionSoftPwmOutputPin(greenPinNo);
		bluePin = gpc.provisionSoftPwmOutputPin(bluePinNo);
		
		//제어 단계를 255 단계로 설정 (rgb의 값이 255까지 있어서 편하게 하려고 255로 세팅한다.)
		redPin.setPwmRange(255);
		greenPin.setPwmRange(255);
		bluePin.setPwmRange(255);
		
		//처음 다 꺼지기 위해서 가장 높은 값인 255로 세팅 <- 모든 값이 high일 때 led가 꺼지므로
		redPin.setPwm(255);
		greenPin.setPwm(255);
		bluePin.setPwm(255);
		
		//프로그램 종료시 불 끄기
		redPin.setShutdownOptions(true);
		greenPin.setShutdownOptions(true);
		bluePin.setShutdownOptions(true);
			
	}
	
	
	public void ledColorSet(int r, int g, int b){
		curColorSet[0] = r;
		curColorSet[1] = g;
		curColorSet[2] = b;
		
		r=255-r;  // low일 때 커지기 때문에 거꾸로 가시적으로 하기 위해서 반대로 빼도록 설정
		g=255-g;
		b=255-b;
		redPin.setPwm(r);
		greenPin.setPwm(g);
		bluePin.setPwm(b);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		RgbLedPWM test = new RgbLedPWM(RaspiPin.GPIO_03, RaspiPin.GPIO_02, RaspiPin.GPIO_04);
		
		// 번갈아가면서 색깔을 주고 두개, 그리고 전부 색깔을 주는 것을 테스트
		test.ledColorSet(255, 0, 0);
		Thread.sleep(1000);
		test.ledColorSet(0, 255, 0);
		Thread.sleep(1000);
		test.ledColorSet(0, 0, 255);
		Thread.sleep(1000);
		test.ledColorSet(255, 255, 0);
		Thread.sleep(1000);
		test.ledColorSet(0, 255, 255);
		Thread.sleep(1000);
		test.ledColorSet(255, 0, 255);
		Thread.sleep(1000);
		test.ledColorSet(255, 255, 255);
		Thread.sleep(1000);
		
		test.ledColorSet(0, 0, 0);
		
		System.out.println("ready");
		System.in.read();
		
	}
	
}
