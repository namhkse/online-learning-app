$(document).ready(function () {
    $(".lesson-type").on('change', function () {
        var values = $('.lesson-type').val();
        console.log("values: "+values);
        if(values == 1){
            $('.type-contentv').attr('class', "type-contentv lestype");
            console.log("true1");
        } else{
            $('.type-contentv').attr('class', "type-contentv");
        }
        if(values == 2){
            $('.type-contentq').attr('class', "type-contentq lestype");
            console.log("true2");
        } else{
            $('.type-contentq').attr('class', "type-contentq");
        }
    });
});

$(document).ready(function () {
    $(".sublesson").on('change', function () {
        var lessontype = $(this).val();
        var lorder = $('.lesson-order').val();
        if(lorder == null){
            lorder = "";
        }
        $.ajax({
            type: 'get',
            url: "../order",
            data: {
                orderId: lessontype,
                lessonOrder: lorder,
            },
            success: function (data) {
                var order = document.getElementById("order");
                order.innerHTML = data;
            }
        });
    });
});
    