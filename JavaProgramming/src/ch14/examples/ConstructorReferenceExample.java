package ch14.examples;

public class ConstructorReferenceExample {
	public static void main(String[] args){
		
		
		createMember(new FunctionalInterface(){

			@Override
			public Member createMember(String mid, String mname) {
				return new Member(mid, mname);
			}
			
		});
		
		createMember((mid, mname)->{return new Member(mid, mname);});
	}
	
	public static void createMember(FunctionalInterface i){
		Member member = i.createMember("white", "ȫ�浿");
		System.out.println(member.getMid()+" "+ member.getMname());
	}
}
