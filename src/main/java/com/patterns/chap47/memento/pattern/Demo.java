package com.patterns.chap47.memento.pattern;

public class Demo {
	public static void main(String args[]) {
		BankAccount ba = new BankAccount(100);
	    Memento m1 = ba.deposit(50); // 150
	    Memento m2 = ba.deposit(25); // 175
	    System.out.println(ba);

	    // restore to m1
	    ba.restore(m1);
	    System.out.println(ba);

	    // restore to m2
	    ba.restore(m2);
	    System.out.println(ba);
	}
}

class BankAccount {
	private int balance;

	public BankAccount(int balance) {
		super();
		this.balance = balance;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Memento deposit(int amount) {
		balance += amount;
		return new Memento(balance);
	}

	public void restore(Memento m) {
		this.balance = m.balance;
	}

	@Override
	public String toString() {
		return "BankAccount [balance=" + balance + "]";
	}
	
	

}

class Memento {
	public int balance;

	public Memento(int balance) {
		super();
		this.balance = balance;
	}
}