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
            img_detailsimg_details

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
    $("#Available_￼Books").css("display", "none")
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



var uiContainer = document.getElementById("nav");

// Get all buttons with class="btn" inside the container
var btns = uiContainer.getElementsByClassName("for-active");
console.log(btns)
// Loop through the buttons and add the active class to the current/clicked button
for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", function() {
        var current = document.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
        this.className += " active";
    });
}




$.validator.setDefaults({
    submitHandler: function() {
        alert("submitted!");
    }
});

$(document).ready(function() {
    $("#validate_form").validate({
        rules: {
            y_name: "required",
            lastname: "required",
            username: {
                required: true,
                minlength: 2,
                lettersonly: true
            },
            password: {
                required: true,
                minlength: 5
            },
            confirm_password: {
                required: true,
                minlength: 5,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            },
            agree: "required"
        },
        messages: {
            y_name: "Please enter your firstname",
            lastname: "Please enter your lastname",
            username: {
                required: "Please enter a username",
                minlength: "Your username must consist of at least 2 characters",
                lettersonly: "name should be a characters"
            },
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 5 characters long"
            },
            confirm_password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 5 characters long",
                equalTo: "Please enter the same password as above"
            },
            email: "Please enter a valid email address",
            agree: "Please accept our policy"
        },
        errorElement: "em",
        errorPlacement: function(error, element) {
            // Add the `help-block` class to the error element
            error.addClass("help-block");

            if (element.prop("type") === "checkbox") {
                error.insertAfter(element.parent("label"));
            } else {
                error.insertAfter(element);
            }
        },
        highlight: function(element, errorClass, validClass) {
            $(element).parents(".col-sm-5").addClass("has-error").removeClass("has-success");
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).parents(".col-sm-5").addClass("has-success").removeClass("has-error");
        }
    });
});