package com.javatpoint.model;

public class Login {
	private int id;
	private String name;
	private String designation;
public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


  public Login(){
	  
  }
 
  public Login(String name, String designation) {
	super();
	this.name = name;
	this.designation = designation;
}
 
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDesignation() {
	return designation;
}
public void setDesignation(String designation) {
	this.designation = designation;
}  
	
	
}
