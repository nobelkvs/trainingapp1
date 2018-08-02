
// When User Wants to enter there own Label(By Selecting the 'Other' Option in Select Box)

$(document).ready(function () {
    $('select[name=things]').change(function () {
        if ($(this).val() == "") {
            $("#add").show();
        }
    });
});


//Table Filter In Retrieve Response form

$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

//Customized Alerts
function showalert(message,alerttype) {
          $('#alert_placeholder').append('<div id="alertdiv" class="alert ' +  alerttype + '"><a class="close" data-dismiss="alert">×</a><span>'+message+'</span></div>');
          $("#trigger").on('click', function() {
             $("#alertdiv").remove();
          });
      }

//Table Filter In Delete Response form

$(document).ready(function () {
    $("#myInput1").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#myTable1 tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

//By Click On CreatResponse -> Updating the Select Box List From DataBase (Dynamicaliy Adding Options To Select Box)
//Navbar Create Response

$("#create1").click(function () {
    $("#create_form1").css("display", "block");
    $("#retrieve_form1").css("display", "none");
    $("#delete_form1").css("display", "none");

    //Sending action as a Parameter to Controller
    const obj1 = {
        "action": "getLables"
    }

    $.ajax({
        type: "POST",
        url: `http://localhost:8080/CannedResponse-1.0-SNAPSHOT/servlets`,
        data: obj1,
        success: successMsg,
        error: function () {
             showalert("Lables failed to retrieve","alert-danger");
        }
    });

    //To Get the List of Lables Data

    function successMsg(data, status, response) {

        var LabelArray = data.substr(1, (data.length - 2));
        var strarr = LabelArray.split(",");
        for (var i = 0; i < strarr.length; i++) {
            $('#form_addlabel')
                .append($('<option>', { value: strarr[i].trim() })
                    .text(strarr[i].trim()));
        }
        $('#form_addlabel')
            .append($('<option value=""><button id = "otherValue">Other</button></option>'));
    }
    $('#form_addlabel').children().remove();
});


$("#cancel1").click(function () {
    $("#create_form1").css("display", "none");

});

//Navbar Retrieve Responses
$("#retrieve1").click(function () {
    $("#dtable1").css("display", "none");
    $("#ttable1").css("display", "none");
    $("#create_form1").css("display", "none");
    $("#retrieve_form1").css("display", "block");
    $("#delete_form1").css("display", "none");
    $("#Login").css("display", "none");

    const obj1 = {
        "action": "getLables"
    }
    //ajax call to get the labels from servlet to add options dynamically to select box.
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/CannedResponse-1.0-SNAPSHOT/servlets`,
        data: obj1,
        success: successMsg,
        error: function () {
            showalert("Lables failed to retrieve","alert-danger");
        }
    });

    function successMsg(data, status, response) {
        var labelsArr = data.substr(1, (data.length - 2));
        var strarr = labelsArr.split(",");
        for (var i = 0; i < strarr.length; i++) {
            $('#form_retrievelabel')
                .append($('<option>', { value: strarr[i].trim() })
                    .text(strarr[i].trim()));
        }

    }

    $('#form_retrievelabel').children().remove();

});

$("#cancel2").click(function () {
    $("#retrieve_form1").css("display", "none");

});

//Navbar Delete Responses
$("#delete1").click(function () {
    $("#dtable").css("display", "none");
    $("#ttable").css("display", "none");
    $("#create_form1").css("display", "none");
    $("#retrieve_form1").css("display", "none");
    $("#delete_form1").css("display", "block");

    const obj1 = {
        "action": "getLables"
    }

    $.ajax({
        type: "POST",
        url: `http://localhost:8080/CannedResponse-1.0-SNAPSHOT/servlets`,
        data: obj1,
        success: successMsg,
        error: function () {
            showalert("Lables failed to retrieve","alert-danger");
        }
    });

    //To Get the List of Lables Data

    function successMsg(data, status, response) {

        var LabelArray = data.substr(1, (data.length - 2));
        var strarr = LabelArray.split(",");
        for (var i = 0; i < strarr.length; i++) {
            $('#form_deletelabel')
                .append($('<option>', { value: strarr[i].trim() })
                    .text(strarr[i].trim()));
        }
    }
    $('#form_deletelabel').children().remove();

});

$("#cancel3").click(function () {
    $("#delete_form1").css("display", "none");

});



