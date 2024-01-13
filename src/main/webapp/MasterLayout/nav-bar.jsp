<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Start navbar -->
<div id="nav-bg" class="font-nav container-fluid">
	<nav class="p-0 navbar navbar-expand-lg">
		<div class="container">
			<a class="navbar-brand" href="http://localhost:8080/NMPhoneShop/Home">NMShop</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-between"
				id="navbarSupportedContent">
				<ul class="navbar-nav mb-2 mb-lg-0 align-items-center">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="http://localhost:8080/NMPhoneShop/Home">TRANG
							CHỦ</a></li>
					<li class="nav-item"><a class="nav-link"
						href="ProductController">SẢN PHẨM</a></li>
				</ul>
				
				<form action="ProductController" method="get" id="FormSearch">
					<input form="FormSearch" id="search-form" class="form-control me-2"
						type="search" name="searchKey" placeholder="Search" />
				</form>
				
				<ul class="navbar-nav mb-2 mb-lg-0 align-items-center">
					<li class="nav-item align-items-center"><a
						class="nav-link active text-center" aria-current="page">MIỄN
							PHÍ VẬN CHUYỂN</a></li>
					<li class="nav-item align-items-cente position-relativer"><a
						class="nav-link active" aria-current="page" href="#"> <i
							style="font-size: 30px; margin-left: 30%" class="bi bi-bag"></i>
					</a></li>

					<li class="nav-item"><a class="text-center nav-link active"
						aria-current="page" href="GioHang"> GIỎ HÀNG <span></span>
					</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#"> <i style="font-size: 30px"
							class="bi bi-person-circle"></i>
					</a></li>
					<li class="nav-item"><c:if
							test="${empty sessionScope.KhachHang}">
							<a style="padding: 0" class="nav-link active" aria-current="page"
								href="DangNhap"> ĐĂNG NHẬP</a>
						</c:if> <c:if test="${not empty sessionScope.KhachHang}">
							<div class="dropdown">
								<button style="color: #fff" class="btn dropdown-toggle"
									type="button" id="dropdownMenuButton1"
									data-bs-toggle="dropdown" aria-expanded="false">
									${sessionScope.KhachHang.ten_Khach_Hang}</button>
								<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
									<li><a class="dropdown-item" href="DangNhap">Lịch sử
											mua hàng</a></li>
									<li><a class="dropdown-item" href="DangXuat">Đăng xuất</a></li>
								</ul>
							</div>
						</c:if></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<!-- End navbar -->