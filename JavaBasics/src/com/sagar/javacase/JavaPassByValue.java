package com.sagar.javacase;

public class JavaPassByValue {

	public static void main(String[] args) {
		
		System.out.println(JavaPassByValue.class.getClassLoader());

		try {
			Class.forName("com.sagar.javacase.JavaPassByValue", true, JavaPassByValue.class.getClassLoader().getParent());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
