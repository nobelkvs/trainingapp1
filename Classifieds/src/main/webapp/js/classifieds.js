//Search Filter

$(document).ready(function () {

    $("#myInput").on("keyup", function () {

        var value = $(this).val().toLowerCase();

        $("#myTable tr").filter(function () {

            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)

        });

    });

});


//Toaster function

$(document).ready(function () {

    function toasterOptions() {
        toastr.options = {
            "closeButton": false,
            "debug": false,
            "newestOnTop": false,
            "progressBar": true,
            "positionClass": "toast-top-right",
            "preventDuplicates": true,
            "onclick": null,
            "showDuration": "100",
            "hideDuration": "1000",
            "timeOut": "2500",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "show",
            "hideMethod": "hide"
        };
    };
    toasterOptions();


});

//Script to show the password only when admin is selected

$(document).ready(function () {

    $('select[name = things1]').change(function () {

        if ($(this).val() == "admin") {

            $("#add1").show();

        }

        else if ($(this).val() == "user") {

            $("#add1").hide();

        }

        else {

            $("#add1").hide();

        }

    });


});

//Script to show and hide when a button is clicked

$("#home").click(function () {

    $("#homeForm").css("display", "block");
    $("#CreateForm").css("display", "none");
    $("#retriveForm").css("display", "none");
    $("#deleteForm").css("display", "none");
    $('#deleteForm1').css("display", "none");


});

$("#create").click(function () {

    $("#CreateForm").css("display", "block");
    $("#retriveForm").css("display", "none");
    $("#deleteForm").css("display", "none");
    $('#deleteForm1').css("display", "none");
    $("#homeForm").css("display", "none");
    $('#CreateForm')[0].reset();

});

$("#createCancel").click(function () {

    $("#CreateForm").css("display", "none");

});

$("#retrive").click(function () {

    $("#retriveForm").css("display", "block");
    $("#CreateForm").css("display", "none");
    $("#deleteForm").css("display", "none");
    $('#deleteForm1').css("display", "none");
    $("#tableDiv").css("display", "none");
    $("#homeForm").css("display", "none");
    $('#retriveForm')[0].reset();

});

$("#retrieveCancel").click(function () {

    $("#retriveForm").css("display", "none");

});

//Js code for create form

$('#CreateForm').submit(function (event) {

    event.preventDefault();

    const dataObj = {

        "classifiedDescription": document.getElementById("classifiedDesc").value,

        "category": document.getElementById("classifiedCategory").value,

        "city": document.getElementById("classifiedCity").value,

    }


    $.ajax({

        type: "POST",
        url: 'http://localhost:8080/Classifieds-1.0-SNAPSHOT/servlets',
        data: dataObj,
        success: function (data) {

            if (data == 1) {

                toastr.success("Classified created successfully");

            }

            else {

                toastr.error("Failed to create classified");

            }

            $('#CreateForm')[0].reset();

        },

        error: function (error) {

            toastr.error("Something went wrong");

        },

    });

});

//Delete button functionality

$('#deleteBtn').on('click', function (event) {

    $('#deleteForm1').css("display", "block");

    $('#delSubmit').on('click', function (event) {

        $("#add1").hide();

        //Array to add all the checked values
        var cid = [];

        $("input[name='deleteCheckBox']:checked").each(function (i) {

            cid[i] = $(this).val();

        });


        const role = document.getElementById("roleId1").value;
        const Password = document.getElementById("pwd1").value;
        const wurl = `http://localhost:8080/Classifieds-1.0-SNAPSHOT/servletFilter?cid=${cid}&role=${role}&password=${Password}`;

        $.ajax({

            url: wurl,

            type: 'DELETE',

            success: deleteRow,

            error: DeleteError
        });


        function deleteRow(data) {

            if (data === "unauthorizedAccess") {

                toastr.error("you are not able to delete");


            }

            else {

                if (data === "success") {

                    toastr.success("Classified(s) deleted successfully ");

                    //Function to delete the checked values from ui
                    var checked = jQuery('input:checkbox:checked').map(function () {

                        return this.value;

                    }).get();

                    jQuery('input:checkbox:checked').parents("tr").remove();

                    $('#deleteForm1').css("display", "none");



                }



            }

            $('#deleteForm1')[0].reset();


        }

        function DeleteError(request, status, error) {

            toastr.error('error' + request + status + error);
        }


    });
});

//Js code for retrieve form

$("#retriveForm").submit(function (event) {

    event.preventDefault();



    var dataObj3 = {
        "city": document.getElementById("searchCity").value
    };

    $.ajax({
        type: "GET",
        url: 'http://localhost:8080/Classifieds-1.0-SNAPSHOT/servlets',
        data: dataObj3,

        dataType: "json",
        contentType: 'application/json',
        success: fillClassifieds,
        error: function (error) {
            alert(error);
        }
    });


    function fillClassifieds(data, status, response) {

        if (data.length == 0) {

            toastr.error("No such classified found in Database");

            $("#tableDiv").css("display", "none");

            $('#retriveForm')[0].reset();

        }

        else {

            toastr.success("Classifieds retrieved successfully");

            $("#tableDiv").css("display", "block");

            $("#myTable").empty();

            var table = document.getElementById("classified");

            var colNumber = 4;

            var tableRef = table.getElementsByTagName('tbody')[0];

            $(data).each(function (index, value) {

                var chk = "<td><input type='checkbox' value='" + value.id + "' name='deleteCheckBox' id='chk_" + index + "' /></td>";

                var row = tableRef.insertRow(tableRef.rows.length);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);


                cell1.innerHTML = chk;
                cell2.innerHTML = value.id;
                cell3.innerHTML = value.classifiedDescription;
                cell4.innerHTML = value.category;
                cell5.innerHTML = value.city;

                $('#myTable tr > *:nth-child(2)').hide();

                $('#retriveForm')[0].reset();

            });

        }

    }
});







