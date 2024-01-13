<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Phần footer -->
<div class="container-fluid footer mt-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<ul>
					<li style="font-weight: bold">HỖ TRỢ KHÁCH HÀNG</li>
					<li>Tích điểm quà tặng</li>
					<li>Lịch sử mua hàng</li>
					<li>Tìm hiểu về mua trả góp</li>
					<li>Chính sách bảo hành</li>
				</ul>
			</div>
			<div class="col-md-3">
				<ul>
					<li style="font-weight: bold">CHÍNH SÁCH MUA HÀNG</li>
					<li>Quy định chung</li>
					<li>Chính sách bảo mật thông tin</li>
					<li>Chính sách đổi trả và hoàn tiền</li>

				</ul>
			</div>
			<div class="col-md-3">
				<ul>
					<li style="font-weight: bold">Giới thiệu công ty</li>
					<li>Tuyển dụng</li>
					<li>Gửi góp ý, khiếu nại</li>
					<li>Tìm siêu thị</li>
				</ul>
			</div>
			<div class="col-md-3">
				<ul>
					<li style="font-weight: bold">Tổng đài hỗ trợ (Miễn phí gọi)</li>
					<li>Gọi mua hàng ngay: 1900-xxx</li>
					<li>Gọi khiếu nại: 1900.xxxx</li>
					<li>Gọi bảo hành: 1900.xxx</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- Kết thúc footer -->

<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
	integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
	crossorigin="anonymous"></script>
<c:if test="${not empty productDeatil}">
	<script src="ScriptFileForUser/ProductDetail.js"></script>
</c:if>
<c:if test="${not empty productScript}">
	<script src="ScriptFileForUser/Product.js"></script>
</c:if>
<c:if test="${not empty shoppingCartScript}">
	<script src="ScriptFileForUser/GioHang.js"></script>
</c:if>

<c:if test="${not empty CheckedFilejs}">
	<script src="ScriptFileForUser/CheckedFilejs.js"></script>
</c:if>
</body>
</html>
