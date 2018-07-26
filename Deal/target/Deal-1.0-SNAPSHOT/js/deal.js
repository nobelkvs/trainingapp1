
// For search
$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

/*
    To search based on table header column
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
        /* Clean previous result if exist */
        $table.find('tbody .no-result').remove();
        /* Show all rows */
        $rows.show();
        $filteredRows.hide();
        /* Prepend no-result row if all rows are filtered */
        if ($filteredRows.length === $rows.length) {
            $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="' + $table.find('.filters th').length + '">No result found</td></tr>'));
        }
    });
});




// Create function
$("#create_menu").click(function () {
    $('#create_mesg').text(" ");
    clearRetriveOnClickOfOther();
    $("#Create").css("display", "block");//display only create form
    $("#Update").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
});

$("#cancel_create").click(function () {
    $("#Create").css("display", "none");

});

// To reset message upon another deal creation
$("#deal_name").click(function () {
    $('#create_mesg').text(" ");
});

// Update function
$("#update_menu").click(function () {
    $('#update_mesg').text(" ");
    clearRetriveOnClickOfOther();
    $("#Update").css("display", "block");//display only update form
    $("#Create").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");

});

$("#cancel_update").click(function () {
    $("#Update").css("display", "none");

});

// To reset message upon another deal updation
$("#deal_id1").click(function () {
    $('#update_mesg').text(" ");
});


// Retrive function
$("#retrive_menu").click(function () {
    $('#retrive_mesg').text(" ");
    clearRetriveOnClickOfOther();
    $("#Retrieve").css("display", "block");//display only retrive form
    $("#Create").css("display", "none");
    $("#Update").css("display", "none");
    $("#Delete").css("display", "none");
});

$("#cancel_retrive").click(function () {
    $("#Retrieve").css("display", "none");
    $("#body").css("display", "none");
});


// Delete function
$("#delete_menu").click(function () {
    $('#delete_mesg').text(" ");
    clearRetriveOnClickOfOther();
    $("#Delete").css("display", "block");//display only delete form
    $("#Create").css("display", "none");
    $("#Update").css("display", "none");
    $("#Retrieve").css("display", "none");
});

$("#cancel_delet").click(function () {
    $("#Delete").css("display", "none");
});

// To reset message upon another deal deletion
$("#deal_id2").click(function () {
    $('#delete_mesg').text(" ");
});

/*
On submit event to create deal
*/
$('#Create').on('submit', function () {
    const dataObj = {
        "deal_name": document.getElementById("deal_name").value,
        "owner_name": document.getElementById("owner_name").value,
        "deal_value": document.getElementById("deal_value").value,
        "probability": document.getElementById("probability").value,
        "customer_name": document.getElementById("customer_name").value,
        "customer_contact": document.getElementById("customer_contact").value
    };

    console.log(dataObj);

    $.ajax({
        url: 'http://localhost:8080/Deal-1.0-SNAPSHOT/deal',
        type: 'POST',
        data: dataObj,
        crossDomain: true,
        success: created,
        error: createFail,

    });
    function created() {
        $('#create_mesg').addClass("success").html("Created successfully...");
        $('#Create')[0].reset();

    }
    function createFail(error) {
        $('#create_mesg').addClass("fail").html("Creation failure...." + error);
    }


});
/*
Event to update deal
*/
$('#Update').on('submit', function (event) {
    event.preventDefault();
    const updateObj = {
        "deal_id": document.getElementById("deal_id1").value,
        "deal_value": document.getElementById("deal_value1").value,
        "probability": document.getElementById("probability1").value
    };
    console.log(updateObj);
    updateObj2 = JSON.stringify(updateObj);
    console.log(updateObj2);
    console.log(typeof (updateObj2));
    $.ajax({
        url: 'http://localhost:8080/Deal-1.0-SNAPSHOT/deal',
        type: 'PUT',
        contentType: 'application/json',
        data: updateObj2,
        success: updateTable,
        crossDomain: true,
        error: updateError
    });
    $('#Update')[0].reset();
    function updateTable(data) {
        if (data != 0) {
            $('#update_mesg').addClass("success").html("Updated successfully...");
        } else {
            $('#update_mesg').addClass("fail").html("enter a valid Id..");
        }
    }
    function updateError(error) {

        $('#update_mesg').addClass("fail").html("Updation failure...." + error);
    }





});
/*
Event to delete from delete menu method
*/

