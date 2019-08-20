package com.sagar.javacase.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Java8Scenario {

	public static void main(String[] args) {

		Student student1 = new Student("Jayesh", 20, new Address("1234"),
				Arrays.asList(new MobileNumber("1233"), new MobileNumber("1234")));
		
		Student student4 = new Student("Jayesh", 21, new Address("New Address"),
				Arrays.asList(new MobileNumber("7896"), new MobileNumber("8765")));

		Student student2 = new Student("Khyati", 20, new Address("1235"),
				Arrays.asList(new MobileNumber("1111"), new MobileNumber("3333"), new MobileNumber("1233")));

		Student student3 = new Student("Jason", 20, new Address("1236"),
				Arrays.asList(new MobileNumber("3333"), new MobileNumber("4444")));

		List<Student> students = Arrays.asList(student1, student2, student3,student4);

		/*****************************************************
        Get student with exact match name "jayesh"
       *****************************************************/
		students.stream()
				.filter((student) -> student.getName().equals("Jayesh"))
				.forEach((student) -> System.out.println(student.getName() +" "+student.getAddress().toString()));
		
		/*****************************************************
        Get student with matching address "1235"
       *****************************************************/
		students.stream()
				.filter((student)->student.getAddress().getZipcode().equals("1235"))
				.forEach((student) -> System.out.println(student.getName()));
		
		/*****************************************************
        Get all student having mobile numbers 3333.
       *****************************************************/
		students.stream()
			.filter( student -> student.getMobileNumbers().stream().anyMatch(mobNo -> mobNo.getNumber().equals("3333")))
			.forEach(student -> System.out.println(student.getName()));
		
		/*****************************************************
        Get all student having mobile number 1233 and 1234
        *****************************************************/

	}

}
