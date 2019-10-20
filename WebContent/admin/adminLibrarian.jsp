<%@page import="java.util.LinkedList"%>
<%@page import="model.College"%>
<%@page import="model.Librarian.Post"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="model.Librarian"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="">

	<h3 class="h3 text-center bg-dark text-info p-3">Librarian
		Management</h3>
	<button class="btn btn-success btn-block mb-2 mt-2" data-toggle="modal"
		data-target="#addlibrarian">Add Librarian</button>
	<!-- Modal -->
	<div class="modal fade" id="addlibrarian" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="lib">Add Librarian</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form method="post" action="admin/addLibrarian"
					enctype="multipart/form-data">
					<div class="modal-body">

						<div class="form-row">
							<img src="" class="m-auto" id="target" alt="Not Found">
						</div>
						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="addlpic">Image:</label> <input class="form-control"
									type="file" id="src" name="pic">
								<script>
										var src = document.getElementById("src");
										var target = document.getElementById("target");
										showImage(src, target);
									</script>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="addlname">Name:</label> <input class="form-control"
									id="addlname" name="name">
							</div>
							<div class="form-group col-md-6">
								<label for="addlemail">Email:</label> <input
									class="form-control" id="addlemail" type="email" name="email">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="addlPassword">Password:</label> <input
									class="form-control" id="addlPassword" name="password"
									type="password">
							</div>
							<div class="form-group col-md-6">
								<label for="addlcPassword">Confirm Password:</label> <input
									class="form-control" id="addlcPassword" name="cPassword"
									type="password">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="addlcontact">Contact:</label> <input
									class="form-control" id="addlcontact" name="cell">
							</div>
							<div class="form-group col-md-6">
								<label for="addlpost">Post:</label> <select
									class="form-control custom-select" id="addlpost" name="post">
									<c:forEach items="${posts}" var="post">
										<option value="${post}">${post}</option>
									</c:forEach>
									
								</select>
							</div>
						</div>
					</div>

					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save
							changes</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="alert alert-warning" role="alert">${param.msg}</div>
	<%
		List<Librarian> librarians = (List<Librarian>) request.getAttribute("librarians");
		int i = 0;
		if (librarians != null)
			for (Librarian librarian : librarians) {
				String image = Base64.encodeBase64String(librarian.getPic());
				i++;
	%>
	<div style="background-color: #f7ecb5; border-radius: 10px">
		<table class="table table-responsive-md">
			<tr>
				<td rowspan="8"><img src="data:image/gif;base64,<%=image%>"
					class="img-fluid"></td>
			</tr>
			<tr class="">
				<td class="p-2">Name:</td>
				<td class="p-2"><%=librarian.getName()%></td>
			</tr>
			<tr class="">
				<td class="p-2">cell:</td>
				<td class="p-2"><%=librarian.getCell()%></td>
			</tr>
			<tr class="">
				<td class="p-2">Email</td>
				<td class="p-2"><%=librarian.getEmail()%></td>
			</tr>
			<tr>
				<td class="p-2">Password</td>
				<td class="p-2"><%=librarian.getPassword()%></td>
			</tr>
			<tr>
				<td class="p-2">Post</td>
				<td class="p-2"><%=librarian.getPost()%></td>
			</tr>
			<tr>
				<td class="p-2">College</td>
				<td class="p-2"><%=librarian.getCollege().getName()%></td>
			</tr>
			<tr>
				<td colspan="3" class="text-center">
					<form action="admin/delLibrarian" method="post">
						<div class="btn-group">
							<button type="button" class="btn btn-success" data-toggle="modal"
								data-target="#<%=i%>" name="edit"
								value="<%=librarian.getEmail()%>">Edit</button>
							<button class="btn btn-danger" name="delete"
								value="<%=librarian.getEmail()%>">Delete</button>
						</div>
					</form>
					<div>
						<!-- Modal -->
						<div class="modal fade" id="<%=i%>" tabindex="-1" role="dialog"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="librarian">Edit Librarian</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<form method="post" action="admin/updateLibrarian" enctype="multipart/form-data">
										<div class="modal-body">

											<div class="form-row">
												<img src="data:image/gif;base64,<%=image%>"
													class="m-auto img-fluid" id="eLT<%=i%>">
											</div>
											<div class="form-row">
												<div class="form-group col-md-12">
													<label for="pic">Image:</label> <input class="form-control"
														type="file" id="eLS<%=i %>" name="pic">
												</div>
												<script>
													var src = document.getElementById('eLS<%=i%>');
													var target = document.getElementById('eLT<%=i%>');
													showImage(src, target);
												</script>
											</div>
											<div class="form-row">
												<div class="form-group col-md-6">
													<label for="name">Name:</label> <input class="form-control"
														id="name" value="<%=librarian.getName()%>" name="name">
												</div>
												<div class="form-group col-md-6">
													<label for="email">Email:</label> <input
														class="form-control" id="email"
														value="<%=librarian.getEmail()%>" name="email" readonly="readonly">
												</div>
											</div>
											<div class="form-row">
												<div class="form-group col-md-6">
													<label for="Password">Password:</label> <input
														class="form-control" id="Password"
														value="<%=librarian.getPassword()%>" name="password">
												</div>
												<div class="form-group col-md-6">
													<label for="cPassword">Confirm Password:</label> <input
														class="form-control" id="cPassword"
														value="<%=librarian.getPassword()%>" name="cPassword">
												</div>
											</div>
											<div class="form-row">
												<div class="form-group col-md-6">
													<label for="editlcontact">Contact:</label> <input
														class="form-control" id="editlcontact"
														value="<%=librarian.getCell()%>" name="cell">
												</div>

												<div class="form-group col-md-6">
													<label for="post">Post:</label> <select
														class="form-control custom-select" id="post" name="post">
														<%
															String[] post=(String[])request.getAttribute("posts");
																	for (String p : post) {
																		if (librarian.getPost().toString().equals(p))
																			out.print("<option value='" + p.toString() + "' selected>" + p.toString() + "</option>");
																		else
																			out.print("<option value='" + p.toString() + "'>" + p.toString() + "</option>");
																	}
														%>
													</select>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Close</button>
											<button type="submit" class="btn btn-primary">Save
												changes</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	
	<%
		}
	%>
</div>
