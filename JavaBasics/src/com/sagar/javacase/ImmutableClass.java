package com.sagar.javacase;

final class Immutable{
	
	private final String name;
	private final String id;
	
	public Immutable(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	
	
}

public class ImmutableClass {
	
	public static void main(String[] args) {
		
		System.out.println("Creating Immutable Object");
		
		Immutable imm = new Immutable("Sagar", "123");
		imm.getName();
		
	}

}

