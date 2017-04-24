package ch18.homework1;

import java.util.Scanner;

public class ScannerExample {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("���ڿ� �Է�> ");
		String inputString = scanner.nextLine();
		System.out.println(inputString);
		System.out.println();
		
		System.out.print("���� �Է�> ");
		String inputInt = scanner.nextLine();
		System.out.println(inputInt);
		System.out.println();
		
		System.out.print("�Ǽ� �Է�> ");
		String inputDouble = scanner.nextLine();
		System.out.println(inputDouble);
		System.out.println();
		

	}

}
