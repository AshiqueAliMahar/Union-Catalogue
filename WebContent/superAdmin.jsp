<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="model.Librarian"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard SuperAdmin</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="fontawesome/css/all.css">
    <style>
        #dashList a li:hover {
            background-color: #660000;
        }
    </style>
    <script type="text/javascript">
		function showImage(src, target) {
			var fr = new FileReader();
			// when image is loaded, set the src of the image where you want to display it
			fr.onload = function(e) {
				target.src = this.result;
			};
			src.addEventListener("change", function() {
				// fill fr with image data    
				fr.readAsDataURL(src.files[0]);
			});
		}
	</script>
</head>
<body>
<div class="container-fluid">
	<%-- <jsp:include page="Navbar.jsp"/> --%>
    <div class="row">
        <div class="col-md-3 alert-success rounded p-2">
            <div class="m-auto text-center border bg-dark rounded">
                <img src="data:image/gif;base64,${sessionScope.pic }" class="img-fluid rounded-circle w-25" >
                <p class="text-light">${sessionScope.name}</p>
            </div>
            <div class="list-group">
			  <a href="changePage?page=college" class="list-group-item list-group-item-action">College</a>
			  <a href="changePage?page=librarian" class="list-group-item list-group-item-action list-group-item-primary">Librarian</a>
			  <a href="changePage?page=department" class="list-group-item list-group-item-action list-group-item-success">Department</a>
			  <a href="changePage?page=subject" class="list-group-item list-group-item-action list-group-item-danger">Subject</a>
			  <a href="changePage?page=profile" class="list-group-item list-group-item-action list-group-item-warning">Profile</a>
			  <a href="changePage?page=opacuser" class="list-group-item list-group-item-action list-group-item-warning">OPAC</a>
			  <a href="logout" class="list-group-item list-group-item-action list-group-item-info">Logout</a>
			</div>
        	
    	</div>
    	<div class="col-md-9">
    	<jsp:include page="${pages}"></jsp:include>
    	</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>