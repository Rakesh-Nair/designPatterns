package com.patterns.chap37.proxy.pattern.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/*A proxy constructed at run time instead of compile time is called Dynamic Proxy*/

public class Demo {
	public static void main(String args[]) {
		Person person = new Person();
		Human logged = withLogging(person, Human.class);
		logged.walk();
		logged.walk();
		logged.talk();
		System.out.println(logged);
	}

	public static <T> T withLogging(T target, Class<T> itf) {
		return (T) Proxy.newProxyInstance(itf.getClassLoader(), new Class<?> [] {itf}, new LoggingHandler(target));
	}
}

class LoggingHandler implements InvocationHandler {

	private final Object target;
	private Map<String, Integer> calls = new HashMap<>();

	public LoggingHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String name = method.getName();
		if (name.contains("toString")) {
			return calls.toString();
		}

		calls.merge(name, 1, Integer::sum);
		return method.invoke(target, args);
	}

}

interface Human {
	void walk();

	void talk();
}

class Person implements Human {

	@Override
	public void walk() {
		System.out.println("Hii I am walking");

	}

	@Override
	public void talk() {
		System.out.println("Hii I am talking");
	}

}
