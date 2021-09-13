package com.patterns.chap35.proxy.pattern;

public class Demo {
	public static void main(String args[]) {
		Driver driver = new Driver(11);
		
		Car car = new Car(driver);
		System.out.println("With Normal Car class ");
		car.drive();
		
		car = new CarProxy(driver);
		System.out.println("With Proxy Car class ");
		car.drive();
	}
}

interface drivable{
	public void drive();
}

class Driver {
	
	int age;

	public Driver(int age) {
		super();
		this.age = age;
	}
	
}

class Car implements drivable{

	
	protected Driver driver;
	
	public Car(Driver driver) {
		super();
		this.driver = driver;
	}

	@Override
	public void drive() {
		System.out.println("Car is being driven");
	}
	
}

class CarProxy extends Car{

	public CarProxy(Driver driver) {
		super(driver);
	}

	@Override
	public void drive() {
		if(driver.age > 12)
		super.drive();
		else
			System.out.println("Driver too Young");
	}
	
	
	
}
