<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link
	href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css"
	rel="stylesheet">
<meta charset="EUC-KR">
<title>���� ���� ������</title>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="./home">Home</a> <a
					class="navbar-brand" href="./home">�������</a> <a class="navbar-brand"
					href="./home">�Խ���</a>
			</div>
		</div>
	</nav>

	<div class="jumbotron">
		<div class="jcontainer">
			<h1 class="display-3">���� ����</h1>
			<!-- <h2>${pageContext.request.contextPath }</h2> -->
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-mid-12">
				<h3>${book.name }</h3>
				<p>${book.description }
					<br>
				<p>
					<b>���� �ڵ� : </b>${book.bookId }
				<p>
					<b>���� : </b>${book.author }
				<p>
					<b>���ǻ� : </b>${book.publisher }
				<p>
					<b>�з� : </b>${book.category }
				<p>
					<b>������ : </b>${book.releaseDate }
				<p>
					<b>���� : </b>${book.unitStock }
				<h4>${book.unitPrice }��</h4>
				<br>
				<a href="<c:url value="/books"/>" 
				class="btn btn-secondary">���� ��� &raquo;</a>
			</div>
		</div>

	</div>

</body>
</html>