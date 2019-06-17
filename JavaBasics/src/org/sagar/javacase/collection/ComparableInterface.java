package org.sagar.javacase.collection;

import java.util.ArrayList;
import java.util.Collections;

import org.sagar.javacase.Employee;

public class ComparableInterface {
	
	public static void main(String[] args) {
		
		ArrayList<Employee> empList = new ArrayList<>();
		
		empList.add(new Employee(1, "Sagar Amrutkar", "Calsoft"));
		empList.add(new Employee(3, "Rohit Amrutkar", "NonEmployee"));
		empList.add(new Employee(2, "Amar Amrutkar", "Metricstream"));
		empList.add(new Employee(5, "Nikhil Amrutkar", "Akzonobel"));
		empList.add(new Employee(4, "Mahesh Amrutkar", "VSNL"));
		
		Collections.sort(empList);
		
		System.out.println("Emp List : "+empList.toString());
	}

}
