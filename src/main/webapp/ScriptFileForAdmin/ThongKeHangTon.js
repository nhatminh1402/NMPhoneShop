
$.ajax({
	url: "http://localhost:8080/NMPhoneShop/Admin/ThongKe?action=HangTon",
	method: "GET",
	dataType: 'json',
	success: function(data) {
		google.charts.load('current', { 'packages': ['corechart'] });
		google.charts.setOnLoadCallback(drawChart);

		let arrData = [
			["Hãng", "Cái"]
		]

		data.forEach(function(item) {
			for (let key in item) {
				arrData.push([key, item[key]])
			}
		})
		function drawChart() {
			//console.log(arrData)
			// Set Data
			const dataChart = google.visualization.arrayToDataTable(arrData);

			// Set Options
			const options = {
				title: 'THỐNG KÊ LƯỢNG HÀNG TỒN KHO THEO TỪNG HÃNG'
			};

			// Draw
			const chart = new google.visualization.ColumnChart(document.getElementById('myChart'));
			chart.draw(dataChart, options);
		}

	}
})