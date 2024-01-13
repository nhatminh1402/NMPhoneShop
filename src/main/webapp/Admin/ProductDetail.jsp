<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/Admin/Layout/Head.jsp"%>
<%@ include file="/Admin/Layout/nav.jsp"%>
<%@ include file="/Admin/Layout/SideBar.jsp"%>

<c:set var="importProductDetailFileJS" value="true"></c:set>

<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<div class="content-header">
		<div class="container-fluid">

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
						<div style="padding-left: 10px"
							class="card-header align-items-center">
							<h3 class="card-title">MÔ TẢ SẢN PHẦM</h3>
							<div class="card-tools">
								<button id="btn-save-describe"
									style="font-size: 15px; padding: 3px" type="button"
									class="btn btn-primary">
									Update<i style="padding-left: 3px" class="bi bi-pencil-square"></i>
								</button>
							</div>
						</div>
						<!-- /.card-header -->
						<div class="card-body table-responsive p-0">
							<form>
								<textarea name="product-detail" id="editor1" rows="20" cols="80">
                          			${product_describe}
                      			</textarea>
								<script>
									CKFinder.setupCKEditor();
									CKEDITOR.replace('editor1');
								</script>
							</form>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
			</div>
			<!--=======================HET MÔ TẢ==============================================-->
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div style="padding-left: 10px"
							class="card-header align-items-center">
							<h3 class="card-title">DANH SÁCH HÌNH ẢNH SẢN PHẨM</h3>

							<div class="card-tools">
							<button id="btn-update-img"
									style="font-size: 15px; padding: 2px; margin-right: 5px" type="button"
									class="btn btn-primary">
									Update<i style="padding-left: 3px; margin-top: 10px" class="bi bi-pencil-square"></i>
								</button>
								<i id="btn-add-picture" style="color: blue; font-size: 20px"
									class="bi bi-plus-square"></i>
							</div>
						</div>
						<!-- /.card-header -->
						<div class="card-body table-responsive p-0" id="container-img">
							<c:if test="${not empty listAnh}">
								<table class="table table-hover text-nowrap">
									<thead>
										<tr>
											<th>STT</th>
											<th>HÌNH ẢNH</th>
											<th>THUMBNAIL</th>
											<th>THAO TÁC</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="index" value="1"></c:set>
										<c:forEach var="item" items="${listAnh}">
											<tr>
												<td>${index}<c:set var="index" value="${index+1}"></c:set>
												</td>
												<td><img id="show-full-img"
													style="width: 200px; height: 200px" alt=""
													src="${item.link_anh}"></td>
												<td class="checked-thumbnail">
											<c:if test="${item.thumbnail == true}">
												<input data-id="${item.id_anh}" type="checkbox" name="checked-thumbnail" checked="checked">
											</c:if>
											<c:if test="${item.thumbnail == false}">
												<input data-id="${item.id_anh}" type="checkbox" name="checked-thumbnail">
											</c:if>
												</td>
												<td style="display: flex; align-items: center">
												<i
													id="btn-delete-img" data-id="${item.id_anh}"
													style="font-size: 18px; padding-right: 5px"
													class="bi bi-trash text-danger">
													</i></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
							<c:if test="${empty listAnh}">
								<h1
									style="font-size: 17px; text-align: center; padding: 15px; font-weight: bold;"
									class="">SẢN PHẨM CHƯA CÓ ẢNH ĐỂ HIỂN THỊ!</h1>
							</c:if>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
			</div>
			<!--=======================HET ẢNH==============================================-->
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div style="padding-left: 10px"
							class="card-header align-items-center">
							<h3 class="card-title">Danh sách thông tin chi tiết sản phẩm
							</h3>

							<div class="card-tools">
								<i id="btn-add-chi-tiet" style="color: blue; font-size: 20px"
									class="bi bi-plus-square"></i>
							</div>
						</div>
						<!-- /.card-header -->
						<div class="card-body table-responsive p-0">
							<div id="product-detail-infomtion">
								<c:if test="${empty listDeatil}">
									<h1
										style="font-size: 17px; text-align: center; padding: 15px; font-weight: bold;"
										class="">VUI LÒNG BỔ SUNG CÁC CHI TIẾT SẢN PHẨM</h1>
								</c:if>
								<c:if test="${not empty listDeatil}">
									<table class="table table-hover text-nowrap">
										<thead>
											<tr>
												<th>STT</th>
												<th>RAM</th>
												<th>DUNG LƯỢNG</th>
												<th>CAMERA-TRƯỚC</th>
												<th>CAMERA-SAU</th>
												<th>MÀU SẮC</th>
												<th>MÀN HÌNH</th>
												<th>CHIP</th>
												<th>GIÁ NHẬP VÀO</th>
												<th>GIÁ BÁN RA</th>
												<th>SỐ LƯỢNG</th>
												<th>THAO TÁC</th>
											</tr>
										</thead>
										<tbody>
											<%
											int stt = 1;
											%>
											<c:forEach var="item" items="${listDeatil}">
												<tr>
													<td><%=stt%> <%
 stt++;
 %></td>
													<td>${item.ram}GB</td>
													<td>${item.dung_luong_luu_tru}GB</td>
													<td>${item.camera_truoc}MB</td>
													<td>${item.camera_sau}MB</td>
													<td>${item.mau_sac}</td>
													<td>${item.man_hinh}Inch</td>
													<td>${item.chip}</td>
													<td class="price">${item.gia_nhap_vao}</td>
													<td class="price">${item.gia_ban_ra}</td>
													<td>${item.so_luong}</td>
													<td><i data-id="${item.id_thong_tin_chi_tiet}"
														style="font-size: 18px; padding-right: 5px"
														class="bi bi-pencil-square text-warning btn-update-product-describe"></i>
														<i style="font-size: 18px; padding-right: 5px"
														class="bi bi-trash text-danger btn-delete-describe"
														data-id="${item.id_thong_tin_chi_tiet}"></i></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>
							</div>
						</div>
						<!-- /.card-body -->
					</div>
					<!-- /.card -->
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<!-- Modal upload file-->
<div>
	<!-- Modal -->
	<div class="modal fade modal-add-picture" id="exampleModalCenter"
		tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-lg modal-dialog-centered"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLongTitle">THÊM HÌNH
						ẢNH SẢN PHẨM</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="">
						<div class="form-row">
							<div class="form-group col-md-12">
								<input type="file" class="form-control-file" id="upload-field"
									name="file_upload">
							</div>
							<div id="picture-preview" class="form-group">
								<label for="inputState">PREVIEW</label> <img width="100%"
									height="400px" alt="" src="./Admin/Layout/preview-img.jpg">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Hủy</button>
					<button type="button" id="btn-insert-image" class="btn btn-primary">Lưu</button>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- MODAL thêm chi tiết sản phẩm -->
