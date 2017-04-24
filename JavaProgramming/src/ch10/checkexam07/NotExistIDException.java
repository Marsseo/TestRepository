package ch10.checkexam07;

public class NotExistIDException extends Exception{
	public NotExistIDException(){}
	public NotExistIDException(String message){
		super(message);
	}
}
