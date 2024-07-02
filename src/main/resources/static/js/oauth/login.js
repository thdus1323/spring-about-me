$(document).ready(function () {
    // 기본 역할 설정
    $('#userRole').val('CLIENT');

    // 클라이언트와 상담사 버튼 클릭 이벤트 설정
    $('#getClient').click(function () {
        $('#userRole').val('CLIENT');
        $('#clientLoginForm input[name="userRole"]').val('CLIENT');
    });

    $('#getExpert').click(function () {
        $('#userRole').val('EXPERT');
        $('#expertLoginForm input[name="userRole"]').val('EXPERT');
    });

    // 클라이언트 카카오 로그인 버튼 클릭 이벤트
    $('#kakaoSignupClient').click(function (e) {
        e.preventDefault();
        var userRole = 'CLIENT';
        $.post('/setUserRole', { userRole: userRole }, function () {
            var kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize?redirect_uri=http://localhost:8080/oauth/callback/kakao&response_type=code&client_id=f07259c71010e17f9a081c435bc8328b";
            window.location.href = kakaoAuthUrl;
        });
    });

    // 클라이언트 네이버 로그인 버튼 클릭 이벤트
    $('#naverSignupClient').click(function (e) {
        e.preventDefault();
        var userRole = 'CLIENT';
        var state = uuid.v4(); // UUID 생성
        $.post('/setUserRole', { userRole: userRole }, function () {
            var naverAuthUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=elWt0DvVScIBARwZfyU7&redirect_uri=http://localhost:8080/oauth/callback/naver&state=" + state;
            window.location.href = naverAuthUrl;
        });
    });

    // 상담사 카카오 로그인 버튼 클릭 이벤트
    $('#kakaoSignupExpert').click(function (e) {
        e.preventDefault();
        var userRole = 'EXPERT';
        $.post('/setUserRole', { userRole: userRole }, function () {
            var kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize?redirect_uri=http://localhost:8080/oauth/callback/kakao&response_type=code&client_id=f07259c71010e17f9a081c435bc8328b";
            window.location.href = kakaoAuthUrl;
        });
    });

    // 상담사 네이버 로그인 버튼 클릭 이벤트
    $('#naverSignupExpert').click(function (e) {
        e.preventDefault();
        var userRole = 'EXPERT';
        var state = uuid.v4(); // UUID 생성
        $.post('/setUserRole', { userRole: userRole }, function () {
            var naverAuthUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=elWt0DvVScIBARwZfyU7&redirect_uri=http://localhost:8080/oauth/callback/naver&state=" + state;
            window.location.href = naverAuthUrl;
        });
    });

    // 슬라이드 애니메이션
    $('.client-login').click(function () {
        $('.login-slide-content').animate({ left: '0' }, 500);
    });

    $('.counselor-login').click(function () {
        $('.login-slide-content').animate({ left: '-800px' }, 500);
    });

    $('.right-for-mid').click(function () {
        $('.login-slide-content').animate({ left: '-400px' }, 500);
    });

    $('.left-for-mid').click(function () {
        $('.login-slide-content').animate({ left: '-400px' }, 500);
    });
});