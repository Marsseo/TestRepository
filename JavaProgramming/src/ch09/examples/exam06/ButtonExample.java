package ch09.examples.exam06;

public class ButtonExample {

	public static void main(String[] args) {
		
		Button button = new Button();
		button.setOnClinkListener(new CallListener());
		button.touch();
		
		button.setOnClinkListener(new MessageListener());
		button.touch();
	}

}
