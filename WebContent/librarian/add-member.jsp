<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:if test="${param.msg!=null}">
		<div class="alert alert-warning">${param.msg}</div>
	</c:if>
<div class="card">
	<div class="card-header bg-info text-white text-center h3">
		Member Registration</div>
	<div class="card-body">
		<form class="form-inline my-2 my-lg-0" action="add-member"
			method="get">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Enter Member Id" aria-label="Search" name="memberId">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
		<form action="add-member" method="post" enctype="multipart/form-data">

			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Member Id:</label> <input class="form-control"
						id="addbisbn" name="memberId" value="${member.memberId }">

				</div>

				<div class="form-group col-md-6">
					<label for="addbtitle">Name:</label> <input class="form-control"
						type="text" id="addbtitle" name="name" value="${member.name }">
				</div>
			</div>
			<div class="form-row">

				<div class="form-group col-md-6">
					<label for="edition">Father Name:</label> <input
						class="form-control" id="edition" name="fatherName"
						value="${member.fatherName}">
				</div>
				<div class="form-group col-md-6">
					<label for="volume">Address:</label> <input class="form-control"
						id="volume" name="address" value="${member.address }">
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Blood Group:</label> <select class="form-control" name="bloodGroup">
						<c:forEach items="${bloodGroups}" var="bloodGroup">
							<c:if test="${ bloodGroup.value.equals(member.bloodGroup.value)}">
								<option value="${bloodGroup.value}" selected="selected">${ bloodGroup.value}</option>
							</c:if >
							<c:if test="${ !bloodGroup.value.equals(member.bloodGroup.value)}">
								<option value="${bloodGroup.value}">${bloodGroup.value}</option>
							</c:if>
							
						</c:forEach>
					</select>

				</div>
				<div class="form-group col-md-3">
					<label>Gender:</label> <br> 
					<label class="radio-inline">
						<c:if test="${member.gender.toString().equals('Male') }">
							<input type="radio" name="gender" value="Male" checked>Male
						</c:if>
						<c:if test="${!member.gender.toString().equals('Male') }">
							<input type="radio" name="gender" value="Male">Male
						</c:if>
					</label> 
					<label class="radio-inline"> 
						<c:if test="${member.gender.toString().equals('Female') }">
							<input type="radio" name="gender" value="Female" checked>Female
						</c:if>
						<c:if test="${!member.gender.toString().equals('Female') }">
							<input type="radio" name="gender" value="Female">Female
						</c:if>
					</label>

				</div>
				<div class="form-group col-md-3">
					<label for="location">Status:</label> <br> <label
						class="radio-inline">
						<c:if test="${member.status.toString().equals('Active') }">
							<input name="status" type="radio" value="Active" checked="checked">Active
						</c:if>
						<c:if test="${!member.status.toString().equals('Active') }">
							<input name="status" type="radio" value="Active">Active
						</c:if>
						</label>
					<label class="radio-inline">
						<c:if test="${member.status.toString().equals('Inactive') }">
							<input name="status" type="radio" value="Inactive" checked="checked">Inactive
						</c:if>
						<c:if test="${!member.status.toString().equals('Inactive') }">
							<input name="status" type="radio" value="Inactive">Inactive
						</c:if>
					</label>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label>Department:</label> 
					<select name="dept" class="form-control">
						<c:forEach items="${depts}" var="dept">
							<c:if test="${ dept.name.equals(member.dept.name)}">
								<option value="${ dept.code}" selected="selected">${ dept.name}</option>
							</c:if>
							<c:if test="${ !dept.name.equals(member.dept.name)}">
								<option value="${ dept.code}">${ dept.name}</option>
							</c:if>
						</c:forEach>
						
					</select>

				</div>
				<div class="form-group col-md-6">
					<label>Upload Image:<input
						class="form-control form-control-file" id="publicationplace"
						name="pic"
						type="file">
					</label>
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="pages">CNIC/BF/PP No:</label> <input
						class="form-control" id="pages" name="cnicBfPpNo"
						value="${member.cnicBfPpNo}">
				</div>
				<div class="form-group col-md-6">
					<label>Email:</label><input class="form-control" name="email"
						value="${member.email }">
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="billNo">Cell No:</label> <input class="form-control"
						id="billNo" name="cell" value="${member.cell }">
				</div>
				<div class="form-group col-md-6">
					<label for="billDate">Date Of Birth:</label> <input
						class="form-control" id="billDate" name="dob" type="date"
						placeholder="2018-12-22" value="${member.dob}">
				</div>
			</div>
			<div class="form-row"></div>
			<button type="submit" name="add-member"
				class="btn btn-outline-info btn-block">Save</button>
		</form>
	</div>
</div>