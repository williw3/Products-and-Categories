<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Show Category</title>
</head>
<body>
	<h1><c:out value="${thisCategory.name}"></c:out></h1>
	<ul>
		<c:forEach var="prod" items="${thisCategory.products}" >
			<li><c:out value="${prod.name}"></c:out></li>
		</c:forEach>
	</ul>
	<form action="/categories/add" method="post">
		<p>
			<input type="hidden" name="id" value="${thisCategory.id}"/>
		</p>
    	<p>
        	<label for="name">Add Product:</label>
        	<select name="name">
        		<c:forEach var="prod" items="${availableProducts}">
        			<option value="${prod.id}"><c:out value="${prod.name}"></c:out></option>
        		</c:forEach>
        	</select>
    	</p>
    	<input type="submit" value="Add"/>
	</form>
</body>
</html>