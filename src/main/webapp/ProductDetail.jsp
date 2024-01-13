<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="MasterLayout/head.jsp"></jsp:include>
<jsp:include page="MasterLayout/nav-bar.jsp"></jsp:include>

<link rel="stylesheet" href="public/css/productDetail.css">
<%
	boolean productScript = true;
%>
<c:set var="productDeatil" value="true"></c:set>
<div class="container mt-3">
	<div class="row">
		<!-- Tiêu đề sản phẩm -->
		<div class="product-title col-md-12">
			<h1><!-- Tên của sản phẩm này -->
				${productName.ten_san_pham}
			</h1>
		</div>
		<!-- Kết thúc tiêu đề sản phẩm -->

		<!-- Phần dành cho slide của sản phẩm -->
		<div class="col-md-7 mt-2 bg-box-shadow p-2">
			<div class="list-img-product">
				<div id="carouselExampleIndicators" class="carousel slide"
					data-bs-ride="true">
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="0" class="active" aria-current="true"
							aria-label="Slide 1"></button>
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="1" aria-label="Slide 2"></button>
						<button type="button" data-bs-target="#carouselExampleIndicators"
							data-bs-slide-to="2" aria-label="Slide 3"></button>
					</div>
					<div class="carousel-inner">
						<c:forEach items="${listSlideProduct}" var="item">
							<div class="carousel-item active">
								<img src="${item.link_anh}" class="d-block w-100" alt="...">
							</div>
						</c:forEach>
					</div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
		</div>
		<!-- Kết thúc slide sản phẩm -->

		<div class="col-md-5 mt-2 ps-3 ">
			<div class="row pb-3 bg-box-shadow">
				<!-- Start choose color -->
				<div class="product-color col-md-12">
					<div id="price-title">
						<h1 id="price"
							style="font-size: 16px; padding: 10px; text-align: center;">VUI
							LÒNG CHỌN CẤU HÌNH ĐỂ BIẾT GIÁ CHI TIẾT</h1>
					</div>
					<div class="list-button">
						<h5 style="font-size: 16px">Chọn màu</h5>
						<c:forEach items="${listColor}" var="item">
							<button data-value="${item}" class="btn-color">${item}</button>
						</c:forEach>
					</div>
				</div>

				<div id="storage-insertion"></div>
				<div id="ram-insertion"></div>
				<div id="buy-insertion"></div>
			</div>
		</div>

	</div>
</div>

<div class="container mt-3">
	<div class="row">
		<div class="col-md-12 bg-box-shadow p-3">
			<!--  Nội dung của sản phẩm này -->
			${product_describe}
			
		</div>
<!--  		<div class="col-md-5">
			<table class="table table-hover bg-box-shadow table-bordered">
				<tr>
					<th class="text-center" colspan="2">THÔNG TIN CẤU HÌNH SẢN
						PHẨM</th>
				</tr>
				<tr>
					<td>RAM</td>
					<td>8 GB</td>
				</tr>
				<tr>
					<td>Dung lượng lưu trữ</td>
					<td>256 GB</td>
				</tr>
				<tr>
					<td>Camera trước</td>
					<td>12 MB</td>
				</tr>
				<tr>
					<td>Camera sau</td>
					<td>12 MB</td>
				</tr>
				<tr>
					<td>Màu sắc</td>
					<td>Cam</td>
				</tr>
				<tr>
					<td>Màn hình</td>
					<td>6.7 inch</td>
				</tr>
				<tr>
					<td>Chip</td>
					<td>A17 Pro</td>
				</tr>
			</table>
		</div>-->
	</div>
	
</div>
<jsp:include page="MasterLayout/footer.jsp"></jsp:include>