$('#create_form1').submit(function (event) {

    event.preventDefault();

    $("#add").hide();

    var utitle, umessage, ulabel, umarkpublic, vlable;

    $("input[type='checkbox']").on('click', function () {
        $(this).val(this.checked ? "YES" : "NO");
    })
    vlable = document.getElementById("form_addlabel").value;

    if (vlable == "") {
        vlable = document.getElementById("otherlabel").value;
        var status;
        //To add Other as Option at the end of the Select Box in Create Response Form
        $("#form_addlabel > option").each(function () {
            status = (this.value == vlable);
            if (status == true) {
                return false;
            }
            else
                status = false;
        });
        if (status != true) {

            var length1 = $('#form_addlabel').children('option').length;
            $("#form_addlabel option").eq(length1 - 1).before($("<option></option>").val(vlable).text(vlable));

        }
    }
    const dataObj = {
        "utitle": document.getElementById("form_title").value,
        "umessage": document.getElementById("form_message").value,
        "ulabel": vlable,
        "umarkpublic": document.getElementById("markPublic").value,
        "action": ""

    }

    //ajax call to create new Response.
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/CannedResponse-1.0-SNAPSHOT/servlets`,
        data: dataObj,
        success: successMessage,
        error: errorMessage
    });
    $('#form_message').val('').empty();
    function successMessage(data, status, response) {
        if (data === "Success") {
            showalert("Response Saved Successfully !!","alert-success");
        }
        else {
            showalert("The Title You entered is already existed Please try with another Title","alert-danger");
        }
    }
    function errorMessage(request, status, error) {
        showalert("Oops!! Response is Failed to Save ,Please Try Again","alert-danger");
    }

    $("#create_form1")[0].reset();

});



//Retrieve Form -- To Retrieve Responses -- Ajax call
$('#retrieve_form1').on('submit', function () {

    event.preventDefault();
    $("#ttable").css("display", "block");
    $("#dtable").css("display", "block");
    var role_user = "user";
    getResponses();
    function getResponses() {

        const v1 = document.getElementById("form_retrievelabel").value;
        $("#myTable").empty();

        $.ajax({
            url: `http://localhost:8080/CannedResponse-1.0-SNAPSHOT/servlets?ulabel=${v1}&Role=${role_user}`,
            type: 'GET',
            dataType: 'json',
            success: fillResponse,
            error: fillResponsesError
        });

    }
    function fillResponse(data, status, response) {
        if (data.length <= 0) {
            $("#dtable").hide();
            showalert("No Responses are Available","alert-danger")

        }
        else {

            $("#myTable").empty();
            var level1obj = data;
            var table = document.getElementById("response_table");
            var tableRef = table.getElementsByTagName('tbody')[0];

            $(data).each(function (index, value) {
                var row = tableRef.insertRow(tableRef.rows.length);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);

                cell1.innerHTML = value.title;
                cell2.innerHTML = value.message;
                cell3.innerHTML = value.label;
                cell4.innerHTML = value.mark_public;
                var up_date = value.update_date.day + "/" + value.update_date.month + "/" + value.update_date.year;
                cell5.innerHTML = up_date;
            });
        }
    }

    function fillResponsesError(request, status, error) {
        showalert("Oops!! Responses are failed to Load, Please Try again Later","alert-danger");
    }

});

//CheckBox Deletion-- For Multiple Deletion of rows in Delete Response Form

$('#deletebtn1').on('click', function (event) {
    var response_id = [];
    var role_admin = "admin";
    const user_id = document.getElementById("user_id").value;
    const pass = document.getElementById("pwd").value;
    $("input[name='deleteCheckBox1']:checked").each(function (i) {
        response_id[i] = $(this).val();
    });

    //For Removing the Checked Rows from UI table
    var checked = jQuery('input:checkbox:checked').map(function () {
        return this.value;
    }).get();
    jQuery('input:checkbox:checked').parents("tr").remove();

    const deleteUrl = `http://localhost:8080/CannedResponse-1.0-SNAPSHOT/servletFilter?admin1=${user_id}&passwd=${pass}&delete_uId=${response_id}&Role=${role_admin}`;

    $.ajax({
        url: deleteUrl,
        type: 'DELETE',
        success: deleteRow,
        error: deleteError
    });

    function deleteRow(data) {
        if (data === "adminloginfailed") {
           showalert("User Name and Password does not Matached Please Enter Correct Login Credential's","alert-danger");
        }
        else if (data === "Success") {
           showalert("Selected Rows are Deleted Successfully","alert-success");
        } else {
           showalert("Please Select the Rows to Delete","alert-danger")
        }
    };

    function deleteError(request, status, error) {
        showalert("Oops Failed To Deleted Please Try Again Later","alert-danger");
    }
    document.getElementById("user_id").value = "";
    document.getElementById("pwd").value = "";

});

//Deletion Form -- Ajax Call to delete responses

$('#delete_form1').submit(function (event) {

    $("#Login").show();

    event.preventDefault();
    $("#ttable1").css("display", "block");
    $("#dtable1").css("display", "block");

    var role_admin = "admin";
    const v2 = document.getElementById("form_deletelabel").value;
    getResponses();
    function getResponses() {
        $.ajax({
            url: `http://localhost:8080/CannedResponse-1.0-SNAPSHOT/servlets?ulabel=${v2}&Role=${role_admin}`,
            type: 'GET',
            dataType: 'json',
            success: fillResponse,
            error: fillResponsesError
        });

    }
    document.getElementById("form_deletelabel").value = "";
    function fillResponsesError() {
        showalert("failed to fill","alert-danger");
    }


    function fillResponse(data, status, response) {
        $("#myTable1").empty();
        var level1obj = data;
        var table = document.getElementById("response_table1");
        var tableRef = table.getElementsByTagName('tbody')[0];

        $(data).each(function (index, value) {
            var check = "<td><input type='checkbox' value='" + value.response_id + "' name='deleteCheckBox1'/></td>";
            var row = tableRef.insertRow(tableRef.rows.length);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);
            var cell6 = row.insertCell(5);
            var cell7 = row.insertCell(6);

            cell1.innerHTML = check;
            cell2.innerHTML = value.response_id;
            cell3.innerHTML = value.title;
            cell4.innerHTML = value.message;
            cell5.innerHTML = value.label;
            cell6.innerHTML = value.mark_public;
            var up_date = value.update_date.day + "/" + value.update_date.month + "/" + value.update_date.year;
            cell7.innerHTML = up_date;

        });
        $('#myTable1 tr>*:nth-child(2)').hide();

    }

});

