$(function () {
    $('.page_num').hover(function () {
        $(this).addClass('page_hover');
    }, function () {
        $(this).removeClass('page_hover');
    }).click(function () {
        $(this).addClass('page_now').siblings('.page_num').removeClass('page_now');
    });
});
