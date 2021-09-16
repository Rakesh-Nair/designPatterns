package com.patterns.chap51.state.pattern;

public class Demo {
	public static void main(String args[]) {
		LightSwitch ls = new LightSwitch();
		ls.on();
		System.out.println("-------------------");
		ls.off();
		System.out.println("-------------------");
		ls.off();
	}
}

class State{
	void off(LightSwitch ls) {
		System.out.println("Light is already Off");
	}
	
	void on(LightSwitch ls) {
		System.out.println("Light is already On");
	}
}

class LightSwitch {
	private State state;
	
	LightSwitch(){
		state = new OffState();
	}
	
	void on() {
		state.on(this);
	}
	
	void off() {
		state.off(this);
	}
	
	public void setState(State state) {
		this.state = state;
	}
}

class OnState extends State {

	public OnState() {
		System.out.println("Light turned On in OnState constructor");
	}

	@Override
	void off(LightSwitch ls) {
		
		System.out.println("Switching lights Off ...from OnState");
		ls.setState(new OffState());
	}	
}

class OffState extends State{
	OffState(){
		System.out.println("Light turned Off in OffState constructor");
	}

	@Override
	void on(LightSwitch ls) {
		System.out.println("Switching Lights On from OffState.. ");
		ls.setState(new OnState());
	}
	
}

