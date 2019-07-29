package com.sagar.javacase.multithreading;

class PrintSequence implements Runnable {

	static int number = 1;
	static int max = 10;
	int remainder;
	static Object lock = new Object();

	public PrintSequence(int remainder) {
		this.remainder = remainder;
	}

	@Override
	public void run() {

		while (number < max - 1) {
			synchronized (lock) {
				try {
					if (number % 3 != remainder) {
						lock.wait();
					}
					System.out.println(Thread.currentThread().getName() + " " + number);
					number++;
					lock.notifyAll();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

public class SequencePrinting {

	public static void main(String[] args) {
		PrintSequence s1 = new PrintSequence(0);
		PrintSequence s2 = new PrintSequence(1);
		PrintSequence s3 = new PrintSequence(2);

		Thread t1 = new Thread(s1);
		Thread t2 = new Thread(s2);
		Thread t3 = new Thread(s3);

		t1.start();
		t2.start();
		t3.start();

	}

}
