package com.sagar.javacase.multithreading;

class ClassAndInstanceLevelLock {

	public static void main(String[] args) {
		final ClassAndInstanceLevelLock threadDemo1 = new ClassAndInstanceLevelLock();

		new Thread(() -> {
			threadDemo1.getA();
		}).start();

		new Thread(() -> {
			threadDemo1.getB();
		}).start();

		new Thread(() -> {
			ClassAndInstanceLevelLock.getC();
		}).start();

		new Thread(() -> {
			ClassAndInstanceLevelLock.getD();
		}).start();

	}

	/*** INSTANCE METHOD ***/
	public synchronized void getA() {
		System.out.println("ThreadDemo.getA() :" + Thread.currentThread().getName() + " enetered");
		try {
			Thread.sleep(5000);
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ThreadDemo.getA() :" + Thread.currentThread().getName() + " leaving");
	}

	public synchronized void getB() {
		System.out.println("ThreadDemo.getB() :" + Thread.currentThread().getName() + " enetered");
		try {
			Thread.sleep(5000);
			notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ThreadDemo.getB() :" + Thread.currentThread().getName() + " leaving");
	}

	/*** CLASS METHOD ***/
	public static synchronized void getC() {
		System.out.println("ThreadDemo.getC() :" + Thread.currentThread().getName() + " enetered");
		try {
			Thread.sleep(2000);
			ClassAndInstanceLevelLock.class.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ThreadDemo.getC() :" + Thread.currentThread().getName() + " leaving");
	}

	public static synchronized void getD() {
		System.out.println("ThreadDemo.getD() :" + Thread.currentThread().getName() + " enetered");
		try {
			Thread.sleep(2000);
			ClassAndInstanceLevelLock.class.notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ThreadDemo.getD() :" + Thread.currentThread().getName() + " leaving");
	}

}
