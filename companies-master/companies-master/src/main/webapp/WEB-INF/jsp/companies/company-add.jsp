<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<c:url value="/webjars/bootstrap/4.0.0-beta.3/css/bootstrap.min.css"/>">

<script src="<c:url value="/webjars/jquery/3.2.1/jquery.min.js"/>"></script>
<script
	src="<c:url value="/webjars/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"/>"></script>

<title>Add new company</title>

</head>
<body>
	<div class="container">
		<div class="p-3">
			<h1>Add new Company</h1>
		</div>
		<div class="pt-3">
			<form:form method="POST" action="/companies/companies/add"
				modelAttribute="company">
				<div class="form-group">
					<label>Id:</label>
					<form:input disabled="true" class="form-control" path="id" value="${nextId}" />
				</div>
				<div class="form-group">
					<label>Name:</label>
					<form:input class="form-control" width="300px" path="name" />
				</div>
				<div class="form-group">
					<label>Number:</label>
					<form:input class="form-control" width="300px" path="number" />
				</div>
				
				<div class="form-group">
					<label for="sel1">Select country:</label>
					<form:select path="countryId" class="form-control" id="sel1">
						<form:options items="${countries}" itemLabel = "name" itemValue="id"></form:options>
					</form:select>
				</div>
				<div class="form-group">
					<br> <input type="submit" value="Submit"
						class="btn btn-outline-success btn-lg btn-block" />
				</div>
			</form:form>
		</div>

	</div>

</body>
</html>

