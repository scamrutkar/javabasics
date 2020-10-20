package com.sagar.javacase.async;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
	
	public static void main(String[] args) {
		
		CompletableFuture<String> future = CompletableFuture.completedFuture("Message");
		System.out.println("Future is completed : "+future.isDone());
		String str = "null";
	}

}
