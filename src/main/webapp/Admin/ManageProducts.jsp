<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/Admin/Layout/Head.jsp"%>
<%@ include file="/Admin/Layout/nav.jsp"%>
<%@ include file="/Admin/Layout/SideBar.jsp"%>


<!-- Khai báo cờ giúp định vị đúng file js cần dùng và include --!>
<c:set var="importManageProductFileJS" value="true"></c:set>

<div class="content-wrapper">
	<!-- Content Header (Page header) -->
<div class="content-header">
	<div class="container-fluid">
		<div class="row mb-2">
			<div class="col-sm-6">
				<h1 class="m-0">QUẢN LÝ SẢN PHẨM</h1>
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
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<i id="btn-modal-add-product" style="font-size: 22px"
								class="bi bi-plus-square text-primary"></i>
						</h3>
						<div class="card-tools">
							<div class="input-group input-group-sm" style="width: 150px;">
								<input type="text" name="table_search"
									class="form-control float-right" placeholder="Search">

								<div class="input-group-append">
									<button type="submit" class="btn btn-default">
										<i class="fas fa-search"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
					
					<div id="phone-table-content">
						<!-- /.card-header -->
						<div class="card-body table-responsive p-0">
							<table class="table table-hover text-nowrap">
								<thead>
									<tr>
										<th>MÃ SẢN PHẨM</th>
										<th>TÊN SẢN PHẨM</th>
										<th>HỆ ĐIỀU HÀNH</th>
										<th>NGÀY NHẬP KHO</th>
										<th>BẢO HÀNH</th>
										<th>THƯƠNG HIỆU</th>
										<th>THAO TÁC</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="product" items="${listSP}">
										<tr>
											<td>${product.id_san_pham}</td>
											<td>${product.ten_san_pham}</td>
											<td>${product.he_dieu_hanh}</td>
											<td><fmt:formatDate pattern="dd-MM-yyyy"
													value="${product.ngay_nhap_hang}" />
												</p></td>
											<td>${product.tong_thang_bao_hanh}tháng</span></td>
											<td>${product.ten_hang}</td>
											<td data-id="${product.id_san_pham}"><a
												href="Admin/ProductDetail?id=${product.id_san_pham}"> <i
													style="font-size: 18px; padding-right: 5px;"
													class="bi bi-eye see-picture text-primary"></i>
											</a> <i style="font-size: 18px; padding-right: 5px"
												class="bi bi-pencil-square text-warning btn-update"></i> <i
												style="font-size: 18px; padding-right: 5px"
												class="bi bi-trash text-danger btn-delete"></i></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.card-body -->
						<div class="card-footer clearfix">
							<ul id="product-pagination"
								class="pagination pagination-sm m-0 float-right">
								<c:forEach var="index" begin="1" end="${listPage}">
									<c:if test="${index == 1}">
										<li data-id="${index}"
											class="page-item active page-product-item"><a
											class="page-link">${index}</a></li>
									</c:if>

									<c:if test="${index != 1}">
										<li data-id="${index}" class="page-item page-product-item"><a
											class="page-link">${index}</a></li>
									</c:if>

								</c:forEach>
							</ul>
						</div>
						<!--end card footer-->
					</div>
				</div>
				<!-- /.card -->
			</div>
		</div>
		<!-- /.row -->

	</div>
</section>
<!-- /.content -->
</div>

<div>
	<!-- Modal THÊM MỚI MỘT SẢN PHẨM-->
	<div class="modal fade modal-add-product" id="exampleModalCenter"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">THÊM MỚI
						SẢN PHẨM</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- FORM THÊM MỚI SẢN PHẨM -->
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail4">Tên sản phẩm</label> <input type="text"
								name="product-name" class="form-control">
							<div class="invalid-feedback"></div>
						</div>
						<div class="form-group col-md-6">
							<label>Thời gian bảo hành</label>
							<div class="input-group">
								<input name="month" type="number" min="1" class="form-control">
								<div class="input-group-prepend">
									<div class="input-group-text">THÁNG</div>
								</div>
								<div class="invalid-feedback"></div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label for="inputState">Hệ điều hành</label> <select
								id="inputState" name="system-operation" class="form-control">
								<option value="IOS">IOS</option>
								<option value="ANDROID">ANDROID</option>
							</select>
							<div class="invalid-feedback"></div>
						</div>

						<div class="form-group col-md-6">
							<label for="inputState">Hãng sản xuất</label> <select
								id="inputState" name="brand-name" class="form-control">
								<c:forEach var="item" items="${listBrand}">
									<option value="${item.id_hang}">${item.hang_san_xuat}</option>
								</c:forEach>
							</select>
							<div class="invalid-feedback"></div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Đóng</button>
						<button type="button" id="btn-insert" class="btn btn-primary">Thêm
							mới</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal chỉnh sửa thông tin sản phẩm -->
	<div class="modal fade modal-update-product" id="exampleModalCenter"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">CHỈNH SỬA
						THÔNG TIN SẢN PHẨM</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<!-- FORM CHỈNH SỬA SẢN PHẨM -->
					<div class="form-row">
						<input type="hidden" id="id-product-update">
						<div class="form-group col-md-6">
							<label for="inputEmail4">Tên sản phẩm</label> <input type="text"
								name="product-name-update" class="form-control">
							<div class="invalid-feedback"></div>
						</div>
						<div class="form-group col-md-6">
							<label>Thời gian bảo hành</label>
							<div class="input-group">
								<input name="month-update" type="number" min="1"
									class="form-control">
								<div class="input-group-prepend">
									<div class="input-group-text">THÁNG</div>
								</div>
								<div class="invalid-feedback"></div>
							</div>
						</div>

						<div class="form-group col-md-6">
							<label for="inputState">Hệ điều hành</label> <select
								id="inputState" name="system-operation-update"
								class="form-control">
								<option value="IOS">IOS</option>
								<option value="ANDROID">ANDROID</option>
							</select>
							<div class="invalid-feedback"></div>
						</div>

						<div class="form-group col-md-6">
							<label for="inputState">Hãng sản xuất</label> <select
								id="inputState" name="brand-name-update" class="form-control">
								<c:forEach var="item" items="${listBrand}">
									<option value="${item.id_hang}">${item.hang_san_xuat}</option>
								</c:forEach>
							</select>
							<div class="invalid-feedback"></div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
						<button type="button" id="btn-update-product"
							class="btn btn-primary">Save changes</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/Admin/Layout/Footer.jsp"%>