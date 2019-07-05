package com.sagar.javacase.multithreading;

import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueue {

	public static void main(String[] args) {
		LinkedBlockingQueue<Integer> sharedQ = new LinkedBlockingQueue<Integer>();

		Producer1 p = new Producer1(sharedQ);
		Consumer1 c = new Consumer1(sharedQ);

		p.start();
		c.start();
	}

}

class Producer1 extends Thread {
	private LinkedBlockingQueue<Integer> sharedQueue;

	public Producer1(LinkedBlockingQueue<Integer> aQueue) {
		super("PRODUCER");
		this.sharedQueue = aQueue;
	}

	public void run() {
		// no synchronization needed
		for (int i = 1; i <= 10; i++) {
			try {
				System.out.println(getName() + " produced " + i);
				sharedQueue.put(i);
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

class Consumer1 extends Thread {
	private LinkedBlockingQueue<Integer> sharedQueue;

	public Consumer1(LinkedBlockingQueue<Integer> aQueue) {
		super("CONSUMER");
		this.sharedQueue = aQueue;
	}

	public void run() {
		try {
			while (true) {
				Integer item = sharedQueue.take();
				System.out.println(getName() + " consumed " + item);
			}
		} catch (

		InterruptedException e) {
			e.printStackTrace();
		}
	}
}
