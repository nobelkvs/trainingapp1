// This is used to create leave by clicking on submit
$("#Create").submit(function () {
    //alert("create");
    event.preventDefault();
    //alert("entered createForm");
    var FromDate, ToDate, LeaveType;
   // alert("hello");
    //Creating the object for the input form data
    const createObj = {
        "EmpId": document.getElementById("EmpId").value,
        "FromDate": document.getElementById("FromDate").value,
        "ToDate": document.getElementById("ToDate").value,
        "LeaveType": document.getElementById("LeaveType").value,
        "Comments": document.getElementById("Comments").value,
        "NoOfDays": document.getElementById("noofdays").value

    }
   // alert(createObj);
    console.log(createObj);
    $.ajax({

        url: "http://localhost:8080/LeaveManageSystem-1.0-SNAPSHOT/servlet",
        type: 'POST',
        // dataType: 'json',
        data: createObj,
        success: fillEmployeeTable,
        error: errorRetrieve
    });
   // alert("outside of ajax call");


    function fillEmployeeTable() {
        alert("success");
        $('#create_msg').addClass("success").html("Created successfully...")
    }
    function errorRetrieve(request, status, error) {
        alert('error' + request + status + error);
        console.log(request + status + error);
    }


});
//This is used for deleting the input form data by clicking on delete button after retrieving

$('#deletebtn').on('click', function (event) {

   var type = [];
   $("input[name='deleteCheckBox']:checked").each(function (i) {
   type[i] = $(this).val();
   }).get();
    jQuery('input:checkbox:checked').parents("tr").remove();


const url = `http://localhost:8080/LeaveManageSystem-1.0-SNAPSHOT/servlet?EmpId=${type}`;

$.ajax({
url: url,
type: 'DELETE',
success: deleteRow,
error: DeleteError
});

$('#deletebtn')[0].reset();
function deleteRow() {
//alert("hiiiii");
$('#retrive_mesg').addClass("success").html("Deleted successfully...").Style.css(position=center);
//$('#retrive_mesg').html("Deleted successfully...");
getCustomer();
}



function DeleteError(request, status, error) {

alert('error' + request + status + error);
}
});

//('#deletebtn')[0].empty();

function getCustomer(){
var v1 = document.getElementById("empId").value;
//alert("enetered id is.."+v1);
$.ajax({
        url: `http://localhost:8080/LeaveManageSystem-1.0-SNAPSHOT/servlet?EmpId=${v1}`,
        type: 'GET',
        dataType: 'json',
        // data: data,
        crossDomain: true,
        success: fillLeave,
        error: CustomerError,
    });

function CustomerError(request, status, error) {

        alert('error' + request + status + error);
    }       
}
function fillLeave(data,status,response) {
       // alert("in filling ticket");
       
        console.log("data" + data);
   var table = document.getElementById("retriveId1");
         var colNumber = 8; // number of table columns

     var tableRef = table.getElementsByTagName('tbody')[0];
      //  alert("foreach");
     $(data).each(function (index,value) {

           // alert("in fillling data");

    var chk = "<td><input type='checkbox' value='" + value.EmpId + "' name='deleteCheckBox' id='chk_" + index + "' /></td>";
    var row = tableRef.insertRow(tableRef.rows.length);
    alert(tableRef.rows.length)
   // alert("in chk");
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    var cell6 = row.insertCell(5);
    var cell7 = row.insertCell(6);
   

    cell1.innerHTML = chk;
    cell2.innerHTML = value.EmpId;
    cell3.innerHTML = value.FromDate;
    cell4.innerHTML = value.ToDate;
    cell5.innerHTML = value.LeaveType;
    cell6.innerHTML = value.Comments;
    cell7.innerHTML = value.No_Of_Days;            

    });
}
//This is used for retrieving the input form data

$('#Retrieve').on('submit', function (event) {

//alert("hello");
event.preventDefault();
$("#retriveId1").css("display", "block");
$("#myInput").css("display", "block");
getCustomer();

});

//This is used to delete the input form data


$("#Delete").submit(function () {

    // alert(document.getElementById("Emp_Id").value);
    event.preventDefault();
   // alert("entered deleteform");
    var EmpId = document.getElementById("Emp_Id").value;
    console.log(EmpId);
    // alert("hello");

    //alert(createObj.ticketId);
    $.ajax({

        url: `http://localhost:8080/LeaveManageSystem-1.0-SNAPSHOT/servlet?EmpId=${EmpId}`,
        type: 'DELETE',
        //dataType: 'json',
        // data: createObj1,
        success: fillEmployeeTable,
        error: errorRetrieve
    });
   // alert("outside of ajax call ");
    function fillEmployeeTable() {

        alert("success ");

    }
    function errorRetrieve(request, status, error) {
        alert('error' + request + status + error);
        console.log(request + status + error);
    }
   
});




