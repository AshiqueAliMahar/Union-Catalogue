<%@page import="model.College"%>
<%@page import="java.util.List"%>
<div class="mb-1">
<img src="images/banner.png"  width="100%" class="img-fluid">
	</div>
	<!-- class="img-fluid" height="100px" -->
<form action="search" method="post">


	<div class="input-group mb-3">
		<div class="input-group-prepend ">
			<select class="form-control" name="searchBy">
				<option value="title">Title</option>
				<option value="isbn">ISBN</option>
				<option value="subject">Subject</option>
				<option value="author">Author</option>
			</select>
		</div>

		<input type="text" class="form-control mr-1"
			aria-label="Text input with dropdown button" name="searchText">
		<div class="input-group-append">
			<div class="">
				<select class="form-control" id="college" name='college'>
					<option value="All">All</option>

					<%
						List<College> colleges = (List<College>) request.getAttribute("collegeList");
						if (colleges != null)
							for (College college : colleges) {
								out.print("<option value='" + college.getCode() + "'>" + college.getName() + "</option>");
							}
					%>
				</select>
			</div>
			<button class="btn btn-outline-secondary" name="search"
				value="search">Search</button>

		</div>
	</div>
</form>