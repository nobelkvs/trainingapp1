
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

//for alert message
function showalert(message, alerttype) {

    $('#alert_placeholder').append('<div id="alertdiv" class="alert ' + alerttype + '">' + message + '</span></div>');
    $('#trigger_event').on('click', function () {
        $("#alertdiv").remove();
    });
}

// Add active class to the current button (highlight it)
var header = document.getElementById("navbarNav");
var btns = header.getElementsByClassName("nav-link");
for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", function () {
        var current = document.getElementsByClassName("active");
        current[0].className = current[0].className.replace(" active", "");
        this.className += " active";
    });
}


$("#home_menu").click(function () {
    $("#home").css("display", "block");//display only home page content
    $("#Create").css("display", "none");
    $("#Update").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
});

// Create function
$("#create_menu").click(function () {
    $("#Create").css("display", "block");//display only create form
    $("#home").css("display", "none");
    $("#Update").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
});

$("#cancel_create").click(function () {
    $("#Create").css("display", "none");

});


// Update function
$("#update_menu").click(function () {
    $('#Update')[0].reset();
    $("#Update").css("display", "block");//display only update form
    $("#home").css("display", "none");
    $("#Create").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");

});

$("#cancel_update").click(function () {
    $("#Update").css("display", "none");

});



// Retrive function
$("#retrive_menu").click(function () {
    clearRetriveOnClickOfOther();
    $("#Retrieve").css("display", "block");//display only retrive form
    $("#home").css("display", "none");
    $("#Create").css("display", "none");
    $("#Update").css("display", "none");
    $("#Delete").css("display", "none");
});

$("#cancel_retrive").click(function () {
    $("#Retrieve").css("display", "none");
    $("#body").css("display", "none");
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
        success: created,
        crossDomain: true,
        error: createFail,

    });
    function created() {
        showalert("Created successfully", "alert-success");
        $('#Create')[0].reset();

    }
    function createFail(error) {
        showalert("Creation failure...." + error, "alert-danger");
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
        success: update,
        crossDomain: true,
        error: updateFail
    });

    function update(data) {
        if (data != 0) {
            showalert("Updated Successfully", "alert-success");
            $('#Update')[0].reset();
        } else {
            showalert("enter a valid Id..", "alert-danger");
            $('#deal_id1').val(" ");
        }
    }
    function updateFail(error) {
        showalert("Updation failure...." + error, "alert-danger");
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
        success: deleteSuccess,
        error: DeleteFail
    });
    function deleteSuccess() {
        showalert("Deleted successfully...", "alert-success");
        $('#Retrieve')[0].reset();
    }

    function DeleteFail(error) {
        showalert("Deletion Failure..." + error, "alert-danger");
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

    function CustomerError(error) {
        showalert("Retrive Failure..." + error, "alert-danger");

    }

}
/*
This method is to fill table body
*/
function fillDeal(data) {

    console.log(data);
    if (data == []) {
        showalert("Enter a valid name...", "alert-danger");
    }
    var table = document.getElementById("retrive");
    var colNumber = 8; // number of table columns
    $("#myTable").empty();
    var tableRef = table.getElementsByTagName('tbody')[0];

    $(data).each(function (index, value) {


        var chk = "<td><input type='checkbox' value='" + value.dealId + "' name='deleteCheckBox' id='chk_" + index + "' /></td>";
        var row = tableRef.insertRow(tableRef.rows.length);

        row.insertCell(0).innerHTML = chk;
        row.insertCell(1).innerHTML = value.dealId;
        row.insertCell(2).innerHTML = value.dealName;
        row.insertCell(3).innerHTML = value.ownerName;
        row.insertCell(4).innerHTML = value.dealValue;
        row.insertCell(5).innerHTML = value.probability;
        row.insertCell(6).innerHTML = value.customerName;
        row.insertCell(7).innerHTML = value.customerContact;

        $("#deletebtn").css("display", "block");


        // It resets on Retrival of another deal
        $("#deal_name22").click(function () {
            $('#Retrieve')[0].reset();
            $(".panel-heading").css("display", "none");
            $('#retrive').css("display", "none");
            $('#deletebtn').css("display", "none");
        });

    });


}

// Event for retrive menu option
$('#Retrieve').on('submit', function (event) {
    event.preventDefault();
    $(".panel-heading").css("display", "block");
    $("#retrive").css("display", "block");
    getCustomer();

});