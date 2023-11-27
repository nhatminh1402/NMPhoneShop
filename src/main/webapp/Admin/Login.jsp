<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Admin Login</title>
<base href="http://localhost:8080/NMPhoneShop/">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="public/plugins/fontawesome-free/css/all.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet"
	href="public/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="public/dist/css/adminlte.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body class="hold-transition login-page">
	<div class="login-box">
		<!-- /.login-logo -->
		<div class="card card-outline card-primary">
			<div class="card-header text-center">
				<a href="public/index2.html" class="h1"><b>NM</b>shop</a>
			</div>
			<div class="card-body">
				<p class="login-box-msg">Đăng nhập để sử dụng các chức năng</p>
				<form action="Admin/Dang-nhap" method="post">
					<div class="input-group mb-3">
						<input type="email" name="email"
							class="form-control ${empty emailErr ? '' : 'is-invalid'}"
							placeholder="Email" value="${email}">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-envelope"></span>
							</div>
						</div>
						<div class="invalid-feedback">
							<c:if test="${not empty emailErr}">
								${emailErr}
							</c:if>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" name="password" class="form-control ${empty passErr ? '' : 'is-invalid'}"
							placeholder="Password" value="${pass}">
						<div class="input-group-append">
							<div class="input-group-text">
								<span class="fas fa-lock"></span>
							</div>
						</div>
						<div class="invalid-feedback">
							<c:if test="${not empty passErr}">
								${passErr}
							</c:if>
						</div>
					</div>
					<div class="row">
						<div class="col-8">
							<div class="icheck-primary">
								<input type="checkbox" name="remember" value="true"
									id="remember"> <label for="remember"> Remember
									Me </label>
							</div>
						</div>
						<!-- /.col -->
					</div>


					<div class="social-auth-links text-center mt-2 mb-3">
						<button type="submit" name="btn-submit" value="submit"
							class="btn btn-block btn-primary">Đăng nhập</button>
					</div>
				</form>
			</div>
			<!-- /.card-body -->
		</div>
		<!-- /.card -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery -->
	<script src="public/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script src="public/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="public/dist/js/adminlte.min.js"></script>
	<script type="text/javascript" src="ScriptFileForAdmin/AdminLogin.js"></script>
</body>
</html>