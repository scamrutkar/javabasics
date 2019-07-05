package com.sagar.javacase.multithreading;

class ShareObject implements Runnable{
	
	private volatile int count = 0;
	
	public void getIncrement() {
		count++;
	}
	
	public int getCount() {
		return count;
	}

	@Override
	public void run() {
		System.out.println("Thread name: "+Thread.currentThread().getName());
		System.out.println("Current count: "+count);
		System.out.println("Incrementing Count");
		getIncrement();
		System.out.println("Count after increment: "+count);
	} 
	
}


public class VolatileExample {

	public static void main(String[] args) {

		ShareObject object = new ShareObject();
		
		Thread thread1 = new Thread(object);
		Thread thread2 = new Thread(object);
		thread1.start();
		thread2.start();
	}

}
