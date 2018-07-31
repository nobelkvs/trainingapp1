
    // Homepage content
        function home(){
           const home = document.getElementById("homePageData");
           home.innerHTML = "Welcome to our website Groups Application.<br> Here you can create your own groups and modify your groups.";
            $("#create_form").css("display", "none");
            $("#retrieve_form").css("display", "none");
            $("#delete_form").css("display", "none");
            $("#delete_form2").css("display", "none");
            $("#tableshow").css("display", "none");
            $("#homePageData").css("display","block");
        }

        //Search table code
        $(document).ready(function() {
            $("#myInput").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("#myTable tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });

        // function for alert messages
        function showalert(message,alerttype) {
         $('#alert_placeholder').append('<div id="alertdiv" class="alert ' +  alerttype + '"><a class="close" data-dismiss="alert">×</a><span>'+message+'</span></div>');
         setTimeout(function() {
          $("#alertdiv").remove(); // this will automatically close the alert and remove this if the users doesn't close it in 5 secs
           }, 5000);
         }

        //Create code
        $("#create").click(function() {
            $("#create_form").css("display", "block");
            $("#delete_form").css("display", "none");
            $("#retrieve_form").css("display", "none");
            $("#delete_form2").css("display", "none");
            $("#tableshow").css("display", "none");
            $("#homePageData").css("display","none");
        });

        $("#cancel1").click(function() {
            $("#create_form").css("display", "none");

        });

       //checking the checkbox validation
        $(document).ready(function(){
            $('#feedback').on('change', function(){
                    if($(this).prop('checked')){
                        $(this).val('YES');
                    }else{
                        $(this).val('NO');
                    }
            });
        });

        $('#create_form').submit(function(event) {
            // Stop the browser from submitting the form.
            event.preventDefault();
            //Making the all html data into json object
            var dataObj = {
                "groupName": document.getElementById("groupName").value,
                "owner": document.getElementById("owner").value,
                "sendAs": document.getElementById("sendAs").value,
                "feedback": document.getElementById("feedback").value
            }
            // Clearing form fields
             $('#create_form')[0].reset();
            //submitting form using ajax
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/groups-1.0-SNAPSHOT/servlets',
                data: dataObj,
               // dataType: 'json',
                success: function message(data) {
                    if(data < 1)
                    {
                       showalert("Entered values already existed","alert-danger");
                    }else{
                        showalert("Successfully Group Created","alert-success");
                    }

                },
                error: function Error() {
                    showalert("Error in Insertion","alert-danger");
                }
            });

        });


        //  Delete code for Admin
        $("#delete").click(function() {
            $("#create_form").css("display", "none");
            $("#retrieve_form").css("display", "none");
            $("#delete_form").css("display", "block");
            $("#delete_form2").css("display", "none");
            $("#tableshow").css("display", "none");
            $("#homePageData").css("display","none");
        });

        $("#cancel3").click(function() {
            $("#delete_form").css("display", "none");

        });

        $("#delete_form").submit(function(event) {
            // Stop the browser from submitting the form.
            event.preventDefault();
            const Group_Id = document.getElementById("gid").value;
            const username = document.getElementById("username").value;
            const pwd  = document.getElementById("password").value;
            $("#delete_form")[0].reset();
            //submitting form using ajax
            $.ajax({
                type: "DELETE",
                url: `http://localhost:8080/groups-1.0-SNAPSHOT/filter?gid=${Group_Id}&username=${username}&password=${pwd}`,
                success: Message,
                error: function() {
                    showalert("Error in deletion","alert-danger");
                }
            });

           function Message(data){
               if(data==="UnauthorizedAccess"){
                   showalert("Unauthorized Access","alert-danger");
               }else{
                data < 1 ? showalert("Id doesn't Exist","alert-warning") : showalert("Group Deleted","alert-success");
            }
            };

        });

        // Retrieve code

        $("#retrieve").click(function() {
            $("#retrieve_form").css("display", "block");
            $("#create_form").css("display", "none");
            $("#delete_form").css("display", "none");
            $("#delete_form2").css("display", "none");
            $("#homePageData").css("display","none");
        });

        $("#cancel2").click(function() {
            $("#retrieve_form").css("display", "none");

        });

        $("#retrieve_form").submit(function(event) {
            event.preventDefault();
            $("#tableshow").css("display", "block");
            getTable();
         });


        //Deletion using selected checkboxes
        $('#deletebtn').on('click', function (event) {
            var gid = [];
            $("input[name='deleteCheckBox']:checked").each(function (i) {
                gid[i] = $(this).val();
            });


           //   get and checking Credentials
                $("#delete_form2").css("display", "block");
                $("#deletebtnn").on('click', function(event) {
                    // Stop the browser from submitting the form.
                    event.preventDefault();

                    const username = document.getElementById("username1").value;
                    const pwd  = document.getElementById("password1").value;
                  //  $("#delete_form")[0].reset();
                    //submitting form using ajax
                    $.ajax({
                        type: "DELETE",
                        url: `http://localhost:8080/groups-1.0-SNAPSHOT/filter?username=${username}&password=${pwd}&gid=${gid}`,
                        success: Message,
                        error: DeleteError

                    });
                    $("#cancel4").click(function() {
                        $("#delete_form2").css("display", "none");
                    });
                 function Message(data){

                    if(data==="UnauthorizedAccess"){
                        showalert("Unauthorized Access","alert-danger");
                    }else{
                      //  data < 1 ? showalert("Id doesn't Exist","alert-warning") : showalert("Successfully Deleted","alert-success");
                        if(data>=1)
                        {
                            showalert("Successfully Deleted","alert-success");
                            $("#delete_form2").css("display", "none");
                            // Removing the deleted records from table
                            var checked = jQuery('input:checkbox:checked').map(function () {
                            return this.value;
                            }).get();
                            jQuery('input:checkbox:checked').parents("tr").remove();
                        }
                        // }else{
                        //     alert("Id doesn't Exist");
                        // }

                        $("#delete_form2")[0].reset();
                    }
                 }
                  function DeleteError(request, status, error) {
                    if(error)
                        showalert('error in deletion' + error,"alert-danger");
                    else
                        showalert('Please select option to Delete',"alert-warning");
                  }

                });
        });

            function getTable() {
                var Group_Name = document.getElementById("GroupName1").value;
                //submitting form using ajax
                $.ajax({
                    url: `http://localhost:8080/groups-1.0-SNAPSHOT/servlets?GroupName=${Group_Name}`,
                    type: 'GET',
                    dataType: 'JSON',
                    success: filltable,
                    error: function() {
                        showalert("Error in Retrieving","alert-danger");
                    }
                });
            }
            //filling data into table
            function filltable(data, status, response) {
                $('#retrieve_form')[0].reset();
                $("#myTable").empty();
                if(data.length == 0){
                    showalert("No Records found");
                    }else{
                        var table = document.getElementById("Group");
                        var tableRef = table.getElementsByTagName('tbody')[0];
                        $(data).each(function(index, value) {
                            var chk = "<td><input type='checkbox' value='" + value.gid + "' name='deleteCheckBox' /></td>";
                            var row = tableRef.insertRow(tableRef.rows.length);
                            var cell1 = row.insertCell(0);
                            var cell2 = row.insertCell(1);
                            var cell3 = row.insertCell(2);
                            var cell4 = row.insertCell(3);
                            var cell5 = row.insertCell(4);
                            var cell6 = row.insertCell(5);
                            var cell7 = row.insertCell(6);
                            var cell8 = row.insertCell(7);

                            cell1.innerHTML = chk;
                            cell2.innerHTML = value.gid;
                            cell3.innerHTML = value.groupName;
                            cell4.innerHTML = value.Owner;
                            cell5.innerHTML = value.sendAs;
                            cell6.innerHTML = value.feedback;
                        });
                        $('#myTable tr > *:nth-child(2)').hide();
                }
             }