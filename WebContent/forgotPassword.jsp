<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Title</title>

<style>
:root { -
	-input-padding-x: 1.5rem; -
	-input-padding-y: .75rem;
}

body {
	background: #007bff;
	background: linear-gradient(to right, #0062E6, #33AEFF);
}

.card-signin {
	border: 0;
	border-radius: 1rem;
	box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
}

.card-signin .card-title {
	margin-bottom: 2rem;
	font-weight: 300;
	font-size: 1.5rem;
}

.card-signin .card-body {
	padding: 2rem;
}

.form-signin {
	width: 100%;
}

.form-signin .btn {
	font-size: 80%;
	border-radius: 5rem;
	letter-spacing: .1rem;
	font-weight: bold;
	padding: 1rem;
	transition: all 0.2s;
}

.form-label-group {
	position: relative;
	margin-bottom: 1rem;
}

.form-label-group input {
	height: auto;
	border-radius: 2rem;
}

.form-label-group>input, .form-label-group>label {
	padding: var(- -input-padding-y) var(- -input-padding-x);
}

.form-label-group>label {
	position: absolute;
	top: 0;
	left: 0;
	display: block;
	width: 100%;
	margin-bottom: 0;
	/* Override default `<label>` margin */
	line-height: 1.5;
	color: #495057;
	border: 1px solid transparent;
	border-radius: .25rem;
	transition: all .1s ease-in-out;
}

.form-label-group input::-webkit-input-placeholder {
	color: transparent;
}

.form-label-group input:-ms-input-placeholder {
	color: transparent;
}

.form-label-group input::-ms-input-placeholder {
	color: transparent;
}

.form-label-group input::-moz-placeholder {
	color: transparent;
}

.form-label-group input::placeholder {
	color: transparent;
}

.form-label-group input:not (:placeholder-shown ) {
	padding-top: calc(var(- -input-padding-y)+ var(- -input-padding-y)* (2/3));
	padding-bottom: calc(var(- -input-padding-y)/3);
}

.form-label-group input:not (:placeholder-shown )~label {
	padding-top: calc(var(- -input-padding-y)/3);
	padding-bottom: calc(var(- -input-padding-y)/3);
	font-size: 12px;
	color: #777;
}

.btn-google {
	color: white;
	background-color: #ea4335;
}

.btn-facebook {
	color: white;
	background-color: #3b5998;
}

</style>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="css/all.css">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h5 class="card-title text-center">FORGOT PASSWORD</h5>
						<form method="post" action="forgotPassword">
							<div>
								<input type="email" class="form-control p-2"
									placeholder="Email address" required name="email" style="border-radius: 30px " value="${param.email }">
								<button
									class="btn btn-lg btn-primary btn-block text-uppercase mt-4 mb-4"
									style="border-radius: 30px " type="submit" name="vfyCode">Send Verfication Code</button>
							</div>
							<div>
								<input type="number" class="form-control mt-4 mb-4 p-2"
									placeholder="Verification Code" name="verfCode" style="border-radius: 30px ">
							</div>
							<div>
								<input type="password" class="form-control mt-4 mb-4 p-2"
									placeholder="Password" name="password" style="border-radius: 30px ">
							</div>
							<div>
								<input type="password" class="form-control mt-4 mb-4 p-2"
									placeholder="Confirm Password" name="cPassword" style="border-radius: 30px ">
							</div>
							<div class="alert alert-warning" role="alert">${param.msg}</div>
							<button class="btn btn-lg btn-primary btn-block text-uppercase"
								type="submit" style="border-radius: 30px " name="changePassword">Change Password</button>
							<a class="nav-link" href="login">Already Have Account</a>
							<hr class="my-4">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>