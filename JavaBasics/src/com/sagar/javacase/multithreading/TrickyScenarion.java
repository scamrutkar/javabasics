package com.sagar.javacase.multithreading;

class TrickyClass{
	
	static synchronized void staticMethod() {
		System.out.println("In static method");
	}
	
	public synchronized void Method1() {
		System.out.println("In method1");
		
	}
	
	public synchronized void Method2() {
		System.out.println("In method2");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}

public class TrickyScenarion {

	public static void main(String[] args) {
		TrickyClass tricky = new TrickyClass();
		
		Thread t1 = new Thread(()->tricky.Method2());
		Thread t2 = new Thread(()->tricky.Method1());
		Thread t3 = new Thread(()->tricky.Method1());
		Thread t4 = new Thread(()->TrickyClass.staticMethod());
		
		t4.start();
		t1.start();
		t2.start();
		t3.start();
		
		MyClass i = new MyClass();
		i.start();
		
		

	}
	
	static class MyClass extends Thread{
		public void run() throws RuntimeException{
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
