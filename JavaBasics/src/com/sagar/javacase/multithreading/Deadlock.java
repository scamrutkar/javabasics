package com.sagar.javacase.multithreading;

public class Deadlock {
	
	public static void main(String[] args) {
		
		String resource1 = "Sagar";
		String resource2 = "Nikhil";
		
		Thread t1 = new Thread() {
			@Override
			public void run() {
				synchronized(resource1) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Executing thread1 resource1");
					synchronized(resource2) {
						System.out.println("Executing thread1 resource2");
					}
				}
			}
		};
		
		Thread t2 = new Thread() {
			@Override
			public void run() {
				synchronized(resource2) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Executing thread2 resource2");
					synchronized(resource1) {
						System.out.println("Executing thread2 resource1");
					}
				}
			}
		};
		
		t1.start();
		t2.start();
	}

}
