//This is used to activate the navbars buttons after clicking on that

$("nav li").on("click", function() {
    $("nav li").removeClass("active");
    $(this).addClass("active");
  });


// This is used to create leave by clicking on submit

$('#Create').submit(function () {   
 event.preventDefault();
    
    //Creating the object for the input form data
    const createObj ={
        "empId": document.getElementById("empId").value,
        "empName": document.getElementById("empName").value,
        "fromDate": document.getElementById("FromDate").value,
        "toDate": document.getElementById("ToDate").value,
        "leaveType": document.getElementById("LeaveType").value,
        "comments": document.getElementById("Comments").value,
    }
   
    $.ajax({
         url: "http://localhost:8080/LeaveManageSystem-1.0-SNAPSHOT/servlet",
        type: 'POST',
        data: createObj,
        success: createRow,
        error: createError
    });
   
    function createRow() {
      
        $('#create_msg').addClass("success").html("Applied Leave  Successfully...");
    }
    function createError(error) {
       
        console.log("something went wrong"+ error);
    }

});


//This is used for deleting the input form data by clicking on delete button after retrieving

$('#deletebtn').on('click', function (event) {
    var type = [];
    $("input[name='deleteCheckBox']:checked").each(function (i) {
        type[i] = $(this).val();
    }).get();
    jQuery('input:checkbox:checked').parents("tr").remove();

    const url = `http://localhost:8080/LeaveManageSystem-1.0-SNAPSHOT/servlet?empName=${type}`;
    $.ajax({
        url: url,
        type: 'DELETE',
        success: deleteRow,
        error: DeleteError
    });

    function deleteRow() {
      
        $('#retrive_msg').addClass("success").html("Deleted Successfully...").Style.css(position = center);
      
        getEmployeeLeave();
    }
    function DeleteError(request, status, error) {

        console.log('something went wrong' + request + status + error);
    }
});

function getEmployeeLeave() {
    var v1 = document.getElementById("EmpName").value;   
    $.ajax({
        url: `http://localhost:8080/LeaveManageSystem-1.0-SNAPSHOT/servlet?empName=${v1}`,
        type: 'GET',
        dataType: 'json', 
        success:applyLeave,
        crossDomain:true,
        error:LeaveError
    
    });  

    function LeaveError(error)  {
        console.log("something went wrong"+error);
    }
}

function applyLeave(data) {
    document.myCreateForm.reset();
    $("#myTable").empty();
    console.log("data" + data);
    var table = document.getElementById("retriveId1");
    var tableRef = table.getElementsByTagName('tbody')[0];  
    $(data).each(function (index, value) {
        var chk = "<td><input type='checkbox' value='" + value.empName + "' name='deleteCheckBox' id='chk_" + index + "' /></td>";
        var row = tableRef.insertRow(tableRef.rows.length);
      
      // alert(" "+value.empId+value.empName);
        row.insertCell(0).innerHTML = chk;
        row.insertCell(1).innerHTML = "#";
        row.insertCell(2).innerHTML = value.empName;      
        row.insertCell(3).innerHTML = value.FromDate;
        row.insertCell(4).innerHTML = value.ToDate;
        row.insertCell(5).innerHTML = value.LeaveType;
        row.insertCell(6).innerHTML = value.Comments;
       
    });
}

//This is used for retrieving the input form data

$('#Retrieve').on('submit', function (event) {
    event.preventDefault();
    $("#retriveId1").css("display", "block");
    $("#myInput").css("display", "block");
    getEmployeeLeave();

});

//This is used to delete the leave

// $("#Delete").submit(function () {   
//     event.preventDefault();
//   //reading employee id from ui
//     var v1 = document.getElementById("empName").value;
//    console.log("delete form"+v1);
//     alert("name"+v1);
//     $.ajax({

//         url: `http://localhost:8080/LeaveManageSystem-1.0-SNAPSHOT/servlet?EmpName=${v1}`,
//         type: 'DELETE',
//         success: msg,
//         error: errorDelete
//     });

//     function msg(data){
//        alert("resp"+data);
//        alert(data=='success'?'withdrawn successfully':'failed to withdraw');
//     }
   
//     function errorDelete(request, status, error) {
       
//         console.log("Error while deleting"+request + status + error);
//     }

// });
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
            $tbody.find('.no-result').remove();
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
        /* Dirtiest filter function ever ;) */
        var $filteredRows = $rows.filter(function () {
            var value = $(this).find('td').eq(column).text().toLowerCase();
            return value.indexOf(inputContent) === -1;
        });
        /* Clean previous no-result if exist */
        $table.find('tbody .no-result').remove();
        /* Show all rows, hide filtered ones (never do that outside of a demo ! xD) */
        $rows.show();
        $filteredRows.hide();
        /* Prepend no-result row if all rows are filtered */
        if ($filteredRows.length === $rows.length) {
            $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="' + $table.find('.filters th').length + '">No result found</td></tr>'));
        }
    });
});


// This script is used for filter the data 

$(document).ready(function(){
    $("#myInput").on("keyup", function() {
      var value = $(this).val().toLowerCase();
      $("#myTable tr").filter(function() {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    })
  });
  $("input[required]").parent("label").addClass("required");
  
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
