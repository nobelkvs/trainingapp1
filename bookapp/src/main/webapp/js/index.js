
// Searching books through any  of the field in table
	$("#searchh").on("keyup paste", function() {
	    var value = $(this).val();
	    var $rows = $("table tr");
	
	    if (value === '') {
	        $rows.show(500);
	        return false;
	    }
	
	    $rows.each(function(index) {
	        if (index !== 0) {
	
	            $row = $(this);
	
	            var column1 = $row.find("td:nth-child(2)").html();
	            var column2 = $row.find("td:nth-child(3)").html();
	            var column3 = $row.find("td:nth-child(4)").html();
	            var column4 = $row.find("td:nth-child(5)").html();
	            if (column1.indexOf(value) > -1 || column2.indexOf(value) > -1 || column3.indexOf(value) > -1 || column4.indexOf(value) > -1) {
	                $row.show(500);
	            } else {
	                $row.hide(500);
	            }
	        }
	    });
	});

//Searching orders through any  of the field in table
	$("#searchhh").on("keyup paste", function() {
	    var value = $(this).val();
	    var $rows = $("table tr");
	
	    if (value === '') {
	        $rows.show(500);
	        return false;
	    }
	
	    $rows.each(function(index) {
	        if (index !== 0) {
	
	            $row = $(this);
	
	            var column1 = $row.find("td:nth-child(2)").html();
	            var column2 = $row.find("td:nth-child(3)").html();
	            var column3 = $row.find("td:nth-child(4)").html();
	            var column4 = $row.find("td:nth-child(5)").html();
	            if (column1.indexOf(value) > -1 || column2.indexOf(value) > -1 || column3.indexOf(value) > -1 || column4.indexOf(value) > -1) {
	                $row.show(500);
	            } else {
	                $row.hide(500);
	            }
	        }
	    });
	});
	
	
	$("#order_book").click(function() {
	    $("#order_Book").css("display", "block");
	    $("#Orders").css("display", "none");
	    $("#Total_Orders").css("display", "none");
	    $("#Available_Books").css("display", "none")
	    $("#homee").css("display", "none");
	
	});
	
	
	$("#orders").click(function() {
	    $("#Orders").css("display", "block");
	    $("#order_Book").css("display", "none");
	    $("#Total_Orders").css("display", "none");
	    $("#Available_Books").css("display", "none");
	    $("#homee").css("display", "none");
	
	});
	
	$("#home").click(function() {
	    $("#homee").css("display", "block");
	
	    $("#Orders").css("display", "none");
	    $("#order_Book").css("display", "none");
	    $("#Total_Orders").css("display", "none");
	    $("#Available_Books").css("display", "none")
	
	});
	
	$("#total_orders").click(function() {
	
	    $("#Total_Orders").css("display", "block")
	    $("#Orders").css("display", "none");
	    $("#order_Book").css("display", "none");
	    $("#Available_Books").css("display", "none")
	    $("#homee").css("display", "none");
	
	});
	
	$("#availble_books").click(function() {
	
	    $("#Total_Orders").css("display", "none")
	    $("#Orders").css("display", "none");
	    $("#order_Book").css("display", "none");
	    $("#Available_Books").css("display", "block")
	    $("#homee").css("display", "none");
	
	});
	
	$('select').on('change', function() {
	    if (this.value == 'author') {
	        $("#author").css("display", "block");
	        $("#book").css("display", "none");
	        $('#book').val('');
	        $("#disable1").removeAttr("disabled");
	        $("#disable").css("display", "none");
	        $("#disable1").css("display", "block");
	
	
	    } else if (this.value == 'book') {
	        $("#author").css("display", "none");
	        $("#book").css("display", "block");
	        $('#author').val('')
	        $("#disable").removeAttr("disabled");
	        $("#disable1").css("display", "none");
	        $("#disable").css("display", "block");
	
	
	    } else {
	        $("#author").css("display", "none");
	        $("#book").css("display", "none");
	
	    }
	});