import { isEmpty, isNumberValid, CheckNumberValid } from "../ModuleScript/FormValidation.js"


// Xử lý hiển thị giá tiền VND
$(".price").each(function() {
	var content = $(this).text();

	// Chuyển đổi tiền và thay thế nội dung
	var formattedAmount = parseFloat(content).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
	$(this).text(formattedAmount.replace(/₫/, '') + 'VND');
})

// Xử lý khi type thì xóa đi các lỗi
$("#brand-name, input").on("keypress", function() {
	$(this).removeClass("is-invalid")
	$(this).siblings(".invalid-feedback").text("")
})

// Xử lý sự kiện người dùng click file để upload
$("input[name='file_upload']").on("change", function() {
	// Lấy đối tượng File từ đối tượng input
	var fileUpload = this.files[0];

	var allowedImageTypes = [
		"image/jpeg",
		"image/pjpeg",
		"image/png",
		"image/gif",
		"image/bmp",
		"image/x-windows-bmp",
		"image/tiff",
		"image/webp",
		"image/svg+xml"
	];

	if (allowedImageTypes.indexOf(fileUpload.type) == -1) {
		// Định dạng file không được hổ trợ
		Swal.fire({
			title: "ĐỊNH DẠNG FILE KHÔNG HỢP LỆ!",
			icon: "error"
		});
		$(this).val("")
	} else {
		let urlFile = URL.createObjectURL(fileUpload)
		$("#picture-preview img").attr("src", urlFile)
	}

});


$("#btn-add-picture").on("click", function() {
	$(".modal-add-picture").modal("toggle")
})


// Xử lý gởi request insert bằng Ajax
function sendFormData(formData) {
	//sử dụng ajax post
	$.ajax({
		url: 'http://localhost:8080/NMPhoneShop/Admin/ProductDetail?action=insertIMG',
		cache: false,
		contentType: false,
		processData: false,
		data: formData,
		dataType: "html",
		type: 'post',
		success: function(response) {
			$(".modal-add-picture").modal("toggle")
			Swal.fire({
				title: "THÊM THÀNH CÔNG!",
				icon: "success"
			});
			$("#container-img").html(response)
		}
	});
}


// THỰC HIỆN GỞI REQUEST
$("#btn-insert-image").on("click", function() {
	let formUpoad = $("input[name='file_upload']")

	let searchParams = new URLSearchParams(window.location.search);
	let id_san_pham = searchParams.get('id');

	let file_data = $(formUpoad).prop("files")[0]

	if (!file_data) {
		Swal.fire({
			title: "VUI LÒNG CHỌN FILE TRƯỚC KHI UPLOAD!",
			icon: "error"
		});
	} else {
		let form_data = new FormData()
		form_data.append("file", file_data)
		form_data.append("id_san_pham", id_san_pham)
		sendFormData(form_data)
		formUpoad.val("")
		$("#picture-preview img").attr("src", "./Admin/Layout/preview-img.jpg")
	}

})

// Thực hiện request để gởi yêu cầu xóa file
function deleteIMG(id_san_pham, id_img, img_name) {
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/ProductDetail?action=deleteIMG",
		method: "GET",
		dataType: "html",
		data: {
			id_img: id_img,
			img_name: img_name,
			id_san_pham: id_san_pham
		},
		success: function(response) {
			Swal.fire({
				title: "XÓA THÀNH CÔNG!",
				text: "Ảnh sản phẩm đã được xóa.",
				icon: "success"
			});
			$("#container-img").html(response)
		}
	})
}

// Thêm sự kiện click để xóa file
$("body").on("click", "#btn-delete-img", function() {
	let id_img = $(this).data("id")
	let imgName = $(this).parent().siblings("td:nth-child(2)").find("img").attr("src")
	let searchParams = new URLSearchParams(window.location.search);
	let id_san_pham = searchParams.get('id');
	Swal.fire({
		title: "BẠN CÓ CHẮC CHẮN MUỐN XÓA?",
		text: "MỌI DỮ LIỆU SAU KHI BẠN XÓA KHÔNG THỂ HOÀN TÁC!",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "XÓA NGAY!"
	}).then((result) => {
		if (result.isConfirmed) {
			deleteIMG(id_san_pham, id_img, imgName)
		}
	});
})



