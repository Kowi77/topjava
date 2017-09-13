var ajaxUrl = "ajax/meals/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
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

$(document).ready(function () {
    $("#filterForm").submit(function () {
        var form = $("#filterForm");
        /*  $.get(ajaxUrl + "filter", {data: form.serialize()}, function (data) {
         datatableApi.clear().rows.add(data).draw();
         });*/
        $.ajax({
            type: "GET",
            url: ajaxUrl + "filter",
            data: form.serialize(),
            success: function (data) {
                datatableApi.clear().rows.add(data).draw();
                successNoty("Filtered");
            }
        });
        return false;
    });
});
