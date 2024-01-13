// Hàm xử lý phân trang
function paginate(pageNumber) {
	let dataInsertionArea = $("#data-table-brand")

	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/Quan-ly-hang-san-xuat?action=pagination",
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
				$("#content-manage-brand").html(response)
			}
		}
	})
}


// Xử lý sự kiện click và phân trang theo đúng trang mà user click vào
$(function() {
	$("body").on("click", ".page-item", function() {
		let pageNumber = $(this).data("page")
		paginate(pageNumber)
	})
})


// Hàm kiểm tra hãng thêm vào có bị trùng lặp
function isBrandExist(brandName) {
	let result = true
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/Quan-ly-hang-san-xuat?action=check-brand-exist",
		method: "GET",
		dataType: "json",
		data: {
			brandName: brandName
		},
		async: false,
		success: function(response) {
			if (response.result == true) {
				result = true
			} else {
				result = false
			}
		}
	})
	return result
}

// Thực hiện insert dữ liệu
function insertBrand(brandName) {
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/Admin/Quan-ly-hang-san-xuat?action=insert",
		method: "GET",
		data: {
			brandName: brandName
		},
		success: function() {
		}
	})
}


// Acton thêm mới một hãng sản xuất
$(".btn-add-brand").on("click", function() {
	let inputBrand = $("#brand-name")
	let isError = false;

	// Validate trường hợp không nhập gì
	if (inputBrand.val() == "") {
		inputBrand.addClass("is-invalid")
		inputBrand.next().text("Vui lòng không để trống!")
		isError = true
	}

	if (inputBrand.val().length > 0) {
		// Validate trường hợp nhập một hãng đã có trong CSDL
		if (isBrandExist(inputBrand.val()) == true) {
			inputBrand.addClass("is-invalid")
			inputBrand.next().text("Hãng đã tồn tại!")
			isError = true
		}
	}

	// Nếu không có lỗi nào thì tiến hành insert dữ liệu
	if (isError == false) {
		insertBrand(inputBrand.val())
		inputBrand.val("")
		Swal.fire("THÊM MỚI THÀNH CÔNG!", "", "success");
		paginate(1)
		$('.modal-form-add-brand').modal('hide')
	}

})


// Xử lý khi type thì xóa đi các lỗi
$("#brand-name, input").on("keypress", function() {
	$(this).removeClass("is-invalid")
	$(this).next().text("")
})


// Delete brand
$("body").on("click", ".brand-button-delete", function() {
	// Lấy ra id cần xóa
	let brandId = $(this).parent().data("id")
	// Thực hiện gởi request xóa hãng này theo ID

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
			$.ajax({
				url: "http://localhost:8080/NMPhoneShop/Admin/Quan-ly-hang-san-xuat?action=delete",
				method: "GET",
				data: {
					brandId: brandId
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
	});
})

//Update Brand Show Modal
$(function() {
	$("body").on("click", ".brand-button-update", function() {
		$(".modal-update-brand").modal('toggle')
		let id = $(this).parent().data("id")
		$("#brand-id-update").data("id", id)

		let inputUpdate = $("#brand-name-update")
		let brandNameClick = $(this).parent().prev().text()

		inputUpdate.val(brandNameClick)
	})
})

//Update Brand
$("#btn-update-brand").on("click", function() {
	let inputUpdate = $("#brand-name-update")
	let isError = false

	// Kiểm tra trường để trống
	if (inputUpdate.val().trim() == "") {
		isError = true
		inputUpdate.addClass("is-invalid")
		inputUpdate.next().html("Vui lòng không để trống")
	}

	// Kiểm tra tên Update nhập vào có bị trùng lặp
	if (inputUpdate.val().trim().length > 0) {
		if (isBrandExist(inputUpdate.val())) {
			isError = true
			inputUpdate.addClass("is-invalid")
			inputUpdate.next().html("HÃNG ĐÃ TỒN TẠI! VUI LÒNG KIỂM TRA VÀ NHẬP LẠI")
		}
	}

	// Tiến hành request update
	if (isError == false) {
		$.ajax({
			url: "http://localhost:8080/NMPhoneShop/Admin/Quan-ly-hang-san-xuat?action=update",
			method: "GET",
			data: {
				brandName: inputUpdate.val(),
				brandId: $("#brand-id-update").data("id")
			},
			success: function() {
				$(".modal-update-brand").modal("hide")
				Swal.fire({
					title: "CẬP NHẬT THÀNH CÔNG!",
					icon: "success"
				});
				paginate($("ul.pagination li.active").data("page"))
			}
		})
	}

})



