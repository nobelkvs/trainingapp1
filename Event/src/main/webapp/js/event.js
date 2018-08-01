/******************* Home page *********************** */

//By clicking on Home in UI home page will be displayed
$("#home").click(function () {
    $("#Register").css("display", "none");
    $("#Details").css("display", "none");
    $("#displayTable").css("display", "none");
    $("#Home").css("display", "block");
});

//Highlighting the navbar fields on click event
$("nav li").on("click",function(){
$("nav li").removeClass("active");
$(this).addClass("active");
});

/*********************  Register  ***************************************************************/


//By clicking on register in UI register form will be displayed
$("#register").click(function () {
    $("#Register").css("display", "block");
    $("#Details").css("display", "none");
    $("#displayTable").css("display", "none");
    $("#Home").css("display", "none");
});

$("#c1").click(function () {
    $("#Register").css("display", "none");

});


//Inserting into database
$('#Register').on('submit', function () {
    event.preventDefault();
    //alert("In insert method");
    //Creating json Object of user data from UI to servlet controller
    var insertObj = {
        "eventName": document.getElementById("event_name").value,
        "priority": document.getElementById("priority").value,
        "status": document.getElementById("status").value,
        "owner": document.getElementById("owner_name").value,
        "startDate": document.getElementById("start_date").value,
        "startTime": document.getElementById("start_time").value,
        "endDate": document.getElementById("end_date").value,
        "endTime": document.getElementById("end_time").value,
        "emailAddress": document.getElementById("email_addrs").value
    }

    //calling function to insert the data in a database
    insertIntoDatabase(insertObj);


    function insertIntoDatabase(insertObj) {
        //console.log(insertObj);
        //alert("in ajax")
        //Assigning servlet url to a variable
        const res = `http://localhost:8080/Event-1.0-SNAPSHOT/EventController`;

        //Making ajax call to hit the servlet Controller
        $.ajax({
            url: res,
            type: 'POST',
            data: insertObj,
            success: result,
            crossDomain: true,
            error: CustomerError
        });

          //Clearing the fields of form
                $("#myRegisterForm")[0].reset();
                //Success function result to display the message in UI
                function result() {
                    //$("#Register").css("display", "none");
                    $("#msgRegister").html("<b>Successfully Registered for the Event</b>").addClass("successmsg");
                    $("#msgRegister").delay(5000).fadeOut('slow');
                }
                //Error function to give a alert to the user
                function CustomerError(request, status, error) {
                    $("#msgRegister").html("<b>Ooops!!! problem with the server,try again later..</b>").addClass("errormsg");
                    $("#msgRegister").delay(5000).fadeOut('slow');
                }
    }
});



/************************************** Details *****************************************************/

//By clicking on details event details will be displayed
$("#details").click(function () {
    $("#Register").css("display", "none");
    $("#Details").css("display", "block");
    $("#displayTable").css("display", "block");
    $("#Home").css("display", "none");
});


//Showing Details in UI
$('#details').click(function () {
    // alert("entered");
    event.preventDefault();
    //Calling function to get the event details in table
    getEventDetails();

    //Function to get the Event Table Details
    function getEventDetails() {
        // alert("In ajax call");
        const res = `http://localhost:8080/Event-1.0-SNAPSHOT/EventController`;

        //Making ajax call to hit the servlet Controller
        $.ajax({
            url: res,
            type: 'GET',
            dataType: 'json',
            success: fillEventData,
            crossDomain: true,
            error: CustomerError
        });

        //Error function to give error alert
        function CustomerError(request, status, error) {
            $("#msgDeatails").html("<b>Ooops!!! problem with the server,try again later..</b>").addClass("errormsg");
        }
    }
    //Function to insert the details in created table for display purpose
    function fillEventData(data) {
        // alert("in filling event");
        // console.log(data + " in fillEvent");
        var table = document.getElementById("details_table");
        var colNumber = 11; // number of table columns
        $("#myTable").empty();
        var tableRef = table.getElementsByTagName('tbody')[0];
        //looping through data to insert into table
        $(data).each(function (index, value) {
            //Creating cells for each column in a table
            var row = tableRef.insertRow(tableRef.rows.length);

            row.insertCell(0).innerHTML = value.eventId;
            row.insertCell(1).innerHTML = value.eventName;
            row.insertCell(2).innerHTML = value.priority;
            row.insertCell(3).innerHTML = value.status;
            row.insertCell(4).innerHTML = value.owner;
            row.insertCell(5).innerHTML = value.startDate;
            row.insertCell(6).innerHTML = value.startTime;
            row.insertCell(7).innerHTML = value.endDate;
            row.insertCell(8).innerHTML = value.endTime;
            row.insertCell(9).innerHTML = value.emailAddress;
            //Assigning delete button to last field and calling function on click to delete the details
            var cell11 = row.insertCell(10).innerHTML = "<button onclick='deleteEvent(" + value.eventId + ")'>Delete</button>";
            //hiding eventId column
            $('#myTable tr > *:nth-child(1)').hide();
        });

    }

});

//For searching table data on UI
$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});


/************Delete from displayed data in table ************************************/

//Function called from onclick button of delete in table
function deleteEvent(eventId) {
    //Accessing user role as input
    let userRole = prompt("Please Enter user Role");
    deleteFromTable(eventId, userRole);

}
//Function to hit java servlet and delete the details based on id and userRole
function deleteFromTable(eventId, userRole) {
    // alert("in ajax call");
    const res = `http://localhost:8080/Event-1.0-SNAPSHOT/EventController?eventId=${eventId}&userRole=${userRole}`;

    //Making ajax call to hit the servlet Controller
    $.ajax({
        url: res,
        type: 'DELETE',
        success: result,
        crossDomain: true,
        error: CustomerError
    });

        //Success function to give a message to the user
        function result(data) {
            //$("#Remove").css("display", "none")
            if (data == 0) {
                $("#deleteMsg").html("<b>You are not authorized to delete</b>").addClass("errormsg");
                $("#deleteMsg").delay(5000).fadeOut('slow');
            }
            else {
                $("#deleteMsg").html("<b>Successfully Deleted the details</b>").addClass("successmsg");
                $("#deleteMsg").delay(5000).fadeOut('slow');
            }
            //To refresh the table after deleting
            $("#details").click();
        }
        //Error function to give a message to the user
        function CustomerError() {

            $("#deleteMsg").html("<b>Ooops!!! problem with the server,try again later..<b>").addClass("errormsg");
            $("#deleteMsg").delay(5000).fadeOut('slow');
        }
}

/************ Date validater for start date less than end date *********************/

$("#end_date").change(function () {
    var startDate = document.getElementById("start_date").value;
    var endDate = document.getElementById("end_date").value;

    if ((Date.parse(endDate) <= Date.parse(startDate))) {
        $("#date_msg").css("display", "block");
        document.getElementById("end_date").value = "";
    }
    else {
        $("#date_msg").css("display", "none");
    }
});




