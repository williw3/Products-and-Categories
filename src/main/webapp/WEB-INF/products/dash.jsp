<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Show Product Page</title>
</head>
<body>
	<h1><c:out value="${thisProduct.name}"></c:out></h1>
	<ul>
		<c:forEach var="cat" items="${thisProduct.categories}" >
			<li><c:out value="${cat.name}"></c:out></li>
		</c:forEach>
		
	</ul>
	<form action="/products/add" method="post">
		<p>
			<input type="hidden" name="id" value="${thisProduct.id}"/>
		</p>
    	<p>
        	<label for="name">Add Category:</label>
        	<select name="name">
        		<c:forEach var="cat" items="${availableCategories}">
        			<option value="${cat.id}"><c:out value="${cat.name}"></c:out></option>
        		</c:forEach>
        	</select>
    	</p>
    	<input type="submit" value="Add"/>
	</form>
</body>
</html>