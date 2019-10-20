<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="">
	<c:if test="${param.msg!=null}">
		<div class="alert alert-warning">${param.msg}</div>
	</c:if>
	<form class="form-inline my-2 my-lg-0" action="add-article"
		method="get">
		<input class="form-control mr-sm-2" type="search"
			placeholder="Enter E-ISSN" aria-label="Search" name="eIssn">
		<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	</form>
	<form method="post" action="add-article">

		<input type="hidden" value="${article.id==null?0:article.id}" name="id">
		<div class="form-row">
			<label class="form-group col-md-6">Title* <input
				class="form-control" value="${article.title }" name="title">
			</label> <label class="form-group col-md-6">First Author* <input
				class="form-control" value="${article.firstAuthor }"
				name="firstAuthor">
			</label>
		</div>
		<div class="form-row">
			<label class="form-group col-md-6">Second Author* <input
				class="form-control" value="${article.secondAuthor }"
				name="secondAuthor">
			</label> <label class="form-group col-md-6">Third Author* <input
				class="form-control" value="${article.thirdAuthor }"
				name="thirdAuthor">
			</label>
		</div>
		<div class="form-row">
			<label class="form-group col-md-6">Other Authors* <input
				class="form-control" value="${article.otherAuthor }"
				name="otherAuthor">
			</label> <label class="form-group col-md-6">Issuance* <input
				type="number" class="form-control" value="${article.issuance }"
				name="issuance">
			</label>
		</div>
		<div class="form-row">
			<label class="form-group col-md-6">Volume* <input
				type="number" class="form-control" value="${article.volume }"
				name="volume">
			</label> <label class="form-group col-md-6">Year* <input
				type="number" class="form-control"
				value='<fmt:formatDate value="${article.year}" pattern="YYYY" />'
				name="year">
			</label>
		</div>
		<div class="form-row">
			<label class="form-group col-md-6">Journal Title* <input
				class="form-control" value="${article.journalTitle }"
				name="journalTitle">
			</label> <label class="form-group col-md-6">E-ISSN* <input
				class="form-control" value="${article.eIssn }" name="eIssn">
			</label>
		</div>
		<div class="form-row">
			<label class="form-group col-md-6">P-ISSN* <input
				class="form-control" value="${article.pIssn }" name="pIssn">
			</label> <label class="form-group col-md-6">Publisher* <input
				class="form-control" value="${article.publisher }" name="publisher">
			</label>
		</div>
		<div class="form-row">
			<label class="form-group col-md-6">Website* <input
				class="form-control" value="${article.website }" name="website"
				type="url">
			</label> <label class="form-group col-md-6">Email* <input
				type="email" class="form-control" value="${article.email }"
				name="email">
			</label>
		</div>

		<button type="submit" class="btn btn-primary">Save changes</button>
	</form>
</div>
