$("nav li").on("click", function() {
    $("nav li").removeClass("active");
    $(this).addClass("active");
});


//Displays the Home page and hides all the other forms when user clicks on Home button
$("#s0").click(function () {
    $("#home").css("display", "block");
    $("#Create").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
    $("#myDIV").css("display", "none");
    $("tbody").children().remove();
});


//By click on-Invest Button, it displays the Invest form
function showAlertCreate(message, alerttype) {
    $('#alert_placeholder').append('<div id="alertdiv" class="alert ' + alerttype + '"><a class="close" data-dismiss="alert">×</a><span>' + message + '</span></div>');
    $('#alert').on('click', function () {
        $("#alertdiv").remove();
    });
}

//Displays the home page and hides other forms, on click of home button
$("#s1").click(function () {
    $("#Create").css("display", "block");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
    $("#home").css("display", "none");
    $("#myDIV").css("display", "none");
    $("tbody").children().remove();
});
//By click on Cancel button of Invest form--it goes to Home page
$("#c1").click(function () {
    $("#Create").css("display", "none")
    $("#home").css("display", "block");
});
//On clicking on Submit button, user data is passed to Servlet
$('#Create').on('submit', function (event) {
    var First_Name, Last_Name, Principal, Annual_rate, No_years, Periods;
    event.preventDefault();
    const data1 = {
        "First_Name": document.getElementById("First_Name").value,
        "Last_Name": document.getElementById("Last_Name").value,
        "Principal": document.getElementById("Principal").value,
        "Annual_rate": document.getElementById("Annual_rate").value,
        "No_years": document.getElementById("No_years").value,
        "Periods": document.getElementById("Periods").value,

    }
    document.getElementById("Create").reset();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/Invest-1.0-SNAPSHOT/invest",
        data: data1,
        success: succe_Fun,
        error: error_fun,
    });
    //checks whether the data in inserted successfully or not
    function succe_Fun(data, status, response) {
        if (data >= 1) {
            showAlertCreate("Data inserted Successfully", "alert alert-success");
        }
        else {
            showAlertCreate("Unable to insert Data", "alert alert-danger");
        }
    }
    //executed when user provides data in different format
    function error_fun(data, status, response) {
        showAlertCreate("Please...provide the appropiate input", "alert alert-info");
    }
});

//By clicking on Retrieve button, it displays the Retrieve form
function showAlert(message, alerttype) {

    $('#alert_placeholder_retrieve').append('<div id="alertdiv" class="alert ' + alerttype + '"><a class="close" data-dismiss="alert">×</a><span>' + message + '</span></div>');
    $('#alert').on('click', function () {
        $("#alertdiv").remove();

    });
}

//Displays the retrieve form
$("#s3").click(function () {
    $("#Delete").css("display", "none");
    $("#Retrieve").css("display", "block");
    $("#Create").css("display", "none");
    $("#home").css("display", "none");
    $("#retrive1").css("display", "none");
    $("#myDIV1").css("display", "none");
    $("#myInput").css("display", "none");
});

//On submitting the required retrival data, it displays the table and its contents.
$('#Retrieve').on('submit', function (event) {
    $("#myDIV1").css("display", "block");
    $("#myInput").css("display", "block");
    $("#myTable tr").remove();
    event.preventDefault();
    getData();      //It is used to get the required contents and fill in the table


    //Filtering the table based on given data in text field
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });

});

//used to get the data and set the table
function getData() {
    let data = document.getElementById("First").value;
    document.getElementById("Retrieve").reset();
    $.ajax({
        url: `http://localhost:8080/Invest-1.0-SNAPSHOT/invest?First_Name=${data}`,
        type: 'GET',
        dataType: 'json',
        success: function (res) {
            //used to check whether the object we get from servlet is empty or not
            if (jQuery.isEmptyObject(res)) {
                showAlert("No data Found", "alert alert-danger");
                $("#retrive").css("display", "none");
                $("#myInput").css("display", "none");
            } else {
                $("#retrive").css("display", "block");
                $("#myInput").css("display", "block");
                //set the data in rows of tables
                console.log(res);
                fillTicket(res);
            }
        },
        crossDomain: true,
        error: CustomerError
    });
    //error function
    function CustomerError(request, status, error) {
        showAlert("Unable to fetch the data", "alert alert-warning");
        $("#myInput").css("display", "none");
    }
    function fillTicket(res) {
        var table = document.getElementById("retrive");
        var tableRef = table.getElementsByTagName('tbody')[0];
        $(res).each(function (index, value) {
            var row = tableRef.insertRow(tableRef.rows.length);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);
            var cell6 = row.insertCell(5);
            var cell7 = row.insertCell(6);
            var cell8 = row.insertCell(7);

            cell1.innerHTML = value.uid;
            cell2.innerHTML = value.fname;
            cell3.innerHTML = value.lname;
            cell4.innerHTML = value.principal;
            cell5.innerHTML = value.arate;
            cell6.innerHTML = value.no_years;
            cell7.innerHTML = value.periods;
            $('#myTable tr > *:nth-child(1)').hide();
        });
    }
}
//displays messages of deletion form
function alert_placeholder_delete(message, alerttype) {

    $('#alert_placeholder_retrieve').append('<div id="alertdiv" class="alert ' + alerttype + '"><a class="close" data-dismiss="alert">×</a><span>' + message + '</span></div>');
    setTimeout(function () { // this will automatically close the alert and remove this if the users doesnt close it in 5 secs
        $("#alertdiv").remove();

    }, 5000);
}


