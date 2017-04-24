package ch10.exam06;

public class Account {
	private long balance;
	
	
	
	public long getBalance() {
		return balance;
	}
	public void deposit(int money){
		this.balance += money;
	}
	public void withdraw(int money) throws BalanceInsufficientException{
		if(this.balance<money){
			//throw new BalanceInsufficientException();
			throw new BalanceInsufficientException("ÀÜ°í ºÎÁ·");
		}
		this.balance -=money;
	}
}
