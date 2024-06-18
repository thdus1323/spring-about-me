package com.example.aboutme;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckController {

    //TODO: health check
//    @GetMapping("/")
//    public String index() {
////        여기에서 클라이언트와 익스퍼트 if로 구분해서 리턴합니다.
//        return "client/main";
//    }

    //TODO: 지울거임
    @GetMapping("/")
    public String expert() {
        return "expert/main";
    }



    //TODO:클라이언트용 페이지
//    //전문가 찾기 - 메인
//    @GetMapping("/client/findExpert")
//    public String findExpert() {
//        return "client/findExpert/main";
//    }

//    //전문가 찾기 - 상세보기
//    @GetMapping("/client/findExpert/detail")
//    public String findExpertDetail() {
//        return "client/findExpert/detail";
//    }

//    //전문가 칮기 - 이용권
//    @GetMapping("/client/findExpert/voucher")
//    public String findExpertVoucher() {
//        return "client/findExpert/voucher";
//    }

//    //전문가 칮기 - 예약하기
//    @GetMapping("/client/findExpert/reservation")
//    public String findExpertReservation() {
//        return "client/findExpert/reservation";
//    }

//    //전문가 칮기 - 결제하기
//    @GetMapping("/client/findExpert/payment")
//    public String findExpertPayment() {
//        return "client/findExpert/payment";
//    }

//    //클라이언트 - 마이페이지
//    @GetMapping("/client/mypage")
//    public String clientMypage() {
//        return "client/mypage";
//    }

//    //커뮤니티 - 메인
//    @GetMapping("/comm")
//    public String community() {
//        return "comm/comm-main";
//    }

//    //후기
//    @GetMapping("/review")
//    public String review() {
//        return "expert/review";
//    }

//    //이용권목록
//    @GetMapping("/voucher-list")
//    public String voucherList() {
//        return "expert/voucher-list";
//    }

//    //상담일정
//    @GetMapping("/schedule")
//    public String schedule() {
//        return "expert/schedule";
//    }


}
