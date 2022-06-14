function openCity(evt, cityName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

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