<div class="modal fade modal-them-chi-tiet" id="exampleModalCenter"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-lg modal-dialog-centered"
		role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLongTitle">THÊM CHI
					TIẾT SẢN PHẨM</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label>RAM</label>
						<div class="input-group mb-2">
							<input name="ram" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">GB</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label>DUNG LƯỢNG LƯU TRỮ</label>
						<div class="input-group mb-2">
							<input name="bo-nho" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">GB</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label>Camera trước</label>
						<div class="input-group mb-2">
							<input name="camera-truoc" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">MB</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label>Camera sau</label>
						<div class="input-group mb-2">
							<input name="camera-sau" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">MB</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label>Màu sắc</label>
						<div class="input-group mb-2">
							<input name="mau-sac" type="text" class="form-control">
							<div class="invalid-feedback"></div>
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label>Màn hình</label>
						<div class="input-group mb-2">
							<input name="man-hinh" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">Inch</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label>Chip xử lý</label>
						<div class="input-group mb-2">
							<input name="chip-xu-ly" type="text" class="form-control">
							<div class="invalid-feedback"></div>
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label>Số lượng</label>
						<div class="input-group mb-2">
							<input name="so-luong" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">Cái</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
				</div>


				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label>Giá nhập vào</label>
						<div class="input-group mb-2">
							<input name="gia-nhap" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">VND</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label>Giá bán ra</label>
						<div class="input-group mb-2">
							<input name="gia-ban" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">VND</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button id="btn-insert-detail" type="button" class="btn btn-primary">THÊM
					THÔNG TIN</button>
			</div>
		</div>
	</div>
</div>

<!-- MODAL cập nhật chi tiết sản phẩm -->
<div class="modal fade modal-update-chi-tiet-sp" id="exampleModalCenter"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-lg modal-dialog-centered"
		role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLongTitle">CẬP NHẬT CHI
					TIẾT SẢN PHẨM</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-row">
					<input id="id-describe" type="hidden">
					<div class="col-md-6 mb-3">
						<label>RAM</label>
						<div class="input-group mb-2">
							<input name="ram-update" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">GB</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label>DUNG LƯỢNG LƯU TRỮ</label>
						<div class="input-group mb-2">
							<input name="bo-nho-update" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">GB</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label>Camera trước</label>
						<div class="input-group mb-2">
							<input name="camera-truoc-update" type="text"
								class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">MB</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label>Camera sau</label>
						<div class="input-group mb-2">
							<input name="camera-sau-update" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">MB</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label>Màu sắc</label>
						<div class="input-group mb-2">
							<input name="mau-sac-update" type="text" class="form-control">
							<div class="invalid-feedback"></div>
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label>Màn hình</label>
						<div class="input-group mb-2">
							<input name="man-hinh-update" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">Inch</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
				</div>

				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label>Chip xử lý</label>
						<div class="input-group mb-2">
							<input name="chip-xu-ly-update" type="text" class="form-control">
							<div class="invalid-feedback"></div>
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label>Số lượng</label>
						<div class="input-group mb-2">
							<input name="so-luong-update" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">Cái</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
				</div>


				<div class="form-row">
					<div class="col-md-6 mb-3">
						<label>Giá nhập vào</label>
						<div class="input-group mb-2">
							<input name="gia-nhap-update" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">VND</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label>Giá bán ra</label>
						<div class="input-group mb-2">
							<input name="gia-ban-update" type="text" class="form-control">
							<div class="input-group-prepend">
								<div class="input-group-text">VND</div>
							</div>
							<div class="invalid-feedback"></div>
						</div>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button id="btn-update-detail-product" type="button"
					class="btn btn-primary">CẬP NHẬT</button>
			</div>
		</div>
	</div>
</div>
<%@ include file="/Admin/Layout/Footer.jsp"%>