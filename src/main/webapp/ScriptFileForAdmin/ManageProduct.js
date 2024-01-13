import { isEmpty } from "../ModuleScript/FormValidation.js"

// Xử lý khi type thì xóa đi các lỗi
$("#brand-name, input").on("keypress", function() {
	$(this).removeClass("is-invalid")
	$(this).siblings(".invalid-feedback").text("")
})

// Hàm xử lý phân trang
function paginate(pageNumber) {
	let dataInsertionArea = $("#phone-table-content")

	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/Quan-ly-san-pham?action=pagination",
		method: "GET",
		dataType: "html",
		data: {
			pageNumber: pageNumber
		},
		success: function(response) {
			if (response.length == 0) {
				dataInsertionArea.html("<h5 style='text-align: center; padding: 15px'>KHÔNG CÓ DỮ LIỆU ĐỂ HIỂN THỊ</h5>")
				$(".card-footer").remove()
			} else {
				dataInsertionArea.html(response)
			}
		}
	})
}

// Thực hiện gọi dữ liệu phân trang khi click
$("body").on("click", ".page-product-item", function() {
	let pageClick = $(this).data("id")
	paginate(pageClick)
})


// Hàm xử lý xóa dữ liệu điện thoại
function deleteById(productId) {
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/Quan-ly-san-pham?action=delete",
		method: "GET",
		dataType: "json",
		data: {
			productId: productId
		},
		success: function() {
			Swal.fire({
				title: "Deleted!",
				icon: "success"
			});
			paginate(1)
		}
	})
}

// Thực hiện xóa điện thoại theo mã tương ứng khi client nhấn click
$(function() {
	$("body").on("click", ".btn-delete", function() {
		let idProduct = $(this).parent().data("id")

		Swal.fire({
			title: "BẠN CÓ CHẮC CHẮN MUỐN XÓA?",
			text: "Vui lòng cân nhắc trước khi xóa. Dữ liệu không thể hoàn tác!",
			icon: "warning",
			showCancelButton: true,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: "#d33",
			confirmButtonText: "Xóa ngay!"
		}).then((result) => {
			if (result.isConfirmed) {
				deleteById(idProduct)
			}
		});
	})
})


// PopUp modal thêm mới sản phẩm
$("#btn-modal-add-product").on("click", function() {
	$(".modal-add-product").modal("toggle")
})


// Hàm gởi Request thêm sản phẩm
function insert(ten_san_pham, he_dieu_hanh, tong_thang_bao_hanh, id_hang) {
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/Quan-ly-san-pham?action=insert",
		method: "GET",
		data: {
			ten_san_pham: ten_san_pham,
			he_dieu_hanh: he_dieu_hanh,
			tong_thang_bao_hanh: tong_thang_bao_hanh,
			id_hang: id_hang
		},
		success: function() {
			Swal.fire("THÊM MỚI THÀNH CÔNG!", "", "success");
			paginate(1)
			$('.modal-add-product').modal('hide')
		}
	})
}

// Validation dữ liệu trước khi submit request form
$("#btn-insert").on("click", function() {
	let tenSanPham = $("input[name='product-name']")
	let thoiGianBaoHanh = $("input[name='month']")
	let heDieuHanh = $("select[name='system-operation'] option:selected")
	let idHangSanXuat = $("select[name='brand-name'] option:selected")
	let isError = false

	isError = isEmpty([tenSanPham, thoiGianBaoHanh])

	if (!Number.isInteger(parseInt(thoiGianBaoHanh.val()))) {
		thoiGianBaoHanh.addClass("is-invalid")
		thoiGianBaoHanh.siblings(".invalid-feedback").text("Thời gian không hợp lệ!")
		isError = true
	}

	if (isError == false) {
		insert(tenSanPham.val(), heDieuHanh.val(), thoiGianBaoHanh.val(), idHangSanXuat.val())
	}

})


// Hiện modal update sản phẩm
$("body").on("click", ".btn-update", function() {
	$(".modal-update-product").modal("toggle")
	let product_id = $(this).parent().data("id")
	// Lấy ra các giá trị để update 
	//....
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/Quan-ly-san-pham?action=selectById",
		method: "GET",
		dataType: "json",
		data: {
			id: product_id
		},
		success: function(response) {
			let productName = $("input[name='product-name-update']")
			let thoiGianBaoHanh = $("input[name='month-update']")
			
			//set tên và tháng bảo hành
			productName.val(response.ten_san_pham)
			thoiGianBaoHanh.val(response.tong_thang_bao_hanh)
			
			// set selected hãng sản xuất
			$("select[name='system-operation-update'] option").removeAttr("selected")
			$("select[name='system-operation-update'] option[value="+response.he_dieu_hanh+"]").attr("selected", true)
			
			// set selected hệ điều hành
			$("select[name='brand-name-update'] option").removeAttr("selected")
			$("select[name='brand-name-update'] option[value="+response.id_hang+"]").attr("selected", true)
			
			$("input#id-product-update").data("id", response.id_san_pham)
		}
	})

})

// Xử lý request Update sản phẩm
function update(ten_san_pham, he_dieu_hanh, tong_thang_bao_hanh, id_hang, id_sp) {
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/Quan-ly-san-pham?action=update",
		method: "GET",
		data: {
			ten_san_pham: ten_san_pham,
			he_dieu_hanh: he_dieu_hanh,
			tong_thang_bao_hanh: tong_thang_bao_hanh,
			id_hang: id_hang,
			id_sp: id_sp
		},
		success: function() {
			$(".modal-update-product").modal("toggle")
			Swal.fire({
				title: "CẬP NHẬT THÀNH CÔNG!",
				icon: "success"
			});
			paginate($("#product-pagination li.active").data("id"))
		}
	})
}

// Tiến hành update sản phẩm
$("#btn-update-product").on("click", function() {
	let tenSanPham = $("input[name='product-name-update']")
	let thoiGianBaoHanh = $("input[name='month-update']")
	let heDieuHanh = $("select[name='system-operation-update'] option:selected")
	let idHangSanXuat = $("select[name='brand-name-update'] option:selected")
	let id_sp = $("#id-product-update").data("id")
	let isErr = false
	
	isErr = isEmpty([tenSanPham, thoiGianBaoHanh])
	
	if (!Number.isInteger(parseInt(thoiGianBaoHanh.val()))) {
		thoiGianBaoHanh.addClass("is-invalid")
		thoiGianBaoHanh.siblings(".invalid-feedback").text("Thời gian không hợp lệ!")
		isError = true
	}

	if (isErr == false) {
		update(tenSanPham.val(), heDieuHanh.val(), thoiGianBaoHanh.val(), idHangSanXuat.val(), id_sp)
	}
})


