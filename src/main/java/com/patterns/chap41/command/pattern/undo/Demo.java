package com.patterns.chap41.command.pattern.undo;

import java.util.List;

import com.google.common.collect.Lists;

public class Demo {
	public static void main(String args[]) {
		BankAccount ba = new BankAccount();
		System.out.println(ba);

		List<BankAccountCommand> commands = List.of(new BankAccountCommand(ba, BankAccountCommand.Action.DEPOSIT, 100),
				new BankAccountCommand(ba, BankAccountCommand.Action.WITHDRAW, 1000));
		for(BankAccountCommand command : commands) {
			command.call();
			System.out.println(ba);
		}
		
		System.out.println("Undo Operation in progress");
		for(BankAccountCommand command : Lists.reverse(commands)) {
			command.undo();
			System.out.println(ba);
		}
	}
}

/*
 * An object which represents an instruction to perform a particular action
 * Contains all the information necessary for the action to be taken.
 * 
 */

class BankAccount {
	private int balance;
	private int overdraftLimit = -500;
	private boolean succeeded;

	public void deposit(int amount) {
		balance += amount;
		System.out.println("Deposited " + amount + ", balance is now " + amount);
	}

	public boolean withdraw(int amount) {
		if (balance - amount >= overdraftLimit) {
			balance -= amount;
			System.out.println("Withdrew " + amount + ", balance is now " + amount);
			succeeded = true;
		}
		succeeded = false;
		return succeeded;
	}

	@Override
	public String toString() {
		return "BankAccount [balance=" + balance + ", overdraftLimit=" + overdraftLimit + "]";
	}

}

interface Command {
	void call();
	void undo();
}

class BankAccountCommand implements Command {
	private BankAccount account;
	private Boolean succeeded;

	public enum Action {
		DEPOSIT, WITHDRAW
	}

	private Action action;
	private int amount;

	public BankAccountCommand(BankAccount account, Action action, int amount) {
		super();
		this.account = account;
		this.action = action;
		this.amount = amount;
	}

	@Override
	public void call() {

		switch (action) {
		case DEPOSIT:
			account.deposit(amount);
			succeeded = true;
			break;
		case WITHDRAW:
			succeeded = account.withdraw(amount);
			break;
		}
	}

	@Override
	public void undo() {
		if(!succeeded) return;
		switch (action) {
		case WITHDRAW:
			account.deposit(amount);
			break;
		case DEPOSIT:
			account.withdraw(amount);
			break;
		}
		
	}

}

