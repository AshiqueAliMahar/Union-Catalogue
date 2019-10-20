<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="">
<div class="alert alert-warning" role="alert">${param.msg }</div>
<form action="addBook" method="post" enctype="multipart/form-data">
	
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="addbisbn">ISBN:</label>
				<!-- <input class="form-control" id="addbisbn" name="isbn"> -->
				<div class="input-group">
					<input type="text" name="isbn" value="${book.isbn}"
						class="form-control" placeholder="Search for ISBN"> <span
						class="input-group-btn">
						<button class="btn btn-secondary" type="submit" name="find"
							value="find">Go!</button>
					</span>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label for="addbtitle">Title:</label> <input class="form-control"
					type="text" id="addbtitle" name="title" value="${book.title}">
			</div>
		</div>
		<div class="form-row">

			<div class="form-group col-md-6">
				<label for="edition">Edition:</label> <input class="form-control"
					id="edition" name="edition" value="${book.edition==null?0:book.edition}" type="number">
			</div>
			<div class="form-group col-md-6">
				<label for="volume">Volume:</label> <input class="form-control"
					id="volume" name="volume" value="${book.volume==null?0:book.volume }" type="number">
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="subject">Subject:</label> <select class="form-control"
					id="subject" name="subjectTitle">
					<c:forEach items="${subjects}" var="subject">
						<option value="${subject.title}">${subject.title}</option>
					</c:forEach>

				</select>
			</div>
			<div class="form-group col-md-6">
				<label for="publisher">Publisher:</label> <input
					class="form-control" id="publisher" name="publisher"
					value="${book.publisher }">
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="publication">Publication Year:</label> <input
					class="form-control" type="number" id="publication"
					name="publicationYear" value="${book.publicationYear==null?2019:book.publicationYear}" type="number">
			</div>
			<div class="form-group col-md-6">
				<label for="publicationplace">Publication Place:</label> <input
					class="form-control" id="publicationplace" name="publicationPlace"
					value="${book.publicationPlace }">
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="pages">Pages:</label> <input class="form-control"
					id="pages" name="pages" value="${book.pages==null?0:book.pages}" type="number">
			</div>
			<div class="form-group col-md-6">
				<label for="dept">Deptt:</label> <select class="form-control"
					id="dept" name="deptCode">
					<c:forEach items="${depts }" var="dept">
						<option value="${dept.code}">${dept.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="billNo">Bill No:</label> <input class="form-control"
					id="billNo" name="billNo">
			</div>
			<div class="form-group col-md-6">
				<label for="billDate">Bill Date:</label> <input class="form-control"
					id="billDate" name="billDate" type="date" placeholder="2018-12-22">
			</div>
		</div>
		<div class="form-row">

			<div class="form-group col-md-6">
				<label for="location">Location:</label> <input class="form-control"
					id="location" name="location">
			</div>
			<div class="form-group col-md-6">
				<label for="Author">Author:</label> <input class="form-control"
					id="Author" value="${book.author}" name="author">
			</div>
		</div>
		<div class="form-row">

			<div class="form-group col-md-6">
				<label for="Vendor">Vendor(Detailed Name):</label> <input
					class="form-control" id="Vendor" name="vendor">
			</div>
			<div class="form-group col-md-6">
				<label for="noOfCopies">No of Copies:</label> <input type="number"
					value="0" class="form-control" id="noOfCopies" name="noOfCopies">
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-12">
				<label for="noOfCopies">Accession No From:</label> <input
					type="number" value="0" class="form-control" id="noOfCopies"
					name="accessionNoStart">
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="image">Image:</label> <input type="file"
					class="form-control " id="src" name="image">
				<hr>

			</div>
			<div class="form-group col-md-6">
				<label for="pdf">PDF file:</label> <input class="form-control"
					type="file" id="pdf" name="pdf">
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<img src="data:image/gif;base64,${image }" class="img-fluid rounded"
					id="target">
			</div>
			<div id="dvd" class="form-group col-md-6">
				<label for="dvd">CD/DVD:</label> <input type="radio" name="dvd"
					value="Yes" checked="checked"><span>Yes</span> <input
					type="radio" name="dvd" value="No"><span>No</span>
			</div>
		</div>

	
		<button type="submit" name="addBook" class="btn btn-primary btn-block">Save</button>
</form>
</div>