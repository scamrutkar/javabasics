package com.sagar.javacase.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
		System.out.println("------------------------------------------------------------------------");
		
		/*****************************************************
        Get student with matching address "1235"
       *****************************************************/
		students.stream()
				.filter((student)->student.getAddress().getZipcode().equals("1235"))
				.forEach((student) -> System.out.println(student.getName()));
		System.out.println("------------------------------------------------------------------------");
		
		/*****************************************************
        Get all student having mobile numbers 3333.
       *****************************************************/
		students.stream()
			.filter( student -> student.getMobileNumbers().stream().anyMatch(mobNo -> mobNo.getNumber().equals("3333")))
			.forEach(student -> System.out.println(student.getName()));
		System.out.println("------------------------------------------------------------------------");
		
		/*****************************************************
        Get all student having mobile number 1233 and 1234
        *****************************************************/
		List<Student> list = students.stream()
			.filter(student -> student.getMobileNumbers().stream().allMatch(mobNo -> mobNo.getNumber().equals("4444") || mobNo.getNumber().equals("3333")))
			.collect(Collectors.toList());
		
		String str = list.stream().map(student -> student.getName()).collect(Collectors.joining(",", "[", "]"));
	    System.out.println(str);
	    System.out.println("------------------------------------------------------------------------");
	    
	    /*******************************************************
        Create a List<Student> from the List<TempStudent>
       *********************************************************/
		TempStudent tmpStud1 = new TempStudent("Jayesh1", 201, new Address("12341"),
				Arrays.asList(new MobileNumber("12331"), new MobileNumber("12341")));

		TempStudent tmpStud2 = new TempStudent("Khyati1", 202, new Address("12351"),
				Arrays.asList(new MobileNumber("11111"), new MobileNumber("33331"), new MobileNumber("12331")));

		List<TempStudent> tmpStudents = Arrays.asList(tmpStud1, tmpStud2);
		
		List<Student> studentList = tmpStudents.stream()
														.map(tmpStd -> new Student(tmpStd.name, tmpStd.age, tmpStd.address, tmpStd.mobileNumbers))
														.collect(Collectors.toList());
		
		System.out.println(studentList);
		System.out.println("------------------------------------------------------------------------");
		
		/**********************************************************
        Convert List<Student> to List<String> of student name
       ***********************************************************/
		
		List<String> studentNameList = students.stream().map(student -> student.getName()).collect(Collectors.toList());
		System.out.println(studentNameList);
		System.out.println("------------------------------------------------------------------------");
		
		/*****************************************************
        Change the case of List<String>
       *****************************************************/
		List<String> upperCaseList = studentNameList.stream().map(String::toUpperCase).collect(Collectors.toList());
		System.out.println(upperCaseList);
		System.out.println("------------------------------------------------------------------------");
		
		/*****************************************************
        Sort List<String>
        *****************************************************/
		List<String> sortedStringList = studentNameList.stream().sorted((s1,s2)->s1.compareTo(s2)).collect(Collectors.toList());
		System.out.println(sortedStringList);
		System.out.println("------------------------------------------------------------------------");
		
		/*****************************************************
        Comparator Interface Java8
        *****************************************************/
		System.out.println(students);
		Collections.sort(students, 
					Comparator.comparing(Student::getName)
								.thenComparing(Student::getAge)
								.reversed());
		System.out.println(students);
		System.out.println("------------------------------------------------------------------------");
		

	}

}
