/*
    Javascript for book html

*/
// Event for createOrder form
$("#createOrder").click(function () {
    $('#create_msg').text(" ");
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
                $('#create_msg').addClass("fail").html("enter a valid book name...");
            } else {
                $('#create_msg').addClass("success").html("order successfully created");
            }

        }).fail(function (status) {
            alert('error---' + status);
            console.log(+status);
        });
    }
    $("#createForm")[0].reset(); // clearing the input fields of create form


});

// Event for cancel button of create form
$("#createCancel").click(function () {
    $("#createForm").css("display", "none");

});


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
    // alert("getting inputs...");

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
        updateObj = JSON.stringify(data1); // Converting data object to json
        console.log(updateObj);
        console.log(typeof (updateObj));


        // alert("about to update");

        $.ajax({
            url: "http://localhost:8080/bookOrderApp-1.0-SNAPSHOT/book",
            type: "PUT",
            contentType: "application/json",
            data: updateObj,
            success: function (result) {
                if (result != 0) {
                    console.log("returned result is..." + result);
                    $('#update_msg').addClass("success").html("successfully updated");
                } else {
                    $('#update_msg').addClass("fail").html("id does not exist.. please enter valid id...");
                }
            },
            error: function (error) {
                alert("error");
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


// Event for delete Order nav item click
$("#deleteOrder").click(function () {
    $('#delete_msg').text(" ");
    $("#deleteForm").css("display", "block");
    $("#updateForm").css("display", "none");
    $("#createForm").css("display", "none");
    $("#retrieveForm").css("display", "none");

});

// Event for submit button of delete
$("#deleteForm").submit(function (event) {
    event.preventDefault();
    $('#delete_msg').text(" ");

    const deleteObj = {
        "orderId": document.getElementById("orderId").value
    }

    console.log("object is--" + deleteObj.orderId);

    $.ajax({
        url: `http://localhost:8080/bookOrderApp-1.0-SNAPSHOT/book?orderId=${deleteObj.orderId}`,
        type: "DELETE",
        data: deleteObj,
        success: function (result) {
            console.log("result is..." + result);
            if (result != 0) {
                $('#delete_msg').addClass("success").html("successfully deleted");
            } else {
                $('#delete_msg').addClass("fail").html("no order available on that id..");
            }
        },
        error: function (status, error) {
            alert("error");
            console.log("error is---" + status + error);
        }
    });
    $("#deleteForm")[0].reset();


});

// Event for cancel button of delete form
$("#deleteCancel").click(function () {
    $("#deleteForm").css("display", "none");

});


// Event for delete button in retrieved order table
$('#deleteBtn').on('click', function () {

    $("#retrieve_msg").text(" ");

    // alert("entered delete button event...");
    var idArray = [];
    $("input[name='deleteCheckBox']:checked").each(function (i) {
        idArray[i] = $(this).val();
    }).get();
    jQuery('input:checkbox:checked').parents("tr").remove();
    console.log("checked values are..." + type);

    const wurl = `http://localhost:8080/bookOrderApp-1.0-SNAPSHOT/book?orderId=${idArray}`;

    $.ajax({
        url: wurl,
        type: 'DELETE',
        success: deleteRow,
        error: DeleteError
    });

    // $('#deletebBtn')[0].reset();
    function deleteRow() {
        $('#deleteSuccess').addClass("success").html("Deleted successfully...");

        // $('#deleteSuccess').html("<b>deal deleted sucessfully</b>");
    }

    function DeleteError(request, status, error) {

        alert('error' + request + status + error);
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

    function errorRetrieve(error) {
        $("#retrieve_msg").addClass("fail").html("error ehile retrieving book..." + error);
    }

}

// Function to fill order table
function fillOrderTable(data) {

    console.log(data);
    if (data == " ") {
        alert("no orders available on that book..");
    }
    $('#orderTableBody').empty(); // clearing the table body data

    var table = document.getElementById("ordreTable");

    var tableRef = table.getElementsByTagName('tbody')[0];

    $(data).each(function (index, value) {

        var chk = "<td><input type='checkbox' value='" + value.orderId + "' name='deleteCheckBox' id='chk_" + index + "' /></td>";

        /*
        Filling the empty cells into table
        */
        var row = tableRef.insertRow(tableRef.rows.length);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var cell6 = row.insertCell(5);

        /*
        Fill data into table cells
        */
        cell1.innerHTML = chk;
        cell2.innerHTML = value.orderId;
        cell3.innerHTML = value.orderDate;
        cell4.innerHTML = value.bookName;
        cell5.innerHTML = value.authorName;
        cell6.innerHTML = value.quantity;

    });
    $("#filterButton").css("display", "block");
    $('#deleteBtn').css("display", "block");
    $("#retrieve_msg").addClass("success").html("retrieved successfully...");
}
//  alert("Successfully Retrieved");


// Event for retrieve_order click
$("#retrieveOrder").click(function () {
    $("#retrieveForm").css("display", "block");
    $("#updateForm").css("display", "none");
    $("#createForm").css("display", "none");
    $("#deleteForm").css("display", "none");


    $('#deleteSuccess').text(" ");
    $('#retrieve_msg').text(" ");

    /* hide search_bar,table,filter and delete button */
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
    $("#bAuthorName")[0].reset();

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