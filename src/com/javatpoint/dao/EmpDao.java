package com.javatpoint.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import com.javatpoint.controllers.EmpController;
import com.javatpoint.model.Emp;
import com.javatpoint.model.Login;
import com.sun.istack.internal.logging.Logger;

public class EmpDao {
	JdbcTemplate template;  
	  public String path;
	
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	
	public int save(Emp p) throws IOException, SQLException{  
		
	//==========================================================================================
  /* MultipartFile userimage=p.getPath();
      	System.out.println("userimage========>"+userimage);
        
    	String fileName1 = userimage.getOriginalFilename();
    	System.out.println("fileName1===>"+fileName1);
    	String path="D:\\Springworkspace\\NewDynamic\\Image\\";
    	//String path="D:\\Springworkspace\\NewDynamic";
    	
    	File imageFile = new File(path, fileName1);
    	System.out.println("path=====> "+imageFile);
       	
    	if (userimage != null && !userimage.isEmpty()) {
    		//String filePath = request.getServletContext().getRealPath("/"); 
    		//String ApplicationPath =  ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("");
		    userimage.transferTo(imageFile);
		}
		System.out.println("p.getPath==>"+ path + userimage.getOriginalFilename());
		*/
	//==========================================================================================
	/*	 HttpServletRequest request = null;
		 MultipartFile file=null;
		  if (!file.isEmpty()) {
              
                  String uploadsDir = "/uploads/";
                  String realPathtoUploads =  request.getServletContext().getRealPath(uploadsDir);
                  if(! new File(realPathtoUploads).exists())
                  {
                      new File(realPathtoUploads).mkdir();
                  }

                 System.out.println("realPathtoUploads = {}"+ realPathtoUploads);

                  String orgName = file.getOriginalFilename();
                  String filePath = realPathtoUploads + orgName;
                  File dest = new File(filePath);
                  file.transferTo(dest);
              }
		   */
	
		//===========================================================================================	
		//String file=p.getImages().getOriginalFilename();
		 //String sql="insert into employees values()";
		//InputStream file=p.getImages().getInputStream();
	    //String sql="insert into Emp(name,salary,designation) values('"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"')";  
	    String sql="insert into employees(name,salary,designation,images) values('"+p.getName()+"',"+p.getSalary()+",'"+p.getDesignation()+"','"+"gg"+"')"; 
		
		System.out.println("sql==>"+sql);
	    System.out.println("EMPDAO");
	    return template.update(sql);  
	}  
	
	public int update(Emp p){  
	    String sql="update Emp set name='"+p.getName()+"', salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id="+p.getId()+"";   
	    return template.update(sql);  
	} 
	
	
	public int delete(int id){  
	    String sql="delete from Emp where id="+id+"";  
	    return template.update(sql);  
	}
	
	
	public Emp getEmpById(int id){  
	    String sql="select * from Emp where id="+id+"";  
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Emp>(Emp.class));  
	}  
	
	public List<Emp> getEmployees(){  
	    return template.query("select * from Employees",new RowMapper<Emp>(){  
	        public Emp mapRow(ResultSet rs, int row) throws SQLException {  
	            Emp e=new Emp();  
	            e.setId(rs.getInt(1));  
	            e.setName(rs.getString(2));  
	            e.setSalary(rs.getFloat(3));  
	            e.setDesignation(rs.getString(4));  
	            e.setDesignation(rs.getString(5));  
	            return e;  
	        }  
	    });  
	  }
	
	
	//=============In mutiple return rows===============
	public List<Emp> findAll(){

		String sql = "SELECT * FROM CUSTOMER";

		List<Emp> customers = new ArrayList<Emp>();

		List<Map<String, Object>> rows = template.queryForList(sql);
		for (Map row : rows) {
			Emp customer = new Emp();
		//	customer.setId(row.get("CUST_ID"));
			customer.setName((String)row.get("NAME"));
			customer.setDesignation((String)row.get("AGE"));
			customers.add(customer);
		}
       return customers;
	}
	
	@SuppressWarnings("unused")
	public boolean isUserExists(String username) {
		boolean result = false;
		try{
		
        String query="select name from employees where name='" + username + "'";
		int count = template.queryForObject(query, new Object[] { username }, Integer.class);
		if (count >= 0) {
			result = true;
	    }else{
	    	System.out.println("In Dao else aprt");
	    }
		
    }catch(Exception e){
    	e.printStackTrace();
    }
    return result;
	}
	
	public Integer getUserId(String email) {
        String query = "SELECT id from employees where name= '" + email + "'"; 
        Integer id = template.queryForList(query, Integer.class).get(1);
        return id;
    }
	
	public Emp getUserById(int user_id) {
        String query = "SELECT * from employees where id= '" + user_id + "'"; 
        Emp user = new Emp();
        List<Emp> user_list = new ArrayList<Emp>();
        user_list = template.query(query, new BeanPropertyRowMapper<Emp>(Emp.class));
        if(user_list.size()>0){
        	user = user_list.get(0);
        }
        return user;
    }
	
	public String getEmailID(Integer userID) {
	     String query = "SELECT email from employees where id= '" + userID + "'"; 
	     String email = template.queryForList(query, String.class).get(0);
	     return email;
	}
	
	
	/*public List<Emp> getEmployeesByPage(int pageid,int total){  
	    String sql="select * from Emp limit "+(pageid-1)+","+total;  
	    return template.query(sql,new RowMapper<Emp>(){  
	        public Emp mapRow(ResultSet rs, int row) throws SQLException {  
	            Emp e=new Emp();  
	            e.setId(rs.getInt(1));  
	            e.setName(rs.getString(2));  
	            e.setSalary(rs.getFloat(3));  
	            return e;  
	        }  
	    });  
	}
	*/
	
	public String findCustomerNameById(int custId){

		String sql = "SELECT NAME FROM CUSTOMER WHERE CUST_ID = ?";

		String name = (String)template.queryForObject(
				sql, new Object[] { custId }, String.class);

		return name;

	}
	  public String getEmployeeName(long id) {
	        String sql = "select name from employee where id = ?";
	        String name = template.queryForObject(sql,new Object[]{id}, String.class);
	        return name;
	    }
	  
	  public Emp getEmployee(int id){
	        String sql = "select * from employee where id = ?";
	        Emp emp = template.queryForObject(sql,new Object[]{id}, new RowMapper<Emp>(){
	 
	            public Emp mapRow(ResultSet rs, int rownum)
	                    throws SQLException {
	                Emp emp = new Emp();
	                emp.setId(rs.getInt("id"));
	                emp.setName(rs.getString("name"));
	                emp.setDesignation(rs.getString("designation"));
	                return emp;
	            }
	            
	        });
	        return emp;
	 
	    }
	
   }
