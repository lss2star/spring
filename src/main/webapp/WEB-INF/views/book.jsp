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
<title>유일 도서 페이지</title>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="./home">Home</a> <a
					class="navbar-brand" href="./home">도서목록</a> <a class="navbar-brand"
					href="./home">게시판</a>
			</div>
		</div>
	</nav>

	<div class="jumbotron">
		<div class="jcontainer">
			<h1 class="display-3">도서 정보</h1>
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
					<b>도서 코드 : </b>${book.bookId }
				<p>
					<b>저자 : </b>${book.author }
				<p>
					<b>출판사 : </b>${book.publisher }
				<p>
					<b>분류 : </b>${book.category }
				<p>
					<b>출판일 : </b>${book.releaseDate }
				<p>
					<b>재고수 : </b>${book.unitStock }
				<h4>${book.unitPrice }원</h4>
				<br>
				<a href="<c:url value="/books"/>" 
				class="btn btn-secondary">도서 모록 &raquo;</a>
			</div>
		</div>

	</div>

</body>
</html>