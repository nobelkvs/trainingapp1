$(document).ready(function(){
    $("#myInput").on("keyup", function() {
      var value = $(this).val().toLowerCase();
      $("#myTable tr").filter(function() {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });
  function showalert(message, alerttype) {

    $('#alert_placeholder').append('<div id="alertdiv" class="alert ' + alerttype + '"><a class="close" data-dismiss="alert"></a><span>' + message + '</span></div>');
    $('#trigger_event').on('click', function () {
        $("#alertdiv").remove();
    });
}

  $("#show_home").click(function () {
    $("#demo").css("display", "block");
    $("#showHome_form").css("display", "block");
    $("#showCrt_form").css("display", "none");
    $("#showRetrieve_form").css("display", "none");
});

      
$("#show_crt").click(function () {
    $("#demo").css("display", "none");
    $("#showCrt_form").css("display", "block");
    $("#showRetrieve_form").css("display", "none");
    $("#showHome_form").css("display", "none");
});

$("#create_cancel").click(function () {
    $("#showCrt_form").css("display", "none");

});

$("#show_retrieve").click(function () {
    $("#demo").css("display", "none");
    $("#showRetrieve_form").css("display", "block");
    $("#showCrt_form").css("display", "none");
    $("#showHome_form").css("display", "none");
    

});

$("#retrieve_cancel").click(function () {
    $("#showRetrieve_form").css("display", "none");

});

$('#showCrt_form').submit(function (event) {
    event.preventDefault();
    var customername,branch,address,phoneno,emailaddr;
    const dataObj2 = {
        "customername": document.getElementById("customername").value,
        "branch": document.getElementById("branch").value,
        "address": document.getElementById("address").value,
        "phoneno": document.getElementById("phoneno").value,
        "emailaddr": document.getElementById("emailaddr").value
    };
  

    $.ajax({
        type: "POST",
        url: `http://localhost:8080/Banking-1.0-SNAPSHOT/servlets`,
        data: dataObj2,
        success:function(data,status,error) {
            showalert("Created successfully", "alert-success");        
        },
        error: function (error) {
            showalert("Failed to create your account", "alert-danger");
        }
    });

    
});

function deletefun(phonenum){
    event.preventDefault();
   
    $.ajax({
            type: "DELETE",
            url: `http://localhost:8080/Banking-1.0-SNAPSHOT/servlets?phoneno=${phonenum}`,
            success: function (res) {
                showalert("Deleted your account successfully", "alert-success");        
                $("#"+phonenum).css("display","none");
            },
            error: function (error) {
                showalert("Failed to delete your account", "alert-danger");
            },

        });
}

$("#retrivefun").click(function() {

    var dataObj3 = {
        "branch": document.getElementById("bn").value
    };
    
    filltable(dataObj3);
    function filltable(dataObj3) {
       
        if(dataObj3.branch.length==0){
        }
        else{
        $.ajax({
            type: "GET",
            url: `http://localhost:8080/Banking-1.0-SNAPSHOT/servlets`,
            data: dataObj3,
            dataType:"JSON",
            success:fillticket,
            
            error: function(error) {
                showalert("Failed to Retrieve your account", "alert-danger");
            }
        });
    }
    }
    function fillticket(data, status, response) {
        var table = document.getElementById("bankingtable");
        var colNumber = 5; // number of columns the table should consist
        var tableRef = table.getElementsByTagName('tbody')[0];
        $("#myTable").empty();// here the id is tablebody id
        showalert("Retrieved your account successfully", "alert-success");        
        $(data).each(function(index, value) {
           
            var row = tableRef.insertRow(tableRef.rows.length);
            row.setAttribute("id",value.phoneno)
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);
            var cell6 = row.insertCell(5);
            cell1.innerHTML = value.customername;
            cell2.innerHTML = value.branch;
            cell3.innerHTML = value.address;
            cell4.innerHTML = value.phoneno;
            cell5.innerHTML = value.emailaddr;
            cell6.innerHTML = '<button type="submit" class="btn btn-inverse" onclick="deletefun('+value.phoneno+')" >Delete</button>';
        });
    }
});