
$(document).ready(function () {
    $('#table').DataTable();
});

$(document).ready(function () {
    $("#status").on('change', function () {
        var value = $('#status').val();
        let params = (new URL(document.location)).searchParams;
        let Sid = params.get("Sid");
        $.ajax({
            type: 'POST',
            url: "../management/course-list",
            data: {
                status: value,
                subjectid: Sid
            },
            success: function (data) {
                var table = document.getElementById("content-course");
                table.innerHTML = data;
                console.log(value);
            }
        });
    });
});

$(document).ready(function () {
    $(".text-danger").click(function () {
        //this là cái mà vừa click
        var id = $(this).attr('id');
        if (confirm("Do you want to delete this course?")) {
            $.ajax({
                type: 'delete',
                url: "../management/course-list?Cid=" + id,
                success: function (data) {
                    $(this).closest('tr').fadeOut();
                }
            });
        }
    });
});