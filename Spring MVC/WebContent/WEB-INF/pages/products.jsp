<%@page import="training.entity.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product list</title>
</head>
<body>
<h1>Product list</h1>
<hr />
<ul>
<%
	List<Product> products = (List<Product>)request.getAttribute("products");

	for(Product p: products){
		out.println("<li>" + p.getName() + "</li>");
	}
%>
</ul>
</body>
</html>