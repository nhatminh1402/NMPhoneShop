<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="vi_VN" />
<fmt:setTimeZone value="Asia/Ho_Chi_Minh" />

<jsp:include page="MasterLayout/head.jsp"></jsp:include>
<jsp:include page="MasterLayout/nav-bar.jsp"></jsp:include>

<c:set var="CheckedFilejs" value="true" scope="page" />

<link rel="stylesheet" href="public/css/productDetail.css">

<div class="container mt-5">
	<div class="row">
		<!-- Phần filter lọc sản phẩm -->
		<div class="col-md-3">
			<div class="filter">

				<div class="brand-filter">
					<h4>Hãng sản xuất</h4>
					<div class="form-check form-check-inline">
						<input class="form-check-input check-all" name="brand"
							type="checkbox" form="FormSearch" value="all"
							${empty BrandsCheck ? 'checked':''} /> <label
							class="form-check-label " for="inlineCheckbox1">Tất cả</label>
					</div>

					<c:forEach var="sanPham" items="${listBrand}">
						<div class="form-check form-check-inline">
							<input form="FormSearch" class="form-check-input" name="brand"
								type="checkbox" value="${sanPham.id_hang}" /> <label
								class="form-check-label" for="inlineCheckbox1">${sanPham.hang_san_xuat}</label>
						</div>
					</c:forEach>

				</div>

				<div class="price-filter mt-2">
					<h4>Mức giá</h4>
					<form action="">
						<div class="form-check">
							<input form="FormSearch" class="form-check-input" name="price"
								type="checkbox" value="all" checked /> <label
								class="form-check-label" for="inlineCheckbox1">Tất cả</label>

						</div>
						<div class="form-check">
							<input form="FormSearch" class="form-check-input" name="price"
								type="checkbox" value="0-2000000" /> <label
								class="form-check-label" for="inlineCheckbox2">Dưới 2
								triệu</label>
						</div>

						<div class="form-check">
							<input form="FormSearch" class="form-check-input" name="price"
								type="checkbox" value="2000000-4000000" /> <label
								class="form-check-label" for="inlineCheckbox1">Từ 2 - 4
								triệu</label>
						</div>
						<div class="form-check">
							<input form="FormSearch" class="form-check-input" name="price"
								type="checkbox" value="4000000-7000000" /> <label
								class="form-check-label" for="inlineCheckbox2">Từ 4 - 7
								triệu</label>
						</div>
						<div class="form-check">
							<input form="FormSearch" class="form-check-input" name="price"
								type="checkbox" value="7000000-13000000" /> <label
								class="form-check-label" for="inlineCheckbox1">Từ 7 - 13
								triệu</label>
						</div>
						<div class="form-check">
							<input form="FormSearch" class="form-check-input" name="price"
								type="checkbox" value="13000000-500000000" /> <label
								class="form-check-label" for="inlineCheckbox2">Trên 13
								triệu</label>
						</div>
				</div>

				<div class="operation-filter mt-2">
					<h4>Hệ điều hành</h4>
					<div class="form-check">
						<input form="FormSearch" class="form-check-input check-all"
							name="operation" type="checkbox" id="" value="all"
							checked="checked" /> <label class="form-check-label"
							for="inlineCheckbox1">Tất cả</label>
					</div>
					<div class="form-check">
						<input form="FormSearch" class="form-check-input" name="operation"
							type="checkbox" id="" value="IOS" /> <label
							class="form-check-label" for="inlineCheckbox1">IOS</label>
					</div>
					<div class="form-check">
						<input form="FormSearch" class="form-check-input" name="operation"
							type="checkbox" id="" value="ANDROID" /> <label
							class="form-check-label" for="inlineCheckbox1">ANDROID</label>
					</div>
					<button form="FormSearch"
						style="text-align: center; margin-top: 10px; margin-left: 30%"
						type="submit" class="btn btn-primary">LỌC</button>
				</div>
			</div>
		</div>

		<div class="col-md-9 ">
			<!-- Phần ảnh banner brand -->
			<div class="row brand-bar-img">
				<div class="box-brand col-md-2">
					<img src="smallslide/637340490193124614_iPhone-Apple@2x.jpg.webp"
						alt="" />
				</div>
				<div class="box-brand col-md-2">
					<img src="smallslide/637340490904217021_Samsung@2x.jpg.webp" alt="" />
				</div>
				<div class="box-brand col-md-2">
					<img src="smallslide/637340491304997311_Oppo@2x.jpg.webp" alt="" />
				</div>
				<div class="box-brand col-md-2">
					<img src="smallslide/637340491898745716_Realme@2x.jpg.webp" alt="" />
				</div>
				<div class="box-brand col-md-2">
					<img src="smallslide/637340493755614653_Nokia@2x.jpg.webp" alt="" />
				</div>
				<div class="box-brand col-md-2">
					<img src="smallslide/637340491898901930_Masstel@2x.jpg.webp" alt="" />
				</div>
			</div>
			<!-- Kết thúc-->


			<div class="container p-0">
				<div class="row mt-2 gy-4">
					<!--  			<div class="col-md-3">
						<div class="product-wrapper">
							<div class="p-1">
								<div class="img-product">
									<img src="samsung-galaxy-s23-ultra.webp" alt="" />
								</div>
								<div class="product-content">
									<h1 class="product-name">Iphone 15 128GB | Chính hãng VN/A</h1>
									<h1 class="product-price">7.990.000đ</h1>
								</div>
							</div>

							<div class="btn-buy-now">
								<button>MUA NGAY</button>
							</div>
						</div>
					</div>
-->
					<c:if test="${not empty listProduct}">
						<c:forEach var="product" items="${listProduct}">
							<div class="col-md-3">
								<a href="ChiTietSanPham?product-id=${product.id_san_pham}">
									<div class="product-wrapper">
										<div class="p-1">
											<div class="img-product">
												<img src="${product.link_anh}" alt="" />
											</div>
											<div class="product-content">
												<h1 class="product-name">${product.ten_san_pham}</h1>
												<h1 class="product-price">
													<span style="font-size: 13px; color: black;">Chỉ từ
													</span>
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
						<!--  -->
						<div class="col-md-12 d-flex justify-content-end">
							<nav aria-label="Page navigation example ">
								<ul class="pagination">
									<c:forEach var="i" begin="1" end="${ListPage}">
										<li class="page-item"><a class="page-link ${currentPage == i ? 'active' : '' }" href="ProductController?pageNumber=${i}&${PaginationLink}">${i}</a></li>
									</c:forEach>
								</ul>
							</nav>
						</div>
					</c:if>

					<c:if test="${empty listProduct}">
						<h1 style="font-size: 20px; text-align: center;">KHÔNG CÓ SẢN
							PHẨM ĐỂ HIỂN THỊ</h1>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="MasterLayout/footer.jsp"></jsp:include>
