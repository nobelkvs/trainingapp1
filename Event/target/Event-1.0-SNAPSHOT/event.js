/*********************  Register  ***************************************************************/


//Inserting into database
$('#Register').on('submit', function () {
    event.preventDefault();
    //alert("In insert method");
    //Creating Object of user data from UI based on ID
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
            $("#Register").css("display", "none");
            alert("Successfully Inserted the details");
        }
        //Error function to give a alert to the user
        function CustomerError(request, status, error) {
            alert("Ooops!!! problem with the server,try again later..");
        }
    }
});



/************************************** Details *****************************************************/
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

        /* function result1(data) {
             this.result = data;
             console.log(this.result);
         }*/
        //Error function to give error alert
        function CustomerError(request, status, error) {
            alert("Ooops!!! problem with the server,try again later..");
        }
    }
    //Function to insert the details in created table for display purpose
    function fillEventData(data) {
       // alert("in filling event");
       // console.log(data + " in fillEvent");

        var level1obj = data;
        console.log(level1obj);
        var table = document.getElementById("details_table");
        var colNumber = 11; // number of table columns
        $("#myTable").empty();

        var tableRef = table.getElementsByTagName('tbody')[0];
        //looping through data to insert into table
        $(level1obj).each(function (index, value) {
            //Creating cells for each column in a table
            var row = tableRef.insertRow(tableRef.rows.length);

            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);
            var cell6 = row.insertCell(5);
            var cell7 = row.insertCell(6);
            var cell8 = row.insertCell(7);
            var cell9 = row.insertCell(8);
            var cell10 = row.insertCell(9);
            var cell11 = row.insertCell(10);

            //inserting values in cells of table column
            cell1.innerHTML = value.eventId;
            cell2.innerHTML = value.eventName;
            cell3.innerHTML = value.priority;
            cell4.innerHTML = value.status;
            cell5.innerHTML = value.owner;
            cell6.innerHTML = value.startDate;
            cell7.innerHTML = value.startTime;
            cell8.innerHTML = value.endDate;
            cell9.innerHTML = value.endTime;
            cell10.innerHTML = value.emailAddress;
            //Hiding Event Id which should not visible to all
            $('#myTable tr > *:nth-child(1)').hide();
            //Assigning delete button to last field and calling function on click to delete the details
            cell11.innerHTML = "<button onclick='deleteEvent(" + value.eventId + ")'>Delete</button>"

        });

    }

});



/************************************* Remove  ********************************************************/

//Deleting from the Database by giving userRole and eventId through UI
$('#Remove').on('submit', function () {
    event.preventDefault();
    //alert("In delete Method ");
    deleteFromTable();

    function deleteFromTable() {
        //alert("in ajax call")
        const userRole = document.getElementById("user_role").value;
        const eventId = document.getElementById("event_id").value;

        const res = `http://localhost:8080/Event-1.0-SNAPSHOT/EventController?eventId=${eventId}&userRole=${userRole}`;

        //Making ajax call to hit the servlet Controller
        $.ajax({
            url: res,
            type: 'DELETE',
            success: result,
            crossDomain: true,
            error: CustomerError
        });

        //Clearing the fields of form
        $("#myRemoveForm")[0].reset();

        function result(data) {
            $("#Remove").css("display", "none")
            console.log(data + "hiiiiii");
            if (data == 0) {
                alert("You are not authorized to Delete");
            }
            else {
                alert("Successfully Deleted the details");
            }
        }
        function CustomerError(request, status, error) {

            alert("Ooops!!! problem with the server,try again....");
        }
    }
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

    function result(data) {
        $("#Remove").css("display", "none")
       // console.log(data + "hiiiiii");
        if (data == 0) {
            alert("You are not authorized to Delete");
        }
        else {
            alert("Successfully Deleted the details");
        }
        //To refresh the table after deleting
        $("#details").click();
    }
    function CustomerError() {

        alert("Ooops!!! problem with the server,try again later..");
    }
}

