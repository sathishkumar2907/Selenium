  <!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
 <!-- <meta http-equiv="Content-Type" content="application/json; charset=UTF-8"> -->

<!-- <script>

$(document).ready(function() {
	$("#submitForm").click(function(e) {
	
		var name=$("name").val();
		var salary=$("email").val();

		var obj = 'name='+name+'&email'+email;
		  $.ajax({
		   url:"simple.form",
		   type:"GET",
		   data:obj,
		   contentType:"application/json",
		   success:function(response){
		  alert(response);
		  },
		  error:function(error){
		  alert(error);
		  }
		});
	
	});
});

</script> -->

<title>Insert title here</title>
</head>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<body>
  
  
<h1>Employees List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Id</th><th>Name</th><th>Salary</th><th>Designation</th><th>Images</th><th>Edit</th><th>Delete</th></tr>  

   <c:forEach var="emp" items="${list}" >   
   <tr>  
   <td>${emp.id}</td>  
   <td>${emp.name}</td>
   <td>${emp.salary}</td>  
   <td>${emp.designation}</td> 
   <td>${emp.path}</td> 
   
   <td><a href="editemp/${emp.id}">Edit</a></td>  
   <td><a href="deleteemp/${emp.id}">Delete</a></td>  
   </tr>  
   </c:forEach>  
   </table> 
   
   
  <%--  <c:forEach items="${emp.images}" var="image">
            <li>${image.originalFilename}
            <img width="100" src="<c:url value="/image/"/>${image.originalFilename}"/>
            </li>
        </c:forEach> --%>
    
   
   <a href="empform">Add New Employee</a>  
   
   
  
   
  <%--  <div id="pagination">

    <c:url value="/viewemp" var="prev">
        <c:param name="page" value="${page-1}"/>
    </c:url>
    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
    </c:if>

    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <c:choose>
            <c:when test="${page == i.index}">
                <span>${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/user/list" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="/user/list" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
</div> --%>
</body>
