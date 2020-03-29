package com.sagar.javacase.multithreading;

import java.util.concurrent.Semaphore;

class SharedPrinter {

	Semaphore semEven = new Semaphore(0);
	Semaphore semOdd = new Semaphore(1);

	public void printEven(int number) {
		try {
			semEven.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Even number "+Thread.currentThread().getName()+" "+number);
		semOdd.release();
	}

	public void printOdd(int number) {
		try {
			semOdd.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("odd number "+Thread.currentThread().getName()+" "+number);
		semEven.release();
	}
}

public class PrintEvenOddUsingSemaphore {

	public static void main(String[] args) {

		SharedPrinter printer = new SharedPrinter();

		new Thread(() -> {
			for (int i = 2; i < 10; i += 2)
				printer.printEven(i);
		}).start();

		new Thread(() -> {
			for (int i = 1; i < 10; i += 2)
				printer.printOdd(i);
		}).start();
	}

}
