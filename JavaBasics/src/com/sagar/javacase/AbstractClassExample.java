package com.sagar.javacase;

abstract class SuperClass{
	
	protected synchronized void defaultMethod() {
		System.out.println("In super class");
	};
	
}

class BaseClass extends SuperClass{
	
	@Override
	public synchronized void defaultMethod() {
		System.out.println("In base class");
	}; 
}


public class AbstractClassExample {
	
	private AbstractClassExample() {};
	
	
	public static void main(String[] args) {
		
		AbstractClassExample obj = new AbstractClassExample();
		
		SuperClass object = new BaseClass();
		object.defaultMethod();
		
	}
	
	

}
