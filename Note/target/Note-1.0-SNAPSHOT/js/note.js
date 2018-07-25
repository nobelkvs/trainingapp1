//alert("into js file");

// create script
$('#Create').on('submit', function () {
    alert("create");
     //Creating an object
    const dataObj = {
        "subject": document.getElementById("subject").value,
        "note": document.getElementById("description").value,
        "owner": document.getElementById("owner").value,

    };

    console.log(dataObj);
    //Making an ajax call, to insert the data throgh servlet
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
        if (data == "sucessfully inserted")
        {
             alert("sucessfully inserted");
             $('#Create')[0].reset();
        }
        else
             alert("failed to insert no duplicates allowed");
    }
    //Failure Function
    function NoteError(request, status, error) {

        alert('error' + request + status + error);

    }

});

//For delete
$('#Delete').on('submit', function () {
    alert("delete");
    //gets the data from UI
    id = document.getElementById("note_id").value;
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

        //validating
        if (data == "deleted sucessfully") {
            alert("sucessfully deleted");
            $('#Delete')[0].reset()
            }
        else
            alert("failed to delete check your input");
    }
    //Failure function
    function DeleteError(request, status, error) {

        alert('error' + request + status + error);
    }
}


//Retrieve function
$('#Retrieve').on('submit', function () {

    alert("retrieve");
    getNote();   //gets the data

});

//Method to connect to servlet
function getNote() {

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

        alert('error' + request + status + error);

    }

    //Success function
    function retriveNote(data, status, response) {


        $('.note_details').empty();  //stops from appending data
        //looping through data and appending to HTML
        for (let i = 0; i < data.length; i++) {
            console.log(data[i]);
           if (i % 2 == 0) {
                //creating HTML string and appending it
                const disp = "<ul><li><div class='container' style='background-color:rgba(199, 50, 50, 0.5)' ><h2>" + data[i].subject + "</h2><button type='submit' class='btn btn-primary' onclick='display(" + data[i].id + ")'  id='Deletebtn' style='float: right'>delete</button><h4>" + data[i].note + "</h4><h6>" + data[i].owner + "</h6></div></li><ul>";

                document.querySelector('.note_details').insertAdjacentHTML('afterbegin', disp);
            }
            else {
                    //creating HTML string and appending it
                     const disp = "<ul><li><div class='container'  style='background-color:#7fbbbb'><h2>" + data[i].subject + "</h2><button type='submit' class='btn btn-primary' onclick='display(" + data[i].id + ")'  id='Deletebtn' style='float: right'>delete</button><h4>" + data[i].note + "</h4><h6>" + data[i].owner + "</h6></div></li><ul>";

                document.querySelector('.note_details').insertAdjacentHTML('afterbegin', disp);
            }


        }




    }
}

//deletes from UI, displays remaining data
function display(id) {

    //alert(id);
    deleteNote(id);
    getNote();

}

