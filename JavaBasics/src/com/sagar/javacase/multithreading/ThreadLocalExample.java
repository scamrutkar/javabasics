package com.sagar.javacase.multithreading;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalExample implements Runnable {

	private static AtomicInteger nextId = new AtomicInteger(0);

	private static final ThreadLocal<Integer> intLocal = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return nextId.getAndIncrement();
		}
	};

	public int getThreadId() {
		return intLocal.get();
	}

	private static final ThreadLocal<Date> dateLcoal = new ThreadLocal<Date>() {
		@Override
		protected Date initialValue() {
			return new Date();
		}
	};

	@Override
	public void run() {
		System.out.printf("Starte executing thread %s : %s\n", getThreadId(), dateLcoal.get());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Finished executing thread %s : %s\n", getThreadId(), dateLcoal.get());
	}

	public static void main(String[] args) {

		ThreadLocalExample local = new ThreadLocalExample();
		new Thread(local).start();
		new Thread(local).start();
		new Thread(local).start();
		new Thread(local).start();

	}

}
