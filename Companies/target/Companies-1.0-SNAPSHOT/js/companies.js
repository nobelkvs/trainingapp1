$("#CompanyForm").submit(function (event) {
    event.preventDefault();
    var companyname, employees, HeadOffice;
    const insertData = {
        "companyname": document.getElementById("companyName").value,
        "employees": document.getElementById("Employees").value,
        "HeadOffice": document.getElementById("headofc").value
    }
    console.log(insertData);
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/Companies-1.0-SNAPSHOT/companies`,
        //dataType: "json",
        data: insertData,
        success: successMessage,
        error: errorMessage
    });
    $('#CompanyForm')[0].reset();
    function successMessage() {
        alert("success");
        console.log("succes");

    }

    function errorMessage() {
        alert("failure ");
        console.log("fail ");
    }

});
$("#RetrieveCompanyForm").submit(function (event) {
    event.preventDefault();

    const Headoffice = document.getElementById("Headoffice").value;

    $.ajax({
        type: "GET",
        url: `http://localhost:8080/Companies-1.0-SNAPSHOT/companies?Headoffice=${Headoffice}`,
        dataType: "json",
        success: FillCompanyTable,
        error: errorMessage
    });

    function FillCompanyTable(data, status, response) {
        if (data.length == 0) {
            alert("No Such record")
        } else {
            $("#table").css("display", "block");

            console.log("data inside FillCompanyTable " + data);
            var table = document.getElementById("retrieve");
            var colNumber = 4; // number of table columns
            $('#myTable').empty();
            var tableRef = table.getElementsByTagName('tbody')[0];

            $(data).each(function (index, value) {
                var chk = "<td><input type = 'checkbox' value='" + value.id + "' name = 'deleteCheckBox' id='chk_" + index + "'/></td>";
                var row = tableRef.insertRow(tableRef.rows.length);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);

                cell1.innerHTML = chk;
                cell2.innerHTML = value.id;
                cell3.innerHTML = value.name;
                cell4.innerHTML = value.employees;
                cell5.innerHTML = value.headOffice;
                //cell4.innerHTML = value.headOffice;
            });
        }
    }

    $('#RetrieveCompanyForm')[0].reset();

    function errorMessage() {
        alert("failure ");
        console.log("fail ");
    }
});


$('#deleteButton').on('click', function (event) {
    var type = [];
    $("input[name='deleteCheckBox']:checked").each(function (i) {
        type[i] = $(this).val();

    });
    var checked = jQuery('input:checkbox:checked').map(function () {
        return this.value;
    }).get();
    jQuery('input:checkbox:checked').parents("tr").remove();

    const weburl = `http://localhost:8080/Companies-1.0-SNAPSHOT/companies?companyIds=${type}`;
    $.ajax({
        url: weburl,
        type: 'DELETE',
        success: deleteRow,
        error: DeleteError
    });
    $('#deleteButton')[0].reset();
    function deleteRow() {
        alert("row deleted sucessfully");
        document.getElementById("myTable").deleteRow(type[i]);
    }
    function DeleteError(request, status, error) {

        alert('error' + request + status + error);
    }
});

$("#DeleteCompanyForm").submit(function (event) {
    event.preventDefault();
    const companyIds = document.getElementById("companyId").value;
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/Companies-1.0-SNAPSHOT/companies?companyIds=${companyIds}`,
        success: successM,
        error: errorM
    });
    function successM(data, status, response) {
        console.log(data)
        if (data === "success")
            alert("successfully deleted");
        else
            alert("It is not available in db")
    }
    $('#DeleteCompanyForm')[0].reset();
    function errorM() {
        alert("failure");
        console.log("fail");
    }
});
