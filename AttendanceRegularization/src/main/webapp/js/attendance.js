//Home form
$("#home").click(function () {
    $("#Home_form").css("display", "block");
    $("#message").css("display", "none");
    $("#Create_form").css("display", "none");
    $("#Retrieve_form").css("display", "none");
    $("#Delete_form").css("display", "none");
});

//Create form on click function
$("#Create").click(function () {
    $("#Create_form").css("display", "block");
    $("#Home_form").css("display", "none");
    $("#Retrieve_form").css("display", "none");
    $("#Delete_form").css("display", "none");
});
$("#cancel").click(function () {
    $("#create_form").css("display", "none");
});

//Create form submit function
$('#Create_form').submit(function (event) {
    event.preventDefault();
    const dataObj = {
        "id": document.getElementById("id").value,
        "date": document.getElementById("date").value,
        "checkintime": document.getElementById("checkintime").value,
        "checkouttime": document.getElementById("checkouttime").value
    }
    //Call ajax function for post method
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/AttendanceRegularization-1.0-SNAPSHOT/servlets`,
        data: dataObj,
        success: function (data) {
            console.log(data)
            if (data == 1) {

                $('#message').addClass("success").html("Successfully Created");
            }
            else {
                $('#message').addClass("failure").html("Already exist");
            }
        },
        error: function () {

            $('#message').addClass("failure").html("Failed to Create");

        },

    });
    $('form').each(function () {
        this.reset();
    });

});

//Retrieve form on click function
$("#Retrieve").click(function () {
    $("#Retrieve_form").css("display", "block");
    $("#message").css("display", "none");
    $("#Home_form").css("display", "none");
    $("#Create_form").css("display", "none");
    $("#Delete_form").css("display", "none");
});

$("#cancel").click(function () {
    $("#Retrieve_form").css("display", "none");

});
//Retrieve form submit function
$("#Retrieve_form").submit(function (event) {
    $("#rtvmessage").hide();

    event.preventDefault();

    $("#table").css("display", "block");

    const rdate = document.getElementById("Rdate").value;

    //Call ajax function for get method
    $.ajax({

        url: `http://localhost:8080/AttendanceRegularization-1.0-SNAPSHOT/servlets?rdate=${rdate}`,
        type: 'GET',
        dataType: 'json',
        contentType: "application/json",
        success: fillAttendance,
        error: Error
    });
    $('form').each(function () {
        this.reset();
    });

    function Error() {
        $("#table").css("display", "none");
        $('#rtvmessage').addClass("failure").html("give valid user id");
    }

    //Fill the table
    function fillAttendance(data) {

        if (data.length === 0) {

            $("#rtvmessage").show();
            $('#rtvmessage').addClass("failure").html("Date is not available");
        }
        else {
            var level1obj = data;
            var table = document.getElementById("retreiveTable");
            $("#table").css("display", "block");
            $("#myTable").empty();
            var tableRef = table.getElementsByTagName('tbody')[0];

            $(level1obj).each(function (index, value) {

                //Create table rows
                var chk = "<td><input type='checkbox' value='" + value.id + "' name='deleteCheckBox' id='chk_" + index + "' /></td>";

                var row = tableRef.insertRow(tableRef.rows.length);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);

                cell1.innerHTML = chk;
                cell2.innerHTML = value.id;
                cell3.innerHTML = value.date.year + "-" + value.date.month + "-" + value.date.day;
                cell4.innerHTML = value.checkintime;
                cell5.innerHTML = value.checkouttime;

            });
        }

    }

    $('#form').each(function () {
        this.reset();
    });
});
//Filter function
$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

//Delete form click function
$("#Delete").click(function () {
    $("#Delete_form").css("display", "block");

    $("#Home_form").css("display", "none");
    $("#Create_form").css("display", "none");
    $("#Retrieve_form").css("display", "none");

});
$("#dcancel").click(function () {
    $("#Delete_form").css("display", "none");

});
//Delete button on click function
$('#deletebtn').on('click', function () {
    $("#rtvmessage").empty();
    $("#rtvmessage").show();
    var type = [];

    $("input[name='deleteCheckBox']:checked").each(function (i) {

        type[i] = $(this).val();

    });
    jQuery('input:checkbox:checked').parents("tr").remove();

    //Call ajax function for delete method
    $.ajax({
        url: `http://localhost:8080/AttendanceRegularization-1.0-SNAPSHOT/servlets?id=${type}`,
        type: 'DELETE',
        success: deleteRow,
        error: DeleteError
    });

    function deleteRow(data) {
        if (data == 1) {
            $('#rtvmessage').addClass("success").html("Successfully deleted").css("color", "green");
        }
        else {
            $('#rtvmessage').addClass("failure").html("Already deleted");
        }
    }
    function DeleteError() {

        $('#rtvmessage').addClass("failure").html("Failed delete");
    }
});

//Delete form submit function
$('#Delete_form').submit(function (event) {
    event.preventDefault();

    const URole = document.getElementById("userRole").value;
    const eid = document.getElementById("Eid").value;

    //Call ajax function for delete method
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/AttendanceRegularization-1.0-SNAPSHOT/servletfilter?userRole=${URole}&id=${eid}`,
        success: function (data) {

            if (data >= 1)

                $('#dltmessage').addClass("failure").html("You can not delete");

            else {

                if (data === "success") {
                    $('#dltmessage').addClass("success").html("Successfully deleted");
                }
            }
        },
        error: function () {

            $('#dltmessage').addClass("failure").html("Failed to delete");
        },
    });
    $('form').each(function () {
        this.reset();
    });
});
