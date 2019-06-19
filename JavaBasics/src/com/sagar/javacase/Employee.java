package com.sagar.javacase;

public class Employee implements Comparable<Employee>{
	
	private int id;
	private String name;
	private String org;
	
	public Employee(int id, String name, String org) {
		super();
		this.id = id;
		this.name = name;
		this.org = org;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}

	@Override
	public int compareTo(Employee emp) {
		if(this.id == emp.getId())
			return 0;
		else if (this.id > emp.getId())
			return 1;
		else 
			return -1;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", org=" + org + "]";
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (org == null) {
			if (other.org != null)
				return false;
		} else if (!org.equals(other.org))
			return false;
		return true;
	}
	
	

}
