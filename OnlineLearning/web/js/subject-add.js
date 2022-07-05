function showFilterBox() {
    var searchCategory = document.getElementById('filter-category-box');
    searchCategory.style.display = "flex";
}

function hideFilterBox() {
    var searchCategory = document.getElementById('filter-category-box');
    searchCategory.style.display = "none";
}

function checkedCategory(element) {
    if (element.checked) {
        uncheckedSubcategory();
        uncheckedCategory();
        element.checked = true;
        textDisplay = element.parentNode.children[1].innerText;
        document.querySelector('#current-category').innerText = textDisplay;
    }
    let count = 0;
    let checkBoxes = document.querySelectorAll('#category-checkbox input');
    for (let i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked === false) {
            count++;
        }
    }
    if (count === checkBoxes.length) {
        document.querySelector('#current-category').innerText = '';
    }
}

function uncheckedSubcategory() {
    let listSubCategory = document.querySelectorAll('.category');
    for (var i = 0; i < listSubCategory.length; i++) {
        listSubCategory[i].children[0].checked = false;
    }
}

function uncheckedCategory() {
    let listCategory = document.querySelectorAll('.main-category');
    for (var i = 0; i < listCategory.length; i++) {
        listCategory[i].children[0].checked = false;
    }
}

function searchCategory(element) {
    let searchValue = element.value.toUpperCase();
    let items = document.querySelector('#category-checkbox').children;
    for (var i = 0; i < items.length; i++) {
        for (var j = 1; j < items[i].children.length; j++) {
            if (items[i].children[j].querySelector('label').innerText.toUpperCase().indexOf(searchValue) > -1) {
                items[i].children[j].style.display = 'block';
            } else {
                items[i].children[j].style.display = 'none';
            }
        }
    }
    for (var i = 0; i < items.length; i++) {
        if (items[i].children[0].querySelector('span').innerText.toUpperCase().indexOf(searchValue) > -1) {
            items[i].children[0].style.display = 'block';
        } else {
            items[i].children[0].style.display = 'none';
        }
    }
}

function searchExpert(element) {
    let searchValue = element.value.toUpperCase();
    let items = document.querySelector('#expert-checkbox').children;
    for (var i = 0; i < items.length; i++) {
        if (items[i].children[1].innerText.toUpperCase().indexOf(searchValue) > -1) {
            items[i].style.display = 'block';
        } else {
            items[i].style.display = 'none';
        }
    }
}

function showExpertBox() {
    var searchCategory = document.getElementById('filter-expert-box');
    searchCategory.style.display = "flex";
}

function hideExpertBox() {
    var searchCategory = document.getElementById('filter-expert-box');
    searchCategory.style.display = "none";
}

function checkSelectCategory() {
    let inputElements = document.querySelectorAll('#category-checkbox input');
    for (var i = 0; i < inputElements.length; i++) {
        if (inputElements[i].checked) {
            return true;
        }
    }
    document.querySelector('#category-item .text-danger').style.display = 'block';
    return false;
}