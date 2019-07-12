package com.sagar.javacase.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import com.sagar.javacase.Employee;

/**
 * @author sagar
 *
 */
public class CollectionOperation {

	public static void main(String[] args) {
		
		userDefinedTreeSet();
		
		arraySetOperation();

		hashSetOperation();
		
		treeSetOperation();
		
		failSafeImplementation();
		
		failFastImplementation();
		
	}

	
	private static void userDefinedTreeSet() {
		
		/*
		 * You need to override hashCode() method if you are overriding equals() method.
		 * If you don't do so and overrides only equals() method, it will generate two
		 * different hashcodes for same object
		 */		
		TreeMap<Employee,String> set = new TreeMap<>();
		
		Employee e1 = new Employee(1,"Sagar","Oracle");
		Employee e2 = new Employee(2,"Amar","KPMG");
		Employee e3 = new Employee(1,"Sagar","Oracle");
		
		System.out.println("E1 Hashcode : "+e1.hashCode());
		System.out.println("E3 Hashcode : "+e3.hashCode());
		
		set.put(e1,"Sagar");
		set.put(e2,"Amar");
		set.put(e3,"Nikhil");
		
		System.out.println(set);
		System.out.println("***************************************************************************");
		
	}


	private static void arraySetOperation() {
		List<String> list = new ArrayList<>(5);
		list.add("sagar");
		list.add("nikhil");
		list.add("vijay");
		list.add("nitesh");
		list.add("amar");
		list.add("amrut");
		
		int var2 = 10;
		int var3 = var2 + (var2 >> 1);
		
		System.out.println(var3);
		
	}


	/**
	 * Hashset allows null element 
	 */
	private static void hashSetOperation() {
		Set<String> set = new HashSet<>();
		
		set.add(null);
		set.add(null);
		set.add("Sagar");
		
		System.out.println(set);
	}
	
	/**
	 * Treeset doesn't allow null element 
	 */
	private static void treeSetOperation() {
		try {
		Set<String> set = new TreeSet<>();
		
		//set.add(null);
		set.add("Sagar");
		set.add("Rohit");
		set.add("Amar");

		System.out.println(set);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void failFastImplementation() {
		try {
			Map<String, String> map = new HashMap<>();
			map.put("1", "Sagar");
			map.put("2", "Sagar");
			map.put("3", "Sagar");
			map.put("4", "Sagar");
			
			Iterator<String> itr = map.keySet().iterator();
			
			while(itr.hasNext()) {
				String key = itr.next();
				System.out.println("Key : "+key+" ,Value : "+map.get(key));
				map.remove(key); //Fails as we are modifying the state of map
				map.put(key, "Rohit");//Works fine
				map.put("5", "Rohit");//Fails as we are adding an extra element
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void failSafeImplementation() {
		try {
			Map<String, String> map = new ConcurrentHashMap<>();
			map.put("1", "Sagar");
			map.put("2", "Sagar");
			map.put("3", "Sagar");
			map.put("4", "Sagar");
			
			Iterator<String> itr = map.keySet().iterator();
			
			while(itr.hasNext()) {
				String key = itr.next();
				System.out.println("Key : "+key+" ,Value : "+map.get(key)+" ,Hash : "+map.hashCode());
				map.remove(key); 
				map.put(key, "Rohit");
				map.put("5", "Rohit");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