$('#Delete').on('submit', function () {
    const deal_id = document.getElementById("deal_id2").value;
    const wurl = `http://localhost:8080/Deal-1.0-SNAPSHOT/deal?deal_id=${deal_id}`;
    $.ajax({
        url: wurl,
        type: 'DELETE',
        success: deleteRow,
        error: DeleteError
    });
    $('#Delete')[0].reset();
    function deleteRow(data) {
        if (data != 0) {
            $('#delete_mesg').addClass("success").html("Deleted successfully...");

        } else {
            $('#delete_mesg').addClass("fail").html("Enter a valid Id...");
        }
    }

    function DeleteError(error) {

        $('#delete_mesg').addClass("fail").html("Deletion Failure..." + error);
        $('#delete_mesg').removeClass("fail");
    }



});
/*
To refresh retrive menu event
*/
function clearRetriveOnClickOfOther() {
    $('#Retrieve')[0].reset();
    $(".panel-heading").css("display", "none");
    $('#retrive').css("display", "none");
    $('#Retrieve').css("display", "none");
    $('#mysearch').css("display", "none");
    $('#deletebtn').css("display", "none");

}

/*
Event to delete from retrive menu method
*/

$('#deletebtn').on('click', function () {
    var check = [];
    $("input[name='deleteCheckBox']:checked").each(function (i) {
        check[i] = $(this).val();
    }).get();
    jQuery('input:checkbox:checked').parents("tr").remove();    //removes attributes from UI after deleting

    const wurl = `http://localhost:8080/Deal-1.0-SNAPSHOT/deal?deal_id=${check}`;

    $.ajax({
        url: wurl,
        type: 'DELETE',
        success: deleteRow,
        error: DeleteError
    });
    $('#deletebtn')[0].reset();
    function deleteRow() {
        $('#retrive_mesg').addClass("success").html("Deleted successfully...");
        $('#Retrieve')[0].reset();
    }

    function DeleteError(request, status, error) {
        $('#retrive_mesg').addClass("fail").html("Deletion Failure...");
        alert('error' + request + status + error);
    }



});
/*
This function is to call the serv
*/
function getCustomer() {

    let deal_name = document.getElementById("deal_name22").value;
    const res = `http://localhost:8080/Deal-1.0-SNAPSHOT/deal?deal_name=${deal_name}`;

    $.ajax({
        url: res,
        type: 'GET',
        dataType: 'json',
        success: fillDeal,
        crossDomain: true,
        error: CustomerError
    });

    function CustomerError(request, status, error) {
        alert('error' + request + status + error);

    }

}
/*
This method is to fill table body
*/
function fillDeal(data, status, response) {

    console.log(data);
    if (data == []) {
        $('#retrive_mesg').addClass("fail").html("Enter a valid name...");
    }
    var table = document.getElementById("retrive");
    var colNumber = 8; // number of table columns
    $("#myTable").empty();
    var tableRef = table.getElementsByTagName('tbody')[0];

    $(data).each(function (index, value) {


        var chk = "<td><input type='checkbox' value='" + value.dealId + "' name='deleteCheckBox' id='chk_" + index + "' /></td>";
        var row = tableRef.insertRow(tableRef.rows.length);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);
        var cell6 = row.insertCell(5);
        var cell7 = row.insertCell(6);
        var cell8 = row.insertCell(7);

        cell1.innerHTML = chk;
        cell2.innerHTML = value.dealId;
        cell3.innerHTML = value.dealName;
        cell4.innerHTML = value.ownerName;
        cell5.innerHTML = value.dealValue;
        cell6.innerHTML = value.probability;
        cell7.innerHTML = value.customerName;
        cell8.innerHTML = value.customerContact;

        $("#deletebtn").css("display", "block");
        // It resets on Retrival of another deal
        $("#deal_name22").click(function () {
            $('#Retrieve')[0].reset();
            $(".panel-heading").css("display", "none");
            $('#retrive').css("display", "none");
            $('#mysearch').css("display", "none");
            $('#deletebtn').css("display", "none");
        });

    });



}

// Event for retrive menu option
$('#Retrieve').on('submit', function (event) {
    event.preventDefault();

    $("#mysearch").css("display", "block");
    $(".panel-heading").css("display", "block");
    $("#retrive").css("display", "block");
    getCustomer();

});