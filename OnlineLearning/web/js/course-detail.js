
var btnEnroll = document.getElementsByClassName("course-enroll-button")[0];
var overlay = document.getElementById("cd-overlay");
var notice = document.getElementById("notice-buy-course");
var closeBtn = document.getElementById("close-btn");
if (btnEnroll !== null || btnEnroll !== undefined) {
    btnEnroll.onclick = function () {
        overlay.style.display = "block";
        notice.style.transform = "translateY(0)";
    };
}

function closeNotice() {
    overlay.style.display = "none";
    notice.style.transform = "translateY(calc(-100% - 500px))";
}
overlay.onclick = closeNotice;
closeBtn.onclick = closeNotice;

var noticeNotEnough = document.querySelector("#notice-not-enough");
var btnBuy = document.querySelector("#btn-buy-course");
function checkBalance() {
    var priceNum = document.querySelectorAll(".notice-price .price-num");
    var txt = priceNum[0].innerText;
    if (txt === "Free") {
        priceCourse = 0;
    } else {
        priceCourse = parseFloat(priceNum[0].innerText);
    }
    var balanceAccount = parseFloat(priceNum[1].innerText);
    if (priceCourse <= balanceAccount) {

    } else {
        noticeNotEnough.innerText = "Your balance is not enough to pay this course";
        btnBuy.disabled = true;
        btnBuy.style.opacity = 0.4;
        btnBuy.style.cursor = "not-allowed";
    }
}

checkBalance();