<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="./MasterLayout/head.jsp"></jsp:include>
<jsp:include page="./MasterLayout/nav-bar.jsp"></jsp:include>

<style>
.thank-you-message {
	font-size: 24px;
	margin-bottom: 20px;
}

.email-notification {
	font-size: 18px;
	margin-bottom: 20px;
}

.back-to-home {
	display: inline-block;
	padding: 10px 20px;
	font-size: 16px;
	text-decoration: none;
	background-color: #4CAF50;
	color: #fff;
	border-radius: 5px;
}
</style>
<div style="text-align: center; margin-top: 10%" >
	<div style="text-align: center;" class="thank-you-message">
		<p>Cảm ơn bạn đã đặt hàng!</p>
	</div>

	<div class="email-notification">
		<p style="color: red">Chúng tôi đã gửi một email xác nhận đơn hàng đến địa chỉ email
			của bạn.</p>
	</div>

	<a href="Home" class="back-to-home">Trở về trang chủ mua
		hàng</a>
</div>
