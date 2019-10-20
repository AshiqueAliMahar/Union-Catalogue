<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="model.College"%>
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
<div class="">
	<h3 class="h3 text-center bg-dark text-info p-3">College
		Management</h3>
	<!-- Modal -->
	<div class="alert alert-warning" role="alert">${param.msg}</div>
	<%
		List<College> colleges = (List<College>) request.getAttribute("colleges");
		int i=0;
		for (College college : colleges) {
			String image = Base64.encodeBase64String(college.getLogo());
			i++;
	%>
	<div style="background-color: #f7ecb5; border-radius: 10px">
		<table class="table table-responsive-md">
			<tr >
				<td rowspan="7"><img src="data:image/gif;base64,<%=image%>" class="img-fluid rounded" ></td>
			</tr>
			<tr class="">
				<td class="p-2">College Name:</td>
				<td class="p-2"><%=college.getName()%></td>
			</tr>
			<tr class="">
				<td class="p-2">Website:</td>
				<td class="p-2"><a href='<%=college.getWebsite()%>'><%=college.getWebsite()%></a></td>
			</tr>
			<tr class="">
				<td class="p-2">Email</td>
				<td class="p-2"><%=college.getEmail()%></td>
			</tr>
			<tr>
				<td class="p-2">Contact</td>
				<td class="p-2"><%=college.getContact()%></td>
			</tr>
			<tr>
				<td class="p-2">Address</td>
				<td class="p-2"><%=college.getAddress()%></td>
			</tr>
			<tr>
				<td colspan="3" class="text-center">
						<button class="btn btn-success" data-toggle="modal"
							data-target="#<%=i%>">Edit</button>
					<div>
						<!-- Modal -->
						<div class="modal fade" id="<%=i%>" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="m">Edit College</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<form method="post" action="updateCollege" enctype="multipart/form-data">
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
														value="<%=college.getName()%>" name="name">
												</div>
											</div>
											<div class="form-row">
												<div class="form-group col-md-6">
													<label for="website">Website:</label> <input
														class="form-control" id="website"
														value="<%=college.getWebsite()%>" name="website">
												</div>
												<div class="form-group col-md-6">
													<label for="editcemail">Email:</label> <input
														class="form-control" id="editcemail"
														value="<%=college.getEmail()%>" name="email">
												</div>
											</div>
											<div class="form-row">
												<div class="form-group col-md-6">
													<label for="editccontact">Contact:</label> <input
														class="form-control" id="editccontact"
														value="<%=college.getContact()%>" name="contact">
												</div>
												<div class="form-group col-md-6">
													<label for="address">Address:</label> <input
														class="form-control" id="address"
														value="<%=college.getAddress()%>" name="address">
												</div>
											</div>

										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-dismiss="modal">Close</button>
											<button type="submit" name="code"
												value="<%=college.getCode()%>" class="btn btn-primary">Save
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
<script>
	var src = document.getElementById("src");
	var target = document.getElementById("target");
	showImage(src, target);
</script>