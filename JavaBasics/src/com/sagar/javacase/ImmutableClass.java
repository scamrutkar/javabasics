package com.sagar.javacase;

class ImmutableBaseClass {
	
	private final int id;
	private final String name;

	public ImmutableBaseClass(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}

public class ImmutableClass {

}
