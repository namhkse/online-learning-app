function deleteDimension(id, btn) {
    if (confirm('Are you sure you want to delete this dimension?')) {
        $(function () {
            $.ajax({
                type: "delete",
                url: `subject-detail?dimensionID=${id}`,
                cache: false,
                success: function () {
                    $(btn).closest('tr').fadeOut("slow");
                }
            });
        });
    }
}

$(document).ready( function () {
    $('#table').DataTable();
} );