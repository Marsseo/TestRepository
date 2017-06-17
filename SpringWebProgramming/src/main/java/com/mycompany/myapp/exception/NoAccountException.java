package com.mycompany.myapp.exception;


// 스프링에서 트랜잭션을 처리하려면 발생되는 예외가 반드시!!!!! RuntimeException 이어야 한다
public class NoAccountException extends RuntimeException{
	
	public NoAccountException(String a) {
		super(a);
	}
	public NoAccountException() {
		
	}
}
