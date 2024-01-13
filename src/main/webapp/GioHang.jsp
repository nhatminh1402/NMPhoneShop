<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="./MasterLayout/head.jsp"></jsp:include>
<jsp:include page="./MasterLayout/nav-bar.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fmt:setLocale value="vi_VN" />
<fmt:setTimeZone value="Asia/Ho_Chi_Minh" />

<link rel="stylesheet" href="public/css/ShoppingCart.css" />
<%
boolean shoppingCartScript = true;
%>

<div class="container d-flex justify-content-center">
	<div class="shopping-cart w-50">
		<h1 id="shopping-cart-title" class="text-center">Giỏ hàng của bạn</h1>

		<h1 class="btn-back-to-product">
			<i class="bi bi-chevron-left"></i> <a href="Home">Mua thêm sản
				phẩm khác</a>
		</h1>

		<div>
			<c:choose>
				<c:when test="${empty sessionScope.gioHang.getAll()}">
					<h1 style="text-align: center; font-size: 18px; padding: 20px">GIỎ
						HÀNG CỦA BẠN ĐANG TRỐNG. HÃY CHỌN THÊM SẢN PHẨM ĐỂ MUA NHÉ!</h1>
				</c:when>
				<c:otherwise>
					<!-- Sản phẩm của giỏ hàng -->
					<c:forEach var="sanPham" items="${sessionScope.gioHang.getAll()}">
						<div class="row bg-box-shadow mb-3">
							<div class="col-md-4 p-0 m-0">
								<div style="height: 160px; width: 180px; margin: 0 auto">
									<img class="w-100 h-100 p-0" src="${sanPham.img}" alt="" />
								</div>
							</div>
							<div
								class="col-md-6 product-content d-flex align-items-center ps-0">
								<div>
									<h1>${sanPham.ten_san_pham}</h1>
									<h2 style="font-size: 14px">
										Màu: ${sanPham.mau_sac} - Dung lượng:
										${sanPham.dung_luong_luu_tru} GB <br> RAM: ${sanPham.ram}
									</h2>
									<h1>
										<fmt:formatNumber type="currency" value="${sanPham.gia}" />
									</h1>
								</div>
							</div>
							<div class="col-md-2 product-button d-flex align-items-center">
								<div>
									<div style="padding: 0px; margin-bottom: 0px" class="text-end">
										<a href="GioHang?del-id=${sanPham.ma_chi_tiet_San_pham}"><i
											style="font-size: 18px; color: red" class="bi bi-trash"></i></a>

									</div>
									<div class="d-flex">
										<button data-id="${sanPham.ma_chi_tiet_San_pham}" class="btn-decrease">-</button>
										<input class="number-product"
											style="width: 20px; outline: none; border: none; text-align: center;"
											type="text" readonly value="${sanPham.so_luong_mua}" />
										<button data-id="${sanPham.ma_chi_tiet_San_pham}" class="btn-increase">+</button>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>

		</div>
		<c:if test="${not empty sessionScope.gioHang.getAll()}">
			<div class="mt-4 d-flex justify-content-between">
				<div class="Thanh-tien">
					<h1>Thành tiền</h1>
					<h1 id="sum-price">
						<fmt:formatNumber type="currency"
							value="${ sessionScope.gioHang.Tongtien()}" />
					</h1>
				</div>
				<div>
					<button class="btn-buy">
						<a style="color: #fff" href="DatHang">Mua ngay</a>
					</button>
				</div>
			</div>
		</c:if>
	</div>
</div>

<jsp:include page="./MasterLayout/footer.jsp"></jsp:include>
