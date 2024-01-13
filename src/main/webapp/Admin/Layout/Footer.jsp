<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<footer class="main-footer">
	<strong>&copy; LÊ BÁ NHẬT MINH - <a href="mailto:nhatminhle1402@gmail.com">nhatminhle1402@gmail.com</a><br>
	</strong>Khoa Công nghệ thông tin, trường Đại học Khoa học – Đại học Huế
	<div class="float-right d-none d-sm-inline-block">
		<b>Version</b> 1.0.0
	</div>
</footer>

<!-- Control Sidebar -->
<aside class="control-sidebar control-sidebar-dark">
	<!-- Control sidebar content goes here -->
</aside>
<!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->
<!-- jQuery -->
<script src="public/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="public/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Bootstrap 4 -->
<script src="public/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- overlayScrollbars -->
<script
	src="public/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="public/dist/js/adminlte.js"></script>

<c:if test="${not empty importManageBrandFileJS}">
	<script src="ScriptFileForAdmin/ManageBrand.js"></script>
</c:if>

<c:if test="${not empty importManageProductFileJS}">
	<script type="module" src="ScriptFileForAdmin/ManageProduct.js"></script>
</c:if>

<c:if test="${not empty importProductDetailFileJS}">
	<script type="module" src="ScriptFileForAdmin/ProductDetail.js"></script>
</c:if>

<c:if test="${not empty ThongKeHangTon}">
	<script type="module" src="ScriptFileForAdmin/ThongKeHangTon.js"></script>
</c:if>


</body>
</html>
