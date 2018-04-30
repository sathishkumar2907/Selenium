package com.javatpoint.model;

import java.nio.file.Path;
import java.sql.Blob;
import java.util.List;

import javax.jdo.annotations.Extensions;

import org.springframework.web.multipart.MultipartFile;



public class Emp {
	
	private int id;  
	private String name; 
	
	private float salary;  
	private String designation;  
	//private String path;
	
	private MultipartFile path; 

    public MultipartFile getPath() {
		return path;
	}


	public void setPath(MultipartFile path) {
		this.path = path;
	}
	/*  public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}*/
	private String image_name;
    
    public Emp(){}
    
 
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	
	
	
	public int getId() {  
	    return id;  
	}  
	
	public Emp(MultipartFile images) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.designation = designation;
		
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
	public float getSalary() {  
	    return salary;  
	}  
	public void setSalary(float salary) {  
	    this.salary = salary;  
	}  
	public String getDesignation() {  
	    return designation;  
	}  
	public void setDesignation(String designation) {  
	    this.designation = designation;  
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", designation=" + designation + "]";
	}

}
