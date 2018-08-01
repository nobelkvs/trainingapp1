//while clicking create
$("#homepage").click(function () {
    $("#home").css("display", "block");
    $("#Create").css("display", "none");
    $("#Update").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
    $(".table_div").css("display", "none");
    $("#msg").css("display","none");
});

$("#c5").click(function () {
    $("#home").css("display", "none");

});
//while clicking create
$("#s1").click(function () {
    $("#Create").css("display", "block");
    $("#Update").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
    $(".table_div").css("display", "none");
    $("#home").css("display", "none");
    $("#msg").css("display","none");

});

$("#c1").click(function () {
    $("#Create").css("display", "none");

});
//
//while clicking update





//while clicking retrive

$("#s3").click(function () {
    $("#Retrieve").css("display", "block");
   
    $("#Create").css("display", "none");
    $("#Update").css("display", "none");
    $("#Delete").css("display", "none");
    $(".table_div").css("display", "none");
    $("#home").css("display", "none");
    $("#msg").css("display","none");


 

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
    $("#home").css("display", "none");
    $("#msg").css("display","none");


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

    //getting user details

    function getUserDetails(object) {
       

        const res = `http://localhost:8080/UserAppp-1.0-SNAPSHOT/userControllerServlet`;
        //calling ajax request

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
            //for dilsplay errors
            console.log(error);

        }
    }


    //filling user table with the json data retrieved
    function fillUserTable(data, status, response) {
        //printing in console
        console.log(data);
        
        $("#msg").css("display","none");
       
       
        (data.length > 0 ? printDataInUi(data) : displayMsg("No records found"));
        // alert("No Records Fount For the Given Credentials" + 
        
      
    }
        

        


        //printing data in the ui
        function printDataInUi(data) {

            //to avoid append data
            $("#mytableBody").empty();
           
            var table = document.getElementById("tbl");
            //var table='HTMLTableElement';
            

            var tableRef = table.getElementsByTagName('tbody')[0];
            
            var sno = 1;
            var rowId=100;

            //looping the data and printing in table
            $(data).each(function (index, value) {
              
                //creating the cells for indaividual field
                var row = tableRef.insertRow(tableRef.rows.length);
                row.setAttribute("id",rowId++)
                //inserting the data to each field
                row.insertCell(0).innerHTML = sno++;
                row.insertCell(1).innerHTML = "#";
                row.insertCell(2).innerHTML = value.userName;
                row.insertCell(3).innerHTML = value.userEmailAddress;
                row.insertCell(4).innerHTML = value.userRole;
                row.insertCell(5).innerHTML = value.userPhoneNumber;
                row.insertCell(6).innerHTML = value.userCreationDate.day + "-" + value.userCreationDate.month + "-" + value.userCreationDate.year;
                row.insertCell(7).innerHTML = ".....";
                var cell9 = row.insertCell(8);
                
                cell9.innerHTML = "<button type='button' class='btn btn-danger' onClick='deleteUserOnUi(" + value.userId+","+(rowId-1) + ")'>Delete</button>";
//               
//                <!-- Trigger the modal with a button -->
//  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Small Modal</button>
// TODO delete this later


            }); 

        }
    

});


//for insertion the user data
$('#Create').on('submit', function () {

    event.preventDefault();
    var ro = document.getElementById("role");
    var userRole = ro.options[ro.selectedIndex].value;
  
    
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
        
            (data === 'success') ? displayMsg("User Registered Successfully") : displayMsg("Failed to Create The User..!,user already existed..");
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

    

    admin_Password = document.getElementById("admin_password").value,
        userId = document.getElementById("user_id").value

   
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


// for display messages
function  displayMsg(msg){
   
    
$("#msg").css("display","block");

document.getElementById("msg").innerHTML=msg;  



 }
//when new user clicked
function addNewUser() {
    $("#Create").css("display", "block");
    $("#Update").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Delete").css("display", "none");
    $(".table_div").css("display", "none");
    $("#msg").css("display","block");

}


//delete on ui
function deleteUserOnUi(id,rowId) {
    
   
    
   var adminPassword = prompt("Please Enter Admin Password");
   confirm("Do u Wants to Delete...?")
   //calling deleteuser method
    deleteUser(adminPassword, id);
    //removing the deleted record in tbl
    $("#"+rowId).remove();
}

//delete user method accepts two arguments password and user Id and performs the operation
function deleteUser(admin_Password, userId) {
   

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

       
       
       (data=='success') ? displayMsg("User deleted Successfully") : displayMsg("Failed to delete The User..!");
       

    }

    function CustomerError(request, status, error) {

        alert('Something Wrong' + error);

    }


}


//validations on the create user form
function validate(){
    //getting the form data
    var username=document.forms["myCreateForm"]["userName"].value;
    var mobile=document.forms["myCreateForm"]["phone"].value;
    var email=document.forms["myCreateForm"]["email"].value;
    var pass=document.forms["myCreateForm"]["pass"].value;
    var regUsername=/^[a-zA-Z ]{3,20}$/;
    var regMobile = /^\d{10}$/;
    var regEmail=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    
    var regPass=/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;
//testing username
     if(regUsername.test(username)==false){
       
        document.getElementById("err").innerHTML="username should be alphabetic minimum 3characters and max 20";
        document.getElementById("userName").focus;
        return false;
    }
//testing email
    if(regEmail.test(email)==false){
        document.getElementById("err").innerHTML="";

        document.getElementById("err3").innerHTML="email should be like ex: example@xxx.xx";
        document.getElementById("email").focus;
        return false;
    }
//testing mobile number
    else if(regMobile.test(mobile)==false){
        document.getElementById("err3").innerHTML="";
        document.getElementById("err1").innerHTML="phone number should be 10 digit number";
        document.getElementById("phone").focus;

        return false;
    }
//testing password    
   else if(regPass.test(pass)==false){

        document.getElementById("err1").innerHTML="";
        document.getElementById("err2").innerHTML="password should be minimum 8ch and atleast 1cha 1 digit 1 Capital letter";
        document.getElementById("pass").focus;


        return false;
    }    
    else{
        
        return true;
    }
}