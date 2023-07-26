<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet">
<meta charset="EUC-KR">
<title>Welcome</title>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="./home">Home</a> 
				<a class="navbar-brand" href="./home">�Խ���</a> 
				<a class="navbar-brand"	href="./home">���θ�</a>
			</div>
		</div>
	</nav>

	<div class="jumbotron">
		<div class="jcontainer">
			<h1 class="display-3">���� ���</h1>
			<!-- <h2>${pageContext.request.contextPath }</h2> -->
		</div>
	</div>

	<div class="container">
		<div class="row" align="center">
			<p>${book }
			<c:forEach items="${bookList }" var="book">
				<div class="col-md-4">
				
					<h3>${book.name } | ${book.bookId } </h3>
					<p>${book.author }  | ${book.publisher }
						<br> ${book.category } | ${book.releaseDate }
					<p align=Left>${fn:substring(book.description, 0, 60)} ...
					<p>${book.condition }
					<p>${book.unitPrice }��
					
					<p><a href="<c:url value="/books/book?id=${book.bookId }"/>"
						class="btn btn-secondary" role="button">�󼼺��� &raquo;</a>
				</div>
			</c:forEach>
			
		</div>
	</div>

	<footer class="container">
		<hr>
		<p>&copy; Book</p>
	</footer>
</body>
</html>