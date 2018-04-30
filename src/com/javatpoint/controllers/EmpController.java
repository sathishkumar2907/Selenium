package com.javatpoint.controllers;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javatpoint.dao.EmpDao;
import com.javatpoint.model.Emp;
import com.javatpoint.model.Login;
import com.javatpoint.validators.UserValidation;


import javafx.scene.control.Alert;


@Controller  
public class EmpController {
	
	    @Autowired  
	    EmpDao dao;//will inject dao from xml file  
	  //private Path path;
	  
	    @RequestMapping("/empform")  
	    public ModelAndView showform(){  
	        return new ModelAndView("empform","command",new Emp());  
	    }  
	  
	 	@RequestMapping(value="/save", method = RequestMethod.POST)  
		//@ResponseBody
	    public  ModelAndView save(Emp emp,HttpServletRequest servletRequest,BindingResult result) throws IOException, SQLException {
			
			 int userId ;
			 ModelAndView mav = new ModelAndView();
			 emp.setId(emp.getId()+1);
			//e.setName("sathish");
			dao.save(emp);	
		  
			System.out.println("studentId " + emp.getId());
	       
			String path=servletRequest.getScheme() + "://" + servletRequest.getServerName() + ":" + servletRequest.getServerPort() + servletRequest.getContextPath();
	    	System.out.println(path+"=======> sathish");
	    		
	//====================================		
	     System.out.println("method End");
	    // Here, you can save the product details in database
	   
	    // return path;
	    // ModelAndView modelview=new ModelAndView("redirect:viewemp");
	     //ModelAndView modelview=new ModelAndView("success");
	     return new ModelAndView("redirect:/viewemp");
	    // return mav;
	    }
	     
	    //================================
		/*  private boolean isValid(Emp product) {
		        boolean valid = false;
		        if (product != null) {
		        	
		            if (!StringUtils.isEmpty(product.getName())
		                    && !StringUtils.isEmpty(product.getDesignation())
		                    && !StringUtils.isEmpty(product.getSalary())
		                    && !StringUtils.isEmpty(product.getName()))
		                valid = true;
		        }
		        return valid;
		    }*/
		
	    
	/* @RequestMapping(value="/save", method = RequestMethod.POST)  
	    public ModelAndView save(@ModelAttribute("emp") Emp emp,BindingResult bindingResult){ 
	    	
	    	if(bindingResult.hasErrors()){
    		System.out.println("proper name plzzzz");
    	}
	    	System.out.println("emp==>"+emp+"==>emp.getName()==>"+emp.getName());
	    	System.out.println("==>emp.getName()==>"+emp.getImages_model());
	    	  dao.save(emp);  
	       

	        return new ModelAndView("redirect:/viewemp");//will redirect to viewemp request mapping  
	    }
	    */
	    
	    /* It provides list of employees in model object */  
		//, headers = "Accept=*/*"
        @RequestMapping(value="/viewemp",method=RequestMethod.GET)  
       // @ResponseBody
	    public ModelAndView viewemp(){  
        	
 		
	        List<Emp> list=dao.getEmployees();
	        
	 /* for(int i=0;i<list.size();i++){
	        	Emp ee=list.get(i);
	        	System.out.println(ee.getId()+"  "+"  "+ee.getName()+"  "+ee.getSalary());
	        }*/	
 			//LocationList list = new LocationList(list);
	        //userId = String.valueOf(e.getId());
	      /* for(Emp e1:list){
	    	  System.out.println(e1.getName()); 
	       }
	        System.out.println("userId  "+list.get(1).getId());*/
	        return new ModelAndView("viewemp","list",list); 
	      // return getMap(list);
		//	return  list;
	    } 
	 
	 
	 private Map<String, Object> getMap(List<Emp> user) {

		 Map<String, Object> modelMap = new HashMap<String, Object>(3);
		 modelMap.put("total", user.size());
		 modelMap.put("data", user);
		 modelMap.put("success", true);

		 return modelMap;
		 }
	 
	    //====================================================
	  /*  @RequestMapping(value={"/viewemp/{pageid}"})  
	    public ModelAndView viewemp(@PathVariable int pageid){  
	        int total=10;  
	        if(pageid==1){}  
	        else{  
	            pageid=(pageid-1)*total+1;  
	        }  
	        List<Emp> list=dao.getEmployeesByPage(pageid,total);  
	     return new ModelAndView("viewemp","list",list);  
	    }  */
	
	    
/*	    @RequestMapping(value="/viewemp")
	    public ModelAndView listOfUsers(@RequestParam(required = false) Integer page) {
	       

	        List<Emp> users = dao.getEmployees();
	        ModelAndView modelAndView = new ModelAndView("viewemp","list",users);
	        System.out.println("users"+users);
	        PagedListHolder<Emp> pagedListHolder = new PagedListHolder<>(users);
	        pagedListHolder.setPageSize(5);
	        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());

	        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;

	        modelAndView.addObject("page", page);
	        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
	            pagedListHolder.setPage(0);
	            modelAndView.addObject("users", pagedListHolder.getPageList());
	        }
	        else if(page <= pagedListHolder.getPageCount()) {
	            pagedListHolder.setPage(page-1);
	            modelAndView.addObject("users", pagedListHolder.getPageList());
	        }

	        return modelAndView;
	    }*/
	    
	    
	    //===================================================
	    /* It displays object data into form for the given id.  
	     * The @PathVariable puts URL data into variable.*/  
	    @RequestMapping(value="/editemp/{id}")  
	    public ModelAndView edit(@PathVariable int id){  
	        Emp emp=dao.getEmpById(id);  
	        return new ModelAndView("empeditform","command",emp);  
	    }  
	    /* It updates model object. */  
	    @RequestMapping(value="/editsave",method = RequestMethod.POST)  
	    public ModelAndView editsave(@ModelAttribute("emp") Emp emp){  
	        dao.update(emp);  
	        return new ModelAndView("redirect:/viewemp");  
	    }  
	    /* It deletes record for the given id in URL and redirects to /viewemp */  
	    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)  
	    public ModelAndView delete(@PathVariable int id){  
	        dao.delete(id);  
	        return new ModelAndView("redirect:/viewemp");  
	    }  
	    
	    
	    //=============Images Upload========================
	  /*  @RequestMapping("/save-product")
	    public String uploadResources(HttpServletRequest servletRequest,
	                                 @ModelAttribute Emp emp,
	                                 Model model)
	    {
	        //Get the uploaded files and store them
	        List<MultipartFile> files = emp.getImages();
	        List<String> fileNames = new ArrayList<String>();
	        if (null != files && files.size() > 0)
	        {
	            for (MultipartFile multipartFile : files) {
	 
	                String fileName = multipartFile.getOriginalFilename();
	                fileNames.add(fileName);
	 
	                File imageFile = new File(servletRequest.getServletContext().getRealPath("/image"), fileName);
	                try
	                {
	                    multipartFile.transferTo(imageFile);
	                } catch (IOException e)
	                {
	                    e.printStackTrace();
	                }
	            }
	        }
	 
	        // Here, you can save the product details in database
	         
	        model.addAttribute("emp", emp);
	        return "viewemp";
	    }*/
	    
	    
}
