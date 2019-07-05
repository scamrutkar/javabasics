package com.sagar.javacase.java8;

import java.io.IOException;

import javax.xml.bind.JAXBException;

interface interface1 {

	public void method1();

	default void log() {
		System.out.println("Executing interface1 method");
	}
}

interface interface2 {
	
	public void method2();

	default void log() {
		System.out.println("Executing interface2 method");
	}
}

public class InterfaceFeeatures implements interface1,interface2{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void method1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		interface1.super.log();
	}

}


class TestException6 {

	public static void main(String[] args) {
		try {
			foo();
		} catch (IOException  e) {
			e = (IOException) new Exception("");
			e.printStackTrace();
		}catch(Exception e){
			e = new Exception("");
			e.printStackTrace();
		}
	}

	public static void foo() throws IOException, JAXBException{
		
	}
}
