<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="vi_VN" />
<fmt:setTimeZone value="Asia/Ho_Chi_Minh" />

<div class="container mt-3 bg-light p-1">
	<div class="row gx-3">
		<!-- Phần menu bên trái -->
		<div class="col-md-2">
			<div class="list-group">
				<c:forEach items="${listBrand}" var="brand">
					<button type="button"
						class="list-group-item list-group-item-action">
						${brand.hang_san_xuat}</button>
				</c:forEach>
				<button type="button" class="list-group-item list-group-item-action">
					...</button>
			</div>
		</div>
		<!-- Kết thúc phần menu -->

		<!-- Phần slider -->
		<div class="col-md">
			<div id="carouselExample" class="carousel slide h-100"
				data-bs-ride="carousel">
				<div class="carousel-inner h-100">
					<div class="carousel-item active h-100">
						<img src="slide/Slide01.png" class="d-block w-100 h-100" alt="..." />
					</div>
					<div class="carousel-item h-100">
						<img src="slide/slide02.png" class="d-block w-100 h-100" alt="..." />
					</div>
					<div class="carousel-item h-100">
						<img src="slide/slide03.png" class="d-block w-100 h-100" alt="..." />
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExample" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExample" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</div>
		<!-- kết thúc slider -->

		<!-- Phần quảng cáo thêm bên phải -->
		<div class="col-md-3">
			<div class="row gy-3">
				<div class="col-12">
					<img class="" style="height: 110px"
						src="slide/m14-right-homepage-th9.png" alt="" />
				</div>
				<div class="col-12">
					<img style="height: 110px" src="slide/ipad-102-th9-001231.png"
						alt="" />
				</div>
				<div class="col-12">
					<img style="height: 110px" src="slide/asus20tuf.jpg" alt="" />
				</div>
			</div>
		</div>
		<!-- Kết thúc quảng cáo -->
	</div>
</div>

<!-- Phần điện thoại mới -->
<div class="container mt-3">
	<div class="d-flex justify-content-between mb-2">
		<h1 class="title-menu-product">Điện thoại mới nhất</h1>
		<span class="all-product-link"><a href="ProductController">Xem tất cả ></a></span>
	</div>
</div>

<div class="container p-0">
	<div class="row gy-4">
		<c:forEach var="product" items="${listProduct}">
			<div class="col-md-2">
				<a href="ChiTietSanPham?product-id=${product.id_san_pham}">
					<div class="product-wrapper">
						<div class="p-1">
							<div class="img-product">
								<img src="${product.link_anh}" alt="" />
							</div>
							<div class="product-content">
								<h1 class="product-name">${product.ten_san_pham}</h1>
								<h1 class="product-price">
									<span style="font-size: 13px; color: black;">Chỉ từ </span>
									<fmt:formatNumber value="${product.price}" type="currency" />
								</h1>
							</div>
						</div>
						<div class="btn-buy-now">
							<button>MUA NGAY</button>
						</div>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>
</div>
