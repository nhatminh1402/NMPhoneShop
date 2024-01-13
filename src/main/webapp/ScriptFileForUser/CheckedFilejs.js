function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

// Xử lý checked hãng
$(function() {
	let ListBrandChecked = getCookie("ListBrandChecked");
	if (ListBrandChecked.length > 0) {
		let arrListBrandChecked = ListBrandChecked.split("-")

		arrListBrandChecked.forEach(function(item) {
			$("input[type='checkbox'][name='brand'][value='" + item + "']").prop("checked", true)
		})
		$("input[type='checkbox'][name='brand'][value='all']").prop("checked", false)
	}

})


// Xử lý checked theo giá
$(function() {
	let listPriceCheck = getCookie("listPriceCheck");
	if (listPriceCheck.length > 0) {
		let arrlistPriceCheck = listPriceCheck.split("/")

		arrlistPriceCheck.forEach(function(item) {
			$("input[type='checkbox'][name='price'][value='" + item + "']").prop("checked", true)
		})

		$(".price-filter input[type='checkbox'][name='price'][value='all']").prop("checked", false)
	} 

})



// Xử lý checked theo hệ điều hành
$(function() {
	let listOperationCheck = getCookie("listOperationCheck");
	if (listOperationCheck.length > 0) {
		let arrlistOperationCheck = listOperationCheck.split("/")

		arrlistOperationCheck.forEach(function(item) {
			$("input[type='checkbox'][name='operation'][value='" + item + "']").prop("checked", true)
		})

		$("input[type='checkbox'][name='operation'][value='all']").prop("checked", false)
	} 

})






















