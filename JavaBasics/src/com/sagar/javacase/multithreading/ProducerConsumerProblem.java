package com.sagar.javacase.multithreading;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerProblem {
	
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		
		Producer producer = new Producer(list, 5);
		Consumer consume = new Consumer(list);
		
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consume);
		
		t1.start();
		t2.start();
		
	}

}

class Producer implements Runnable {

	private List<Integer> sharedList = new ArrayList<>();
	private final int MAX_CAPACITY;

	public Producer(List<Integer> sharedList, int capcity) {
		this.sharedList = sharedList;
		this.MAX_CAPACITY = capcity;
	}

	@Override
	public void run() {
		int counter = 0;

		while (true) {
			try {
				produce(counter++);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void produce(int i) throws InterruptedException {
		synchronized (sharedList) {
			while(sharedList.size() == MAX_CAPACITY) {
				System.out.println("Queue is full");
				sharedList.wait();
			}
			Thread.sleep(1000);
			sharedList.add(i);
			System.out.println("Produced : "+i);
			sharedList.notifyAll();
			
		}
	}
}

class Consumer implements Runnable {

	private List<Integer> sharedList = new ArrayList<>();

	public Consumer(List<Integer> sharedList) {
		this.sharedList = sharedList;
	}

	@Override
	public void run() {
		while (true) {
			try {
				consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void consume() throws InterruptedException {
		synchronized (sharedList) {
			while(sharedList.isEmpty()) {
				System.out.println("Queue is Empty");
				sharedList.wait();
			}
			Thread.sleep(1000);
			int i = sharedList.remove(0);
			System.out.println("Consumed : "+i);
			sharedList.notifyAll();
			
		}
	}
}
