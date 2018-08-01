/*
    Javascript for book html
*/

$("nav li").on("click", function () {
    $("nav li").removeClass("active");
    $(this).addClass("active");
});

// Event for createOrder form
$("#createOrder").click(function () {
    $('#checkQuantity').text(" ");
    $("#createForm").css("display", "block");
    $("#updateForm").css("display", "none");
    $("#retrieveForm").css("display", "none");
    $("#deleteForm").css("display", "none");
});

// Event for submit button of create form
$("#createForm").submit(function (event) {
    event.preventDefault();

    // Create object
    const createObj = {
        "orderBy": document.getElementById("orderBy").value,
        "bookName": document.getElementById("bookName").value,
        "quantity": document.getElementById("quantity").value
    }

    if (createObj.quantity <= 0) {
        $('#checkQuantity').html("<b>please enter more than 1 book</b>");
    }
    else {

        // Make ajax call to server
        $.ajax({
            url: "http://localhost:8080/bookOrderApp-1.0-SNAPSHOT/book",
            type: 'POST',
            data: createObj,
            crossDomain: true,
        }).done(function (result) {

            console.log("returned result is.." + result);
            if (result == 0) {

                showalert("enter a valid book name...", "alert-fail");

            } else {

                showalert("order created successfully...", "alert-success");
            }

        }).fail(function (status) {
            console.log("error is..." + status);
        });
    }
    $("#createForm")[0].reset(); // Clearing the input fields of create form

});

// Event for cancel button of create form
$("#createCancel").click(function () {
    $("#createForm").css("display", "none");
});



function showalert(message, alerttype) {

    $('#alert_placeholder').append('<div id="alertdiv" class="alert ' + alerttype + '"><a class="close" data-dismiss="alert">×</a><span>' + message + '</span></div>');
    $("html").click(function () { // This will close the alert and remove this if the users click wherever on the window
        $("#alertdiv").remove();
    });
}





// Event for updateOrder nav item click
$("#updateOrder").click(function () {
    $('#update_msg').text(" ");
    $('#checkBookQuantity').text(" ");
    $("#updateForm").css("display", "block");
    $("#createForm").css("display", "none");
    $("#retrieveForm").css("display", "none");
    $("#deleteForm").css("display", "none");

});

// Event handler for submit of update form
$("#updateForm").submit(function (event) {

    event.preventDefault();

    // Create update object
    const data1 = {
        "OrderId": document.getElementById("uOrderId").value,
        "quantity": document.getElementById("uQuantity").value
    }
    console.log(data1);

    if (data1.quantity <= 0) {
        $('#checkBookQuantity').html("<b>please enter more than 1 book</b>")
    }
    else {
        updateObj = JSON.stringify(data1); // Converting data1 object to json
        console.log(updateObj);
        console.log(typeof (updateObj));


        $.ajax({
            url: "http://localhost:8080/bookOrderApp-1.0-SNAPSHOT/book",
            type: "PUT",
            contentType: "application/json",
            data: updateObj,
            success: function (result) {
                if (result != 0) {
                    console.log("returned result is..." + result);

                    showalert("successfully updated...", "alert-success");

                } else {

                    showalert("id does not exist...", "alert-danger");

                }
            },
            error: function (error) {

                showalert("error is---'+error+'", "alert-danger");
                console.log("error is---" + error);
            }
        });

        $("#updateForm")[0].reset();
    }

});

// Event for cancel button of update form
$("#Updatecancel").click(function (event) {
    event.preventDefault();
    $("#updateForm").css("display", "none");

});


// Event for cancel button of delete form
$("#deleteCancel").click(function () {
    $("#deleteForm").css("display", "none");

});


// Event for delete button in retrieved order table
$('#deleteBtn').on('click', function (event) {

    event.preventDefault();

    var idArray = [];
    $("input[name='deleteCheckBox']:checked").each(function (i) {
        idArray[i] = $(this).val();
    }).get();
    jQuery('input:checkbox:checked').parents("tr").remove();
    console.log("checked values are..." + idArray);

    const wurl = `http://localhost:8080/bookOrderApp-1.0-SNAPSHOT/book?orderId=${idArray}`;

    $.ajax({
        url: wurl,
        type: 'DELETE',
        success: deleteRow,
        error: DeleteError
    });

    function deleteRow() {

        showalert("Deleted successfully...", "alert-success");

    }

    function DeleteError() {

        showalert("please select book...", "alert-danger");

    }



});

