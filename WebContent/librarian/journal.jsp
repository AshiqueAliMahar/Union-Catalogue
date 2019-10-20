<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="">
	<!-- Button trigger modal Article -->
	<!-- <button type="button" class="btn btn-primary fa fa-2x fa-plus-circle rounded-circle" data-toggle="modal"
		data-target="#exampleModal"></button> -->
	<c:if test="${param.msg!=null}">
		<div class="alert alert-warning">${param.msg}</div>
	</c:if>
	<!-- Add Journal -->
	<!-- <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<form method="post" action="add-journal">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Add Journal</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<input type="hidden" value="0" name="id">
						
						<div class="form-row">
							<label class="form-group col-md-6">Journal Title* <input
								class="form-control" name="title">
							</label>
							<label class="form-group col-md-6">Issuance* <input
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
							 <label class="form-group col-md-6">E-ISSN* <input
								class="form-control" name="eIssn">
							</label>
							<label class="form-group col-md-6">P-ISSN* <input
								class="form-control" name="pIssn">
							</label>
						</div>
						<div class="form-row">
							 <label class="form-group col-md-12">Publisher* <input
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
	<c:forEach items="${journals }" var="journal" varStatus="aStatus">
		<!-- <div> -->
		<%-- <!--Edit arTicle-->
		<div class="modal fade" id="editarticle${aStatus.count}" tabindex="-1" role="dialog"
			aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<form method="post" action="add-journal">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Edit Journal</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<input type="hidden" value="${journal.id}" name="id">
							
							<div class="form-row">
							 	<label class="form-group col-md-6">Journal Title* <input
									class="form-control" value="${journal.title }" name="title">
								</label>
							 	<label class="form-group col-md-6">Issuance* <input type="number"
									class="form-control" value="${journal.issuance }" name="issuance">
								</label>
							</div>
							<div class="form-row">
								<label class="form-group col-md-6">Volume* <input
									type="number" class="form-control" value="${journal.volume }" name="volume">
								</label> <label class="form-group col-md-6">Year* <input
									type="number" class="form-control" value="<fmt:formatDate value="${journal.year}" pattern="YYYY"/>" name="year">
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
									class="form-control" value="${journal.website }" name="website" type="url">
								</label> <label class="form-group col-md-6">Email* <input type="email"
									class="form-control" value="${journal.email }" name="email">
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
				${journal.title}
				<%-- <button class="btn btn-danger rounded-circle float-right"
				data-toggle="modal" data-target="#editarticle${aStatus.count}">Edit</button> --%>
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
	</c:forEach>
</div>
