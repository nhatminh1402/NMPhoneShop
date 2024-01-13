<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="MasterLayout/head.jsp"></jsp:include>
<jsp:include page="MasterLayout/nav-bar.jsp"></jsp:include>

<style>
.btn-color {
	background-color: #0e1c36;
	color: #fff;
}

.cardbody-color {
	background-color: #ebf2fa;
}

a {
	text-decoration: none;
}
</style>
<div class="container">
	<div class="row">
		<div class="col-md-6 offset-md-3">
			<div class="card my-5">

				<form action="DangNhap" method="POST" class="card-body cardbody-color p-lg-5">

					<div class="text-center">
						<h2 class="text-center text-dark mt-5"
							style="padding-bottom: 10px">ĐĂNG NHẬP</h2>
					</div>

					<div class="mb-3">
						<input name="email" type="email" class="form-control"
							placeholder="Email" required="required">
					</div>
					<div class="mb-3">
						<input name="password" type="password" class="form-control"
							id="password" placeholder="password" required="required">
					</div>
					<div class="text-center">
						<h1 style="text-align: center; font-size: 20px; color: red">${errLogin}</h1>
						<button type="submit" class="btn btn-color px-5 mb-5 w-100">Login</button>
					</div>
					<div id="emailHelp" class="form-text text-center mb-5 text-dark">
						CHƯA CÓ TÀI KHOẢN? <a href="DangKy" class="text-dark fw-bold"> Đăng
							ký ngay</a>
					</div>
				</form>
			</div>

		</div>
	</div>
</div>

<jsp:include page="./MasterLayout/footer.jsp"></jsp:include>