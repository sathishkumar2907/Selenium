<!-- <!DOCTYPE html> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%--  <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Login Form</title>
</head>
<body>
    <form:form name="processLogin" method="post" action="processLogin">
        <div align="center">
            <table>
                <tr>
                    <td>User Name</td>
                    <td><input type="text" path="name"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" path="designation"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Submit" /></td>
                </tr>
            </table>
                <table align="center">
                <tr>
                    <td style="font-style: italic; color: red;">${message}</td>
                </tr>
            </table>

        </div>
    </form:form>
</body>
</html> --%>
 

<html>
<head>
<title>Login Form Using jQuery - Demo Preview</title>
<meta name="robots" content="noindex, nofollow">
<!-- Include CSS File Here -->

<!-- Include CSS File Here -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script >





$(document).ready(function(){
	
	
	
	$("#login").click(function(event){
		event.preventDefault();
	var name = $("#name").val();
	var designation = $("#designation").val();
	 
	
	if(name=='' && designation==''){
		alert("Please enter all fields");
		return false;
	}else{
		 $.ajax
		  ({
		/*  dataType : "json", */ 
		  type:'post',
		  url:'processLogin',
		/*   headers : {
	            'Accept' : 'application/json',
	            'Content-Type' : 'application/json'
	        }, */
	       /*  data : JSON.stringify(formData), */
		  data:{
		   name:name,
		   designation:designation
		  },
		  success:function(response) {
			   var jsonResponse = JSON.stringify(response);
            // jsonResponse= $.parseJSON(jsonResponse);
             // console.log("jsonResponse==>"+jsonResponse) 
              var parseJson=JSON.parse(jsonResponse);
              console.log("parseJson==>"+parseJson) 
             if(response=="success")
              {
            	  alert("success");
            	  window.location.href="success";
              }else{
            	 
            	  alert("wrong");
            	  
            	//  window.location.href="404";
            	  
              }  
		 
	
		  }
		  
		  });
		
	    }
	
	
	})

	  });
	  
/* function display(data) {
	var json = "<h4>Ajax Response</h4><pre>"
			+ JSON.stringify(data, null, 4) + "</pre>";
	$('#feedback').html(json);
} */
	

</script>
</head>
<body>

<form method="post" ng-click="processLogin()">
<h2>Create Login Form Using jQuery</h2>
<label>Email :</label>
<input type="text" name="name" id="name">
<label>Password :</label>
<input type="password" name="designation" id="designation">
<input type="button" name="login" id="login" value="Login"><td>${message}</td>


</form>
<div>
 
  </div>
</body>
</html>