<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="./MasterLayout/head.jsp"></jsp:include>
<jsp:include page="./MasterLayout/nav-bar.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<div class="col-md-4"></div>
		<div
			style="box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px; margin-top: 20px; padding: 10px; border-radius: 5px"
			class="col-md-4 ">
			<h1 style="text-align: center; font-size: 30px; padding: 5px">ĐĂNG
				KÝ</h1>
			<form action="DangKy" method="POST">
				<div class="mb-3">
					<label class="form-label">Email address</label> <input type="email"
						name="email"
						class="form-control ${not empty errEmail ? 'is-invalid' : ''}"
						required="required">
					<div class="invalid-feedback">${errEmail}</div>
				</div>
				<div class="mb-3">
					<label class="form-label">Họ tên</label> <input type="text"
						class="form-control" name="hoTen" required="required">
				</div>
				<div class="mb-3">
					<label class="form-label">Số điện thoại</label> <input
						type="number" name="soDienThoai" class="form-control"
						required="required">
				</div>
				<div class="mb-3">
					<label class="form-label">Mật khẩu</label> <input type="password"
						name="password" class="form-control ${not empty errPass ? 'is-invalid' : ''}">
						<div class="invalid-feedback">${errPass}</div>
				</div>
				<div class="mb-3">
					<label for="exampleInputPassword1" class="form-label">Xác
						nhận mật khẩu</label> <input name="xacNhanMatKhau" type="password"
						class="form-control ${not empty errPass ? 'is-invalid' : ''}">
						<div class="invalid-feedback">${errPass}</div>
				</div>
				<div class="d-flex justify-content-center">
					<button type="submit" class="btn btn-primary">Đăng ký</button>
				</div>
			</form>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>

<jsp:include page="./MasterLayout/footer.jsp"></jsp:include>