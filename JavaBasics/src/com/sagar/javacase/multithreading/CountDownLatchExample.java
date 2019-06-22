package com.sagar.javacase.multithreading;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
	
	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(3);
		
		new Thread(new EventService(latch)).start();
		new Thread(new ManagerService(latch)).start();
		new Thread(new BusinessService(latch)).start();
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Event, Manager and Business services started successfully");
		
	}

}

class EventService implements Runnable{
	
	CountDownLatch latch;
	
	public EventService(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Event Service is starting");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Event service has started");
		latch.countDown();
	}
	
}

class ManagerService implements Runnable{
	
	CountDownLatch latch;
	
	public ManagerService(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Manager Service is starting");
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Manager service has started");
		latch.countDown();
	}
	
}

class BusinessService implements Runnable{
	
	CountDownLatch latch;
	
	public BusinessService(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("Business Service is starting");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Business service has started");
		latch.countDown();
	}
	
}
