package com.sagar.javacase.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.sagar.javacase.Employee;

public class ComparatorInterface {
	
public static void main(String[] args) {
		
		ArrayList<Employee> empList = new ArrayList<>();
		
		empList.add(new Employee(1, "Sagar Amrutkar", "Calsoft"));
		empList.add(new Employee(3, "Rohit Amrutkar", "NonEmployee"));
		empList.add(new Employee(2, "Amar Amrutkar", "Metricstream"));
		empList.add(new Employee(5, "Nikhil Amrutkar", "Akzonobel"));
		empList.add(new Employee(4, "Mahesh Amrutkar", "VSNL"));
		
		Collections.sort(empList, (a,b)->a.getName().compareTo(b.getName()));
		
		//Collections.sort(empList, new NameComparator());
		
		System.out.println("Emp List : "+empList.toString());
	}

}

class NameComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		return o1.getName().compareTo(o2.getName());
	}
	
}

