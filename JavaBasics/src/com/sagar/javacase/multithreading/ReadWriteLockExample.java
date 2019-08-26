package com.sagar.javacase.multithreading;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedObject {

	LinkedList<Integer> list = new LinkedList<>();
	ReadWriteLock lock;
	Lock readLock;
	Lock writeLock;

	public SharedObject() {
		lock = new ReentrantReadWriteLock();
		readLock = lock.readLock();
		writeLock = lock.writeLock();
	}

	public void readElement(int index) {
		readLock.lock();
		try {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " Reading data from index " + index+" "+ list.get(index));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
		}
	}

	public void writeElement(int data) {
		writeLock.lock();
		try {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " Writing data " + data);
			list.add(data);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
	}

}

public class ReadWriteLockExample {

	public static void main(String[] args) {
		SharedObject so = new SharedObject();

		ExecutorService service = Executors.newFixedThreadPool(10);
		service.execute(() -> so.writeElement(1));
		service.execute(() -> so.readElement(0));
		service.execute(() -> so.writeElement(2));
		service.execute(() -> so.writeElement(3));
		service.execute(() -> so.readElement(0));
		service.execute(() -> so.readElement(1));
		service.execute(() -> so.readElement(2));
		service.execute(() -> so.writeElement(4));
		
		service.shutdown();
	}

}
