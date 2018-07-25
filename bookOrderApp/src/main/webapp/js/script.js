/*
    javascript for book html 

*/
// evnet for createOrder form 
$("#createOrder").click(function () {
    $('#create_msg').text(" ");
    $("#createForm").css("display", "block");
    $("#updateForm").css("display", "none");
    $("#retrieveForm").css("display", "none");
    $("#deleteForm").css("display", "none");

});

// event for submit button of create form
$("#createForm").submit(function (event) {
    event.preventDefault();

    // create object
    const createObj = {
        "orderBy": document.getElementById("orderBy").value,
        "bookName": document.getElementById("bookName").value,
        "quantity": document.getElementById("quantity").value

    }

    // make ajax call to server
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

    }).fail(function (data, status) {
        alert('error---' + status);
        console.log(+status);
    });
    $("#createForm")[0].reset();


});


$("#createCancel").click(function () {
    $("#createForm").css("display", "none");

});


// event for updateOrder nav item click
$("#updateOrder").click(function () {
    $('#update_msg').text(" ");
    $("#updateForm").css("display", "block");
    $("#createForm").css("display", "none");
    $("#retrieveForm").css("display", "none");
    $("#deleteForm").css("display", "none");


});

// event handler for submit of update form 
$("#updateForm").submit(function (event) {
    event.preventDefault();
    // alert("getting inputs...");

    // create update object
    const data1 = {
        "OrderId": document.getElementById("uOrderId").value,
        "quantity": document.getElementById("uQuantity").value
    }
    console.log(data1);
    updateObj = JSON.stringify(data1);
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
                $('#update_msg').addClass("fail").html("enter valid id...");
            }
        },
        error: function (error) {
            alert("error");
            console.log("error is---" + error);
        }
    });

    $("#updateForm")[0].reset();

});

// event for cancel buuton of update form
$("#Updatecancel").click(function (event) {
    event.preventDefault();
    $("#updateForm").css("display", "none");

});


// event for delete Order nav item click
$("#deleteOrder").click(function () {
    $('#delete_msg').text(" ");
    $("#deleteForm").css("display", "block");
    $("#updateForm").css("display", "none");
    $("#createForm").css("display", "none");
    $("#retrieveForm").css("display", "none");

});

// event for submit button of delete
$("#deleteForm").submit(function (event) {
    event.preventDefault();

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

// event for cancel button of delete form
$("#deleteCancel").click(function () {
    $("#deleteForm").css("display", "none");

});


// event for delete button 
$('#deleteBtn').on('click', function (event) {
    // alert("entered delete button event...");
    var type = [];
    $("input[name='deleteCheckBox']:checked").each(function (i) {
        type[i] = $(this).val();
    }).get();
    jQuery('input:checkbox:checked').parents("tr").remove();
    console.log("checked values are..." + type);

    const wurl = `http://localhost:8080/bookOrderApp-1.0-SNAPSHOT/book?orderId=${type}`;

    $.ajax({
        url: wurl,
        type: 'DELETE',
        success: deleteRow,
        error: DeleteError
    });

    // $('#deletebBtn')[0].reset();
    function deleteRow() {
        $('#deleteSuccess').addClass("success").html("Deleted successfully...");

        $('#deleteSuccess').html("<b>deal deleted sucessfully</b>");
    }

    function DeleteError(request, status, error) {

        alert('error' + request + status + error);
    }



});

// function to get order details 
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

    function errorRetrieve(status, error) {
        alert("error is--- " + status + error);
    }

}

// function to fill order table
function fillOrderTable(data, status, response) {

    console.log(data);
    if (data == " ") {
        alert("enter valid data");
    }
    $('#orderTableBody').empty();
    var table = document.getElementById("ordreTable");

    var tableRef = table.getElementsByTagName('tbody')[0];

    $(data).each(function (index, value) {

        var chk = "<td><input type='checkbox' value='" + value.orderId + "' name='deleteCheckBox' id='chk_" + index + "' /></td>";
        var row = tableRef.insertRow(tableRef.rows.length);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var cell6 = row.insertCell(5);



        cell1.innerHTML = chk;
        cell2.innerHTML = value.orderId;
        cell3.innerHTML = value.orderDate;
        cell4.innerHTML = value.bookName;
        cell5.innerHTML = value.authorName;
        cell6.innerHTML = value.quantity;

    });
    $('#deleteBtn').css("display", "block");

}
//  alert("Successfully Retrieved");


// event for retrieve order click
$("#retrieveOrder").click(function () {
    $("#retrieveForm").css("display", "block");
    $("#updateForm").css("display", "none");
    $("#createForm").css("display", "none");
    $("#deleteForm").css("display", "none");
    $('#deleteSuccess').text(" ");
    $("#myInput").css("display", "none");
    $("#ordreTable").css("display", "none");
    $('#deleteBtn').css("display", "none");

});

// event for retrieve form submit button
$("#retrieveForm").submit(function (event) {

    event.preventDefault();
    $("#myInput").css("display", "block");
    $("#ordreTable").css("display", "block");

    getOrder();
    $("#retrieveForm")[0].reset();
});

// event for retrieve form cancel button
$("#retriveCancel").click(function () {
    $("#retrieveForm").css("display", "none");

});