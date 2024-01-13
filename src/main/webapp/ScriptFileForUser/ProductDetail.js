// Truy vấn theo màu
$(".product-color  .btn-color").on("click", function() {
	$(".btn-color").removeClass("btn-choose")
	$(".btn-color").removeAttr("check")
	
	$(this).addClass("btn-choose")
	$(this).attr("check", "checked")
	

	$("#ram-insertion").html("")
	$("#storage-insertion").html("")
	$("#buy-insertion").html("")
	
	$("#price-title").html('<h1 id="price" style="font-size: 16px; padding: 10px; text-align: center;">VUI LÒNG CHỌN CẤU HÌNH ĐỂ BIẾT GIÁ CHI TIẾT</h1>')

	
	let searchParams = new URLSearchParams(window.location.search);
	let product_id = searchParams.get('product-id');
	let mau_sac = $(this).data("value")
	
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/ChiTietSanPham?action=getAllBoNho",
		method: "GET",
		dataType: "html",
		data: {
			product_id: product_id,
			mau_sac: mau_sac
		},
		success: function(response) {
			$("#storage-insertion").html(response)
		}
	})
}) 
// Truy vấn theo màu và dung lượng lưu trữ
$("body").on("click",".btn-storage" ,function() {
	$(".btn-storage").removeClass("btn-choose")
	$(".btn-storage").removeAttr("check")
	
	$("#price-title").html('<h1 id="price" style="font-size: 16px; padding: 10px; text-align: center;">VUI LÒNG CHỌN CẤU HÌNH ĐỂ BIẾT GIÁ CHI TIẾT</h1>')
	
	$(this).addClass("btn-choose")
	$(this).attr("check", "checked")
	
	$("#buy-insertion").html("")
	
	let searchParams = new URLSearchParams(window.location.search);
	let product_id = searchParams.get('product-id');
	
	let dung_luong = $(this).data("value")
	let mau_sac = $("button.btn-color[check='checked']").data("value")
	
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/ChiTietSanPham?action=getRam",
		method: "GET",
		dataType: "html",
		data: {
			product_id: product_id,
			mau_sac: mau_sac,
			dung_luong: dung_luong
		},
		success: function(response) {
			$("#ram-insertion").html(response)
		}
	})
}) 

// Chọn ram
$("body").on("click",".btn-ram" ,function() {
	$(".btn-ram").removeClass("btn-choose")
	$(".btn-ram").removeAttr("check")
	
	$(this).addClass("btn-choose")
	$(this).attr("check", "checked")
	
	
	let searchParams = new URLSearchParams(window.location.search);
	let product_id = searchParams.get('product-id');
	
	let ram = $(this).data("value")
	let mau_sac = $("button.btn-color[check='checked']").data("value")
	let dung_luong = $("button.btn-storage[check='checked']").data("value")
	
	$.ajax({
		url: "http://localhost:8080/NMPhoneShop/ChiTietSanPham?action=showPrice",
		method: "GET",
		dataType: "json",
		data: {
			product_id: product_id,
			mau_sac: mau_sac,
			dung_luong: dung_luong,
			ram : ram
		},
		success: function(response) {
			let content = '<div class="col-md-12 mt-2"><div class="row g-2"><div id="btn-buy" class="col-md-6"><a href="GioHang?id-chi-tiet='+response.id_thong_tin_chi_tiet+'"><button><span style="font-weight: bold; font-size: 16px;">Mua Ngay</span> <br> (Giao nhanh nội thành trong vòng 2h)</button></a></div><div id="btn-add" class="col-md-6"><button>Thêm vào giỏ hàng</button></div></div>'
			$("#buy-insertion").html(content)
			$("#price-title").html('<h1 style="font-size: 30px; color:red; padding-top: 5px">'+new Intl.NumberFormat("vi-VN", { style: "currency", currency: "VND"}).format(response.gia_ban_ra)+'</h1>')
		}
	})
	
}) 