//On clicking on submit button of admin page,it verifies the user
$("#check").click(function () {
    $("#myDIV").css("display", "none");
    const uname1 = document.getElementById("uname").value;
    const psw1 = document.getElementById("psw").value;
    event.preventDefault();
    document.getElementById("admin").reset();
    //passes the admin data to the servlet for verification
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/Invest-1.0-SNAPSHOT/check?Uname=${uname1}&password=${psw1}`,
        success: succe_Fun,
        error: error_fun,
    });
    //When used provide data in unappropiate format
    function error_fun(data, status, response) {
        alert("Plz..provide appropiate information");
    }
    // if user is authorized, then entire data is displayed
    function succe_Fun(data, status, response) {
        if (data >= 1) {
            //if uses is authorized then it displays delete form, table and hides all other forms
            document.getElementById('id01').style.display = 'none';
            $("#Delete").css("display", "block");
            $("#Retrieve").css("display", "none");
            $("#home").css("display", "none");
            $("#Create").css("display", "none");
            $("#retrive1").css("display", "block");
            //call the function to get the entire data
            getInfo();
        }
        else {
            alert("Enter the  valid username and password");
        }
    }
});


//This function is used to get the entire data and fill it in table
function getInfo() {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/Invest-1.0-SNAPSHOT/check`,
        dataType: 'json',
        crossDomain: true,
        error: CustomerError,
        success: function (res) {
            //used to check whether the object we get from servlet is empty or not
            if (jQuery.isEmptyObject(res)) {
                alert_placeholder_fail("No Data found", " alert alert-info");
                $("#home").css("display", "none");
                $("#myInput").css("display", "none");
                $("#Delete").css("display", "block");
            } else {
                $("#myDIV").css("display", "block");
                $("#retrive1").css("display", "block");
                $("#myInput").css("display", "block");
                $("#myTable1 tr").remove();
                fillTicket(res);
            }
        }
    });

    //error function
    function CustomerError(request, status, error) {
        alert_placeholder_fail("Unable to retrieve the data", " alert alert-info");
    }

    //used to fill the table
    function fillTicket(res) {
        var table = document.getElementById("retrive1");
        var tableRef = table.getElementsByTagName('tbody')[0];
        $(res).each(function (index, value) {
            var row = tableRef.insertRow(tableRef.rows.length);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);
            var cell6 = row.insertCell(5);
            var cell7 = row.insertCell(6);
            var cell8 = row.insertCell(7);


            cell1.innerHTML = value.uid;
            cell2.innerHTML = value.fname;
            cell3.innerHTML = value.lname;
            cell4.innerHTML = value.principal;
            cell5.innerHTML = value.arate;
            cell6.innerHTML = value.no_years;
            cell7.innerHTML = value.periods;
            cell8.innerHTML = "<button type='button' class='button1' onclick='deleteUser1(" + value.User_id + ")'>Delete</button>";
        });
    }
}
//When user clicks on delete button, it deletes the particular row and displays all other data
function deleteUser1(User_id) {
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/Invest-1.0-SNAPSHOT/invest?User_id=${User_id}`,
        success: succe_Fun,
        error: error_fun,
    });
    //After deletion of data, it displays the remaining rows
    function succe_Fun(data, status, response) {
        if (data >= 1) {
            $("#myTable1 tr").remove();
            alert_placeholder_delete("Deleted Successfully", "alert alert-success");
            getInfo();
        }
        else {
            alert_placeholder_delete("Deletion failed", "alert alert-danger");
        }
    }
    //Error function
    function error_fun(data, status, response) {
        alert_placeholder_delete("Unable to fetch the data", "alert alert-danger");
    }
}

//used to filter the table based on coloumn
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
            // $tbody.find('.no-result').remove();
            $tbody.find('tr').show();
        }
    });

    $('.filterable .filters input').keyup(function (e) {
        /* Ignore tab key */
        var code = e.keyCode || e.which;

        if (code == '9') return;

        /* Useful DOM data and selectors */
        var $input = $(this),
            inputContent = $input.val().toLowerCase(),
            $panel = $input.parents('.filterable'),

            column = $panel.find('.filters th').index($input.parents('th')),
            $table = $panel.find('.table'),
            $rows = $table.find('tbody tr');
        console.log($input);

        /* Dirtiest filter function ever ;) */
        var $filteredRows = $rows.filter(function () {
            var value = $(this).find('td').eq(column).text().toLowerCase();
            return value.indexOf(inputContent) === -1;
        });
        /* Clean previous no-result if exist */
        $table.find('tbody .no-result').remove();
        /* Show all rows, hide filtered ones */
        $rows.show();
        $filteredRows.hide();

        /* Prepend no-result row if all rows are filtered */
        if ($filteredRows.length === $rows.length) {
            $table.find('tbody').append($('<tr class="no-result text-center"><td colspan="' + $table.find('.filters th').length + '">No result found</td></tr>'));
        }
    });
});

//By clicking on Cancel button of admin page, it displays home page
$("#return").click(function () {
    document.getElementById('id01').style.display = 'none'
    $("#Retrieve").css("display", "none");
    $("#home").css("display", "block");
});

