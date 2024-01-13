$("body").on("click", ".btn-increase", function() {
	let so_luong_input = $(this).siblings(".number-product")
	let num_sl = parseInt($(this).siblings(".number-product").val()) + 1

	let id_chi_tiet = $(this).data("id")

	$.ajax({
		url: 'GioHang?action=updateQuantity',
		type: 'GET',
		dataType: 'json',
		data: {
			soLuong: num_sl,
			id_chi_tiet: id_chi_tiet
		},
		success: function(responseData) {
			if (responseData.result == "un-valid") {
				Swal.fire({
					position: "top-end",
					icon: "error",
					title: "Xin lỗi! Số lượng hàng trong kho của chúng tôi không đủ để đáp ứng.",
					showConfirmButton: false,
					timer: 1500
				});
			}

			if (responseData.result == "valid") {
				so_luong_input.val(num_sl)
				$("#sum-price").text(responseData.TongTien)
			}
		},
		error: function(error) {

		}
	});

})



$("body").on("click", ".btn-decrease", function() {
	let so_luong_input = $(this).siblings(".number-product")
	let num_sl = parseInt($(this).siblings(".number-product").val()) - 1

	let id_chi_tiet = $(this).data("id")

	if (num_sl == 0) {
		return
	}

	$.ajax({
		url: 'GioHang?action=reduce',
		type: 'GET',
		dataType: 'json',
		data: {
			soLuong: num_sl,
			id_chi_tiet: id_chi_tiet
		},
		success: function(responseData) {
			so_luong_input.val(num_sl)
			$("#sum-price").text(responseData.TongTien)
		},
		error: function(error) {

		}
	});

})