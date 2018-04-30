package com.javatpoint.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.PreparedStatementCallback;  

import com.javatpoint.model.Emp;
import com.javatpoint.model.Login;
import com.mysql.jdbc.PreparedStatement;

public class LoginDao {
	
	JdbcTemplate template; 
	
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	
	public Emp getUserById(int user_id) {
        String query = "SELECT * from employees where id= '" + user_id + "'"; 
        Emp user = new Emp();
        List<Emp> user_list = new ArrayList<Emp>();
        user_list = template.query(query, new BeanPropertyRowMapper<Emp>(Emp.class));
        System.out.println("user_list==>"+user_list);
        if(user_list.size()>0){
        	user = user_list.get(1);
        }
        return user;
    }
	
	
	@SuppressWarnings("unused")
	public boolean isUserExists(String name) {
		boolean userExists  = false;
		try{
		
        String query="select name from employees where name='" + name + "'";
		int count = template.queryForObject(query, new Object[] { name }, Integer.class);
		if (count == 1) {
			userExists  = true;
	    }else{
	    	System.out.println("In Dao else aprt");
	    }
		
    }catch(Exception e){
    	e.printStackTrace();
    }
    return userExists ;
	}
	
	
	
	public String authenticateUser(final Login login){
		
		final String username=login.getName();
		final String password=login.getDesignation();
		
		//System.out.println("in Dao====>"+getUserById(login.getId()));
		
		
				//select name,designation from employees where id='"+login.getId()+"'
		 String sql = "SELECT name,designation FROM employees WHERE name='"+login.getName()+"' AND designation='"+login.getDesignation()+"'";
		 System.out.println("sql  ---->"+sql);
		 
		 return template.query(sql, new ResultSetExtractor<String>() {
			 
		        @SuppressWarnings("unused")
				@Override
		        public String extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		            if (rs.next()) {
		            		
		            	String usernameDB=rs.getString("name");
		            	String passDB=rs.getString("designation");
		            	System.out.println("usernameDB  "+usernameDB+"   passDB "+passDB);
		            	
		            	Login login=new Login();
		            	login.setName(usernameDB);
		            	login.setDesignation(passDB);
		            	
		            	   if(usernameDB.equals(username) && passDB.equals(password)){
		            		System.out.println("success in DB");
		            		return "success";
		            	   }/*else{
		            		   
		            	   }*/
		            	
		            }
		          System.out.println("logindao");
					return "login";
				//return login;
		        }
		 
		    });
		}
	 
	/* public Login get(final String name,final String desig) {
		
		//String userName=login.getName();
		//String pass=login.getDesignation();
        
		//System.out.println("userName===>"+userName+"pass====>"+pass);		
		 //String sql = "select * from emp where name='" + name + "' and designation='" + desig + "'";
		 String sql = "select name,designation from employees";
		 System.out.println("sql  ---->"+sql);
		// String sql = "select * from emp";
	   // String sql = "select count(*) from Emp";
	    return template.query(sql, new ResultSetExtractor<Login>() {
	 
	        @Override
	        public Login extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	System.out.println("inside if condition");
	            	String usernameDB=rs.getString("name");
	            	String passDB=rs.getString("designation");
	            	System.out.println("usernameDB  "+usernameDB+"   passDB "+passDB);
	            	
	            	
	            	if(usernameDB.equals(name) && passDB.equals(desig)){
	            		System.out.println("success in DB");
	            	}else{
	            		System.out.println("not success in DB");
	            	}
	            	
	            	Login login = new Login();
	            	login.setName(rs.getString("name"));
	            	login.setDesignation(rs.getString("designation"));
	            	
	            	return login;
	            	if(userName.equals(usernameDB) && pass.equals(passDB)){
	            		return login;
	            	}
	              
	                
	            }
	 
	            return null;
	        }
	 
	    });
	}
	 */
	 
	 
	
	
}
