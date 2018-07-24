alert("into js file");

// create script
$('#Create').on('submit', function()
{
    alert("create");
    const dataObj = {
        "subject": document.getElementById("subject").value,
        "note": document.getElementById("description").value,
        "owner": document.getElementById("owner").value,

    };

    console.log(dataObj);

    $.ajax(
    {
        url: 'http://localhost:8080/Note-1.0-SNAPSHOT/note',
        type: 'Post',
        //dataType : 'json',
        data: dataObj,
        success: insertData,
        crossDomain: true,
        error: NoteError
    });

    function insertData(data)
    {
        //console.log(data);
        alert("sucessfully created");
        $("#Create")[0].reset();
    }

    function NoteError(request, status, error)
    {

        alert('error' + request + status + error);

    }

});

//for delete
$('#Delete').on('submit', function()
{
    alert("delete");

    id = document.getElementById("note_id").value;
    deleteNote(id);
});

function deleteNote(id)
{

    //alert(id);
    wurl = `http://localhost:8080/Note-1.0-SNAPSHOT/note?id=${id}`;
    //console.log(deleteObj);
    $.ajax(
    {

        url: wurl,
        type: 'Delete',
        success: deleteRow,
        crossDomain: true,
        error: DeleteError
    });

    function deleteRow(data)
    {
        //alert(data);
        if (data == "deleted sucessfully")
            alert("sucessfully deleted");
        else
            alert("failed to delete check your input");
    }

    function DeleteError(request, status, error)
    {

        alert('error' + request + status + error);
    }
 }



$('#Retrieve').on('submit', function()
{

    alert("retrieve");

    //event.preventDefault();
    //fillTicketTable("#retrive");
    getNote();

    });


function getNote()
{
        alert("getnote");

        $.ajax(
        {
//            alert("ajax");
            url: 'http://localhost:8080/Note-1.0-SNAPSHOT/note',
            type: 'GET',
            //data : retriveObj,
            dataType: 'json',
            success: retriveNote,
            crossDomain: true,
            error: CustomerError
        });

//        function result1(data)
//        {
//
//            this.result = data;
//            // kp=this.result;
//            console.log(this.result);
//        }

        function CustomerError(request, status, error)
        {

            alert('error' + request + status + error);

        }


    function retriveNote(data, status, response)
    {
        alert("in filling table");
        console.log(data);
        $('.note_details').empty();

        for (let i = 0; i < data.length; i++)
        {
            console.log(data[i]);
            if (i % 2 == 0)
            {

   const disp = "<ul><li><div class='container'  style='background-color: rgba(199, 50, 50, 0.5)'><h2>"+data[i].subject+"</h2><button type='submit' class='btn btn-primary' onclick='display("+data[i].id+")'  id='Deletebtn' style='float: right'>delete</button><h4>"+data[i].note+"</h4><h6>"+data[i].owner+"</h6></div></li><ul>";



                document.querySelector('.note_details').insertAdjacentHTML('afterbegin', disp);
            }
            else
            {
            //var curl="http://localhost:8080/Note-1.0-SNAPSHOT/notes.html#"+${data[i].id};
               // const disp = "<ul><li><div class='container'  style='background-color:#7fbbbb'><h2>"+data[i].subject+"</h2><button type='submit' class='btn btn-primary' onclick='display("+data[i].id+")'  id='Deletebtn' style='float: right'>delete</button><h4>"+data[i].note+"</h4><h6>"+data[i].owner+"</h6></div></li><ul>";


                





                document.querySelector('.note_details').insertAdjacentHTML('afterbegin', disp);
            }


        }




    }
}
//document.getElementById("Deletebtn").addEventListener("click", display());                                     <a  href="#${data[i].id}" onhashchange="dummy()" >
                                //     <a  href="#${data[i].id}" onhashchange="dummy()" >


function dummy(id)
{
alert("dummy "+id);
//location.replace('http://localhost:8080/Note-1.0-SNAPSHOT/notes.html#'"+data[i].id+"')
//var curl="http://localhost:8080/Note-1.0-SNAPSHOT/notes.html#";
//location.replace("http://localhost:8080/Note-1.0-SNAPSHOT/notes.html#"+${data[i].id})
}


$('a').click(abc());
function abc(){
alert("inside hash");
alert(this.hash.substring(1));

}
     //   ['hashchange', 'load'].forEach(event => window.addEventListener(event, display()));





function display(id)
{
//const id = window.location.hash.replace('#', '');
                         alert(id);
                        //const pattern="[0-9]{1,3}";
//            if(id == pattern)
//            {
//            alert("pattern validated "+id);
//                         //display(id);

                deleteNote(id);
                 getNote();
            //}
}


//
//    console.log(id);
//
//    wurl = `http://localhost:8080/Note-1.0-SNAPSHOT/note?id=${id}`;
//        //console.log(deleteObj);
//        $.ajax(
//        {
//
//            url: wurl,
//            type: 'Delete',
//            success: deleteRow,
//            crossDomain: true,
//            error: DeleteError
//        });
//
//        function deleteRow(data)
//        {
//            //alert(data);
//            if (data == "deleted sucessfully")
//            {
//                alert("sucessfully deleted");
//              //  dummy(id);
//
//            }
//            else
//                alert("failed to delete check your input");
//        }
//
//        function DeleteError(request, status, error)
//        {
//
//            alert('error' + request + status + error);
//        }
//
//
//    }
//
//$('.btn btn-primary').on('submit' ,function(){
//
//        alert("btnn");
//        let sub= $(this).data;
//        alert(sub);
//        console.log(sub);
//
//
//});