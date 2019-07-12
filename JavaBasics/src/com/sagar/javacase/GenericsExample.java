package com.sagar.javacase;

class GenericTypes<T>{

	T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
}

public class GenericsExample {
	
	public static void main(String[] args) {
		
		GenericTypes<String> stringObj = new GenericTypes<String>();
		stringObj.setT("String Object");
		System.out.println(stringObj.toString());
		
		GenericTypes<String> intObj = new GenericTypes<String>();
		stringObj.setT("Integer Object");
		System.out.println(intObj.toString());
		
	}

}