// Thực hiện request để gởi yêu cầu update mô tả sản phẩm
function updateDescribe(id_san_pham, describe) {
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/ProductDetail?action=updateDescribe",
		method: "POST",
		dataType: "html",
		data: {
			describe: describe,
			id_san_pham: id_san_pham
		},
		success: function(response) {
			Swal.fire({
				title: "CẬP NHẬT MÔ TẢ SẢN PHẨM THÀNH CÔNG!",
				text: "",
				icon: "success"
			});
			CKEDITOR.instances["editor1"].setData(response)
		}
	})
}

$("#btn-save-describe").on("click", function() {
	let content = CKEDITOR.instances["editor1"].getData();

	let searchParams = new URLSearchParams(window.location.search);
	let id_san_pham = searchParams.get('id');

	updateDescribe(id_san_pham, content)
})

//Modal thêm chi tiết sản phẩm
$("#btn-add-chi-tiet").on("click", function() {
	$(".modal-them-chi-tiet").modal("toggle")
})

// Hàm gởi requset insert dữ liệu
function insertInforDeatil(ramInput, dungLuongInput, cameraTruocInput, cameraSauInput, mauSacInput, manHinhInput, chipXuLyInput, soLuongInput, giaNhapVaoInput, giaBanRaInput, id_san_pham) {
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/ProductDetail?action=insertDetailProduct",
		method: "POST",
		dataType: "html",
		data: {
			ramInput: ramInput,
			dungLuongInput: dungLuongInput,
			cameraTruocInput: cameraTruocInput,
			cameraSauInput: cameraSauInput,
			mauSacInput: mauSacInput,
			manHinhInput: manHinhInput,
			chipXuLyInput: chipXuLyInput,
			soLuongInput: soLuongInput,
			giaNhapVaoInput: giaNhapVaoInput,
			giaBanRaInput: giaBanRaInput,
			id_san_pham: id_san_pham
		},
		success: function(response) {
			$(".modal-them-chi-tiet").modal("toggle")
			Swal.fire({
				title: "CẬP NHẬT MÔ TẢ SẢN PHẨM THÀNH CÔNG!",
				text: "",
				icon: "success"
			});

			$("#product-detail-infomtion").html(response)
		}
	})
}

// Insert thông tin chi tiết của sản phẩm
$("#btn-insert-detail").on("click", function() {
	let ramInput = $(".modal-them-chi-tiet input[name='ram']")
	let dungLuongInput = $(".modal-them-chi-tiet input[name='bo-nho']")
	let cameraTruocInput = $(".modal-them-chi-tiet input[name='camera-truoc']")
	let cameraSauInput = $(".modal-them-chi-tiet input[name='camera-sau']")
	let mauSacInput = $(".modal-them-chi-tiet input[name='mau-sac']")
	let manHinhInput = $(".modal-them-chi-tiet input[name='man-hinh']")
	let chipXuLyInput = $(".modal-them-chi-tiet input[name='chip-xu-ly']")
	let soLuongInput = $(".modal-them-chi-tiet input[name='so-luong']")
	let giaNhapVaoInput = $(".modal-them-chi-tiet input[name='gia-nhap']")
	let giaBanRaInput = $(".modal-them-chi-tiet input[name='gia-ban']")

	let isErr = false

	isErr = isEmpty([ramInput, cameraTruocInput, cameraSauInput, dungLuongInput, mauSacInput, manHinhInput, chipXuLyInput, soLuongInput, giaNhapVaoInput, giaBanRaInput])

	if (isErr == false) {
		isErr = isNumberValid([ramInput, dungLuongInput, giaNhapVaoInput, giaBanRaInput, soLuongInput])
		isErr = CheckNumberValid([manHinhInput, cameraTruocInput, cameraSauInput])
	}

	// Nếu validate dữ liệu xong thì tiến hành gởi request insert dữ liệu
	if (isErr == true) {
		let searchParams = new URLSearchParams(window.location.search);
		let id_san_pham = searchParams.get('id');

		insertInforDeatil(ramInput.val(), dungLuongInput.val(), cameraTruocInput.val(), cameraSauInput.val(), mauSacInput.val(), manHinhInput.val(), chipXuLyInput.val(), soLuongInput.val(), giaNhapVaoInput.val(), giaBanRaInput.val(), id_san_pham)
		ramInput.val("")
		dungLuongInput.val("")
		cameraTruocInput.val("")
		cameraSauInput.val("")
		mauSacInput.val("")
		manHinhInput.val("")
		chipXuLyInput.val("")
		soLuongInput.val("")
		giaNhapVaoInput.val("")
		giaBanRaInput.val("")
	}
})


