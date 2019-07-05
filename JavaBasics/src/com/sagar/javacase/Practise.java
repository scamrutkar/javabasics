package com.sagar.javacase;

class Test {

	public static String foo(){
		System.out.println("Test foo called");
		return "";
	}
	
	public static void main(String args[]){
		Test obj = new Test();;
		System.out.println(obj.foo());
	}
}


public class Practise {
	
	public static void main(String[] args) {
		
		String a = "Test";
		String b = new String("Test");
		String c = new String("Test");
		
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(c.hashCode());
		
		if(a == b)
			System.out.println("a and b are same");
		if(b == c)
			System.out.println("b and c are same");
		if(a == c)
			System.out.println("a and c are same");

	}

}
