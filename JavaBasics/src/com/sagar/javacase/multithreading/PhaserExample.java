package com.sagar.javacase.multithreading;

import java.util.concurrent.Phaser;

public class PhaserExample {

	public static void main(String[] args) {
		Phaser ph = new Phaser(1);
		int curPhase;
		curPhase = ph.getPhase();
		System.out.println("Phase in Main " + curPhase + " started");
		// Threads for first phase
		new FileReaderThread("thread-1", "file-1", ph);
		new FileReaderThread("thread-2", "file-2", ph);
		new FileReaderThread("thread-3", "file-3", ph);
		// For main thread
		ph.arriveAndAwaitAdvance();
		System.out.println("New phase " + ph.getPhase() + " started");
		// Threads for second phase
		new QueryReaderThread("thread-1", 40, ph);
		new QueryReaderThread("thread-2", 40, ph);
		curPhase = ph.getPhase();
		ph.arriveAndAwaitAdvance();
		System.out.println("Phase " + curPhase + " completed");
		// deregistering the main thread
		ph.arriveAndDeregister();
	}

}

class FileReaderThread implements Runnable {

	private String threadName;
	private String fileName;
	private Phaser phaser;

	public FileReaderThread(String threadName, String fileName, Phaser phaser) {
		this.threadName = threadName;
		this.fileName = fileName;
		this.phaser = phaser;
		phaser.register();
		new Thread(this).start();
	}

	@Override
	public void run() {
		System.out.println("This is phase " + phaser.getPhase());
		try {
			Thread.sleep(20);
			System.out.println("Reading file " + fileName + " thread " + threadName
					+ " parsing and storing in DB");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		phaser.arriveAndDeregister();

	}
}

class QueryReaderThread implements Runnable {

	private String threadName;
	private int param;
	private Phaser phaser;

	public QueryReaderThread(String threadName, int param, Phaser phaser) {
		this.threadName = threadName;
		this.param = param;
		this.phaser = phaser;
		phaser.register();
		new Thread(this).start();
	}

	@Override
	public void run() {
		System.out.println("This is phase " + phaser.getPhase());
		try {
			Thread.sleep(20);
			System.out.println("Querying DB using param " + param + " Thread " + threadName);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		phaser.arriveAndDeregister();

	}

}
