//To display the create form to take the input from the user when the user clicks on add policy.
$("#create").click(function () {
    displayMsg("");
    $("#addForm").css("display", "block");
    $("#addForm")[0].reset();
    $("#retrieveForm ").css("display", "none");
    $("#deleteForm ").css("display", "none");
    $("#tableformat").css("display", "none");
    $("#updateForm ").css("display", "none");
    $("#searchDiv").css("display", "none");
});
//To hide the form when the user clicks on the cancel button of the create form. 
$("#createCancel").click(function () {
    displayMsg("");
    $("#addForm ").css("display", "none");
    $("#tableformat").css("display", "none");
    $("#searchDiv").css("display", "none");
});
//To display the update form to take the input from the user when the user clicks on add policy.
$("#update").click(function () {
    displayMsg("");
    $("#retrieveForm").css("display", "none");
    $("#deleteForm ").css("display", "none");
    $("#addForm ").css("display", "none");
    $("#updateForm ").css("display", "block");
    $("#updateForm ")[0].reset();
    $("#tableformat").css("display", "none");
    $("#searchDiv").css("display", "none");
});
//To hide the form when the user clicks on the cancel button of the update form. 
$("#updateCancel").click(function () {
    displayMsg("");
    $("#updateForm ").css("display", "none");
    $("#tableformat").css("display", "none");
    $("#searchDiv").css("display", "none");
});
//To display the retrieve form to take the input from the user when the user clicks on add policy.
$("#retrieve").click(function () {
    displayMsg("");
    $("#retrieveForm").css("display", "block");
    $("#retrieveForm")[0].reset();
    $("#deleteForm ").css("display", "none");
    $("#addForm ").css("display", "none");
    $("#updateForm ").css("display", "none");
    $("#searchDiv").css("display", "none");
    $("#tableformat").css("display", "none");
});
//To hide the form when the user clicks on the cancel button of the retrieve form.
$("#retriveCancel").click(function () {
    displayMsg("");
    $("#retrieveForm ").css("display", "none");
    $("#tableformat").css("display", "none");
    $("#searchDiv").css("display", "none");
});
//To display the delete form to take the input from the user when the user clicks on delete policy.
$("#delete").click(function () {
    displayMsg("");
    $("#deleteForm").css("display", "block");
    $("#deleteForm")[0].reset();
    $("#retrieveForm ").css("display", "none");
    $("#addForm ").css("display", "none");
    $("#retriveTable").css("display", "none");
    $("#updateForm ").css("display", "none");
    $("#searchDiv").css("display", "none");
});
//To hide the form when the user clicks on the cancel button of the delete form.
$("#deleteCancel").click(function () {
    displayMsg("");
    $("#deleteForm ").css("display", "none");
    $("#retriveTable").css("display", "none");
    $("#searchDiv").css("display", "none");
});
// To display all the policies when the clicks on see all the policies
$("#retrieveAll").click(function () {
    displayMsg("");
    $("#deleteForm").css("display", "none");
    $("#retrieveForm ").css("display", "none");
    $("#addForm ").css("display", "none");
    $("#retriveTable").css("display", "block");
    $("#updateForm ").css("display", "none");
});
//To display a message whether the operation is succeded or not.
function displayMsg(msg) {
    $("#msg").html(msg);
}
//This function sends the entered input from create form to the specified servlet
// and gets corresponding response from servlet
$('#addForm').submit(function (event) {
    event.preventDefault();
    var action, policyAmount, policyHolderName, address, action;
    const dataObj = {
        "action": document.getElementById("createAction").value,
        "policyAmount": document.getElementById("amount").value,
        "policyHolderName": document.getElementById("name").value,
        "address": document.getElementById("address").value,
        "email": document.getElementById("email").value,
    }
    $("#addForm")[0].reset();
    ajaxFunction(dataObj);
});
//This function sends the entered input from update form to the specified servlet.
// gets corresponding response from servlet and display that in UI.
$('#updateForm').submit(function (event) {
    event.preventDefault();
    var action, address, id;
    const dataObj = {
        "action": document.getElementById("updateAction").value,
        "id": document.getElementById("id").value,
        "address": document.getElementById("updateAddress").value
    }
    $("#updateForm")[0].reset();
    //calling the ajax function for hitting the servlet
    ajaxFunction(dataObj);
});

$("#deleteButton").click(function () {
	
    var str = "";
    //For taking the id's of the checked boxes
    $("input[name='check']:checked").each(function () {
        str += $(this).val() + ',';
    });

    const dataObj = {
        "action": "delete",
        "ids": str
    }
    //calling the ajax function for hitting the servlet
    ajaxFunction(dataObj);
    deleteRows();
});

//This function calls the servlet when the user clicks on see all the policies 
// display that in the  UI
$('#retrieveAll').click(function (event) {
    event.preventDefault();
    var action;
    const dataObj = {
        "action": "retriveall"
    }
    var webUrl = location.origin + "/PolicyApplication/policyApp";
    $.ajax({
        type: "POST",
        url: webUrl,
        dataType: 'json',
        data: dataObj,
        success: function (msg) {
            $("#searchDiv").css("display", "block");
            displayData(msg);
        }
    });
});

//This function takes the email from the UI and calls the servlet and diplays the policy corresponding to that email.
$('#retrieveForm').submit(function (event) {
    event.preventDefault();
    var email, action;
    const dataObj = {
        "action": document.getElementById("retrieveAction").value,
        "email": document.getElementById("retriveEmail").value
    }
    $("#retrieveForm")[0].reset();
    var webUrl = location.origin + "/PolicyApplication/policyApp";
    $.ajax({
        type: "POST",
        url: webUrl,
        dataType: 'json',
        data: dataObj,
        success: function (msg) {
            if (jQuery.isEmptyObject(msg)) {
                displayMsg("No data found with the search");
            } else {
                displayData(msg);
            }
        }
    });
});
//Deleting the Rows in UI when we click delete button.
function deleteRows() {
    $("input[name='check']:checked").each(function () {
        $(this).closest('tr').remove();
    });
}
//This function hits the servlet gets the data from database.
function ajaxFunction(dataObj) {
	var webUrl = location.origin + "/PolicyApplication/policyApp";
    $.ajax({
        type: "POST",
        url: webUrl,
        data: dataObj,
        success: function (msg) {
            if (msg) {
                displayMsg(msg);
            }
            else {
            	displayMsg("Cannot raise the ticket");
            }
        }
    });
}

//This function display the policy data in a table form
function displayData(msg) {
    $("#retrieveForm ").css("display", "none");
    $("#tableformat").css("display", "block");
    $("#tableData").empty();
    displayMsg("");
    var table = document.getElementById("retriveTable");
    var tableRef = table.getElementsByTagName('tbody')[0];
//For each object creating a row and assigning values to the cells in each row
    $(msg).each(function (index, value) {
        var checkbox = "<th>" + "<input type = 'checkbox' value = '" + value.policyId + "' name = 'check' />" + "</th>";
        var row = tableRef.insertRow(tableRef.rows.length);

        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);

        cell1.innerHTML = value.policyAmount;
        cell2.innerHTML = value.policyHolderName;
        cell3.innerHTML = value.address;
        cell4.innerHTML = value.email;
        cell5.innerHTML = checkbox;
    });
}
//This searchBar function display the data that matches to the entered data  in the search
$("#searchBar").on("keyup", function () {
    var value = $(this).val().toLowerCase();
    $("#tableData tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
});
