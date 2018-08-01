// Home Page
$("#show_home").click(function () {
    $("#demo").css("display", "block");
    $("#showHome_form").css("display", "block");
    $("#showCreate_form").css("display", "none");
    $("#showRetrive_form").css("display", "none");
});

//Create Form
$("#show_create").click(function () {
    $("#demo").css("display", "none");
    $("#showCreate_form").css("display", "block");
    $("#showRetrive_form").css("display", "none");
    $("#showHome_form").css("display", "none");
});

$("#create_cancel").click(function () {
    $("#showCreate_form").css("display", "none");

});
// Retrieve Form
$("#show_retrive").click(function () {
    $("#demo").css("display", "none");
    $("#showRetrive_form").css("display", "block");
    $("#showCreate_form").css("display", "none");
    $("#showHome_form").css("display", "none");


});

$("#retrive_cancel").click(function () {
    $("#showRetrive_form").css("display", "none");

});



// Create Form on submit
$('#showCreate_form').submit(function (event) {

    event.preventDefault();
    var name, branch, address, phno, email;
    const dataObj = {
        "name": document.getElementById("name").value,
        "branch": document.getElementById("branch").value,
        "address": document.getElementById("address").value,
        "phno": document.getElementById("phno").value,
        "email": document.getElementById("email").value

    }
    console.log(dataObj);

    $.ajax({
        type: "POST",
        url: `http://localhost:8080/Bank-1.0-SNAPSHOT/servlets`,
        //dataType: "json",
        data: dataObj,
        success: function (data) {
            
           if(data=="successfully inserted"){
               toastr.success("Account created succesfully");
           }else{
               toastr.error("Failed to create account");
           }
        },
        error: function (error) {
           toastr.error("Something went wrong");
        },

    });
    $('#showCreate_form')[0].reset();


});




    // Retrive Form on submit

    $('#deletebtn').on('click', function (event) {
        var type = [];
        $("input[name='deleteCheckBox']:checked").each(function (i) {
            type[i] = $(this).val();
        });
        const wurl = `http://localhost:8080/Bank-1.0-SNAPSHOT/servlets?phno=${type}`;

        $.ajax({
            url: wurl,
            type: 'DELETE',
            success: deleteRow,
            error: DeleteError
        });

        
        function deleteRow(data) {
          
            if(data==1){
                toastr.success("Account deleted succesfully");
            }else{
                toastr.error("Failed to delete account");
            }
            
            getCustomer();
        }

        function DeleteError(error) {

            toastr.error("Something went wrong");
        }
        //$('#deletebtn')[0].reset();
    

    });
    /*
   
    this function is to call the serv
    */
    function getCustomer() {

        let branch = document.getElementById("bn").value;
      
        const res = `http://localhost:8080/Bank-1.0-SNAPSHOT/servlets?branch=${branch}`;

        $.ajax({
            url: res,
            type: 'GET',
            dataType: 'json',
            success: fillbank,
            crossDomain: true,
            error: CustomerError
        });
        //$('#Retrieve')[0].reset();

        function CustomerError(request, status, error) {
            toastr.error("Something went wrong");
        }

    }


    function fillbank(data, status, response) {
        var table = document.getElementById("bank");
        var colNumber = 5; // number of table columns
        //alert(table);
        console.log("data" + data);
        var tableRef = table.getElementsByTagName('tbody')[0];
        $("#myTable").empty();// here the id is tablebody id
        $(data).each(function (index, value) {
            var chk = "<td><input type='checkbox' value='" + value.phno + "' name='deleteCheckBox' id='chk_" + index + "' /></td>";
            var row = tableRef.insertRow(tableRef.rows.length);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);
            var cell6 = row.insertCell(5);

            cell1.innerHTML = chk;
            cell2.innerHTML = value.name;
            cell3.innerHTML = value.branch;
            cell4.innerHTML = value.address;
            cell5.innerHTML = value.phno;
            cell6.innerHTML = value.email;


        });
    }


    $('#showRetrive_form').on('submit', function (event) {
        event.preventDefault();
        $("#myInput").css("display", "block");
        $("#bank").css("display", "block");
        getCustomer();

    });

    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
    // Toaster
    $(document).ready(function(){

        function toasterOptions() {
            toastr.options = {
            "closeButton": false,
            "debug": false,
            "newestOnTop": false,
            "progressBar": true,
            "positionClass": "toast-top-right",
            "preventDuplicates": true,
            "onclick": null,
            "showDuration": "100",
            "hideDuration": "1000",
            "timeOut": "2500",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "show",
            "hideMethod": "hide"
            };
        };
        toasterOptions();
    
    
    });