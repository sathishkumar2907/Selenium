  <!DOCTYPE HTML>
  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
  
   <html>
   
     <head >
     
     <script type="text/javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/json2.js"></script>
     <script type="text/javascript" language="javascript" src="http://www.technicalkeeda.com/js/javascripts/plugin/jquery.js"></script>
    
       <meta charset="ISO-8859-1">
         <title>Spring Boot - Angularjs Example</title>
	 
	 
	  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
	
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      
   
     <script>
     
      $(document).ready(function() {
    	$("#submitForm").click(function(e) {
    	e.preventDefault();
    	$(this).prop('disabled',true);
      /*    var name = $("#name").val();
    	 var salary = $("#salary").val();
    	 var designation = $("#designation").val(); */
    	 
    	// var obj = { "name":name, "salary":salary, "designation":designation}; 
    	 //=========
    		//$("#submitForm").on("click", function() {
      var formData = new FormData($('#uploadForm')[0]);
    
        $.ajax({
               url: "save",
               type: "POST",
               /* dataType: 'json', */
               //enctype: 'multipart/form-data',
                contentType:false, 
              // contentType: "application/json; charset=utf-8",
               //contentType: 'multipart/form-data',
               processData: false,
              /*headers : {
   	            'Accept' : 'application/json',
   	            'Content-Type' : 'application/json'
   	        },  */
               cache: false,
               data: formData,
              /*  beforeSend: function(xhr) {
                   xhr.setRequestHeader("Accept", "application/json");
                   xhr.setRequestHeader("Content-Type", "application/json");
               },  */
                  
               success: function(data) {
            	   var myJSON = JSON.stringify(data);
            	   
            	    console.log("SUCCESS:myjon ==>", JSON.parse(myJSON));
            	  /*  console.log("SUCCESS: ==>", myJSON); */
            	  // console.log(myJSON);
            	  alert("success");
                  console.log("data===>"+data);
            	  
              
          },
             error: function () {
            	 window.location.href='http://localhost:8080/NewDynamic/viewform';	  
             alert("unable to create the record");
         }
          
      });
          
    		});		  
    	
    	 });

  /* var app=angular.module('myapp',[]);
       app.controller('myappcontroller',function($scope, $http) {
   	 
   	  $scope.submitForm = function(){ */
   		/*   var urlBase="http://localhost:8081/NewDynamic";
   			var url = urlBase + "/save"; */
   			
   		/* 	var config = {
   	                headers : {
   	                    'Accept': 'text/plain'
   	                }
   	             }
   			
   			var data = {
   					name: $scope.name,
   					salary: $scope.salary,
   					designation: $scope.designation
   	            };
   			 */
   			/*  
   			$scope.users=[];
   	    	  $scope.userform={
   	    		name: "",
   	    		salary:"",
   	    		designation:""
   	    	 };
   	    	console.log($scope.userform); */
   		//=======	
   		/*  $http.post("save", {
    		 name: $scope.name,
    		 salary: $scope.salary,
    		 designation: $scope.designation
    		 }, function(data) {
    		 if (data == 'You have Successfully Registered.....') {
    		 $("form")[0].reset();
    		 }
    		 alert(data);
    		 }); */
   			//===========
   		/* 	$http.post(url, data, config).then(function(response) {
   				$scope.postResultMessage = response.data;
   			   $scope.postResultMessage = "success"
   			}, function error(response) {
   				$scope.postResultMessage = "Error with status: " +  response.statusText;
   			}); */
   		
   	//  } 
     
  
   //  }); 
    
          
      </script>
      </head>
    <body>
    
    <div class="conatiner">
      <h3>User Registration Form</h3>
             
      
             
   <form ng-submit="submitForm()" method="post" id="uploadForm" enctype="multipart/form-data">
    <div class="table-responsive">
      <table class="table table-bordered" style="width: 600px">
      	 
      </div>
      
      
      
        <tr>
          <td>Name</td>
          <td><input type="text" id="name" ng-model="name" name="name" size="30" /></td>
        </tr>
        <tr>
          <td>Salary</td>
          <td><input type="text" id="salary" ng-model="salary"  name="salary" size="30" /></td>
       </tr>
       
        <tr>
          <td>Department</td>
          <td><input type="text" id="designation" ng-model="designation" name="designation" size="30" /></td>
       </tr>
       
        <tr>
        <object data="http://stackoverflow.com/does-not-exist.png" type="file">
            <img src="https://appharbor.com/assets/images/stackoverflow-logo.png" />
       </object>
          <input type="file" name="path" id="path"/>
                
            </tr>
      <!--   <tr>
          <td>Images</td>
          <td><input type="file" id="images" ng-model="images" name="images" size="30" /></td>
       </tr> -->
      <!--  <tr>
       
        <label for="image">Product Images: </label>
                     <td colspan="2"><input type="file" name="images" multiple="multiple"/></td>
       </tr> -->
       
       
       <tr>
          <td colspan="2"><input type="submit" id="submitForm" class="btn btn-primary btn-sm" value="Create / Update User" /></td>
       </tr>
     </table>
   </div>
 </form>
     <p>{{postResultMessage}}</p>
     </div>
     </body>
     

   
   
   <%--   <h3 style="color:red">${filesuccess}</h3>  
     <form:form method="post" action="savefile" enctype="multipart/form-data">  
<p><label for="image">Choose Image</label></p>  
<p><input name="file" id="fileToUpload" type="file" /></p>  
<p><input type="submit" value="Upload"></p>  
</form:form>  
    --%>
     </html>
       
  <%--  <h1>Add New Employee</h1>  
       <form:form method="post" action="save">    
        <table >    
         <tr>    
          <td>Name : </td>   
          <td><form:input path="name"  /></td>  
         </tr>    
         <tr>    
          <td>Salary :</td>    
          <td><form:input path="salary" /></td>  
         </tr>   
         <tr>    
          <td>Designation :</td>    
          <td><form:input path="designation" /></td>  
         </tr>   
         
     
         <tr>    
          <td> </td>    
          <td><input type="submit" value="Save" /></td>    
         </tr>    
        </table>    
       </form:form>    
       --%>