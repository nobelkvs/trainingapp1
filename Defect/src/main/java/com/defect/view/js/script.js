//displaying create form
$("#showCreate").click(function () {

    $("#Create_form").css("display", "block").trigger("reset");
    $("#Retrive_form").css("display", "none");
    $("#RetrieveForm").css("display", "none");
    $("#Delete_form").css("display", "none");
    $("#DeleteForm").css("display", "none");

});

//insert Data
$("#Create_form").submit(function (event) {

    event.preventDefault();

    //json object
    const data = {
        "description": document.getElementById("description").value,
        "category": document.getElementById("category").value,
        "assignedTo": document.getElementById("assignedTo").value,
        "priority": document.getElementById("priority").value,
        "status": document.getElementById("status").value
    }

    //call ajax function for post method
    $.ajax({
        url: `http://localhost:8080/Defect-1.0-SNAPSHOT/servlets`,
        type: 'POST',
        data: data,
        success: function () {
            $("#successCreateModal").modal('show');
        },
        error: function () {
            $("#failCreateModal").modal('show');
        }
    });

});

//displaying delete form
$("#showDelete").click(function () {

    $("#Create_form").css("display", "none");
    $("#Retrive_form").css("display", "none");
    $("#RetrieveForm").css("display", "none");
    $("#Delete_form").css("display", "block").trigger("reset");

});

$(document).ready(function(){
    $('select[name = role1]').change(function(){
        if($(this).val() == "admin") {
            $('#pwd').show();
        }else if($(this).val() == "user"){
            $('#pwd').hide();
        }else {
            $('#pwd').hide();
        }
    });
});

//Delete data
$("#Delete_form").submit(function (event) {

    event.preventDefault();
    $('#pwd').hide();

    
    $("#Delete_form").css("display", "none");
    $("#DeleteForm").css("display", "block");
    $("#DeleteForm").submit(function (event) {

        event.preventDefault();

        const id = document.getElementById("Id").value;
        const role = document.getElementById("role").value;
        const Password = document.getElementById("password").value;

        var webServiceURL = `http://localhost:8080/Defect-1.0-SNAPSHOT/filter?UserRole=${role}&password=${Password}&Id=${id}`;

        //call ajax function for delete method
        $.ajax({
            url: webServiceURL,
            type: 'DELETE',
            success: function (data) {

                if (data >= 1) {
                    if (role != "admin") {
                        $("#unauthorisedAccess").modal('show');
                    } else {
                        $("#wrongPassword").modal('show');
                    }
                }
                else {
                    if (data === "success") {
                        $("#successDeleteModal").modal('show');
                    } else {
                        $("#failDeleteModal").modal('show');
                    }
                }

            },
            error: function () {
                alert("Enter the value...");
            }
        });
        // $('form').each(function () {
        //     this.reset()
        // });
    });
});


//displaying retrireve form
$("#showRetrive").click(function () {
    $("#Retrive_form").css("display", "block").trigger("reset");
    $("#Create_form").css("display", "none");
    $("#Delete_form").css("display", "none");
    $("#DeleteForm").css("display", "none");

});

//Retrive data 
$("#Retrive_form").submit(function () {

    event.preventDefault();

    const retrieve = {
        "assignedTo": document.getElementById("assigned").value
    }

    //call ajax function for get method
    $.ajax({
        url: `http://localhost:8080/Defect-1.0-SNAPSHOT/servlets`,
        type: 'GET',
        data: retrieve,
        dataType: 'json',
        success: toTable,
        error: Error
    });

    function Error(request, status, error) {
        alert('error' + request + status + error);

    }

    //for inserting retrieved data into table
    function toTable(data) {

        if (data.length === 0) {
            $("#noData").modal('show');
        }
        else {
            $('form').each(function () {
                this.reset()
            });
            $("#table").css("display", "block");
            $("#RetrieveTable").empty();
            var tableRef = table.getElementsByTagName('tbody')[0];

            $(data).each(function (index, value) {
                var row = tableRef.insertRow(tableRef.rows.length);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);
                var cell7 = row.insertCell(6);

                cell1.innerHTML = value.Id;
                cell2.innerHTML = value.description;
                cell3.innerHTML = value.category;
                cell4.innerHTML = value.assignedTo;
                cell5.innerHTML = value.priority;
                cell6.innerHTML = value.status;
                cell7.innerHTML = "<input type='checkbox' value='" + value.Id + "' name='check' id='check_" + index + "' />";

            });
        }
    }
});

//for filter in retrieve
$(document).ready(function () {
    $("#filter").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#RetrieveTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

//deleting data in retrieve form
$("#deleteMul").on('click', function (event) {

    event.preventDefault();
    var Id = [];
    $("input[name='check']:checked").each(function (i) {

        if ($(this).is(':checked')) {
            $("#RetrieveForm").css("display", "block").trigger("reset");
            $("#rolePwdSubmit").on('click', function (event) {

                event.preventDefault();

                const role = document.getElementById("role1").value;
                const Password = document.getElementById("password1").value;

                $("input[name='check']:checked").each(function (i) {
                    Id[i] = $(this).val();
                });

                var webServiceURL = `http://localhost:8080/Defect-1.0-SNAPSHOT/filter?UserRole=${role}&password=${Password}&Id=${Id}`;

                //call ajax function for delete method in retrieve form
                $.ajax({
                    url: webServiceURL,
                    type: 'DELETE',
                    success: function (data) {

                        if (data >= 1) {
                            if (role != "admin") {
                                $("#unauthorisedAccess1").modal('show');
                            } else {
                                $("#wrongPassword1").modal('show');
                            }
                        }
                        else {
                            if (data === "success") {
                                jQuery('input:checkbox:checked').map(function () {
                                    return this.value;
                                }).get();
                                jQuery('input:checkbox:checked').parents("tr").remove();
                                $("#successDeleteModal1").modal('show');
                            } else {
                                $("#failDeleteModal1").modal('show');
                            }
                        }

                    },
                    error: function () {
                        alert("select the value");
                    }
                });

            });
        } else {
            $('#RetrieveForm').removeAttr('disabled');
            alert("not checked");
        }


    });


});
