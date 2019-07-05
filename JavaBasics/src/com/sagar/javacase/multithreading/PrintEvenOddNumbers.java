package com.sagar.javacase.multithreading;

class TaskEvenOdd implements Runnable {

	int max;
	private boolean isEven;
	Printer printer;

	public TaskEvenOdd(int max, boolean isEven, Printer printer) {
		super();
		this.max = max;
		this.isEven = isEven;
		this.printer = printer;
	}

	@Override
	public void run() {

		int number = isEven ? 2 : 1;

		while (number <= max) {
			try {
				if (isEven)
					printer.printEven(number);
				else
					printer.printOdd(number);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			number += 2;
		}

	}

}

class Printer {

	private volatile boolean isOdd;

	synchronized public void printEven(int number) throws InterruptedException {

		while (isOdd) {
			wait();
		}
		System.out.println("Thread :" + Thread.currentThread().getName() + " Number :" + number);
		isOdd = true;
		notify();
	}

	synchronized public void printOdd(int number) throws InterruptedException {

		while (!isOdd) {
			wait();
		}
		System.out.println("Thread :" + Thread.currentThread().getName() + " Number :" + number);
		isOdd = false;
		notify();
	}

}

public class PrintEvenOddNumbers {

	public static void main(String[] args) {
		Printer printer = new Printer();
		
		Thread thread1 = new Thread(new TaskEvenOdd(10, false, printer), "Odd");
		Thread thread2 = new Thread(new TaskEvenOdd(10, true, printer), "Even");
		
		thread1.start();
		thread2.start();

	}

}
