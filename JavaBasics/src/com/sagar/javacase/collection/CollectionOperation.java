package com.sagar.javacase.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sagar
 *
 */
public class CollectionOperation {

	public static void main(String[] args) {

		hashSetOperation();
		
		treeSetOperation();
		
		failSafeImplementation();
		
		failFastImplementation();
		
		HashSet<String> set = new HashSet<>();
		
		set.add("Sagar");
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
