
// Save data in database
function formSubmit(form) {

    var form_data = {};

    // if contactId  is not null then update data else save new data
    if(form.contactId != null ){
        form_data = form;
    }
    if($('#form_phone').val()!=""){
        form_data = {
            firstName: $('#form_name').val(),
            lastName: $('#form_last_name').val(),
            title: $('#form_title').val(),
            email: $('#form_email').val(),
            phoneNumber: $('#form_phone').val(),
            tags: $('#form_tags').val(),
            company: $('#form_company').val(),
        };
    }

    console.log(form_data);
    $.ajax({
        url: "http://localhost:8050/contact",
        data: form_data,
        type: "POST",
        success: function (res) {
            $("#contact-form").css("display", "none");
            $("#contactResponse").html(res);
            console.log(res);
        },
        error: function () { alert("error"); }
    });
    $('#contact-form')[0].reset();
    return false;
}

// Delete data from database based on id

function deleteContact(deleteContact) {
    var data;
    data = JSON.stringify($(deleteContact).serializeArray());
    var contactId = [];

    if (deleteContact[0] > 0) {
        contactId = deleteContact;
    }

    if (data[3] == "n") {
        contactId.push($('#form_contactId').val());
    }

    var req = {
        contactId: contactId,
    };

    $.ajax({
        url: "http://localhost:8050/contact",
        data: req,
        type: "POST",
        success: function (response) {
            $("#delete-form").css("display", "none");
            $("#deleteResponse").html(response);
            console.log(response);
        },
        error: function () { alert("error"); }
    });
    $('#delete-form')[0].reset();
    return false;
}

// search data from UI table, from all rows and display in table

function searchTable() {
    var input, filter, found, table, tr, td, i, j;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("contactsTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td");
        for (j = 0; j < td.length; j++) {
            if (td[j].innerHTML.toUpperCase().indexOf(filter) > -1) {
                found = true;
            }
        }
        if (found) {
            tr[i].style.display = "";
            found = false;
        } else {
            tr[i].style.display = "none";
        }
    }
    hidebuttons();
}
function hidebuttons() {
    $(document).find('.btn_save').hide();
    $(document).find('.btn_cancel').hide();
}

$(document).ready(function () {

    // Make table row editable after edit button click
    $(document).on('click', '.btn_edit', function(event) {
        event.preventDefault();
        var tbl_row = $(this).closest('tr');

        var row_id = tbl_row.attr('row_id');

        tbl_row.find('.btn_save').show();
        tbl_row.find('.btn_cancel').show();

        //hide edit button
        tbl_row.find('.btn_edit').hide();

        //make the whole row editable
        tbl_row.find('.row_data')
            .attr('contenteditable', 'true')
            .attr('edit_type', 'button')
            .addClass('bg-warning')
            .css('padding','3px')

        //--->add the original entry > start
        tbl_row.find('.row_data').each(function(index, val)
        {
            //this will help in case user decided to click on cancel button
            $(this).attr('original_entry', $(this).html());
        });

        tbl_row.find('.row_id').each(function(index, val)
        {
            //this will help in case user decided to click on cancel button
            $(this).attr('rowid', $(this).html());
        });
        //--->add the original entry > end

    });

    // Save data of table row without change
    $(document).on('click', '.btn_cancel', function(event) {
        event.preventDefault();

        var tbl_row = $(this).closest('tr');

        var row_id = tbl_row.attr('row_id');

        //hide save and cacel buttons
        tbl_row.find('.btn_save').hide();
        tbl_row.find('.btn_cancel').hide();

        //show edit button
        tbl_row.find('.btn_edit').show();

        tbl_row.find('.row_data').each(function(index, val)
        {
            $(this).html( $(this).attr('original_entry') );
        });

        //make the whole row text

        tbl_row.find('.row_data')
            .attr('contenteditable', 'false')
            .removeClass('bg-warning')
            .css('padding','')


    });

    // Save data after edit of table row
    $(document).on('click', '.btn_save', function(event) {
        event.preventDefault();
        var tbl_row = $(this).closest('tr');

        var row_id = tbl_row.attr('row_id');


        //hide save and cacel buttons
        tbl_row.find('.btn_save').hide();
        tbl_row.find('.btn_cancel').hide();

        //show edit button
        tbl_row.find('.btn_edit').show();


        //make the whole row text


        tbl_row.find('.row_data')
            .attr('contenteditable', 'false')
            .removeClass('bg-warning')
            .css('padding','')

        //--->get row data > start
        var arr = {};
        tbl_row.find('.row_data').each(function(index, val)
        {
            var col_name = $(this).attr('col_name');
            var col_val  =  $(this).html();
            arr[col_name] = col_val;
        });
        tbl_row.find('.row_id').each(function(index, val)
        {
            var col_name = $(this).attr('col_name');
            var col_val  =  $(this).html();
            arr[col_name] = col_val;
        });
        console.log(arr);

        formSubmit(arr);
        //--->get row data > end

        //use the "arr"	object for your ajax call
        $.extend(arr, {row_id:row_id});

        //out put to show
        $('.post_msg').html( '<pre class="bg-success">'+JSON.stringify(arr, null, 2) +'</pre>')


    });

    // Delete multiple checked records from table
    var checkedid = [];

    $("#delete-button").click(function () {
        checkedid = [];
        $("input:checkbox[class=checkBox]:checked").each(function () {
            checkedid.push($(this).attr("id"));
            $('td input:checked').closest('tr').remove();
        });
        deleteContact(checkedid);
        console.log(checkedid);
    });


    $('.pagination').html('')
    var trnum = 0
    var maxRows = parseInt($(this).val())
    var totalRows = $(table + ' tbody tr').length
    $(table + ' tr:gt(0)').each(function () {
        trnum++
        if (trnum > maxRows) {
            $(this).hide()
        }
        if (trnum <= maxRows) {
            $(this).show()
        }
    })
    if (totalRows > maxRows) {
        var pagenum = Math.ceil(totalRows / maxRows)
        for (var i = 1; i <= pagenum;) {
            $('.pagination').append('<li data-page="' + i + '">\<span>' + i++ + '<span class="sr-only">(current)</span> </span>\</li>').show()
        }
    }

    $('.pagination li:first-child').addClass('active')
    $('.pagination li').on('click', function () {
        var pageNum = $(this).attr('data-page')
        var trIndex = 0;
        $('.pagination li').removeClass('active')
        $(this).addClass('active')
        $(table + ' tr:gt(0)').each(function () {
            trIndex++
            if (trIndex > (maxRows * pageNum) || trIndex <= ((maxRows * pageNum) - maxRows)) {
                $(this).hide()
            } else {
                $(this).show()
            }
        })
    })


    // Pagination for table data number of records based on select option from UI
    var table = '#contactsTable'
    $('#select').on('change', function () {
        $('.pagination').html('')
        var trnum = 0
        var maxRows = parseInt($(this).val())
        var totalRows = $(table + ' tbody tr').length
        $(table + ' tr:gt(0)').each(function () {
            trnum++
            if (trnum > maxRows) {
                $(this).hide()
            }
            if (trnum <= maxRows) {
                $(this).show()
            }
        })
        if (totalRows > maxRows) {
            var pagenum = Math.ceil(totalRows / maxRows)
            for (var i = 1; i <= pagenum;) {
                $('.pagination').append('<li data-page="' + i + '">\<span>' + i++ + '<span class="sr-only">(current)</span> </span>\</li>').show()
            }
        }

        $('.pagination li:first-child').addClass('active')
        $('.pagination li').on('click', function () {
            var pageNum = $(this).attr('data-page')
            var trIndex = 0;
            $('.pagination li').removeClass('active')
            $(this).addClass('active')
            $(table + ' tr:gt(0)').each(function () {
                trIndex++
                if (trIndex > (maxRows * pageNum) || trIndex <= ((maxRows * pageNum) - maxRows)) {
                    $(this).hide()
                } else {
                    $(this).show()
                }
            })
        })
    })
})

