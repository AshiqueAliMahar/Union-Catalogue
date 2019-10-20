<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Edit arTicle-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="">
<form class="form-inline my-2 my-lg-0" action="add-journal"
		method="get">
		<input class="form-control mr-sm-2" type="search"
			placeholder="Enter E-ISSN" aria-label="Search" name="eIssn">
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	</form>
<form method="post" action="add-journal">

	<input type="hidden" value="${journal.id==null?0:journal.id}" name="id">

	<div class="form-row">
		<label class="form-group col-md-6">Journal Title* <input
			class="form-control" value="${journal.title }" name="title">
		</label> <label class="form-group col-md-6">Issuance* <input
			type="number" class="form-control" value="${journal.issuance }"
			name="issuance">
		</label>
	</div>
	<div class="form-row">
		<label class="form-group col-md-6">Volume* <input
			type="number" class="form-control" value="${journal.volume }"
			name="volume">
		</label> <label class="form-group col-md-6">Year* <input type="number"
			class="form-control"
			value="<fmt:formatDate value="${journal.year}" pattern="YYYY"/>"
			name="year">
		</label>
	</div>
	<div class="form-row">
		<label class="form-group col-md-12">E-ISSN* <input
			class="form-control" value="${journal.eIssn }" name="eIssn">
		</label>
	</div>
	<div class="form-row">
		<label class="form-group col-md-6">P-ISSN* <input
			class="form-control" value="${journal.pIssn }" name="pIssn">
		</label> <label class="form-group col-md-6">Publisher* <input
			class="form-control" value="${journal.publisher }" name="publisher">
		</label>
	</div>
	<div class="form-row">
		<label class="form-group col-md-6">Website* <input
			class="form-control" value="${journal.website }" name="website"
			type="url">
		</label> <label class="form-group col-md-6">Email* <input type="email"
			class="form-control" value="${journal.email }" name="email">
		</label>
	</div>
	<button type="submit" class="btn btn-primary btn-block">Save</button>
</form>
</div>