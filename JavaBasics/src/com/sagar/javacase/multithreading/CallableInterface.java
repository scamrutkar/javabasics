package com.sagar.javacase.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallableInterface {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

		List<Future<Integer>> resultList = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			Future<Integer> future = executor.submit(new FactorialCalculation(i));
			resultList.add(future);
		}

		for (Future<Integer> future : resultList) {
			System.out.println("Future Result : " + future.get());
		}
		
		executor.shutdown();
	}

}

class FactorialCalculation implements Callable<Integer> {

	private Integer number;

	public FactorialCalculation(Integer number) {
		this.number = number;
	}

	@Override
	public Integer call() throws Exception {
		int result = 1;
		if ((number == 0) || (number == 1)) {
			result = 1;
		} else {
			for (int i = 2; i <= number; i++) {
				result *= i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		System.out.println("Result for number - " + number + " -> " + result);
		return result;
	}

}