$(document).ready(function () {

    // Retrieve data from database
    $('#search').click(function (event) {
        event.preventDefault();
        $.ajax({
            url: "http://localhost:8050/contact",
            type: "Get",
            success: function (responseJson) {
                console.log(responseJson);
                if (responseJson != null) {
                    $("#contactsTable").find("tr:gt(0)").remove();
                    var table1 = $("#contactsTable");
                    $.each(responseJson, function (key, value) {

                        // Create edit cancel save button to edit table row
                        var editbtn ='<span class="btn_edit" > <a href="#" class="btn btn-link " row_id="'+value["contactId"]+1+'" > Edit</a> </span>';

                        editbtn +='<span class="btn_save" style="display:none"> <a href="#" class="btn btn-link" row_id="'+value["contactId"]+1+'"> Save</a> | </span>';
                        editbtn +='<span class="btn_cancel" style="display:none"> <a href="#" class="btn btn-link" row_id="'+value["contactId"]+1+'"> Cancel</a> | </span>';

                        // table row data
                        var rowNew = $("<tr><td class = 'row_id' col_name='contactId'></td><td class = 'row_data' col_name='firstName'></td><td class = 'row_data' col_name='lastName'></td><td class = 'row_data' col_name='phoneNumber'></td><td class = 'row_data' col_name='title'></td><td class = 'row_data' col_name='tags'></td><td class = 'row_data' col_name='email'></td><td class = 'row_data' col_name='company'></td><td></td><td></td></tr>");
                        rowNew.children().eq(0).text(value['contactId']);
                        rowNew.children().eq(1).text(value['firstName']);
                        rowNew.children().eq(2).text(value['lastName']);
                        rowNew.children().eq(3).text(value['phoneNumber']);
                        rowNew.children().eq(4).text(value['title']);
                        rowNew.children().eq(5).text(value['tags']);
                        rowNew.children().eq(6).text(value['email']);
                        rowNew.children().eq(7).text(value['company']);
                        rowNew.children().eq(8).html("<input type='checkbox' class = 'checkBox' id="+value['contactId']+" />");

                        rowNew.children().eq(9).html(editbtn);
                        // Reset buttons to null
                        editbtn = null;
                        rowNew.appendTo(table1);
                    });
                }
            },
            error: function () { alert("error"); }
        });
    });

    // Hide and show data based on button clicked and clear input fields from form
    $("#createContact").click(function () {
        $("#contactResponse").empty();
        $("#deleteResponse").empty();
        $("#contact-form").css("display", "block");
        $("#update").css("display", "none");
        $("#contactsDetails").css("display", "none");
        $("#delete-form").css("display", "none");
    });

    $(cancel2).click(function () {
        $("#contact-form").css("display", "none");
    });


    $("#deleteContact").click(function () {
        $("#contactResponse").empty();
        $("#deleteResponse").empty();
        $("#contact-form").css("display", "none");
        $("#update").css("display", "none");
        $("#contactsDetails").css("display", "none");
        $("#delete-form").css("display", "block");
    });

    $(cancel3).click(function () {
        $("#delete-form").css("display", "none");
    });

    $("#search").click(function () {
        // language=JQuery-CSS
        $("#contactResponse").empty();
        $("#deleteResponse").empty();
        $("#contact-form").css("display", "none");
        $("#update").css("display", "none");
        $("#contactsDetails").css("display", "block");
        $("#delete-form").css("display", "none");
    });



});