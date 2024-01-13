// Kiểm tra các input có rỗng hay không
export function isEmpty(arrInput) {
	let result = false
	arrInput.forEach(item => {
		if (!item.val()) {
			result = true
			item.addClass("is-invalid")
			item.siblings(".invalid-feedback").text("Vui lòng không để trống!")
		}
	})
	return result
}

// Kiểm tra xem giá trị là một số và không có phần thập phân
export function isNumberValid(arrInput) {
	let result = true

	arrInput.forEach(item => {
		if (!Number.isInteger(Number(item.val()))) {
			result = false
			item.addClass("is-invalid")
			item.siblings(".invalid-feedback").text("DỮ LIỆU NHẬP VÀO KHÔNG HỢP LỆ!")
		}
	})

	return result
}


export function CheckNumberValid(arrInput) {
	let result = true

	arrInput.forEach(item => {
		if (isNaN(item.val())) {
			result = false
			item.addClass("is-invalid")
			item.siblings(".invalid-feedback").text("DỮ LIỆU NHẬP VÀO KHÔNG HỢP LỆ!")
		}
	})

	return result
}