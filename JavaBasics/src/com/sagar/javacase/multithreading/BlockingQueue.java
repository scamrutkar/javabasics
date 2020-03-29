package com.sagar.javacase.multithreading;

import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueue {

	static int i = 0;

	public static void main(String[] args) {
		LinkedBlockingQueue<Integer> sharedQ = new LinkedBlockingQueue<Integer>();

		// Java7 way
		Producer1 p = new Producer1(sharedQ);
		Consumer1 c = new Consumer1(sharedQ);

		p.start();
		c.start();

		// Java8 way
		final Runnable producer = () -> {
			while (true) {
				sharedQ.add(createItem());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		};

		final Runnable consumer = () -> {
			while (true)
				try {
					int x = sharedQ.take();
					Thread.sleep(1000);
					System.out.println("Consumed: " + x);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		};

		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		t1.start();
		t2.start();

	}

	private static Integer createItem() {
		i += 1;
		System.out.println("Produced: " + i);
		return i;
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
			Thread.sleep(200);
			while (true) {
				if (sharedQueue.isEmpty())
					break;
				Integer item = sharedQueue.take();
				System.out.println(getName() + " consumed " + item);
				Thread.sleep(200);
			}
		} catch (

		InterruptedException e) {
			e.printStackTrace();
		}
	}
}
