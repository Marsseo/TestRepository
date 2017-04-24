package ch15.exam12;

import java.util.*;

public class QueueExample {

	public static void main(String[] args) {
		Queue<Message> msQueue = new LinkedList<Message>();
		
		msQueue.offer(new Message("sendMail","ȫ�浿"));
		msQueue.offer(new Message("sendSMS", "�ſ��"));
		msQueue.offer(new Message("sendKakaotalk","ȫ�β�"));
		
		
		while(!msQueue.isEmpty()){
			Message message = msQueue.poll();
			switch(message.command){
			case "sendMail":
				System.out.println(message.to+"�Կ��� ������ �����ϴ�.");
				break;
			case "sendSMS":
				System.out.println(message.to+"�Կ��� SMS�� �����ϴ�.");
				break;
			case "sendKakaotalk":
				System.out.println(message.to+"�Կ��� īī������ �����ϴ�.");
				break;
			}
		}
	}

}
