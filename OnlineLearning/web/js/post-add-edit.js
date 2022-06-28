var filterMain = document.querySelectorAll(".c-course-filter-main");
var filterChild = document.querySelectorAll(".c-course-filter-list");
var iconDown = document.querySelectorAll(".icon-down");
filterMain.forEach(function (item, index) {
    item.onclick = function () {
        if (iconDown[index].classList.contains("fa-angle-up")) {
            iconDown[index].classList.toggle("fa-angle-up");
            iconDown[index].classList.toggle("fa-angle-down");
            filterChild[index].style.height = "0";
        } else {
            iconDown[index].classList.toggle("fa-angle-up");
            iconDown[index].classList.toggle("fa-angle-down");
            filterChild[index].style.height = "auto";
        }
    };
});

function checkedCategory(element) {
    var listSubCate = element.parentNode.parentNode.parentNode.childNodes[3].getElementsByClassName("search-sub-category-name");
    for (i = 0; i < listSubCate.length; i++) {
        if (element.checked === true)
            listSubCate[i].childNodes[1].checked = true;
        else {
            listSubCate[i].childNodes[1].checked = false;
        }
    }
}

function checkedSubCategory(element) {
    var category = element.parentNode.parentNode.parentNode.childNodes[1].childNodes[1].childNodes[1];
    var isChecked = false;
    var subCategories = element.parentNode.parentNode.getElementsByClassName("search-sub-category-name");
    for (var i = 0; i < subCategories.length; i++) {
        if (subCategories[i].childNodes[1].checked === true) {
            isChecked = true;
            category.checked = true;
        }
    }
    if (isChecked === false) {
        category.checked = false;
    }

}

var iconDownCates = document.querySelectorAll(".icon-down-cate");
var subCates = document.querySelectorAll(".search-sub-category");
iconDownCates.forEach(function (icon, index) {
    icon.onclick = function () {
        if (icon.classList.contains("fa-angle-up")) {
            icon.classList.toggle("fa-angle-up");
            icon.classList.toggle("fa-angle-down");
            subCates[index].style.height = "0";
        } else {
            icon.classList.toggle("fa-angle-up");
            icon.classList.toggle("fa-angle-down");
            subCates[index].style.height = "auto";
        }
    };
});

function searchCategory() {

    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById('holder-search-category');

    filter = input.value.toUpperCase();
    ul = document.getElementById("form-select-category");
    li = ul.getElementsByClassName("search-category-item");

    for (i = 0; i < li.length; i++) {
        a = li[i].childNodes[1].childNodes[1].childNodes[3];
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}

function AddEditBlog() {

    var datasSubCate = document.querySelectorAll(".search-sub-category-name input");
    var arraySearchSubId = [];
    datasSubCate.forEach(function (data) {
        if (data.checked === true) {
            arraySearchSubId.push(data.value);
        }
    });
    var dataSubCate = arraySearchSubId.join();

    var datasCate = document.querySelectorAll(".search-category-name input");
    var arraySearchCateId = [];
    datasCate.forEach(function (data) {
        if (data.checked === true) {
            arraySearchCateId.push(data.value);
        }
    });
    var dataCate = arraySearchCateId.join();
    var file = document.getElementById("file");
    $(function () {
        $.ajax({
            type: "POST",
            url: "../management/post-detail",
            cache: false,
            data: {
                action: document.getElementById("button-add-edit").value,
                id: document.getElementById("ae-id").value,
                title: document.getElementById("ae-title").value,
                description: document.getElementById("ae-description").value,
                thumbnailUrl: file.value,
                display: document.querySelector(".switch input").checked,
                content: CKEDITOR.instances.content.getData(),
                dataCategory: dataCate,
                dataSubCategory: dataSubCate
            },
            success: function (result) {
                    console.log(result);
//                document.getElementsByClassName("alert-success")[0].classList.toggle("d-none");
//                setTimeout(function() {
//                    document.getElementsByClassName("alert-success")[0].classList.toggle("d-none");
//                }, 2000);
            }
        });
    });
}