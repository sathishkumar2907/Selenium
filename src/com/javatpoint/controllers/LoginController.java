package com.javatpoint.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javatpoint.dao.EmpDao;
import com.javatpoint.dao.LoginDao;
import com.javatpoint.model.Emp;
import com.javatpoint.model.Login;

@SuppressWarnings("unused")
@Controller
public class LoginController {

	@Autowired
	LoginDao loginDao;
	
	@Autowired
	EmpDao dao;
	

	
	@RequestMapping("/login")
	public ModelAndView getLoginPage() {
		return new ModelAndView("login", "command", new Login());
	}
	
	
	
	 /* @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String init(Model model) {
	        model.addAttribute("msg", "Please Enter Your Login Details");
	        return "login";
	    }*/
	 
	
	@RequestMapping(value = "/processLogin", method = RequestMethod.POST )
	/* @ResponseBody*/
	public  ModelAndView processLogin(ModelMap model,@ModelAttribute("login") Login login,HttpSession session) {
		/*boolean userexsts=loginDao.isUserExists(login.getName());	
		if(userexsts==true){
		}*/
		
		Login login1=new Login();
		login1.setId(login.getId());
		
		ModelAndView modelAndView = null;
		String userValidate=loginDao.authenticateUser(login);
		System.out.println("getID==>"+userValidate);
		
		//System.out.println("loginDao--->"+loginDao.getUserById(login.getId()));
		
		/*Emp userId=loginDao.getUserById(login.getId());
		System.out.println("loginDao.getUserById(10)==>"+loginDao.getUserById(10));
		System.out.println("userId=="+userId);*/
		
		if(userValidate.equals("success")){
			/*model.se*/
			System.out.println("In Controller if condition");
			
			//System.out.println("getuserID===>"+userValidate.getId());
			
		    return new ModelAndView("redirect:/success");
			//session.setAttribute("username",login.getName());
			//modelAndView = new ModelAndView("success");
			//modelAndView.addObject("message", "Login successfully");
			//return "success";
		}else {
			
			System.out.println("In Controller else condition");
			return new ModelAndView("login","message", "Username or Password is wrong!!");
			//return new ModelAndView("404","message", "Username or Password is wrong!!");
			//modelAndView.addObject("message", "Username or Password is wrong!!");
			//return "login";
		}
	//	session.removeAttribute(username);
	    	//return modelAndView;
	    	
    	}
	
	@RequestMapping(value="/success", method=RequestMethod.GET)  
	public ModelAndView viewemp1(){  
	    return new ModelAndView("success");  
	}  
	
	@RequestMapping(value="/404", method=RequestMethod.GET)
	public ModelAndView view(){  
	    return new ModelAndView("404");
	}
	
	/*@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(){  
	    return new ModelAndView("login");
	}*/

	
	
/*@RequestMapping(value="/viewemp",method=RequestMethod.GET)  
    public ModelAndView viewemp(){  
        List<Emp> list=dao.getEmployees();  
        return new ModelAndView("viewemp","list",list);  
    } */
	
}
