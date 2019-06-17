package org.sagar.javacase.multithreading;

import java.util.concurrent.Semaphore;

class SharedResource {
	int item;
	static Semaphore semCon = new Semaphore(0);
	static Semaphore semProd = new Semaphore(1);

	void get() {
		try {
			semCon.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}
		System.out.println("Consumer consumed item : " + item);
		semProd.release();
	}

	void put(int item) {
		try {
			semProd.acquire();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}
		this.item = item;
		System.out.println("Producer produced item : " + item);
		semCon.release();
	}
}

class SemaphoreProducer implements Runnable {
	SharedResource sharedResource;

	public SemaphoreProducer(SharedResource sharedResource) {
		this.sharedResource = sharedResource;
		new Thread(this).start();
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++)
			sharedResource.put(i);
	}
}

class SemaphoreConsumer implements Runnable {
	SharedResource sharedResource;

	public SemaphoreConsumer(SharedResource sharedResource) {
		this.sharedResource = sharedResource;
		new Thread(this).start();
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++)
			sharedResource.get();
	}
}

public class SemaphoreTest {
	public static void main(String[] args) {
		SharedResource shared = new SharedResource();
		new SemaphoreConsumer(shared);
		new SemaphoreProducer(shared);
	}
}
