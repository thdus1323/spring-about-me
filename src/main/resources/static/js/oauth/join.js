$(document).ready(function () {
    $('.join-toggle button').click(function () {
        $('.join-toggle button').removeClass('join-active btn-outline-success').addClass('btn-outline-secondary');
        $(this).removeClass('btn-outline-secondary').addClass('join-active btn-outline-success');
    });

    // 비밀번호 가리기/보이기 기능
    $('#togglePassword').click(function () {
        const passwordField = $('#join-password');
        const type = passwordField.attr('type') === 'password' ? 'text' : 'password';
        passwordField.attr('type', type);
        $(this).find('i').toggleClass('fa-eye fa-eye-slash');
    });

    $('#togglePasswordConfirm').click(function () {
        const passwordField = $('#join-password-confirm');
        const type = passwordField.attr('type') === 'password' ? 'text' : 'password';
        passwordField.attr('type', type);
        $(this).find('i').toggleClass('fa-eye fa-eye-slash');
    });

});
