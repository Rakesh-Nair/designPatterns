package com.patterns.chap50.observer.pattern;

import java.util.ArrayList;
import java.util.List;

public class Demo implements Observer<Person>{
	public static void main(String args[]) {
		new Demo();
	}

	@Override
	public void handle(PropertyChangedEventArgs<Person> args) {
		System.out.println("Person's " + args.propertyName
			      + " has been changed to " + args.newValue);
	}
	
	public Demo() {
		Person person = new Person();
	    person.subscribe(this);
	    for (int i = 20; i < 24; ++i)
	      person.setAge(i);
	}
}

class PropertyChangedEventArgs<T> {
	public T source;
	public String propertyName;
	public Object newValue;

	public PropertyChangedEventArgs(T source, String propertyName, Object newValue) {
		super();
		this.source = source;
		this.propertyName = propertyName;
		this.newValue = newValue;
	}
}

//observes objects of type T
interface Observer<T> {
	void handle(PropertyChangedEventArgs<T> args);
}

class Observable<T> {
	private List<Observer<T>> observers = new ArrayList<>();

	public void subscribe(Observer<T> observer) {
		this.observers.add(observer);
	}

	protected void propertyChanged(T source, String propertyName, Object newValue) {
		for(Observer<T> observer : observers) {
			observer.handle(new PropertyChangedEventArgs(source, propertyName, newValue));
		}
	}
}

class Person extends Observable<Person>{
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
		 propertyChanged(this, "age", age);
	}
	
}
