//when user clicks on create document the create form will be displayed
$("#showCreate").click(function () {
    $("#Create_form").css("display", "block");
    $("#Retrive_form").css("display", "none");
    $("#Delete_form").css("display", "none");
    $("#retreivetable").css("display", "none");
});
//When user clicks on cancel button the create form will be disappeared
$("#Createcancel").click(function () {
    $("#Create_form").css("display", "none");
    $("#retreivetable").css("display", "none");
});
//When user clicks on retrive document  form will be displayed
$("#showRetrive").click(function () {
    $("#Retrive_form").css("display", "block");
    $("#Create_form").css("display", "none");
    $("#Delete_form").css("display", "none");
    $("#retreivetable").css("display", "block");
    $("#tableformat").css("display", "none");
});
//when user clicks on cancel button form will be disappeared
$("#Retrivecancel").click(function () {
    $("#Retrive_form").css("display", "none");
    $("#retreivetable").css("display", "none");
});
//When user clicks on delete document delete form will be displayed
$("#showDelete").click(function () {
    $("#Delete_form").css("display", "block");
    $("#Create_form").css("display", "none");
    $("#Retrive_form").css("display", "none");
    $("#retreivetable").css("display", "none");
});
//When user clicks on cancel button the form will ne disappeared
$("#Deletecancel").click(function () {
    $("#Delete_form").css("display", "none");
    $("#retreivetable").css("display", "none");
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
    console.log(obj);
    $.ajax({
        url: "http://localhost:8080/Document-1.0-SNAPSHOT/document",
        type: 'POST',
        data: obj,
        //dataType: 'json',
        success: function (msg) {
            alert(msg);
             $("#demo").append(msg);
        }
    });

    $("#Create_form")[0].reset();

});

//When user clicks on submit button  the event will be activated and corresponding id data will be deleted from database
$("#Delete_form").submit(function (event) {
    event.preventDefault();
    var obj1 = {
        "action": document.getElementById("action3").value,
        "role": document.getElementById("role").value,
        "id": document.getElementById("id").value
    };
    console.log(obj1);
    $.ajax({
        url: "http://localhost:8080/Document-1.0-SNAPSHOT/servlet",
        type: 'POST',
        data: obj1,
        //dataType: 'json',
        success: function (msg) {
            console.log(msg.length);
            if (msg.length == 1)
                alert("id is not there");
            else
                alert(msg);
        }
    });
    $("#Delete_form")[0].reset();
});

//When retrive form is submitted the data present in the database will be displayed

$("#Retrive_form").submit(function (event) {
    event.preventDefault();
    var obj = {
        "action": document.getElementById("action2").value,
    };
    console.log(obj);
    $.ajax({
        url: "http://localhost:8080/Document-1.0-SNAPSHOT/document",
        type: 'POST',
        dataType: 'json',
        data: obj,
        success: function (data) {
            alert("Retrieving the total data")
            displayTable(data);
        }
    });
    $("#Retrive_form")[0].reset();

});

function displayTable(data) {
    $("#tableformat").css("display", "block");
    $("#MyTable").empty();
    var level1obj = data;
    console.log(level1obj);
    var table = document.getElementById("retreivetable");
    var tableRef = table.getElementsByTagName('tbody')[0];
    $(data).each(function (index, value) {
        console.log(value);
        var row = tableRef.insertRow(tableRef.rows.length);

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
    var array = [];
    //var Str= "";
$("input[name='check']:checked").each(function (i) {
        array[i] = $(this).val();
        //Str +=$(this).val()+',';

    });
    var obj = {
        "action": document.getElementById("action4").value,
        //"id":Str
    }
    console.log(obj);
    var link = `http://localhost:8080/Document-1.0-SNAPSHOT/document?id=${array}`;
    $.ajax({
        url: link,
        type: "POST",
        data: obj,
        success: function (msg) {
            alert(msg);
            remove();

        }
    });
});
function remove(){
$("input[name='check']:checked").each(function () {
 $(this).closest('tr').remove();
});
}

