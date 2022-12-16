"use strict";
$(function(){
	calcTotalPrice();
	$(".size").on("change", function(){
		calcTotalPrice();
	});
	$(".checkbox").click(function(){
		calcTotalPrice();
	});
	$("#alohanum").on("change", function(){
		calcTotalPrice();
	}); 
	
	
	function calcTotalPrice() {
	let size = $(".size:checked").val();
	let topping_count = $("#topping input:checkbox:checked").length;
    let aloha_num = $("#alohanum").val();
    let size_price = 1;
    let topping_price = 1;
    
    if(size === "M") {
		size_price = $("#priceM").val();
		topping_price = 200 * topping_count;
	} else {
		size_price = $("#priceL").val();
		topping_price = 300 * topping_count;
	}
	let numberSizePrice = Number(size_price);
	let price = (numberSizePrice + topping_price) * aloha_num;
    $("#totalPrice").text(price.toLocaleString());
    
    
}

});

