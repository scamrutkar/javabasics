package com.sagar.javacase.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CollectionPractise {

	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<>();
		map.put("1", "Sagar");
		map.put("2", "Amar");
		map.put("3", "Rohit");
		
		Map<String, String> m = Collections.synchronizedMap(map);
		//m.put("4", "Saurav");
		map.put("4", "Rohit");
		Iterator<String> itr = m.keySet().iterator();
		
		
		while(itr.hasNext()) {
			//m.put("4", "Rohit");
			System.out.println(m.get(itr.next()));
		}
	}

}
