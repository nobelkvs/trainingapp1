//alert("into js file");
//toaster function
$(document).ready(function () {
    // alert("in toaster");
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

//search filter
$(document).ready(function () {

    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("ul li").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});


//home button
$("#homeBtn").click(function(){
    $("#Home").css("display","block");
    $("#Create").css("display", "none");
    $("#Retrieve").css("display", "none");

});

//create button functionality
$("#createBtn").click(function () {
    $("#Create").css("display", "block");
    $('.note_details').css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#Home").css("display","none");
    // $("#Delete").css("display", "none");

});

$("#cancel1").click(function () {
    $("#Create").css("display", "none");


});

// create script
$('#Create').on('submit', function () {
    //alert("create");
    //Creating an object
    const dataObj = {
        "subject": document.getElementById("subject").value,
        "note": document.getElementById("description").value,
        "owner": document.getElementById("owner").value,

    };

    //console.log(dataObj);
    //Making an ajax call, to insert the data through servlet
    $.ajax(
        {
            url: 'http://localhost:8080/Note-1.0-SNAPSHOT/note',
            type: 'Post',
            data: dataObj,
            success: insertData,
            crossDomain: true,
            error: NoteError
        });
    //Success function
    function insertData(data) {
        if (data == "sucessfully inserted") {
            //$("#successCreateModal").modal('show');
            //alert("sucessfully inserted");
            toastr.success("sucessfully inserted")
            $('#Create')[0].reset();
        }
        else
            toastr.error("failed to insert no duplicates allowed");
    }
    //Failure Function
    function NoteError(request, status, error) {

        toastr.error('error' + request + status + error);

    }

});

//delete button functionality
// $("#deleteBtn").click(function () {
//     $("#Delete").css("display", "block");
//     $("#Create").css("display", "none");
//     $('.note_details').css("display", "none");
//     $("#Retrieve").css("display", "none");



// });

// $("#cancel3").click(function () {
//     $("#Delete").css("display", "none");
// });


//For delete
$('#Delete').on('submit', function () {
    //alert("delete");
    //gets the data from UI
    id = document.getElementById("note_id").value;//noteId
    //calling deletion method
    deleteNote(id);
});

//deletion method
function deleteNote(id) {

    wurl = `http://localhost:8080/Note-1.0-SNAPSHOT/note?id=${id}`;
    //making an ajax call, to delete the row through servlet
    $.ajax(
        {

            url: wurl,
            type: 'Delete',
            success: deleteRow,
            crossDomain: true,
            error: DeleteError
        });
    //Success function
    function deleteRow(data) {

       // toastr.warn(data);
        //$('#Delete')[0].reset();

        //validating
          if (data == "deleted sucessfully") {
                toastr.success("sucessfully deleted");

                }
                else
                    toastr.error("failed to delete check your input");
    }
    //Failure function
    function DeleteError(request, status, error) {

        toastr.error('error' + request + status + error);
    }
}

//Retrieve button functionality
$("#retrivebtn").click(function () {
    $("#Retrieve").css("display", "block");
    $("#Create").css("display", "none");
    $('.note_details').css("display", "block");
    //$("#Delete").css("display", "none");
    $("#myInput").css("display", "none");
    $("#filtter").css("display", "none");
    $("#Home").css("display","none");




});

$("#cancel2").click(function () {
    $("#filtter").css("display", "none");
    $("#Retrieve").css("display", "none");
    $("#myInput").css("display", "none");
    $("#Home").css("display","none");



});

//Retrieve function
$('#Retrieve').on('submit', function () {

    //alert("retrieve");
    getNote();   //gets the data

    $("#filtter").css("display", "block");
    $("#myInput").css("display", "block");


});

//Method to connect to servlet
function getNote() {
    //alert ("reload");
    //Making ajax call
    $.ajax(
        {

            url: 'http://localhost:8080/Note-1.0-SNAPSHOT/note',
            type: 'GET',
            dataType: 'json',
            success: retriveNote,
            crossDomain: true,
            error: CustomerError
        });

    //failure function
    function CustomerError(request, status, error) {

        toastr.error('error' + request + status + error);

    }

    //Success function
    function retriveNote(data, status, response) {

        $('#myInput').css("display", "block");

        $('.note_details').empty();  //stops from appending data
        //looping through data and appending to HTML
        for (let i = 0; i < data.length; i++) {
            // console.log(data[i]);
            if (i % 2 == 0) {
                //creating HTML string and appending it
                const disp = "<ul><li><div class='container'  style='background-color:#8d92c3' ><h2>" + data[i].subject + "</h2><button type='submit' class='btn btn-primary' onclick='display(" + data[i].id + ")'  id='Deletebtn' style='float: right'>delete</button><h4>" + data[i].note + "</h4><h6>" + data[i].owner + "</h6></div></li><ul>";

                document.querySelector('.note_details').insertAdjacentHTML('afterbegin', disp);
            }
            else {
                //creating HTML string and appending it
                const disp = "<ul><li><div class='container'  style='background-color:#6393c3'><h2>" + data[i].subject + "</h2><button type='submit' class='btn btn-primary' onclick='display(" + data[i].id + ")'  id='Deletebtn' style='float: right'>delete</button><h4>" + data[i].note + "</h4><h6>" + data[i].owner + "</h6></div></li><ul>";

                document.querySelector('.note_details').insertAdjacentHTML('afterbegin', disp);
            }


        }




    }
}

//deletes from UI, displays remaining data  #7fbbbb
function display(id) {

    //alert(id);
    // alert("display "+id);
    deleteNote(id);
    getNote();

}

