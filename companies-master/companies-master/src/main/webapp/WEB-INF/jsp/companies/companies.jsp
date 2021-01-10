<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="<c:url value="/webjars/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"/>">

<script src="<c:url value="/webjars/jquery/3.2.1/jquery.min.js"/>"></script>
<script
	src="<c:url value="/webjars/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"/>"></script>

<title>List of Companies</title>

</head>
<body>
	<nav class="navbar sticky-top navbar-dark bg-primary ">
		<div class="container">
			<a class="navbar-brand" href="<c:url value="/companies/list/1"/>">Companies</a>
			<form class="form-inline" method="GET"
				action="/companies/companies/list/1">
				<div>
					<input type="text" class="form-control" name="searchString"
						placeholder="Search ..." /> <input type="submit" value="Submit"
						class="btn btn-outline-light" />
				</div>
			</form>
		</div>
	</nav>
	<div class="container">
		<div class="text-center p-4">
			<h1>List of Companies</h1>
		</div>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Name</th>
					<th scope="col">Number</th>
					<th scope="col">Country</th>
					<th scope="col">Action</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${companies}" var="comp">
					<tr>
						<td>${comp.id}</td>
						<td><c:out value="${comp.name}" /></td>
						<td><c:out value="${comp.number}" /></td>
						<td><c:out value="${comp.country.name}" /></td>
						<td><a class="btn btn-outline-primary" type="button"
							href="<c:url value='http://localhost:8080/companies/companies/edit/${comp.id}' />">Edit</a>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<nav>
			<ul class="pagination justify-content-center">
				<c:forEach items="${pages}" var="page">
					<li class="page-item"><a class="page-link"
						href="http://localhost:8080/companies/companies/list/${page}">${page}</a></li>
				</c:forEach>
			</ul>
		</nav>



		<a class="btn btn-outline-success btn-lg btn-block" type="button"
			href="http://localhost:8080/companies/companies/add">Add new
			company</a>
	</div>

</body>
</html>