// Function to get order details
function getOrder() {
    const name = document.getElementById("bAuthorName").value;
    console.log("input value:" + name);


    var webServiceURL = `http://localhost:8080/bookOrderApp-1.0-SNAPSHOT/book?authorName=${name}`;
    $.ajax({
        url: webServiceURL,
        type: 'GET',
        dataType: 'json',
        success: fillOrderTable,
        error: errorRetrieve
    });

    function errorRetrieve() {
        showalert("error while retrieving book..." + '" +error+ "', "alert-success");
    }

}

// Function to fill order table
function fillOrderTable(data) {

    console.log(data);
    if (data == " ") {
        showalert("no orders available on that book.." + '" +error+ "', "alert-danger");
    }
    $('#orderTableBody').empty(); // Clearing the table body data

    var table = document.getElementById("ordreTable");

    var tableRef = table.getElementsByTagName('tbody')[0];

    $(data).each(function (index, value) {

        var chk = "<td><input type='checkbox' value='" + value.orderId + "' name='deleteCheckBox' id='chk_" + index + "' /></td>";

        var row = tableRef.insertRow(tableRef.rows.length);

        /*
        Fill data into table cells
        */
        row.insertCell(0).innerHTML = chk;
        row.insertCell(1).innerHTML = value.orderId;
        row.insertCell(2).innerHTML = value.orderDate;
        row.insertCell(3).innerHTML = value.bookName;
        row.insertCell(4).innerHTML = value.quantity;

    });
    $("#filterButton").css("display", "block");
    $('#deleteBtn').css("display", "block");

}


// Event for retrieve_order click
$("#retrieveOrder").click(function () {
    $("#retrieveForm").css("display", "block");
    $("#updateForm").css("display", "none");
    $("#createForm").css("display", "none");
    $("#deleteForm").css("display", "none");

    /* Hide search_bar,table,filter and delete button */
    $("#myInput").css("display", "none");
    $("#ordreTable").css("display", "none");
    $("#filterButton").css("display", "none");
    $('#deleteBtn').css("display", "none");

});

// Event for retrieve form submit button
$("#retrieveForm").submit(function (event) {

    event.preventDefault();

    $("#myInput").css("display", "block");
    $("#ordreTable").css("display", "block");

    getOrder(); // Calling getOrder function to fill table


});

// Event for retrieve form cancel button
$("#retrieveCancel").click(function () {
    $("#retrieveForm").css("display", "none");
});

// Event for search bar of order table
$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#orderTableBody tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

$("input[required]").parent("label").addClass("required");


/*
Event for filter button
*/
$(document).ready(function () {
    $('.filterable .btn-filter').click(function () {
        var $panel = $(this).parents('.filterable'),
            $filters = $panel.find('.filters input'),
            $tbody = $panel.find('.table tbody');
        if ($filters.prop('disabled') == true) {
            $filters.prop('disabled', false);
            $filters.first().focus();
        } else {
            $filters.val('').prop('disabled', true);
            $tbody.find('.no-result').remove();
            $tbody.find('tr').show();
        }
    });

    $('.filterable .filters input').keyup(function (e) {
        /* Ignore tab key */
        var code = e.keyCode || e.which;
        if (code == '9') return;

        var $input = $(this),
            inputContent = $input.val().toLowerCase(),
            $panel = $input.parents('.filterable'),
            column = $panel.find('.filters th').index($input.parents('th')),
            $table = $panel.find('.table'),
            $rows = $table.find('tbody tr');

        var $filteredRows = $rows.filter(function () {
            var value = $(this).find('td').eq(column).text().toLowerCase();
            return value.indexOf(inputContent) === -1;
        });

        /* Clean previous no-result if exist */
        $table.find('tbody .no-result').remove();

        /* Show all rows, hide filtered ones  */
        $rows.show();
        $filteredRows.hide();

        /* Prepend no-result row if all rows are filtered */
        if ($filteredRows.length === $rows.length) {
            $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="' + $table.find('.filters th').length + '">No result found</td></tr>'));
        }
    });
});