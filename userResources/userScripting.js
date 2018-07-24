//while clicking create
$("#s1").click(function () {
    $("#Create").css("display", "block");
    $("#Update").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
    $(".table_div").css("display", "none");
});

$("#c1").click(function () {
    $("#Create").css("display", "none");

});
//
//while clicking update


$("#home").click(function () {
    $("#Update").css("display", "none");
    $("#Create").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
    $(".table_div").css("display", "none");

});

//while clicking update


$("#s2").click(function () {
    $("#Update").css("display", "block");
    $("#Create").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
    $(".table_div").css("display", "none");

});

$("#c2").click(function () {
    $("#Update").css("display", "none");    

});

//while clicking retrive

$("#s3").click(function () {
    $("#Retrieve").css("display", "block");
    $("#Retrieve").css("display", "block")
    $("#Create").css("display", "none");
    $("#Update").css("display", "none");
    $("#Delete").css("display", "none");
    $(".table_div").css("display", "none");
 

});

$("#c3").click(function () {
    $("#Retrieve").css("display", "none");

});

//while clicking delete


$("#s4").click(function () {
    $("#Delete").css("display", "block");
    $("#Create").css("display", "none");
    $("#Update").css("display", "none");
    $("#Retrieve").css("display", "none");
    $(".table_div").css("display", "none");
});

$("#c4").click(function () {
    $("#Delete").css("display", "none");
});




//for retriving user information 
$('#Retrieve').on('submit', function () {


    //   alert("hello in retrive js ");
    event.preventDefault();
    $(".table_div").css("display", "block");
    $("#tbl").css("display", "block");





    getUser("#RetrieveTbl");

    function getUser(tabletag) {

        var ro = document.getElementById("retriveBy");
        var userRole = ro.options[ro.selectedIndex].value;



        // creating json object from html input values
        var obj = {
            "type": document.getElementById("userId").value,
            "retriveBy": userRole
        };
        console.log(obj.type + "incomming data" + obj.retriveBy);

        //calling user details
        getUserDetails(obj);


    }


    function getUserDetails(object) {
       

        const res = `http://localhost:8080/UserAppp-1.0-SNAPSHOT/userControllerServlet`;

        $.ajax({

            url: res,
            type: 'GET',
            data: object,
            dataType: 'json',
            success: fillUserTable,
            // crossDomain : true,
            error: CustomerError
        });
        //for clearing the form data after submiting
        $("#Retrieve")[0].reset();


        function CustomerError(request, status, error) {

            alert('error' + request + status + error);

        }
    }


    //filling user table with the json data retrieved
    function fillUserTable(data, status, response) {
        //printing in console
        console.log(data);
        $("#msg").css("display","block");
        //document.getElementById("msg").innerHTML='no Records Found';
        (data.length > 0 ? printDataInUi(data) : alert("no records found please verify for duplicate entry...!"));
        // alert("No Records Fount For the Given Credentials" + 

      
    }
        

        //printing data in the ui
        function printDataInUi(data) {

            //to avoid append data
            $("#mytableBody").empty();
           
            var table = document.getElementById("tbl");
            //var table='HTMLTableElement';
            alert(table + "after ")

            var tableRef = table.getElementsByTagName('tbody')[0];
            
            var sno = 1;
            var rowId=100;

            //looping the data and printing in table
            $(data).each(function (index, value) {
                alert("in inserting data");
                //creating the cells for indaividual field
                var row = tableRef.insertRow(tableRef.rows.length);
                row.setAttribute("id",rowId++)
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                var cell4 = row.insertCell(3);
                var cell5 = row.insertCell(4);
                var cell6 = row.insertCell(5);
                var cell7 = row.insertCell(6);
                var cell8 = row.insertCell(7);
                var cell9 = row.insertCell(8);
                //inserting the data to each field
                cell1.innerHTML = sno++;
                cell2.innerHTML = "#";
                cell3.innerHTML = value.userName;
                cell4.innerHTML = value.userEmailAddress;
                cell5.innerHTML = value.userRole;
                cell6.innerHTML = value.userPhoneNumber;
                cell7.innerHTML = value.userCreationDate.day + "-" + value.userCreationDate.month + "-" + value.userCreationDate.year;
                //here password should not be visible to user
                cell8.innerHTML = ".....";
               
                cell9.innerHTML = "<button type='button' class='btn btn-danger' onClick='deleteUserOnUi(" + value.userId+","+(rowId-1) + ")'>Delete</button>";
               

            });

        }
    

});


//for insertion the user data
$('#Create').on('submit', function () {

    event.preventDefault();
    var ro = document.getElementById("role");
    var userRole = ro.options[ro.selectedIndex].value;
    alert("in insert..");
    var inputObj = {
        "userName": document.getElementById("userName").value,
        "email": document.getElementById("email").value,
        "pass": document.getElementById("pass").value,
        "mobile": document.getElementById("phone").value,
        "role": userRole,
    }
    document.myCreateForm.reset();
    console.log(inputObj);

    insertUser(inputObj);

    function insertUser(obj) {
        const insertUrl = `http://localhost:8080/UserAppp-1.0-SNAPSHOT/userControllerServlet`;
        $.ajax({
            url: insertUrl,
            type: 'POST',
            data: obj,
            success: message,
            crossDomain: true,
            error: CustomerError


        });
        $("#Create")[0].reset();

        function message(data, response, status) {
            console.log(data)

            alert((data === 'success') ? "User Created Successfully...!" : "Failed to Create The User..!,Please check for duplicate entry");
            $("#Create").css("display", "none");

        }

        function CustomerError(request, status, error) {

            alert('something wrong..!' + request + status + error);
            console.log(error);

        }


    }



});



//for deleting the user based on the id of user
$('#Delete').on('submit', function () {

    event.preventDefault();

    alert("in delete..");

    admin_Password = document.getElementById("admin_password").value,
        userId = document.getElementById("user_id").value

    alert(admin_Password + " " + userId);


    //calling deleteuser method

    deleteUser(admin_Password, userId);

});

//performing filter operation
$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#mytableBody tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});
function addNewUser() {
    $("#Create").css("display", "block");
    $("#Update").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
    $(".table_div").css("display", "none");

}


//delete on ui
function deleteUserOnUi(id,rowId) {
   var adminPassword = prompt("Please Enter Admin Password");
   //calling deleteuser method
    deleteUser(adminPassword, id);
    //alert(rowId);
    $("#"+rowId).hide();
}

//delete user method accepts two arguments password and user Id and performs the operation
function deleteUser(admin_Password, userId) {
   // alert("in delete")
    const res1 = `http://localhost:8080/UserAppp-1.0-SNAPSHOT/userDeleteController?admin_Password=${admin_Password}&userId=${userId}`;
    $.ajax({
        url: res1,
        type: 'POST',
        // data:object,
        success: message,
        error: CustomerError
    });
    $("#Delete")[0].reset();

    function message(data) {
        console.log(data);

        if (data == 'wrongPassword') {
            alert("Please Enter the correct password...")
        }

        //getting the status wether it is deleted or not
        alert((data === 'success') ? "User deleted Successfully...!" : "Failed to delete The User..!,Please try again");
        $("#Delete").css("display", "none");

    }

    function CustomerError(request, status, error) {

        alert('Something Wrong' + error);

    }


}
