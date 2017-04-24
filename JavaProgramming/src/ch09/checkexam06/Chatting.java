package ch09.checkexam06;

public class Chatting {
	void startChat(String chatId){
		String nickName = chatId;
		Chat chat = new Chat(){
			@Override
			public void start(){
				while(true){
					String inputData = "æ»≥Á«œººø‰";
					String message = "["+nickName+"]"+inputData;
				}
			}
		};
		chat.start();
	}
	class Chat{
		void start(){}
		void sendMessage(String message){}
	}
}
