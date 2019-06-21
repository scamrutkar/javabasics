package com.sagar.javacase.multithreading;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Resource implements Runnable {

	ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		lock.lock();
		System.out.println("Currently executing thread is " + Thread.currentThread().getName());
		System.out.println("Currently waiting thread " + lock.getQueueLength());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}

}

class Resource1 implements Runnable {

	ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		lock.lock();
		for (int i = 0; i < 2; i++)
			accessResource();
		lock.unlock();
	}

	private void accessResource() {
		System.out.println("Currently executing thread is " + Thread.currentThread().getName());
		System.out.println("Currently acquired lock " + lock.getHoldCount());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class TryLockResource implements Runnable {

	ReentrantLock lock;

	public TryLockResource(ReentrantLock lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			if (lock.tryLock(1, TimeUnit.MILLISECONDS)) {
				System.out
						.println("Currently executing TRYLOCK RESOURCE thread is " + Thread.currentThread().getName());
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (lock.isHeldByCurrentThread()) {
				System.out.println("Release acquired lock on TRYLOCK RESOURCE");
				lock.unlock();
			}
		}
	}

}

public class ReentrantLockExample {

	public static void main(String[] args) throws InterruptedException {

		// Multiple thread are trying to access same resource

		System.out.println("Multiple threads are trying to access same resource execution started");
		Resource resource = new Resource();
		Thread t1 = new Thread(resource);
		Thread t2 = new Thread(resource);
		Thread t3 = new Thread(resource);
		Thread t4 = new Thread(resource);
		t1.start();t2.start();t3.start();t4.start();
		t1.join();t2.join();t3.join();t4.join();
		
		System.out.println("Multiple threads are trying to access same resource execution done");
		System.out.println("*************************************************************************");
		// Single thread is trying to access same lock multiple times
		System.out.println("Single thread is trying to access same lock multiple times execution started");
		Thread t5 = new Thread(new Resource1());t5.start();t5.join();
		System.out.println("Single thread is trying to access same lock multiple times execution done");
		System.out.println("*************************************************************************");

		// trying to acquire lock for particular time frame
		System.out.println("trying to acquire lock for particular time frame execution started");
		ReentrantLock lock = new ReentrantLock();
		TryLockResource tryLockResource = new TryLockResource(lock);
		Thread t6 = new Thread(tryLockResource);t6.start();
		Thread t7 = new Thread(tryLockResource);t7.start();
		t6.join();t7.join();
		System.out.println("trying to acquire lock for particular time frame execution done");

	}

}
