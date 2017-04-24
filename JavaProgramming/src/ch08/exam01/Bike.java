package ch08.exam01;

public class Bike implements Manual{
	
	private int speed;
	
	
	public void turnOn() {
		System.out.println("전동 자전거를 켭니다.");		
	}
	public void turnOff(){
		System.out.println("전동 자전거를 끕니다.");
	}
	public void setSpeed(int speed){
		System.out.println("속도를 "+speed+"로 변경합니다.");
		this.speed = speed;
	}
	public int getSpeed(){
		
		return speed;
	}
	public void run(){
		System.out.println("전동 자전거가 "+speed+" 속도로 달립니다.");
	}
	
}
