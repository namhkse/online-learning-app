$(function () {
    $("#collection").select2();
});

$(document).ready(function () {
    $('#table').DataTable();
});

var filterIcon = document.getElementById('filter-icon');

function showFilterBox() {
    var searchCategory = document.getElementById('filter-category-box');
    searchCategory.style.display = "flex";
}

function hideFilterBox() {
    var searchCategory = document.getElementById('filter-category-box');
    searchCategory.style.display = "none";
}

function checkedSubcategory(element) {
    let listSubCategory = element.parentElement.parentElement.getElementsByTagName('div');
    for (var i = 1; i < listSubCategory.length; i++) {
        if (element.checked === true) {
            listSubCategory[i].children[0].checked = true;
        } else {
            listSubCategory[i].children[0].checked = false;
        }
    }
    search();
}

function checkedCategory(element) {
    let category = element.parentElement.parentElement.children[0].children[0];
    let listSubCategory = element.parentElement.parentElement.getElementsByTagName('div');
    let numberSubCategory = listSubCategory.length;
    let count = 1;
    for (var i = 1; i < listSubCategory.length; i++) {
        if (listSubCategory[i].children[0].checked) {
            count++;
        }
    }
    if (count === numberSubCategory) {
        category.indeterminate = false;
        category.checked = true;
    }
    if (count > 1 && count < numberSubCategory) {
        category.checked = false;
        category.indeterminate = true;
    }
    if (count == 1) {
        category.indeterminate = false;
        category.checked = false;
    }
    search();
}

function search() {
    let categoryElement = document.querySelectorAll('.category');
    let category = [];
    for (var i = 0; i < categoryElement.length; i++) {
        if (categoryElement[i].children[0].checked === true) {
            category.push(categoryElement[i].children[0].value);
        }
    }
    let categoryID = "";
    if (category.length != 0) {
        categoryID = category.join();
    }

    let mainCategoryElement = document.querySelectorAll('.others');
    let mainCategory = [];
    for (var i = 0; i < mainCategoryElement.length; i++) {
        if (mainCategoryElement[i].children[0].checked === true) {
            mainCategory.push(mainCategoryElement[i].children[0].value);
        }
    }
    let mainCategoryID = "";
    if (mainCategory.length != 0) {
        mainCategoryID = mainCategory.join();
    }

    let statusElement = document.querySelector('.select-tag');
    let status;
    for (var i = 0; i < statusElement.length; i++) {
        if (statusElement.children[i].selected === true) {
            status = statusElement.children[i].value;
        }
    }

    $(function () {
        $.ajax({
            type: "POST",
            url: "subject-list",
            cache: false,
            data: {
                categoryID: categoryID,
                mainCategoryID: mainCategoryID,
                status: status
            },
            success: function (result) {
                $('#table').DataTable().destroy();
                document.getElementsByTagName('tbody')[0].innerHTML = "";
                document.getElementsByTagName('tbody')[0].innerHTML += result;
                $('#table').DataTable();
            }
        });
    });
}

function deleteSubject(id, btn) {
    if (confirm('Are you sure you want to delete this subject?')) {
        $(function () {
            $.ajax({
                type: "delete",
                url: `subject-list?subjectID=${id}`,
                cache: false,
                success: function () {
                    $(btn).closest('tr').fadeOut("slow");
                }
            });
        });
    }
}

function searchCategory(element) {
    let searchValue = document.querySelector('#search-category').value.toUpperCase();
    let items = document.querySelector('#category-checkbox').children;
    for (var i = 0; i < items.length; i++) {
        for (var j = 0; j < items[i].children.length; j++) {
            if (items[i].children[j].querySelector('label').innerText.toUpperCase().indexOf(searchValue) > -1) {
                items[i].children[j].style.display = 'block';
            } else {
                items[i].children[j].style.display = 'none';
            }
        }
    }
}