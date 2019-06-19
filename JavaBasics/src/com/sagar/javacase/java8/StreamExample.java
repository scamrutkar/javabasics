package com.sagar.javacase.java8;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamExample {
	
	public static void main(String[] args) {
		
		createStream();
	}
	
	private static void createStream() {
		
		//as multiple values
		Stream<Integer> streams1 = Stream.of(1,2,3,4,5);
		streams1.forEach(p->System.out.print(p));
		
		//as array
		Stream<Integer> streams2 = Stream.of(new Integer[] {1,2,3,4,5});
		streams2.forEach(p->System.out.print(p));
		
		//List.stream()
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0;i<5;i++)
			list.add(i);
		Stream<Integer> streams3 = list.stream();
		streams3.forEach(p->System.out.print(p));
		
	}
	

}
