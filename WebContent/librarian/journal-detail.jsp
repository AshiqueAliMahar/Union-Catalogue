<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="card bg-info text-white mt-2">
	<h4 class="card-header ">
		${journal.title}
		<a class="btn btn-danger rounded-circle float-right"
			href="add-journal?eIssn=${journal.eIssn }">Edit</a>
	</h4>

	<div class="card-body">
		<!--<h4 class="card-title">Article Tit</h4>-->
		<table class="table table-responsive-md table-hover">
			<tr>
				<th>Journal Title:</th>
				<td>${journal.title }</td>
				<th>E-ISSN:</th>
				<td>${journal.eIssn }</td>
				<th>P-ISSN:</th>
				<td>${journal.pIssn }</td>
			</tr>
			<tr>
				<th>Issuance:</th>
				<td>${journal.issuance }</td>
				<th>Volume:</th>
				<td>${journal.volume }</td>
				<th>Year:</th>
				<td><fmt:formatDate value="${journal.year}" pattern="YYYY" /></td>
			</tr>

			<tr>
				<th>Publisher:</th>
				<td>${journal.publisher }</td>
				<th>Website:</th>
				<td><a href="${journal.website}">Website</a></td>
				<th>Email:</th>
				<td>${journal.email }</td>
			</tr>
		</table>

	</div>
</div>