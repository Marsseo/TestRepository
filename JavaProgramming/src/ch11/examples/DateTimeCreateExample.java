package ch11.examples;

import java.time.*;
import java.time.temporal.ChronoUnit;


public class DateTimeCreateExample {

	public static void main(String[] args) throws InterruptedException {
		LocalDate currDate = LocalDate.now();
		System.out.println("현재날짜 :"+ currDate);
		
		LocalDate targetDate = LocalDate.of(2024,5,10);
		System.out.println("목표날짜 :"+ targetDate+"\n");
		
		LocalTime currTime = LocalTime.now();
		System.out.println("현재시간 :"+ currTime);
		
		LocalTime targetTime = LocalTime.of(6,30,0,0);
		System.out.println("목표시간 :"+ targetTime+"\n");
		
		LocalDateTime currDateTime = LocalDateTime.now();
		System.out.println("현재날짜시간 :"+ currDateTime);
		
		LocalDateTime targetDateTime = LocalDateTime.of(2024,5,10,6,30,0,0);
		System.out.println("목표날짜시간 :"+ targetDateTime+"\n");
		
		ZonedDateTime currZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
		System.out.println("협정 세계시 :"+ currZonedDateTime);
		
		ZonedDateTime targetZonedDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
		System.out.println("뉴욕시간존 :"+ targetZonedDateTime+"\n");
		
		Instant instant1 = Instant.now();
		Thread.sleep(10);
		Instant instant2 = Instant.now();
		if(instant1.isBefore(instant2)){
			System.out.println("instant1이 빠릅니다.");
		}else if(instant1.isAfter(instant2)){
			System.out.println("instant1이 늦습니다.");
		}else{
			System.out.println("동일한 시간입니다.");
		}
		System.out.println("차이(nanos): "+instant1.until(instant2, ChronoUnit.NANOS));
	}

}
