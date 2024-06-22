$(document).ready(function () {
    // 클라이언트, 익스퍼트 구분 버튼
    $('#userRole').val('CLIENT');
    $('#getClient').removeClass('btn-outline-secondary').addClass('login-active btn-outline-success');

    $('.login-toggle button').click(function () {
        $('.login-toggle button').removeClass('login-active btn-outline-success').addClass('btn-outline-secondary');
        $(this).removeClass('btn-outline-secondary').addClass('login-active btn-outline-success');
        $('#userRole').val($(this).val());
    });

    $('#kakaoSignup').click(function (e) {
        e.preventDefault();
        var userRole = $('#userRole').val();
        // 세션에 userRole을 저장하기 위해 서버로 AJAX 요청
        $.post('/setUserRole', {userRole: userRole}, function () {
            var kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize?redirect_uri=http://localhost:8080/oauth/callback/kakao&response_type=code&client_id=f07259c71010e17f9a081c435bc8328b";
            window.location.href = kakaoAuthUrl;
        });
    });

    $('#NaverSignup').click(function (e) {
        e.preventDefault();
        var userRole = $('#userRole').val();
        // UUID로 state를 생성
        var state = uuid.v4();

        // 세션에 userRole을 저장하기 위해 서버로 AJAX 요청
        $.post('/setUserRole', {userRole: userRole}, function () {
            var NaverAuthUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=elWt0DvVScIBARwZfyU7&redirect_uri=http://localhost:8080/oauth/callback/naver&state=" + state;
            window.location.href = NaverAuthUrl;
        });
    });
})
