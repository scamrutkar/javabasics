package com.sagar.javacase.multithreading;

class TrickyClass {

	static synchronized void staticMethod() {
		System.out.println("In static method");
	}

	public synchronized void Method1() {
		System.out.println("In method1");

	}

	public synchronized void Method2() {
		System.out.println("In method2");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

public class TrickyScenarion {

	public static void main(String[] args) {
		TrickyClass tricky = new TrickyClass();

		Thread t1 = new Thread(() -> tricky.Method2());
		Thread t2 = new Thread(() -> tricky.Method1());
		Thread t3 = new Thread(() -> tricky.Method1());
		Thread t4 = new Thread(() -> TrickyClass.staticMethod());

		t4.start();
		t1.start();
		t2.start();
		t3.start();

		MyClass i = new MyClass();
		i.start();

	}

	static class MyClass extends Thread {
		public void run() throws RuntimeException {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}

class MyClass {

	public static void main(String ... args) {

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (String.class) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("1");

					synchronized (Object.class) {
						System.out.println("2");
					}
					System.out.println("3");
				}

			}
		});

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (Object.class) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.out.println("4");

					synchronized (String.class) {
						System.out.println("5");
					}
					System.out.println("6");
				}

			}
		});

		thread1.start();
		thread2.start();

	}
}


class  MyRunnable implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" started");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" ended");
	}
	
	public static void main(String[] args) {
		System.out.println("Strated");
		Thread thread = new Thread(new MyRunnable());
		thread.start();
		try {
			thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ended");
	}
	
}
