package com.sagar.javacase.multithreading;

//https://stackoverflow.com/questions/15099324/reading-an-array-consecutively-with-two-threads

class ThreadConcurrent {
	int[] array;
	volatile int i = 0;

	public ThreadConcurrent(int[] array) {
		this.array = array;
	}

	public void checkSum() {
		while (i < array.length) {
			synchronized (this) {
				try {
					if (array[i] % 2 == 0) {
						System.out.println(Thread.currentThread().getName() + " " + array[i]);
						i++;
						if (i < array.length)
							wait();
						notify();
					} else {
						System.out.println(Thread.currentThread().getName() + " " + array[i]);
						i++;
						notify();
						if (i < array.length)
							wait();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}

public class PrintEvenOddInArray {

	public static void main(String[] args) {
		int[] array = new int[] { 12, 14, 15, 16, 17, 18 };
		final ThreadConcurrent er = new ThreadConcurrent(array);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				er.checkSum();
			}
		}, "T1");
		Thread t21 = new Thread(new Runnable() {
			@Override
			public void run() {
				er.checkSum();
			}
		}, "T2");
		t1.start();
		t21.start();

	}

}
