// Nếu đang gõ thì xóa đi tất cả các lỗi
$("input[type='password'], input[type='email']").on("keypress", function() {
	$(this).removeClass("is-invalid")
	$(this).siblings(".invalid-feedback").html("")

})

$(function() {
	// Thực hiện validate khi submit
	$("button[type='submit']").on("click", function(e) {
		e.preventDefault();
		// Kiểm tra nếu không có dữ liệu gì mà tiến hành submit thì hiển thị thông báo lỗi
		let emailInput = $("input[type='email']")
		let passwordInput = $("input[type='password']")
		let isError = false

		let arrValidateEmpty = [emailInput, passwordInput]

		arrValidateEmpty.forEach(element => {
			if (element.val().trim() == "") {
				isError = true
				element.addClass("is-invalid")
				$(element).siblings(".invalid-feedback").html("Vui lòng không để trống")
			}
		});
		
		if(isError == false) {
			$("form").submit()
		}
	})
})