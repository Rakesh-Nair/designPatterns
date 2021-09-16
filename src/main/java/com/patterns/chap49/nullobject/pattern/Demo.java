package com.patterns.chap49.nullobject.pattern;

import java.lang.reflect.Proxy;

public class Demo {
	public static void main(String args[]) {
		//NullLog log = new NullLog();
		Log log = noOp(Log.class);
		BankAccount ba = new BankAccount(log);
		ba.deposit(100);
		System.out.println(ba);
		ba.withdraw(200);
		System.out.println(ba);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T noOp (Class<T> itf) {
		return (T) Proxy.newProxyInstance(itf.getClassLoader(), new Class<?> [] {itf},
				(proxy, method, args) -> {
					if(method.getReturnType().equals(Void.TYPE)) {
						return null;
					}
					else {
						return method.getReturnType().getConstructor().newInstance();
					}
				});
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