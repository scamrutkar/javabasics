package com.sagar.javacase.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
	
	private static class Task implements Runnable{
		
		CyclicBarrier barrier;
		
		public Task(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+" waiting at barrier !");
			try {
				Thread.sleep(1000);
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" started Party !");
		}
		
	}

	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3, ()->{
			System.out.println("All parties have arrived, Let's begin party !!!!");
		});
		
		new Thread(new Task(barrier)).start();
		new Thread(new Task(barrier)).start();
		new Thread(new Task(barrier)).start();
		
		barrier.reset();
		
		new Thread(new Task(barrier)).start();
		new Thread(new Task(barrier)).start();
		new Thread(new Task(barrier)).start();
	}

}
