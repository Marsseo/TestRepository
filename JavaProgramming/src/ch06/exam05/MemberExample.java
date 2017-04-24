package ch06.exam05;

import java.util.Scanner;

public class MemberExample {
	public static void main(String[] args) throws Exception{
		while(true){
			
			Member[] members = new Member[100];
			
			Scanner scanner= new Scanner(System.in);
			
			while(true){
				System.out.println("----------------------------------");
				System.out.println("1.회원가입 | 5. 종료");
				System.out.println("----------------------------------");
				System.out.print("선택: ");
				String strNum = scanner.nextLine();
				if(strNum.equals("1")){
					System.out.print("이름: ");
					String name = scanner.nextLine();
					System.out.print("나이: ");
					int age = Integer.parseInt(scanner.nextLine());
					Member member = new Member(name, age);
					for(int i=0;i<members.length;i++){
						if(members[i]==null){
							members[i] = member;
							break;
						}
					}
				} else if(strNum.equals("5")){
					System.exit(0);
				} else {
					System.out.println("잘못입력하셨습니다. 다시 입력해주세요!");
				}
								
			}
			
		}
	}
}
