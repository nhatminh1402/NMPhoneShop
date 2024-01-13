<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/Admin/Layout/Head.jsp"%>
<%@ include file="/Admin/Layout/nav.jsp"%>
<%@ include file="/Admin/Layout/SideBar.jsp"%>

<c:set var="ThongKeHangTon" value="true"></c:set>

<div class="content-wrapper">
	<div style="background-color: #fff" class="row">
		<div class="col-md-12  d-flex justify-content-center">
			<div id="myChart"
				style="width: 100%; height: 500px;"></div>
		</div>
	</div>
</div>
<!-- /.content-wrapper -->
<%@ include file="/Admin/Layout/Footer.jsp"%>