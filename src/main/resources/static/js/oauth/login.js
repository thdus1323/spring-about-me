$(document).ready(function () {
    $('.login-toggle button').click(function () {
        $('.login-toggle button').removeClass('login-active btn-outline-success').addClass('btn-outline-secondary');
        $(this).removeClass('btn-outline-secondary').addClass('login-active btn-outline-success');
    });
})