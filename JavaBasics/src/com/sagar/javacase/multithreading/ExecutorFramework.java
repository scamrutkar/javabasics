package com.sagar.javacase.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorFramework {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		Future<?> future1= executor.submit(()->System.out.println("Executing thread"));
		System.out.println("Future result : "+future1.get());
		
		Future<?> future2 = executor.submit(()-> 5);
		System.out.println("Future result : "+future2.get());
		
		executor.shutdown();
		
		
	}

}
