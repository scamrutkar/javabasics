package com.sagar.javacase.collection;

import java.util.HashMap;
import java.util.TreeMap;

class Person{
	
	private String id;
	private String name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Person(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
}

public class UserDefinedCollectionTrickyOperations {

	public static void main(String[] args) {
		//HashMap
		HashMap<Person,String> map = new HashMap<>();
		Person person = new Person("1","Sagar");
		map.put(person,"Object1");
		map.put(new Person("1","Sagar"),"Object2");
		map.put(null, "Object3");
		System.out.println(map);
		System.out.println(map.get(new Person("1","Sagar")));
		
		System.out.println("********************************************************************************************");
		
		//TreeMap
		TreeMap<Person,String> treeMap = new TreeMap<>();
		Person person1 = new Person("1","Sagar");
		treeMap.put(person1,"Object1");
		treeMap.put(new Person("1","Sagar"),"Object2");
		treeMap.put(null, "Object3");
		System.out.println(treeMap);
		System.out.println(treeMap.get(new Person("1","Sagar")));
		
	}

}
