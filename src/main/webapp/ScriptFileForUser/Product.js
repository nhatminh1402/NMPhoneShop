$(".brand-filter input[type='checkbox']").change(function() {
	if ($(this).val() != 'all') {
		$(".brand-filter input[value='all']").prop("checked", false);
	} else {
		$(".brand-filter input:not(input[value='all'])").prop("checked", false);
	}
})

$(".price-filter input[type='checkbox']").change(function() {
	if ($(this).val() != 'all') {
		$(".price-filter input[value='all']").prop("checked", false);
	} else {
		$(".price-filter input:not(input[value='all'])").prop("checked", false);
	}
})

$(".operation-filter input[type='checkbox']").change(function() {
	if ($(this).val() != 'all') {
		$(".operation-filter input[value='all']").prop("checked", false);
	} else {
		$(".operation-filter input:not(input[value='all'])").prop("checked", false);
	}
})

