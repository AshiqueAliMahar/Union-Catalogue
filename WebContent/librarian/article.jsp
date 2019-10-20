<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="">
	<!-- Button trigger modal Article -->
	<!-- <button type="button" class="btn btn-primary fa fa-2x fa-plus-circle rounded-circle" data-toggle="modal"
		data-target="#exampleModal"></button> -->
	<c:if test="${param.msg!=null}">
	<div class="alert alert-warning">
		${param.msg}
	</div>
	</c:if>
	<!-- Add Article
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<form method="post" action="add-article">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Add Article</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<input type="hidden" value="0" name="id">
						<div class="form-row">
							<label class="form-group col-md-6">Title* <input
								class="form-control" name="title">
							</label> <label class="form-group col-md-6">First Author* <input
								class="form-control" name="firstAuthor">
							</label>
						</div>
						<div class="form-row">
							<label class="form-group col-md-6">Second Author* <input
								class="form-control" name="secondAuthor">
							</label> <label class="form-group col-md-6">Third Author* <input
								class="form-control" name="thirdAuthor">
							</label>
						</div>
						<div class="form-row">
							<label class="form-group col-md-6">Other Authors* <input
								class="form-control" name="otherAuthor">
							</label> <label class="form-group col-md-6">Issuance* <input
								class="form-control" name="issuance" type="number">
							</label>
						</div>
						<div class="form-row">
							<label class="form-group col-md-6">Volume* <input
								type="number" class="form-control" name="volume">
							</label> <label class="form-group col-md-6">Year* <input
								type="number" class="form-control" name="year">
							</label>
						</div>
						<div class="form-row">
							<label class="form-group col-md-6">Journal Title* <input
								class="form-control" name="journalTitle">
							</label> <label class="form-group col-md-6">E-ISSN* <input
								class="form-control" name="eIssn">
							</label>
						</div>
						<div class="form-row">
							<label class="form-group col-md-6">P-ISSN* <input
								class="form-control" name="pIssn">
							</label> <label class="form-group col-md-6">Publisher* <input
								class="form-control" name="publisher">
							</label>
						</div>
						<div class="form-row">
							<label class="form-group col-md-6">Website* <input
								class="form-control" name="website" type="url">
							</label> <label class="form-group col-md-6">Email* <input
								class="form-control" name="email" type="email">
							</label>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button class="btn btn-primary">Save</button>
					</div>
				</div>
			</form>
		</div>
	</div> -->
	<c:forEach items="${articles }" var="article" varStatus="aStatus">
	<div>
		<%-- <!--Edit arTicle-->
		<div class="modal fade" id="editarticle${aStatus.count}" tabindex="-1" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<form method="post" action="add-article">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Edit Article</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<input type="hidden" value="${article.id}" name="id">
							<div class="form-row">
								<label class="form-group col-md-6">Title* <input
									class="form-control" value="${article.title }" name="title">
								</label> <label class="form-group col-md-6">First Author* <input
									class="form-control" value="${article.firstAuthor }" name="firstAuthor">
								</label>
							</div>
							<div class="form-row">
								<label class="form-group col-md-6">Second Author* <input
									class="form-control" value="${article.secondAuthor }" name="secondAuthor">
								</label> <label class="form-group col-md-6">Third Author* <input
									class="form-control" value="${article.thirdAuthor }" name="thirdAuthor">
								</label>
							</div>
							<div class="form-row">
								<label class="form-group col-md-6">Other Authors* <input
									class="form-control" value="${article.otherAuthor }" name="otherAuthor">
								</label> <label class="form-group col-md-6">Issuance* <input type="number"
									class="form-control" value="${article.issuance }" name="issuance">
								</label>
							</div>
							<div class="form-row">
								<label class="form-group col-md-6">Volume* <input
									type="number" class="form-control" value="${article.volume }" name="volume">
								</label> <label class="form-group col-md-6">Year* <input
									type="number" class="form-control" value="<fmt:formatDate value="${article.year}" pattern="YYYY"/>" name="year">
								</label>
							</div>
							<div class="form-row">
								<label class="form-group col-md-6">Journal Title* <input
									class="form-control" value="${article.journalTitle }" name="journalTitle">
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
									class="form-control" value="${article.website }" name="website" type="url">
								</label> <label class="form-group col-md-6">Email* <input type="email"
									class="form-control" value="${article.email }" name="email">
								</label>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Save
								changes</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div> --%>
	<div class="card bg-info text-white mt-2">
		<h4 class="card-header ">
			${article.title}
			<%-- <button class="btn btn-danger rounded-circle float-right"
				data-toggle="modal" data-target="#editarticle${aStatus.count}">Edit</button> --%>
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
	</c:forEach>
</div>
