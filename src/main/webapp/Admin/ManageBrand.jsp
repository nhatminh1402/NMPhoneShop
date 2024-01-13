<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/Admin/Layout/Head.jsp"%>
<%@ include file="/Admin/Layout/nav.jsp"%>
<%@ include file="/Admin/Layout/SideBar.jsp"%>

<!-- Khai báo cờ giúp định vị đúng file js cần dùng và include --!>
<c:set var="importManageBrandFileJS" value="true"></c:set>

<div class="content-wrapper">
	<!-- Content Header (Page header) -->
<div class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#">Home</a></li>
					<li style="font-weight: bold; color: black;"
						class="breadcrumb-item active">Quản lý hãng sản xuất</li>
				</ol>
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container-fluid -->
</div>
<!-- /.content-header -->

<!-- Main content -->
<section class="content">
	<div class="container-fluid">
		<c:if test="${not empty ListBrand}">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div id="content-manage-brand-head" class="card-header">
							<button type="button" data-toggle="modal" data-toggle="modal"
								data-target="#staticBackdrop"
								class="brand-button btn-add-brand-modal mb-2 btn btn-success">
								<i class="bi bi-plus-square"></i> THÊM
							</button>
							<div class="card-tools">
								<div class="input-group input-group-sm" style="width: 250px;">
								</div>
							</div>
						</div>
						<div id="content-manage-brand">
							<!-- /.card-header -->
							<div id="data-table-brand" class="card-body table-responsive p-0">
								<table class="table table-hover text-nowrap">
									<thead>
										<tr>
											<th>ID</th>
											<th>HÃNG SẢN XUẤT</th>
											<th>HÀNH ĐỘNG</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="brand" items="${ListBrand}">
											<tr>
												<td>${brand.id_hang}</td>
												<td>${brand.hang_san_xuat}</td>
												<td data-id="${brand.id_hang}">
													<button type="button"
														class="brand-button brand-button-update btn btn-primary">
														<i class="bi bi-pencil-square"></i> CHỈNH SỬA
													</button>
													<button type="button"
														class="brand-button-delete btn btn-danger" data-id="37">
														<i class="bi bi-trash3"></i> XOÁ
													</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.card-body -->
							<div class="card-footer clearfix">
								<ul class="pagination pagination-sm m-0 float-right">
									<c:forEach var="pageNumber" begin="1" end="${totalPage}">
										<c:if test="${pageNumber == 1}">
											<li data-page="${pageNumber}" class="page-item active"><a
												style="font-size: 17px" class="page-link">${pageNumber}</a>
											</li>
										</c:if>
										<c:if test="${pageNumber != 1}">
											<li data-page="${pageNumber}" class="page-item"><a
												style="font-size: 17px" class="page-link">${pageNumber}</a>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
					<!-- /.card -->
				</div>
			</div>
		</c:if>
	</div>
	<!-- /.container-fluid -->
</section>
<!-- /.content -->
</div>

<!--  Modal pop-up thêm hãng sản xuất -->
<div class="modal-form-add-brand modal fade" id="staticBackdrop"
	data-backdrop="static" data-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="staticBackdropLabel">THÊM MỚI HÃNG
					ĐIỆN THOẠI</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form>
					<div class="form-group">
						<label for="">Tên hãng</label> <input type="text"
							class="form-control" id="brand-name"> <small
							style="color: red !important;" id="brand-name-error"
							class="form-text text-muted"></small>
					</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn-add-brand btn btn-primary">Add</button>
			</div>
			</form>
		</div>
	</div>
</div>

<!--Modal update hãng sản xuất-->

<!-- Modal -->
<div class="modal fade modal-update-brand" id="exampleModalCenter"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLongTitle">CHỈNH SỬA
					THÔNG TIN</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<input id="brand-id-update" type="hidden">
				<div class="form-group">
					<label>Hãng sản xuất</label> <input id="brand-name-update"
						type="text" class="form-control">
					<div class="invalid-feedback"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button id="btn-update-brand" type="button" class="btn btn-primary">Save
					changes</button>
			</div>
		</div>
	</div>
</div>

<%@ include file="/Admin/Layout/Footer.jsp"%>
