//It will recognize which link in navigation bar had clicked
$(".navbar div ul li a").click(function () {

    switch ($(this).attr("href").substring(1)) {

        case "newTask":
            displayCreateTaskForm();
            break;

        case "retrieve":
            displayRetrieveTaskForm();
            break;

        default:
            alert("please hit a valid link");
    }

});

// **********************************************************TaskCreationArea********************************************************************//
// this method is used to display the task creation form and get the data

function displayCreateTaskForm() {
    $("#searchTaskForm").attr("style", "display:none");// this is used to hide the search-form
    $("#retrieveTable").attr("style", "display:none");// hides the table
    $("#deleteButton").attr("style", "display:none");// hides the delete button
    $("#deleteMessage").attr("style", "display: none");// hides the delete message
    document.getElementById("searchForm").reset();// clears all fields of search-form
    document.getElementById("createTaskForm").reset();
    $("#message").attr("style", "display:none");
    $("#message").html("");
    $("#createTaskForm").attr("style", "margin: 0 auto;width: 80%;display:block");// now displaying the Task Creation Form


}
$("#createTaskForm").submit(function (event) {

    event.preventDefault();

    var taskName = document.getElementById("task").value;// receiving the data
    var category = document.getElementById("category").value;
    var dueDate = document.getElementById("dueDate").value;
    var time = document.getElementById("time").value;
    var owner = document.getElementById("owner").value;
    var priority = document.getElementById("priority").value;
    var status = document.getElementById("status").value;
    var relatedTo = document.getElementById("relatedTo").value;
    var relatedDeals = document.getElementById("relatedDeals").value;
    var taskObject = { // assigning the received data to the object
        "taskName": taskName,
        "category": category,
        "dueDate": dueDate,
        "time": time,
        "owner": owner,
        "priority": priority,
        "status": status,
        "relatedTo": relatedTo,
        "relatedDeals": relatedDeals
    };

    $("#createTaskForm").on('input', function () {
        $("#message").attr("style", "display:none");
    });

    createTask(taskObject); // calling the create method to create task inside database


});
function createTask(taskObject) {

    $.ajax({ // AJAX call to Servlet for task creation
        type: 'GET',
        url: 'CreateTaskServlet',
        data: taskObject,
        async: false,
        success: displayCreated,
        error: function () {
            alert("request failed");
        }
    });
}

function displayCreated(data) { // displaying the return message(either task created or not) from the server


    $("#message").attr("style", "font-size: 1.5cm; display: block");
    $("#message").html("");

    if (data.includes("Duplicate")) {
        $("#message").html("It is duplicate");
    } else if (data.includes("SQLException")) {
        $("#message").html("an error occured please try again");
    } else {
        $("#message").html(data);
    }

}
// **********************************************************TaskRetrievalArea*******************************************************************//

function displayRetrieveTaskForm() { // Displaying the Search Form to retrieve the data

    $("#createTaskForm").attr("style", "display:none");// hiding all the remaining forms and messages
    $("#message").attr("style", "display:none");
    $("#deleteMessage").html("");
    $("#message").html("");
    document.getElementById("createTaskForm").reset();// clearing the data of the creation form
    document.getElementById("searchForm").reset();
    $("#searchTaskForm").attr("style", "display:block");
}

// this function performs ajax call to the corresponding servlet which retrieves the data

$("#searchForm").submit(function (event) { // on submitting the form retrieving the data from the fields 

    event.preventDefault();

    var searchString = document.getElementById("searchFieldValue").value;// retrieving the search string

    $("#searchForm").on('input', function () {
        $("#deleteMessage").html = "";
        $("#deleteMessage").attr("style", "display:none");
    });

    retrieve(searchString);

});

function retrieve(searchString) {

    $.ajax({
        type: 'GET',
        url: 'RetrieveTaskServlet',
        dataType: 'json',
        data: "searchString=" + searchString,
        success: displayRetrieved,
        error: function (msg) {
            alert("retrieve request failed");
        }
    });
}

// this function performs the dispalying of corresponding data to the html page

function displayRetrieved(data) {

    if (!jQuery.isEmptyObject(data)) {

        $("#retrieveTable").attr("style", "display:block");

        document.getElementById("tableBody").innerHTML = "";

        var tableRef = document.getElementById("tableBody");

        $(data).each(function (index, value) {

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

            cell1.innerHTML = "<input name='selector[]' type='checkbox' value='" + value.taskId + "'>";
            cell2.innerHTML = value.taskId;
            cell3.innerHTML = value.taskName;
            cell4.innerHTML = value.category;
            cell5.innerHTML = value.owner;
            cell6.innerHTML = value.priority;
            cell7.innerHTML = value.status;
            cell8.innerHTML = value.dueDate;
            cell9.innerHTML = value.time;
            cell10.innerHTML = value.relatedTo;
            cell11.innerHTML = value.relatedDeals;

        });

        $("#deleteButton").attr("style", "margin-left:6.5cm;margin-top:2cm;display:block");

    } else if ($("#deleteMessage").html().includes("Successfully deleted")) {
        $("#deleteMessage").html("deleted");
        $("#retrieveTable").attr("style", "display:none");
        $("#deleteButton").attr("style", "display:none");

    } else {
        $("#retrieveTable").attr("style", "display:none");
        $("#deleteButton").attr("style", "display:none");
        $("#deleteMessage").attr("style", "color:red;font-size: 1cm; display: block")
        $("#deleteMessage").html("data not available");
    }
}

// this function is used to perform deletion of the checked records
function deleteTheChecked() {
    $("#deleteMessage").html("");
    $("#deleteMessage").attr("style", "display:none");
    var taskId;
    var val = [];
    $(':checkbox:checked').each(function (i) {
        val[i] = $(this).val();
        taskId = $(this).val();
        $.ajax({
            type: 'GET',
            url: 'DeleteTaskServlet',
            async: false,
            data: "taskId=" + taskId,
            success: deleteStatus,
            error: function () {
                alert("Delete request failed");
            }
        });

    });

}

// it shows the delete status of the checked records
function deleteStatus(msg) {
    var searchString = document.getElementById("searchFieldValue").value;
    $("#deleteMessage").attr("style", "color:red;font-size: 2cm; display: block")
    $("#deleteMessage").html(msg);
    retrieve(searchString);
}
