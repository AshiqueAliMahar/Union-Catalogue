<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${members}" var="member" varStatus="memberStatus">
	<div class="card">
		<div class="card-header bg-info text-white h3">
			${member.name}
			<div class="btn-group float-right" role="group" aria-label="Basic example">
				<a class="btn btn-light "
				href="add-member?memberId=${member.memberId}">Edit</a>
				<a  class="btn btn-light" href='del-member?email=${member.email}'>Delete</a>
			</div>
		</div>
		<div class="card-body">
			<table class="table table-responsive-md table-hover table-striped">
				<tr>
					<td>Member Id</td>
					<td>${member.memberId }</td>

					<td rowspan="3" colspan="2" class="text-center"><img
						src="data:image/gif;base64,${membersPic.get(memberStatus.index)}"
						class="img-fluid"></td>

				</tr>
				<tr>
					<td>Name</td>
					<td>${member.name }</td>

				</tr>
				<tr>
					<td>Father Name</td>
					<td>${member.fatherName}</td>
				</tr>
				<tr>
					<td>Address</td>
					<td>${member.address }</td>
					<td>Blood Group</td>
					<td>${member.bloodGroup.value }</td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td>${member.gender }</td>
					<td>Department</td>
					<td>${member.dept.name }</td>
				</tr>
				<tr>
					<td>CNIC/BF/PP No</td>
					<td>${member.cnicBfPpNo }</td>
					<td>Email</td>
					<td>${member.email }</td>
				</tr>
				<tr>
					<td>Contact</td>
					<td>${member.cell }</td>
					<td>Date Of Birth</td>
					<td>${member.dob }</td>
				</tr>
				<tr>
					<td>Status</td>
					<td>${member.status }</td>
					<td>Password</td>
					<td>${member.password }</td>
				</tr>
				<tr>
					<td>Post</td>
					<td>${member.post }</td>
					<td>College</td>
					<td>${member.college.name}</td>
				</tr>
			</table>
		</div>
	</div>
</c:forEach>