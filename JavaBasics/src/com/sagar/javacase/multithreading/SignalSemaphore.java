package com.sagar.javacase.multithreading;

class Semaphore {
	private boolean signal = false;

	public synchronized void take() {
		this.signal = true;
		this.notify();
	}

	public synchronized void release() throws InterruptedException {
		while (!this.signal)
			wait();
		this.signal = false;
	}

}

class SendingThread implements Runnable {

	Semaphore semaphore;

	public SendingThread(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		while (true) {
			try {
				semaphore.take();
				System.out.println("Just Acquired Lock");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

class ReceivingThread implements Runnable {

	Semaphore semaphore;

	public ReceivingThread(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Just Releasing Lock");
				Thread.sleep(5000);
				semaphore.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

public class SignalSemaphore {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore();
		Thread t1 = new Thread(new SendingThread(semaphore));
		Thread t2 = new Thread(new ReceivingThread(semaphore));

		t1.start();
		t2.start();
	}

}
