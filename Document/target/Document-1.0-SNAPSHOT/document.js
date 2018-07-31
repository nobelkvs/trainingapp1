//when user clicks on create document the create form will be displayed
$("#showCreate").click(function () {
    $("#Create_form").css("display", "block");
    $("#Retrive_form").css("display", "none");
    $("#tableformat").css("display", "none");
});
//When user clicks on cancel button the create form will be disappeared
$("#Createcancel").click(function () {
    $("#Create_form").css("display", "none");
    $("#tableformat").css("display", "none");
});
//When user clicks on retrive document  form will be displayed
$("#showRetrive").click(function () {
    $("#Retrive_form").css("display", "block");
    $("#Create_form").css("display", "none");
    $("#retreivetable").css("display", "block");
    $("#tableformat").css("display", "none");
    $("#retrivemsg").text("")
});
//when user clicks on cancel button form will be disappeared
$("#Retrivecancel").click(function () {
    $("#Retrive_form").css("display", "none");
    $("#tableformat").css("display", "none");
});
//When user submit the create form this event will be activated and data will be stored into data base
$("#Create_form").submit(function (event) {
    event.preventDefault();
    var obj = {
        "action": document.getElementById("action").value,
        "title": document.getElementById("title").value,
        "relatedContact": document.getElementById("contact").value,
        "relatedDeal": document.getElementById("deal").value,
        "owner": document.getElementById("owner").value
    };
    $.ajax({
        url: "http://localhost:8080/Document-1.0-SNAPSHOT/document",
        type: 'POST',
        data: obj,
        success: function (msg) {
        $("#createmsg").text(msg);
             createFunction();
             }
    });
 $("#Create_form")[0].reset();
    });
function createFunction(){
setTimeout(function(){$("#createmsg").text("")},5000);
}

//When retrive form is submitted the data present in the database will be displayed

$("#Retrive_form").submit(function (event) {
    event.preventDefault();

    var obj = {
        "action": document.getElementById("action2").value,
    };
    $.ajax({
        url: "http://localhost:8080/Document-1.0-SNAPSHOT/document",
        type: 'POST',
        dataType: 'json',
        data: obj,
        success: function (data) {
        $("#retrivemsg").text("displaying the total information");
        retrieveFunction();
        displayTable(data);
        }
    });
    $("#Retrive_form")[0].reset();
});

//This is the function to display the table data
function displayTable(data) {
    $("#tableformat").css("display", "block");
    //This function is used to donot append the same data
    $("#MyTable").empty();
    var level1obj = data;
    console.log(level1obj);
    var table = document.getElementById("retreivetable");
    var tableRef = table.getElementsByTagName('tbody')[0];
    $(data).each(function (index, value) {
        var row = tableRef.insertRow(tableRef.rows.length);
        //To append checkbox with the table
        var chk = "<th>" + "<input type='checkbox' value='" + value.id + "' name='check' />" + "</th>";
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);

        cell1.innerHTML = chk;
        cell2.innerHTML = value.title;
        cell3.innerHTML = value.relatedContacts;
        cell4.innerHTML = value.owner;
        var createdDate = value.createdDate.day + '/' + value.createdDate.month + '/' + value.createdDate.year;
        cell5.innerHTML = createdDate;
    });
}
//This is a filter function which filters the data based upon the user requirement
$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#MyTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});
//This function is used to delete multiple records
$("#dlt").click(function () {
    $("#Retrievedemo").css("display", "none");
    var str = "";
    $("input[name='check']:checked").each(function (i) {
        str += $(this).val() + ",";
    });
    var obj = {
        "action": document.getElementById("action4").value,
        "id": str
    }
    $.ajax({
        url: 'http://localhost:8080/Document-1.0-SNAPSHOT/document',
        type: "POST",
        data: obj,
        success: function (msg) {
            $("#retrivemsg").text(msg);
            retrieveFunction(msg);
            remove();
        }
    });
});
function retrieveFunction(){
setTimeout(function(){$("#retrivemsg").text("")},5000);
}
//This function is used to remove the selected rows from UI
function remove() {
    $("input[name='check']:checked").each(function () {
        $(this).closest('tr').remove();
    });
}

