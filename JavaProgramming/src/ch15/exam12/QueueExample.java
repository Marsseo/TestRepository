package ch15.exam12;

import java.util.*;

public class QueueExample {

	public static void main(String[] args) {
		Queue<Message> msQueue = new LinkedList<Message>();
		
		msQueue.offer(new Message("sendMail","È«±æµ¿"));
		msQueue.offer(new Message("sendSMS", "½Å¿ë±Ç"));
		msQueue.offer(new Message("sendKakaotalk","È«µÎ²²"));
		
		
		while(!msQueue.isEmpty()){
			Message message = msQueue.poll();
			switch(message.command){
			case "sendMail":
				System.out.println(message.to+"´Ô¿¡°Ô ¸ÞÀÏÀ» º¸³À´Ï´Ù.");
				break;
			case "sendSMS":
				System.out.println(message.to+"´Ô¿¡°Ô SMS¸¦ º¸³À´Ï´Ù.");
				break;
			case "sendKakaotalk":
				System.out.println(message.to+"´Ô¿¡°Ô Ä«Ä«¿ÀÅåÀ» º¸³À´Ï´Ù.");
				break;
			}
		}
	}

}
