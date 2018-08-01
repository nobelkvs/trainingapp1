// Event for create form
$("#CompanyForm").submit(function (event) {
    event.preventDefault();
    var companyname, employees, HeadOffice;
    const insertData = {
        "companyname": document.getElementById("companyName").value,
        "employees": document.getElementById("Employees").value,
        "HeadOffice": document.getElementById("headofc").value
    }
  
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
        var timeOut = 2
        jQuery('#message').text("successfully created").fadeIn()
        jQuery('#message').css("display", "block")
        setTimeout(function() {
        jQuery('#message').fadeOut()
        jQuery('#message').css("display", "none")
        }, timeOut * 1000);
    }
    

    // function successMessage() {
       
    //     $('#message').addClass("success").html("successfully created");
    //     setTimeout(function() {
    //         jQuery('#message').fadeOut()
    //         jQuery('#message').css("display", "none")
    //         }, 5 * 100);
    //     //alert("success1");
    //     //$('#successCreateModal').modal('show');
    //    // alert("succ },30000);
    // }
    function errorMessage() {
        $('#message').addClass("failure").html("successfully created");

    }

});


// Event for retrieve form
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
            $('#RetrieveMessage').addClass("failure").html("No Such Record Found");
        } else {
            $("#table").css("display", "block");
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
                //var cell5 = row.insertCell(4);

                cell1.innerHTML = chk;
                //cell2.innerHTML = value.id;
                cell2.innerHTML = value.name;
                cell3.innerHTML = value.employees;
                cell4.innerHTML = value.headOffice;
                $('#RetrieveMessage').addClass("success").html("   ");

            });
        }
    }
    // $('#selectAll').click(function(e){
    //     var table= $(e.target).closest('button');
    //     $('td input:checkbox',table).prop('checked',this.checked);
    // });

    $('#RetrieveCompanyForm')[0].reset();

    function errorMessage() {
        alert("failure ");

    }
});

// Event for delete button in retrieve form
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

    

    function deleteRow(data) {
       
      if (data == 1){
       
        var timeOut = 2
        jQuery('#RetrieveMessage').text("successfully Deleted").fadeIn()
        jQuery('#RetrieveMessage').css("display", "block")
        setTimeout(function() {
        jQuery('#RetrieveMessage').fadeOut()
        jQuery('#RetrieveMessage').css("display", "none")
        }, timeOut * 1000);
     }
    }
 
    function DeleteError(request, status, error) {

        $('#RetrieveMessage').addClass("failure").html("failed to delete");
    }
});






//**************************************************************************************/
 // Search Filter Script
 $(document).ready(function () {
    (function ($) {
        $('#filter').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.searchable tr').hide();
            $('.searchable tr').filter(function () {
                return rex.test($(this).text());
            }).show();

        })

    }(jQuery));
});

//script for asteric
$("input[required]").parent("label").addClass("required");




//***** */

$("#Home").click(function () {
    $("#HomeForm").css("display", "block");
    $("#CompanyForm").css("display", "none");
    $("#RetrieveCompanyForm").css("display", "none");
    $("#DeleteCompanyForm").css("display", "none ");
});


$("#insertShow").click(function () {
    $("#HomeForm").css("display", "none");
    $("#CompanyForm").css("display", "block");
    $("#RetrieveCompanyForm").css("display", "none");
    $("#DeleteCompanyForm").css("display", "none ");
});
$("#cancel").click(function () {
    $("#CompanyForm").css("display", "none");

});

$("#retriveShow").click(function () {
    $("#RetrieveCompanyForm").css("display", "block");
    $("#CompanyForm").css("display", "none");
    $("#DeleteCompanyForm").css("display", "none");
    $("#HomeForm").css("display", "none");

});

$("#Retrivecancel").click(function () {
    $("#RetrieveCompanyForm").css("display", "none");
    $('#retrieve').css('hide');

});

