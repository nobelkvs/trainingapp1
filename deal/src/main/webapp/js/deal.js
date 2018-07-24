//for search
$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

//create function
$("#s1").click(function () {
    $('#create_mesg').text(" ");
    clearRetriveOnClickOfOther();
    $("#Create").css("display", "block");//display only create form
    $("#Update").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
});

$("#c1").click(function () {
    $("#Create").css("display", "none");

});

//update function
$("#s2").click(function () {
    $('#update_mesg').text(" ");
    clearRetriveOnClickOfOther();
    $("#Update").css("display", "block");//display only update form
    $("#Create").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");

});

$("#c2").click(function () {
    $("#Update").css("display", "none");

});

//retrive function
$("#s3").click(function () {
    $('#retrive_mesg').text(" ");
    clearRetriveOnClickOfOther();
    $("#Retrieve").css("display", "block");//display only retrive form
    $("#Create").css("display", "none");
    $("#Update").css("display", "none");
    $("#Delete").css("display", "none");
});

$("#c3").click(function () {
    $("#Retrieve").css("display", "none");
    $("#body").css("display", "none");

});

//delete function
$("#s4").click(function () {
    $('#delete_mesg').text(" ");
    clearRetriveOnClickOfOther();
    $("#Delete").css("display", "block");//display only delete form
    $("#Create").css("display", "none");
    $("#Update").css("display", "none");
    $("#Retrieve").css("display", "none");
});

$("#c4").click(function () {
    $("#Delete").css("display", "none");
});
/*
on submit event to create deal
*/
$('#Create').on('submit', function () {
    //clearRetriveOnClickOfOther();
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
    //$('#Create')[0].reset();
    function created() {
        $('#create_mesg').addClass("success").html("Created successfully...");
        $('#Create')[0].reset();

    }
    function createFail(request, status, error) {
        $('#create_mesg').addClass("fail").html("Creation failure....");
    }


});
/*
event to update deal
*/
$('#Update').on('submit', function (event) {
    //clearRetriveOnClickOfOther();
    //document.getElementById("retrive_mesg").innerHTML="";
    event.preventDefault();
    const updateObj = {
        "deal_id": document.getElementById("deal_id").value,
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
            $('#update_mesg').addClass("fail").html("enter a valid data..");
        }
    }
    function updateError(request, status, error) {

        $('#update_mesg').addClass("fail").html("Updation failure....");
    }





});
/*
event to delete from delete menu method
*/

$('#Delete').on('submit', function () {
    //clearRetriveOnClickOfOther();
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

    function DeleteError(request, status, error) {

        $('#delete_mesg').addClass("fail").html("Deletion Failure...");
    }



});
/*
to refresh retrive menu event
*/
function clearRetriveOnClickOfOther() {
    $('#Retrieve')[0].reset();
    //$("#myTable").empty();
    $('#retrive').css("display", "none");
    $('#Retrieve').css("display", "none");
    $('#myInput').css("display", "none");
    $('#deletebtn').css("display", "none");

}

/*
event to delete from retrive menu method
*/

$('#deletebtn').on('click', function (event) {
    var type = [];
    $("input[name='deleteCheckBox']:checked").each(function (i) {
        type[i] = $(this).val();
    }).get();
    jQuery('input:checkbox:checked').parents("tr").remove();    //removes attributes from UI after deleting

    const wurl = `http://localhost:8080/Deal-1.0-SNAPSHOT/deal?deal_id=${type}`;

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
this function is to call the serv
*/
function getCustomer() {

    let deal_name = document.getElementById("deal_name22").value;
    //alert(deal_name);
    const res = `http://localhost:8080/Deal-1.0-SNAPSHOT/deal?deal_name=${deal_name}`;

    $.ajax({
        url: res,
        type: 'GET',
        dataType: 'json',
        success: fillDeal,
        crossDomain: true,
        error: CustomerError
    });
    //$('#Retrieve')[0].reset();

    function CustomerError(request, status, error) {
        alert('error' + request + status + error);

    }

}
/*
this method is to fill table body
*/
function fillDeal(data, status, response) {

    console.log(data);
    var table = document.getElementById("retrive");
    var colNumber = 8; // number of table columns
    //alert("filldeal");
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


    });


}

//event for retrive menu option
$('#Retrieve').on('submit', function (event) {
    //event.preventDefault();
    $("#myInput").css("display", "block");
    $("#retrive").css("display", "block");
    getCustomer();

});