package com.patterns.chap36.proxy.pattern.property;

//Provides more control over the Property

public class Demo {
	public static void main(String args[]) {
	    Creature c = new Creature();
	    c.setAgility(12);
	    System.out.println(c);
	}
}

class Property<T>{
	private T value;

	public Property(T value) {
		super();
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		
		if(this == o ) return true;
		if(o == null || getClass() != o.getClass()) return false;
		
		Property<?> property = (Property<?>) o;
		return value != null ? value.equals(property.value):property.value == null;
		
	}
	@Override
	public int hashCode() {
		
		return value != null ? value.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Property [value=" + value + "]";
	}
	
	
}


class Creature {
	private Property<Integer> agility= new Property<>(10);
	
	public void setAgility(int value) {
		agility.setValue(value);
	}
	
	public int getAgility() {
		return agility.getValue();
	}

	@Override
	public String toString() {
		return "Creature [agility=" + agility + "]";
	}
	
	
}

