-- RESERVATION_SCHEDULED("확정 예정"),
--     RESERVATION_COMPLETED("예약 확정"),
-- counsel_tb
-- INSERT INTO counsel_tb (client_id, expert_id, voucher_id, schedule_id, payment_id, reservation_status, counsel_status,
--                         counsel_date,
--                         counsel_time, day_of_week, result, review_state, created_at, updated_at)
-- VALUES
--     --1번 클라이언트가 21번 전문가의 텍스트 바우처 4회권을 구매하고 상담을 1회 완료하였고, 2회 상담을 신청하였다. 그러면 상담완료는 1회 예약은 2회 예약가능한 남은 횟수는 1회이다.
--     (1, 21, 3, 1, 2, 'RESERVATION_SCHEDULED', 'COUNSEL_CONFIRMED', '2024-06-22', '09:00', '월', '상담결과 테스트용',
--      'REVIEW_PENDING', NOW(),
--      NOW()),
--     (1, 21, 1, 1, 1, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-22', '09:00', '월', '상담결과 테스트용',
--      'REVIEW_COMPLETED', NOW(), NOW()),
--     (1, 21, 1, 1, 1, 'RESERVATION_SCHEDULED', 'COUNSEL_CONFIRMED', '2024-06-22', '09:00', '월', '상담결과 테스트용',
--      'REVIEW_PENDING', NOW(),
--      NOW()),
--     (1, 21, 1, 1, 1, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-23', '09:00', '월', '상담결과 테스트용',
--      'REVIEW_PENDING', NOW(), NOW());

INSERT INTO counsel_tb (client_id, expert_id, voucher_id, schedule_id, payment_id, reservation_status, counsel_status,
                        counsel_date, counsel_time, day_of_week, result, review_state, created_at, updated_at)
VALUES
    -- 1번 클라이언트 상담 완료 내역
    (1, 21, 1, 1, 1, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-20', '09:00', '월', '상담결과 테스트용',
     'REVIEW_COMPLETED', NOW(), NOW()),
    (1, 21, 1, 1, 1, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-06-27', '09:00', '월', '상담결과 테스트용',
     'REVIEW_PENDING', NOW(), NOW()),
    (1, 21, 1, 1, 1, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-04', '09:00', '월', '상담결과 테스트용',
     'REVIEW_PENDING', NOW(), NOW()),
    (1, 21, 1, 1, 1, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-11', '09:00', '월', '상담결과 테스트용',
     'REVIEW_PENDING', NOW(), NOW()),
    -- 2번 클라이언트 상담 완료 내역
    (2, 22, 1, 2, 2, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-21', '10:00', '화', '상담결과 테스트용',
     'REVIEW_COMPLETED', NOW(), NOW()),
    (2, 22, 1, 2, 2, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-06-28', '10:00', '화', '상담결과 테스트용',
     'REVIEW_PENDING', NOW(), NOW()),
    (2, 22, 1, 2, 2, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-05', '10:00', '화', '상담결과 테스트용',
     'REVIEW_PENDING', NOW(), NOW()),
    (2, 21, 1, 2, 2, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-12', '10:00', '화', '상담결과 테스트용',
     'REVIEW_PENDING', NOW(), NOW()),
    -- 3번 클라이언트 상담 완료 내역
    (3, 23, 1, 3, 3, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-22', '11:00', '수', '상담결과 테스트용',
     'REVIEW_COMPLETED', NOW(), NOW()),
    (3, 23, 1, 3, 3, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-06-29', '11:00', '수', '상담결과 테스트용',
     'REVIEW_PENDING', NOW(), NOW()),
    (3, 23, 1, 3, 3, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-06', '11:00', '수', '상담결과 테스트용',
     'REVIEW_PENDING', NOW(), NOW()),
    -- 4번 클라이언트 상담 완료 내역
    (4, 24, 1, 4, 4, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-23', '12:00', '목', '상담결과 테스트용',
     'REVIEW_COMPLETED', NOW(), NOW()),
    (4, 24, 1, 4, 4, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-06-30', '12:00', '목', '상담결과 테스트용',
     'REVIEW_PENDING', NOW(), NOW()),
    (4, 24, 1, 4, 4, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-01', '12:00', '목', '상담결과 테스트용',
     'REVIEW_PENDING', NOW(), NOW()),
    (4, 24, 1, 4, 4, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-08', '12:00', '목', '상담결과 테스트용',
     'REVIEW_PENDING', NOW(), NOW()),
    -- 5번 클라이언트 상담 완료 내역
    (5, 25, 1, 5, 5, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-24', '13:00', '금', '상담결과 테스트용',
     'REVIEW_COMPLETED', NOW(), NOW()),
    -- 6번 클라이언트 상담 완료 내역
    (6, 21, 1, 1, 6, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-25', '14:00', '월', '상담결과 테스트용',
     'REVIEW_COMPLETED', NOW(), NOW()),
    -- 7번 클라이언트 상담 완료 내역
    (7, 22, 1, 2, 7, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-26', '15:00', '화', '상담결과 테스트용',
     'REVIEW_COMPLETED', NOW(), NOW()),
    -- 8번 클라이언트 상담 완료 내역
    (8, 23, 1, 3, 8, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-27', '16:00', '수', '상담결과 테스트용',
     'REVIEW_COMPLETED', NOW(), NOW()),
    -- 9번 클라이언트 상담 완료 내역
    (9, 24, 1, 4, 9, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-28', '17:00', '목', '상담결과 테스트용',
     'REVIEW_COMPLETED', NOW(), NOW()),
    -- 10번 클라이언트 상담 완료 내역
    (10, 25, 1, 5, 10, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-29', '18:00', '금', '상담결과 테스트용',
     'REVIEW_COMPLETED', NOW(), NOW());

