<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="">
	<div class="card bg-info text-white mt-2">
		<h4 class="card-header ">
			${article.title}
			<a class="btn btn-danger rounded-circle float-right" href="add-article?eIssn=${article.eIssn}">Edit</a>
		</h4>

		<div class="card-body">
			<!--<h4 class="card-title">Article Tit</h4>-->
			<table class="table table-responsive-md table-hover">
				<tr>
					<th class="font-weight-bold">First Author:</th>
					<td>${article.firstAuthor }</td>
					<th>Second Author:</th>
					<td>${article.secondAuthor }</td>
					<th>Third Author:</th>
					<td>${article.thirdAuthor }</td>

				</tr>
				<tr>
					<th>Issuance:</th>
					<td>${article.issuance }</td>
					<th>Volume:</th>
					<td>${article.volume }</td>
					<th>Year:</th>
					<td><fmt:formatDate value="${article.year}" pattern='YYYY'/></td>
				</tr>
				<tr>
					<th>Journal Title:</th>
					<td>${article.journalTitle }</td>
					<th>E-ISSN:</th>
					<td>${article.eIssn }</td>
					<th>P-ISSN:</th>
					<td>${article.pIssn }</td>
				</tr>
				<tr>
					<th>Publisher:</th>
					<td>${article.publisher }</td>
					<th>Website:</th>
					<td><a href="${article.website}">Link</a></td>
					<th>Email:</th>
					<td>${article.email }</td>

				</tr>
				<tr>
					<th>Other Author:</th>
					<td>${article.otherAuthor}</td>
				</tr>
				
			</table>

		</div>
	</div>
</div>