$("body").on("click", ".btn-delete-describe", function() {
	let id_descibe = $(this).data("id")


	let searchParams = new URLSearchParams(window.location.search);
	let id_san_pham = searchParams.get('id');

	Swal.fire({
		title: "BẠN CÓ CHẮC CHẮN MUỐN XÓA?",
		text: "MỌI DỮ LIỆU SAU KHI BẠN XÓA KHÔNG THỂ HOÀN TÁC!",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "XÓA NGAY!"
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "http://localhost:8080/NMPhoneShop/Admin/ProductDetail?action=deleteDescribe",
				method: "GET",
				data: {
					id_descibe: id_descibe,
					id_san_pham: id_san_pham
				},
				success: function(response) {
					Swal.fire({
						title: "XÓA THÀNH CÔNG!",
						text: "Chi tiết sản phẩm trên đã được xóa.",
						icon: "success"
					});
					$("#product-detail-infomtion").html(response)
				}
			})
		}
	});
})



// Cập nhật chi tiết sản phẩm
$("body").on("click", ".btn-update-product-describe", function() {
	$(".modal-update-chi-tiet-sp").modal("toggle")
	$("#id-describe").data("id", $(this).data("id"))

	let id_descibe = $(this).data("id")

	console.log(id_descibe)
	// Thực hiện hiển thị dữ liệu tương ứng lên modal mà khách đã chọn
	let ramInput = $(".modal-update-chi-tiet-sp input[name='ram-update']")
	let dungLuongInput = $(".modal-update-chi-tiet-sp input[name='bo-nho-update']")
	let cameraTruocInput = $(".modal-update-chi-tiet-sp input[name='camera-truoc-update']")
	let cameraSauInput = $(".modal-update-chi-tiet-sp input[name='camera-sau-update']")
	let mauSacInput = $(".modal-update-chi-tiet-sp input[name='mau-sac-update']")
	let manHinhInput = $(".modal-update-chi-tiet-sp input[name='man-hinh-update']")
	let chipXuLyInput = $(".modal-update-chi-tiet-sp input[name='chip-xu-ly-update']")
	let soLuongInput = $(".modal-update-chi-tiet-sp input[name='so-luong-update']")
	let giaNhapVaoInput = $(".modal-update-chi-tiet-sp input[name='gia-nhap-update']")
	let giaBanRaInput = $(".modal-update-chi-tiet-sp input[name='gia-ban-update']")
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/ProductDetail?action=selectDescribe",
		method: "GET",
		dataType: "json",
		data: {
			id_descibe: id_descibe
		},
		success: function(response) {
			$(ramInput).val(response.ram)
			dungLuongInput.val(response.dung_luong_luu_tru)
			cameraTruocInput.val(response.camera_truoc)
			cameraSauInput.val(response.camera_sau)
			mauSacInput.val(response.mau_sac)
			manHinhInput.val(response.man_hinh)
			chipXuLyInput.val(response.chip)
			soLuongInput.val(response.so_luong)
			giaNhapVaoInput.val(response.gia_nhap_vao)
			giaBanRaInput.val(response.gia_ban_ra)
		}

	})
})

// Hàm gởi requset update dữ liệu
function UpdateInforDeatil(ramInput, dungLuongInput, cameraTruocInput, cameraSauInput, mauSacInput, manHinhInput, chipXuLyInput, soLuongInput, giaNhapVaoInput, giaBanRaInput, id_chi_tiet, id_san_pham) {
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/ProductDetail?action=UpdateChiTietSanPham",
		method: "POST",
		dataType: "html",
		data: {
			ramInput: ramInput,
			dungLuongInput: dungLuongInput,
			cameraTruocInput: cameraTruocInput,
			cameraSauInput: cameraSauInput,
			mauSacInput: mauSacInput,
			manHinhInput: manHinhInput,
			chipXuLyInput: chipXuLyInput,
			soLuongInput: soLuongInput,
			giaNhapVaoInput: giaNhapVaoInput,
			giaBanRaInput: giaBanRaInput,
			id_chi_tiet: id_chi_tiet,
			id_san_pham: id_san_pham
		},
		success: function(response) {
			$(".modal-update-chi-tiet-sp").modal("toggle")
			Swal.fire({
				title: "CẬP NHẬT CHI TIẾT SẢN PHẨM THÀNH CÔNG!",
				text: "",
				icon: "success"
			});

			$("#product-detail-infomtion").html(response)
		}
	})
}


