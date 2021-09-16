package com.patterns.chap48.nullobject.pattern;

public class Demo {
	public static void main(String args[]) {
		NullLog log = new NullLog();

		BankAccount ba = new BankAccount(log);
		ba.deposit(100);
		System.out.println(ba);
		ba.withdraw(200);
		System.out.println(ba);
	}
}

interface Log {
	void info(String msg);

	void warn(String msg);
}

class BankAccount {
	private int balance;
	private Log log;

	public BankAccount(Log log) {
		super();
		this.log = log;
	}

	public void withdraw(int i) {
		if(balance > i) {
			balance -= i;
			log.info("Withdrew "+ i + ", balance is now "+balance);
		}
		
	}

	public void deposit(int amount) {
		balance += amount;
		log.info("Deposited " + amount + ", balance is now " + balance);
	}

	@Override
	public String toString() {
		return "BankAccount [balance=" + balance + "]";
	}
	
	
}

class NullLog implements Log {

	@Override
	public void info(String msg) {

	}

	@Override
	public void warn(String msg) {

	}

}