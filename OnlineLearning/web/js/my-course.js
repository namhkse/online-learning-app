var filterIcon = document.getElementsByClassName("afterTemp")[0];

filterIcon.onmouseover = function () {
    var searchCategory = document.getElementById("search-category");
    searchCategory.style.display = "block";
}

filterIcon.onmouseout = function () {
    var searchCategory = document.getElementById("search-category");
    searchCategory.style.display = "none";
}

var listCourse = document.querySelectorAll(".my-course-item-img");
var overlayImg = document.querySelectorAll(".overlay-img");
listCourse.forEach(function (course, index) {
    course.onmouseover = function () {
        overlayImg[index].style.opacity = 1;
        overlayImg[index].style.zIndex = 1;
    }
    course.onmouseout = function () {
        overlayImg[index].style.opacity = 0;
        overlayImg[index].style.zIndex = -1;
    }
})


function searchCategory() {

    // Declare variables
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById('holder-search-category');

    filter = input.value.toUpperCase();
    ul = document.getElementById("form-select-category");
    li = ul.getElementsByClassName("search-category-item");

    for (i = 0; i < li.length; i++) {
        a = li[i].childNodes[1].childNodes[3];
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}

function searchCourse() {
    var input = document.getElementById("search-my-course");
    var filter = input.value.toUpperCase();
    var listCourse = document.querySelectorAll(".my-course-item");
    listCourse.forEach(function (course) {
        var titleCourse = course.childNodes[3].childNodes[1].innerText.toUpperCase();
        if (titleCourse.indexOf(filter) > -1) {
            course.style.display = "";
        } else {
            course.style.display = "none";
        }
    })
}

var options = document.querySelectorAll(".option-course");
var noticeUnenroll = document.querySelectorAll(".notice-unenroll");
var btnCancels = document.querySelectorAll(".btn-cancel");
options.forEach(function (option, index) {
    option.onclick = function () {
        noticeUnenroll[index].style.opacity = 1;
        noticeUnenroll[index].style.zIndex = 3;
    }

})
btnCancels.forEach(function (cancel, index) {
    cancel.onclick = function () {
        noticeUnenroll[index].style.opacity = 0;
        noticeUnenroll[index].style.zIndex = -1;
    }

})