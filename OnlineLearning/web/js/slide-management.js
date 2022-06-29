$(function () {
    $("#collection").select2();
});

$(document).ready( function () {
    $('#table').DataTable();
} );

function changeStatus(button, id) {
    if (button.innerHTML.indexOf('on') > -1) {
        hideSlide(button, id);
    } else {
        showSlide(button, id);
    }
}

function hideSlide(button, id) {
    if (confirm('Are you sure you want to hide this slide?')) {
        $(function () {
            $.ajax({
                type: "post",
                url: `slide-list?id-hide=${id}`,
                cache: false,
                success: function () {
                    button.innerHTML = "";
                    button.innerHTML += '<i class="fa-solid fa-toggle-off"></i>';
                }
            });
        });
    }
}

function showSlide(button, id) {
    if (confirm('Are you sure you want to show this slide?')) {
        $(function () {
            $.ajax({
                type: "post",
                url: `slide-list?id-show=${id}`,
                cache: false,
                success: function () {
                    button.innerHTML = "";
                    button.innerHTML += '<i class="fa-solid fa-toggle-on"></i>';
                }
            });
        });
    }
}

function deletePricePackage(id, btn) {
    if (confirm('Are you sure you want to delete this slide?')) {
        $(function () {
            $.ajax({
                type: "delete",
                url: `slide-list?id=${id}`,
                cache: false,
                success: function () {
                    $(btn).closest('tr').fadeOut("slow");
                }
            });
        });
    }
}