$("#showHome").click(function () {

    $("#home").css("display", "block");
    $("#Retrive_form").css("display", "none");
    $("#Create_form").css("display", "none");
    $("#Delete_form").css("display", "none");
    $("#DeleteForm").css("display", "none");

});

//displaying create form
$("#showCreate").click(function () {

    $("#home").css("display", "none");
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
    $('form').each(function () {
            this.reset()
        });

});

//displaying retrireve form
$("#showRetrive").click(function () {

    $("#home").css("display", "none");
    $("#Retrive_form").css("display", "block").trigger("reset");
    $("#Create_form").css("display", "none");
    $("#Delete_form").css("display", "none");
    $("#DeleteForm").css("display", "none");

});

//Retrive data 
$("#Retrive_form").submit(function () {

    $("#table").css("display", "none");
    $("#RetreiveTable").empty();

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

        $('form').each(function () {
            this.reset()
        });

        if (data.length === 0) {
            $("#noData").modal('show');
        }
        else {

            $("#table").css("display", "block");
            $("#RetrieveTable").empty();
            var tableRef = table.getElementsByTagName('tbody')[0];

            console.log(tableRef)

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
            
            $('#retreiveTable').DataTable();
    
        }
 

    }  $('#retreiveTable').DataTable().destroy(); 
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
