package com.sagar.javacase.java8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

public class StreamExample {

	public static void main(String[] args) {

		createStream();

		splitIterator();
	}

	private static void splitIterator() {

		List<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

		Spliterator<Integer> splItr = list.spliterator();
		splItr.forEachRemaining((n) -> {
			n = n * n;
			System.out.println("Square of number is : " + n);
		});

		while (splItr.tryAdvance(n -> System.out.println(n)))
			;
	}

	private static void createStream() {

		// as multiple values
		Stream<Integer> streams1 = Stream.of(1, 2, 3, 4, 5);
		streams1.forEach(p -> System.out.print(p));

		// as array
		Stream<Integer> streams2 = Stream.of(new Integer[] { 1, 2, 3, 4, 5 });
		streams2.forEach(p -> System.out.print(p));

		// List.stream()
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 5; i++)
			list.add(i);
		Stream<Integer> streams3 = list.stream();
		streams3.forEach(p -> System.out.print(p));

	}

}
