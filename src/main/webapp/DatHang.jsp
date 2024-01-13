<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="vi_VN" />
<fmt:setTimeZone value="Asia/Ho_Chi_Minh" />

<jsp:include page="./MasterLayout/head.jsp"></jsp:include>
<jsp:include page="./MasterLayout/nav-bar.jsp"></jsp:include>
<!-- Phần tiêu đề -->
<div class="container mt-3">
	<div class="container-wrapper">
		<div class="infor-custor-header d-flex justify-content-between">
			<div class="back-to-cart">
				<a class="btn-back-to-cart" href=""> <i
					class="bi bi-chevron-left"></i>
				</a>
			</div>
			<div>
				<h1 style="font-size: 24px" class="title-info">THÔNG TIN ĐẶT
					HÀNG</h1>
			</div>
			<div style="visibility: hidden">NONE</div>
		</div>
	</div>
</div>

<div class="container mt-4">
	<div class="container-wrapper">
		<div class="row">
			<div class="col-md-12">
				<h1 style="font-size: 16px">Thông tin khách hàng</h1>
			</div>
		</div>
		<form action="DatHang" method="get">
			<div class="row gy-3">
				<div class="col-md-6">
					<input name="hoTen" type="text" class="form-control"
						value="${sessionScope.KhachHang.ten_Khach_Hang}" placeholder="Họ và tên (bắt buộc)" required="required" />
				</div>
				<div class="col-md-6">
					<input type="text" class="form-control" name="sdt"
						placeholder="Số điện thoại (bắt buộc)" value="${sessionScope.KhachHang.soDienThoai}"/>
				</div>
				<div class="col-md-12">
					<input type="text" class="form-control" value="${sessionScope.KhachHang.email}" placeholder="Email" name="email"/>
				</div>
			</div>
			<div class="row mt-4">
				<div class="col-md-12">
					<h1 style="font-size: 16px">Địa chỉ nhận hàng</h1>
				</div>
			</div>
			<div class="col-md-12 mb-2">
				<input type="text" class="form-control"
					placeholder="Nhập địa chỉ nhận hàng " name="diaChiNhanHang" required="required" />
			</div>
			<div class="row gy-3">
				<div class="col-md-12">
					<h1 style="font-size: 16px">Để lại lưu ý / lời nhắn</h1>
					<textarea name="ghichu" class="form-control" style="height: 100px"></textarea>
				</div>
			</div>
			<div class="total d-flex justify-content-between mt-4">
				<h1 style="font-size: 20px">Tổng tiền:</h1>
				<h1 style="font-size: 18px; color: #d70018">
					<fmt:formatNumber type="currency"
							value="${ sessionScope.gioHang.Tongtien()}" />
				</h1>
			</div>
			<div class="mt-1">
				<button type="submit" style="color: red"
					class="w-100 btn-continue-pay btn-primary">ĐẶT HÀNG</button>
			</div>
		</form>
	</div>
</div>

<jsp:include page="./MasterLayout/footer.jsp"></jsp:include>