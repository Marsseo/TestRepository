package ch09.examples.exam09;

public class Button {
	//Field
	private OnClickListener Listener; 
	//Constructor
	//Method
	
	

	public void setOnClinkListener(OnClickListener Listener) {
		this.Listener = Listener;
	}
	public void touch(){

			Listener.onClick();
	}
	//Nested Interface
	interface OnClickListener {
		void onClick();
	}
	
}
