<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="model.Librarian"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Librarian Dashboard</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="fontawesome/css/all.min.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
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
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Acquisition</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="addBookPage">Add Book</a> <a
								class="dropdown-item" href="book-Detail">Edit Book</a>
								<a class="dropdown-item" href="add-bk-excel">Import Excel</a>
						</div></li>
						<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Article</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="add-article">Add Article</a> <a
								class="dropdown-item" href="add-article">Edit Article</a>
						</div></li>
						<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Journal</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="add-journal">Add Journal</a> <a
								class="dropdown-item" href="add-journal">Edit Journal</a>
						</div></li>
					<li class="nav-item"><a class="nav-link" href="add-member">Add Member</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Circulation</a></li>
					
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Reports </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="adminBooksCntrlr">Books</a> <a
								class="dropdown-item" href="articles">Articles</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="journalCntrlr">Journal</a>
							<a class="dropdown-item" href="memberCntrlr">Members</a>
						</div></li>
						<li class="nav-item"><a class="nav-link" href="librarianchangePage?page=opacuser">OPAC</a></li>
						<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
				</ul>
				<a class="navbar-brand" href="librarianchangePage?page=profile">
    			<img src="data:image/gif;base64,${sessionScope.pic}" width="30" height="30" class="d-inline-block align-top" alt="">
    			${sessionScope.name}
  </a>
				<!-- <form class="form-inline my-2 my-lg-0">
					<input class="form-control mr-sm-2" type="search"
						placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form> -->
			</div>
		</nav>
		<div class="row">
			<%-- <div class="col-md-3  rounded ">
				<div class="m-auto text-center border bg-dark">
					<img src="data:image/gif;base64,${sessionScope.pic}"
						class="img-fluid rounded-circle w-25">
					<p class="text-light">${sessionScope.name}</p>
				</div>
				<div class="list-group">
					<!-- <a href="librarianchangePage?page=books"
						class="list-group-item list-group-item-action list-group-item-secondary">Books</a> -->
					<!-- <a href="librarianchangePage?page=articles"
						class="list-group-item list-group-item-action list-group-item-warning">Articles</a> -->
					<!-- <a href="librarianchangePage?page=journals"
						class="list-group-item list-group-item-action list-group-item-warning">Journals</a> -->
					<a href="librarianchangePage?page=opacuser"
						class="list-group-item list-group-item-action list-group-item-warning">OPAC</a>
					<!-- <a href="librarianchangePage?page=profile"
						class="list-group-item list-group-item-action list-group-item-warning">Profile</a> -->
					<!-- <a href="logout"
						class="list-group-item list-group-item-action list-group-item-info">Logout</a> -->
				</div> --%>
			<!-- </div> -->
				<div class="col-md-12">
				<jsp:include page="${pages}"></jsp:include>
				</div>
		</div>
	</div>
	
</body>
<script>
	var src = document.getElementById("src");
	var target = document.getElementById("target");
	showImage(src, target);
</script>
</html>