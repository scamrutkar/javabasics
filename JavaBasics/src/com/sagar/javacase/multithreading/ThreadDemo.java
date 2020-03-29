package com.sagar.javacase.multithreading;

/*1. When we extend Thread class, we can’t extend any other class even we require and 
 * 	  When we implement Runnable, we can save a space for our class to extend any other class in future or now.
 * 
 *2.  When we extend Thread class, each of our thread creates unique object and associate with it. 
 *	  When we implements Runnable, it shares the same object to multiple threads.
 */
public class ThreadDemo {
	
	public static void main(String[] args) {
		ThreadClass tc1 = new ThreadClass();
		ThreadClass tc2 = new ThreadClass();
		tc1.start();
		tc2.start();
		
		ThreadInterface tif1 = new ThreadInterface();
		Thread t1 = new Thread(tif1);
		Thread t2 = new Thread(tif1);
		t1.start();
		t2.start();
		
	}

}

class ThreadInterface implements Runnable {

	@Override
	public void run() {

		System.out.println("Executing thread " + this.hashCode());

	}

}

class ThreadClass extends Thread {

	@Override
	public void run() {

		System.out.println("Executing thread " + this.hashCode());

	}

}
