package ch11.examples;

import java.time.*;
import java.time.temporal.ChronoUnit;


public class DateTimeCreateExample {

	public static void main(String[] args) throws InterruptedException {
		LocalDate currDate = LocalDate.now();
		System.out.println("���糯¥ :"+ currDate);
		
		LocalDate targetDate = LocalDate.of(2024,5,10);
		System.out.println("��ǥ��¥ :"+ targetDate+"\n");
		
		LocalTime currTime = LocalTime.now();
		System.out.println("����ð� :"+ currTime);
		
		LocalTime targetTime = LocalTime.of(6,30,0,0);
		System.out.println("��ǥ�ð� :"+ targetTime+"\n");
		
		LocalDateTime currDateTime = LocalDateTime.now();
		System.out.println("���糯¥�ð� :"+ currDateTime);
		
		LocalDateTime targetDateTime = LocalDateTime.of(2024,5,10,6,30,0,0);
		System.out.println("��ǥ��¥�ð� :"+ targetDateTime+"\n");
		
		ZonedDateTime currZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
		System.out.println("���� ����� :"+ currZonedDateTime);
		
		ZonedDateTime targetZonedDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
		System.out.println("����ð��� :"+ targetZonedDateTime+"\n");
		
		Instant instant1 = Instant.now();
		Thread.sleep(10);
		Instant instant2 = Instant.now();
		if(instant1.isBefore(instant2)){
			System.out.println("instant1�� �����ϴ�.");
		}else if(instant1.isAfter(instant2)){
			System.out.println("instant1�� �ʽ��ϴ�.");
		}else{
			System.out.println("������ �ð��Դϴ�.");
		}
		System.out.println("����(nanos): "+instant1.until(instant2, ChronoUnit.NANOS));
	}

}
