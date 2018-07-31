function myFunction() {
    var x = document.getElementById("snackbar");
    x.className = "show";
    setTimeout(function () { x.className = x.className.replace("show", ""); }, 3000);
}

function myFunction1() {
    var x = document.getElementById("retrieve");
    x.className = "show";
    setTimeout(function () { x.className = x.className.replace("show", ""); }, 3000);
}

// Filter function -->
    
        $(document).ready(function () {
            $("#myInput").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#mytable tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });

        //phone validation
        
                        function phonenumber(inputtxt) {
                            var Phone = /^\d{10}$/;
                            if (inputtxt.value.match(Phone)) {
                                return true;
                            }
                            else {
                                alert("not valid phonenumber");
                                return false;
                            }
                        }
                   

                        
                    // showing the create form 
                    $("#showCreate").click(function () {
                        $("#Create_form").css("display", "block");
                        $("#show_form").css("display", "none");
                        $("#Retrive_form").css("display", "none");
                        $("#Delete_form").css("display", "none");
                        $("#Home_form").css("display", "none");

                    });

                    //  on clicking cancel making display none for create form
                    $("#Create_cancel").click(function () {
                        $("#Create_form").css("display", "none");

                    });




                    // show retrieve form
                    $("#showRetrive").click(function () {
                        $("#Retrive_form").css("display", "block");
                        $("#Create_form").css("display", "none");
                        $("#show_form").css("display", "none");
                        $("#Delete_form").css("display", "none");
                        $("#Home_form").css("display", "none");

                        // removing retrieve form on clicking cancel
                    }); $("#Retrivecancel").click(function () {
                        $("#Retrive_form").css("display", "none");

                    });

                    // showing delete form 
                    $("#showDelete").click(function () {
                        $("#Delete_form").css("display", "block");
                        $("#Create_form").css("display", "none");
                        $("#show_form").css("display", "none");
                        $("#Retrive_form").css("display", "none");

                    });

                    // on clicking on cancel remove 
                    $("#Deletecancel").click(function () {
                        $("#Delete_form").css("display", "none");

                    });

                    // On clicking on home button 
                    $("#showclear").click(function () {
                        //$("#show_form").css("display", "block");
                        $("#Create_form").css("display", "none");
                        $("#Retrive_form").css("display", "none");
                        $("#Delete_form").css("display", "none");
                        $("#Home_form").css("display", "block");

                    });

                    $('#showclear').click(function(){
                        $('')
                    })



                    $("#mytable").click(function () {

                        // Remove the sort classes for all the column, but not the first
                        $("#mytable").not($(this)).removeClass("sorted-asc sorted-desc");

                        // Set or change the sort direction
                        if ($(this).hasClass("sorted-asc") || $(this).hasClass("sorted-desc")) {
                            $(this).toggleClass("sorted-asc sorted-desc");
                        } else {
                            $(this).addClass("sorted-asc");
                        }

                        //Sort the table using the current sorting order
                        sortTable($(myTable),
                            $(this).index(),
                            $(this).hasClass("sorted-asc") ? "asc" : "desc");

                    });


                   

                    // on clicking on submit the data will send to the database
                    $('#Create_form').submit(function (event) {
                        event.preventDefault();
                        var Fname, Lname, Bankname, Branch, Address, Phone;
                        const dataobj = {
                            "Fname": document.getElementById("Fname").value,
                            "Lname": document.getElementById("Lname").value,
                            "Bankname": document.getElementById("Bankname").value,
                            "Branch": document.getElementById("Branch").value,
                            "Address": document.getElementById("Address").value,
                            "Phone": document.getElementById("Phone").value

                        }

                        $.ajax({
                            type: "POST",
                            url: `http://localhost:8080/Accountant-1.0-SNAPSHOT/servlets`,
                            //dataType:"json",
                            data: dataobj,
                            success: function (result) {

                                if (result != 0) {
                                    $('#retrieve').css("display", "block");
                                    //alert("Account Successfully Created");
                                    $('#Create_form')[0].reset();
                                }
                                else
                                $('#notretrieve').css("display", "block");
                                    //alert("Failed to created");

                            },

                            error: function (re, error) {
                                $('#notretrieve').css("display", "block");
                                //alert("Failed to create" );
                            },
                        })



                    })


                    // On clicking on delete button this will remove the rows selected
                    $('#deletebtn').on('click', function (event) {

                        var type = [];

                        $("input[name='deleteCheckBox']:checked").each(function (i) {

                            type[i] = $(this).val();

                        }).get(); jQuery('input:checkbox:checked').parents("tr").remove();



                        const wurl = `http://localhost:8080/Accountant-1.0-SNAPSHOT/servlets?Id=${type}`;




                        $.ajax({
                            url: wurl,
                            type: 'DELETE',
                            success: deleteRow,
                            error: DeleteError
                        });
                        $('#deletebtn')[0].reset();
                        // function deleteRow(deleteStatus) {

                            
                        //     $('#retrive_mesg').addClass("success").html("Deleted successfully...");
                        // }
                        function DeleteError(request, status, error) {
                            alert('error' + request + status + error);
                        }

                    });

                    // On clicking on submit button this function will call
                    $('#Delete_form').submit(function (event) {
                        event.preventDefault();
                        const deletefirst = document.getElementById("Deletefirst").value;
                        var webServiceURL = `http://localhost:8080/Accountant-1.0-SNAPSHOT/servlets?Id=${deletefirst}`;
                        $.ajax({
                            url: webServiceURL,
                            type: 'DELETE',
                            success: function (result) {

                                if (result != 0) {
                                    alert("successfully deleted");
                                }

                                else {
                                    alert("Data not found");
                                }

                            },
                            error: function () {
                                alert("Failed to deleted");
                            }

                        });
                        $('#Delete_form')[0].reset();
                    });

                    // On clicking on submit button for retrieval this will call
                    $('#Retrive_form').submit(function (event) {
                        event.preventDefault();
                        $('#retriveTabl').css("display", "block");

                        const retrieve = {
                            "Fname": document.getElementById("First").value
                        }

                        $.ajax({
                            url: `http://localhost:8080/Accountant-1.0-SNAPSHOT/servlets`,
                            type: 'GET',
                            data: retrieve,
                            dataType: 'JSON',
                            success: toTable,
                            error: customerError
                            

                        });

                        // If there is any error then this function will get call
                        function customerError(error) {
                            $('#norecord').css("display", "block");
                            //alert('Data not found');
                        }


                    });

                    // If it is success then this function get call
                    function toTable(data, status, response) {
                        
                        if(data.length==0)
                        {
                            $('#norecord').css("display", "block");
                        }
                        else
                        {
                            $('#norecord').css("display", "none");
                        $("#details").css("display", "block");
                        $("#deletebtn").css("display", "block");
                        $("#myInput").css("display", "block");
                        var level1obj = data;

                        //console.log(data+"data");
                        var table = document.getElementById("retriveTabl");
                        var colNumber = 8; // number of table columns

                        var tableRef = table.getElementsByTagName('tbody')[0];

                        $("#mytable").empty();
                        $(level1obj).each(function (index, value) {
                            var chk = "<td><input type='checkbox' value='" + value.Id + "' name='deleteCheckBox' id='chk" + index + "' /></td>";

                            var row = tableRef.insertRow(tableRef.rows.length);
                            //var cell1 = row.insertCell(0);
                            var cell1 = row.insertCell(0);
                            var cell2 = row.insertCell(1);
                            var cell3 = row.insertCell(2);
                            var cell4 = row.insertCell(3);
                            var cell5 = row.insertCell(4);
                            var cell6 = row.insertCell(5);
                            var cell7 = row.insertCell(6);

                            cell1.innerHTML = chk;
                            //cell2.innerHTML = value.Id;
                            cell2.innerHTML = value.Fname;
                            cell3.innerHTML = value.Lname;
                            cell4.innerHTML = value.Bankname;
                            cell5.innerHTML = value.Branch;
                            cell6.innerHTML = value.Address;
                            cell7.innerHTML = value.Phone;


                        });

                    }
                }
               

