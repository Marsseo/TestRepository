package ch09.examples.exam06;

public class Button {
	//Field
	private OnClickListener onClickListener; 
	//Constructor
	//Method
	
	

	public void setOnClinkListener(OnClickListener Listener) {
		this.onClickListener = Listener;
	}
	public void touch(){

			onClickListener.onClick();
	}
	//Nested Interface
	interface OnClickListener {
		void onClick();
	}
	
}
