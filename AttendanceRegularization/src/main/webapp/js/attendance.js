//Create form submit function
$('#Create_form').submit(function (event) {
    event.preventDefault();

    var id, date, checkintime, checkouttime;
    const dataObj = {
        "id": document.getElementById("id").value,
        "date": document.getElementById("date").value,
        "checkintime": document.getElementById("checkintime").value,
        "checkouttime": document.getElementById("checkouttime").value,
    }
    console.log(dataObj);

    //Call ajax function for post method
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/AttendanceRegularization-1.0-SNAPSHOT/servlets`,
        data: dataObj,
        success: function () {
            alert("successfully created");
        },
        error: function () {
            alert("failed to create");
        },

    });
    $('form').each(function () {
        this.reset();
    });

});

//Retrieve form submit function
$("#Retrieve_form").submit(function (event) {

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
        error: CustomerError
    });
    $('form').each(function () {
        this.reset();
    });

    function CustomerError() {
        alert("give valid user id");
        console.log('error');

    }

    //Fill the table
    function fillAttendance(data, status, response) {
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

    $('#form').each(function () {
        this.reset();
    });
});

//Delete button on click function
$('#deletebtn').on('click', function (event) {
    var type = [];

    $("input[name='deleteCheckBox']:checked").each(function (i) {

        type[i] = $(this).val();

    });

    var checked = jQuery('input:checkbox:checked').map(function () {
        return this.value;
    }).get();
    jQuery('input:checkbox:checked').parents("tr").remove();

    //Call ajax function for delete method
    $.ajax({
        url: `http://localhost:8080/AttendanceRegularization-1.0-SNAPSHOT/servlets?id=${type}`,
        type: 'DELETE',
        success: deleteRow,
        error: DeleteError
    });

    $('#deletebtn')[0].reset();
    function deleteRow() {

        alert("deleted sucessfully");
    }
    function DeleteError(request, status, error) {

        alert('error');
    }
});

//Delete form submit function
$('#Delete_form').submit(function (event) {
    event.preventDefault();

    const Role1 = document.getElementById("userRole").value;
    const id1 = document.getElementById("Eid").value;

    console.log(id1);

    //Call ajax function for delete method
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/AttendanceRegularization-1.0-SNAPSHOT/servletfilter?userRole=${Role1}&id=${id1}`,
        success: function (data, status, response) {
            console.log(data)
            alert("success")
            if (data === "success")
                alert("Successfully Deleted");
        },
        error: function () {
            alert("failed to delete");
        },

    });
    $('form').each(function () {
        this.reset();
    });
});