// Tiến hành cập nhật chi tiết sản phẩm
$("#btn-update-detail-product").on("click", function() {
	let ramInput = $(".modal-update-chi-tiet-sp input[name='ram-update']")
	let dungLuongInput = $(".modal-update-chi-tiet-sp input[name='bo-nho-update']")
	let cameraTruocInput = $(".modal-update-chi-tiet-sp input[name='camera-truoc-update']")
	let cameraSauInput = $(".modal-update-chi-tiet-sp input[name='camera-sau-update']")
	let mauSacInput = $(".modal-update-chi-tiet-sp input[name='mau-sac-update']")
	let manHinhInput = $(".modal-update-chi-tiet-sp input[name='man-hinh-update']")
	let chipXuLyInput = $(".modal-update-chi-tiet-sp input[name='chip-xu-ly-update']")
	let soLuongInput = $(".modal-update-chi-tiet-sp input[name='so-luong-update']")
	let giaNhapVaoInput = $(".modal-update-chi-tiet-sp input[name='gia-nhap-update']")
	let giaBanRaInput = $(".modal-update-chi-tiet-sp input[name='gia-ban-update']")
	let id_chi_tiet = $("#id-describe").data("id")
	let isErr = false

	let searchParams = new URLSearchParams(window.location.search);
	let id_san_pham = searchParams.get('id');


	isErr = isEmpty([ramInput, cameraTruocInput, cameraSauInput, dungLuongInput, mauSacInput, manHinhInput, chipXuLyInput, soLuongInput, giaNhapVaoInput, giaBanRaInput])

	if (isErr == false) {
		isErr = isNumberValid([ramInput, dungLuongInput, giaNhapVaoInput, giaBanRaInput, soLuongInput])
		isErr = CheckNumberValid([manHinhInput, cameraTruocInput, cameraSauInput])
	}

	// Nếu validate dữ liệu xong thì tiến hành gởi request insert dữ liệu
	if (isErr == true) {
		UpdateInforDeatil(ramInput.val(), dungLuongInput.val(), cameraTruocInput.val(), cameraSauInput.val(), mauSacInput.val(), manHinhInput.val(), chipXuLyInput.val(), soLuongInput.val(), giaNhapVaoInput.val(), giaBanRaInput.val(), id_chi_tiet, id_san_pham)
	}
})


$("body").on("click", "#checked-thumbnail", function() {
	alert("hello")
})

$("body").on("click", "#btn-update-img", function() {
	let arrCheckedImg = $(".checked-thumbnail input[name='checked-thumbnail']:checked")

	if (!arrCheckedImg || arrCheckedImg.length == 0) {
		Swal.fire({
			title: "VUI LÒNG CHỌN ẢNH THUMBNAIL CỦA SẢN PHẨM!",
			text: "",
			icon: "error"
		})

	} else if (arrCheckedImg.length > 1) {
		Swal.fire({
			title: "VUI LÒNG CHỌN DUY NHẤT 1 ẢNH THUMBNAIL!",
			text: "",
			icon: "error"
		})
	} else if (arrCheckedImg.length == 1) {
		let idImg = arrCheckedImg.data("id")

		let searchParams = new URLSearchParams(window.location.search);
		let id_san_pham = searchParams.get('id');
		$.ajax({
			url: "http://localhost:8080/NMPhoneShop/Admin/ProductDetail?action=updateIMG",
			method: "POST",
			dataType: "html",
			data: {
				idImg: idImg,
				id_san_pham: id_san_pham
			},
			success: function(response) {
				Swal.fire({
					title: "CẬP NHẬT ẢNH THUMBNAIL THÀNH CÔNG!",
					text: "",
					icon: "success"
				});
			}
		})


	}
})




