package com.sagar.javacase.multithreading;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ThreadConcurrent {
	int[] array;
	AtomicInteger  counter;
	final ReentrantLock lock;
	final Condition notEven;
	final Condition notOdd;

	public ThreadConcurrent(int[] array) {
		this.array = array;
		lock = new ReentrantLock();
		notEven = lock.newCondition();
		notOdd = lock.newCondition();
		counter = new AtomicInteger(0);
	}

	public void checkSum() {
		try {
			while (counter.get() < array.length) {

				lock.lockInterruptibly();
				int curretnNumber = array[counter.get()];
				if (curretnNumber % 2 == 0) {
					System.out.println(Thread.currentThread().getName() + " " + array[counter.get()]);
					int i = counter.incrementAndGet();
					if (i < array.length) {
						notEven.await();
					}
					notOdd.signalAll();
				} else {
					System.out.println(Thread.currentThread().getName() + " " + array[counter.get()]);
					int i = counter.incrementAndGet();
					notEven.signalAll();
					if (i < array.length) {
						notOdd.await();
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

class PrintSeq {

	int[] array;
	volatile int counter = 0;

	public PrintSeq(int[] array) {
		this.array = array;
	}

	public synchronized void readEven() {

		while (counter < array.length) {
			if (array[counter] % 2 != 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (counter < array.length)
				System.out.println(Thread.currentThread().getName() + "->" + array[counter]);
			counter++;
			notify();
		}

	}

	public synchronized void readOdd() {

		while (counter < array.length) {
			if (array[counter] % 2 == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (counter < array.length)
				System.out.println(Thread.currentThread().getName() + "->" + array[counter]);
			counter++;
			notify();
		}

	}

}

public class PrintEvenOddInArray {

	public static void main(String[] args) {
		int[] array = new int[] { 12, 14, 15, 16, 17, 18 };
		final ThreadConcurrent er = new ThreadConcurrent(array);
		final PrintSeq ps = new PrintSeq(array);

		/*
		 * Thread t1 = new Thread(() -> ps.readEven()); Thread t2 = new Thread(() ->
		 * ps.readOdd()); t1.start(); t2.start();
		 */

		Thread t3 = new Thread(() -> er.checkSum());
		Thread t4 = new Thread(() -> er.checkSum());
		t3.start();
		t4.start();

	}

}
