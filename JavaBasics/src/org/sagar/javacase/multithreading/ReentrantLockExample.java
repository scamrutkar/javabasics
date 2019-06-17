package org.sagar.javacase.multithreading;

import java.util.concurrent.locks.ReentrantLock;

class Resource implements Runnable{
	
	ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		lock.lock();
		System.out.println("Currently executing thread is "+Thread.currentThread().getName());
		System.out.println("Currently waiting thread "+lock.getQueueLength());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}
	
	
}

class Resource1 implements Runnable{
	
	ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		lock.lock();
		for(int i = 0; i < 2;i++)
			accessResource();
		lock.unlock();
	}

	private void accessResource() {
		System.out.println("Currently executing thread is "+Thread.currentThread().getName());
		System.out.println("Currently acquired lock "+lock.getHoldCount());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}

public class ReentrantLockExample {
	
	public static void main(String[] args) {
		
		//Multiple thread are trying to access same resource
		Resource resource = new Resource();
		new Thread(resource).start();
		new Thread(resource).start();
		new Thread(resource).start();
		new Thread(resource).start();
		
		//Single thread is trying to access same lock multiple times
		
		new Thread(new Resource1()).start();
	}

}
