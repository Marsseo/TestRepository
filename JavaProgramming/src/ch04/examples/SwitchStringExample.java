package ch04.examples;

public class SwitchStringExample {
	public static void main(String[] args){
		String position = "°úÀå";

		switch(position){
		case "ºÎÀå":
			System.out.println("700ï¿½ï¿½ï¿½ï¿½");
			break;
		case "°úÀå":
			System.out.println("500ï¿½ï¿½ï¿½ï¿½");
			break;
		default:
			System.out.println("300ï¿½ï¿½ï¿½ï¿½");
		}
	}

}
