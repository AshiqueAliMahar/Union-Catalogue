<%@page import="org.apache.tomcat.util.codec.binary.Base64" %>
<%@page import="model.Dept"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	    function showImage(src,target) {
	  	  var fr=new FileReader();
	  	  // when image is loaded, set the src of the image where you want to display it
	  	  fr.onload = function(e) { target.src = this.result; };
	  	  src.addEventListener("change",function() {
	  	    // fill fr with image data    
	  	    fr.readAsDataURL(src.files[0]);
	  	  });
	  	}
	   
</script>
<div>
	<h3 class="h3 text-center bg-dark text-info p-3">Department Management</h3>
	<button class="btn btn-success btn-block mb-2 mt-2" data-toggle="modal"
		data-target="#addCollege">Add Department</button>
	<!-- Modal -->
	<div class="modal fade" id="addCollege" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add Department</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form method="post" action=addDept
					enctype="multipart/form-data">
					<div class="modal-body">

						<div class="form-row">
							<img src="" id="target" class="m-auto img-fluid">
						</div>
						<div class="form-row">
							<div class="form-group col-md-12">
								<label for="src">Logo:</label> <input class="form-control"
									type="file" name="logo" id="src">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="addcisbn">Code:</label> <input class="form-control"
									id="addcisbn" name="code">
							</div>
							<div class="form-group col-md-6">
								<label for="addctitle">Name:</label> <input class="form-control"
									id="addctitle" name="name">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="addcwebsite">Website:</label> <input
									class="form-control" id="addcwebsite" name="website">
							</div>
							<div class="form-group col-md-6">
								<label for="addcemail">Email:</label> <input
									class="form-control" id="addcemail" name="email">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="addccontact">Contact:</label> <input
									class="form-control" id="addccontact" name="contact">
							</div>
							<div class="form-group col-md-6">
								<label for="addcaddress">Address:</label> <input
									class="form-control" id="addcaddress" name="address">
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
		List<Dept> depts = (List<Dept>) request.getAttribute("departments");
		int i=0;
		for (Dept dept : depts) {
			String image = Base64.encodeBase64String(dept.getLogo());
			i++;
	%>
	<div style="background-color: #f7ecb5; border-radius: 10px">
		<table class="table table-responsive-md">
			<tr >
				<td rowspan="7"><img src="data:image/gif;base64,<%=image%>" class="img-fluid rounded" ></td>
			</tr>
			<tr class="">
				<td class="p-2">Department Name:</td>
				<td class="p-2"><%=dept.getName()%></td>
			</tr>
			<tr class="">
				<td class="p-2">Website:</td>
				<td class="p-2"><a href='<%=dept.getWebsite()%>'><%=dept.getWebsite()%></a></td>
			</tr>
			<tr class="">
				<td class="p-2">Email</td>
				<td class="p-2"><%=dept.getEmail()%></td>
			</tr>
			<tr>
				<td class="p-2">Contact</td>
				<td class="p-2"><%=dept.getContact()%></td>
			</tr>
			<tr>
				<td class="p-2">Address</td>
				<td class="p-2"><%=dept.getAddress()%></td>
			</tr>
			<tr>
				<td colspan="3">
					<div class="btn-group">
						<button class="btn btn-success" data-toggle="modal"
							data-target="#<%=i%>">Edit</button>
					</div>
					<div>
						<!-- Modal -->
						<div class="modal fade" id="<%=i%>" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="m">Edit Department</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<form method="post" action="updateDept" enctype="multipart/form-data">
										<div class="modal-body">

											<div class="form-row">
												<img src="data:image/gif;base64,<%=image%>"
													id="eT<%=i%>" class="m-auto img-fluid"
													alt="Not Found">
											</div>
											<div class="form-row">
												<div class="form-group col-md-12">
													<label for="editcpic">Logo:</label> <input
														class="form-control" type="file"
														id="esrc<%=i%>" name="logo">
													<script type="text/javascript">
		                                            	        	src = document.getElementById("esrc<%=i%>");
		                                            	        	target = document.getElementById("eT<%=i%>");
																	showImage(src, target);
													</script>
												</div>
											</div>
											<div class="form-row">
												<%-- <div class="form-group col-md-6">
                                                            <label for="isbn">Code:</label>
                                                            <input class="form-control" id="isbn" value="<%=college.getCode()%>">
                                                        </div> --%>
												<div class="form-group col-md-12">
													<label for="title">Name:</label> <input
														class="form-control" id="title"
														value="<%=dept.getName()%>" name="name">
												</div>
											</div>
											<div class="form-row">
												<div class="form-group col-md-6">
													<label for="website">Website:</label> <input
														class="form-control" id="website"
														value="<%=dept.getWebsite()%>" name="website">
												</div>
												<div class="form-group col-md-6">
													<label for="editcemail">Email:</label> <input
														class="form-control" id="editcemail"
														value="<%=dept.getEmail()%>" name="email">
												</div>
											</div>
											<div class="form-row">
												<div class="form-group col-md-6">
													<label for="editccontact">Contact:</label> <input
														class="form-control" id="editccontact"
														value="<%=dept.getContact()%>" name="contact">
												</div>
												<div class="form-group col-md-6">
													<label for="address">Address:</label> <input
														class="form-control" id="address"
														value="<%=dept.getAddress()%>" name="address">
												</div>
											</div>

										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Close</button>
											<button type="submit" name="code" value="<%=dept.getCode()%>" class="btn btn-primary">Save changes</button>
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
<script>
	var src = document.getElementById("src");
	var target = document.getElementById("target");
	showImage(src, target);
</script>