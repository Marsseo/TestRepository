package ch04.examples;

public class SwitchStringExample {
	public static void main(String[] args){
		String position = "����";

		switch(position){
		case "ddd":
			System.out.println("700����");
			break;
		case "aaa":
			System.out.println("500����");
			break;
		default:
			System.out.println("300����");
		}
	}

}
