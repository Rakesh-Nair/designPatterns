package com.patterns.chap33.flyweight.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Demo {
	public static void main(String args[]) {
		User2 user3 = new User2("Sanji Vinsmoke");
		User2 user1 = new User2("Ichiji Vinsmoke");
		User2 user2 = new User2("Niji Vinsmoke");
		User2 user4 = new User2("Yonji Vinsmoke");
		
		System.out.println("Full Name of Sanji : "+ user3.getFullName());
		System.out.println("Full Name of Ichiji : "+ user1.getFullName());
	}
}

class User{
	public String fullName;

	public User(String fullName) {
		super();
		this.fullName = fullName;
	}
	
}

class User2{
	static List<String> strings = new ArrayList<>();
	private int[]names;
	
	public User2(String fullName) {
		Function<String, Integer> getOrAdd = (String s) -> {
			int idx = strings.indexOf(s);
			if(idx != -1) {
				return idx;
			}
			else {
				strings.add(s);
				return strings.size() -1;
			}
		};
		
		names = Arrays.stream(fullName.split(" "))
				.mapToInt(s -> getOrAdd.apply(s)).toArray();
		
		System.out.println("After Creating Object : Strings array"+ strings);
		System.out.println("After Creating Object : names array "+ names);
	}
	
	public String getFullName() {
		return Arrays.stream(names).mapToObj(i -> strings.get(i)).collect(Collectors.joining(" "));
	}
}
