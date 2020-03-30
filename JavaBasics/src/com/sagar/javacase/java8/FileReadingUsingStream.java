package com.sagar.javacase.java8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReadingUsingStream {

	public static void main(String[] args) throws IOException {

		String fileName = "E:\\SampleFiles\\Sample1.txt";

		try {
			Stream<String> stream = Files.lines(Paths.get(fileName));
			stream.forEach(System.out::println);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();

	}

}
