var ajaxUrl = "ajax/admin/users/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

$("#datatable").on("click", "#enable", false, function () {
    var id = $(this).attr("userId");
    var isEnable;
    if ($("#enable").is(":checked")){
        isEnable = "true";
    }
    else{
        isEnable = "false";
    }
    $.ajax({
        type: "POST",
        url: ajaxUrl + id,
        data: {"isEnable" : isEnable},
        success: function () {
            $(updateTable());
            successNoty("Update!");
        }
    });
});
