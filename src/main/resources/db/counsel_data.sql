-- RESERVATION_SCHEDULED("확정 예정"),
--     RESERVATION_COMPLETED("예약 확정"),

-- counsel_tb
INSERT INTO counsel_tb (client_id, expert_id, voucher_id, schedule_id, payment_id, reservation_status, counsel_status,
                        counsel_date, counsel_time, day_of_week, result, review_state, created_at, updated_at)
VALUES
-- TODO: 21번 상담완료
-- 1번 클라이언트 상담 완료 내역
(1, 21, 1, 1, 1, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-20', '09:00', '월', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),

-- 2번 클라이언트 상담 완료 내역
(2, 21, 1, 2, 2, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-21', '10:00', '화', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),

-- 3번 클라이언트 상담 완료 내역
(3, 21, 1, 3, 3, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-22', '11:00', '수', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),

-- 4번 클라이언트 상담 완료 내역
(4, 21, 1, 4, 4, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-23', '12:00', '목', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 5번 클라이언트 상담 완료 내역
(5, 21, 1, 5, 5, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-24', '13:00', '금', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 6번 클라이언트 상담 완료 내역
(6, 21, 1, 1, 6, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-25', '14:00', '월', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 7번 클라이언트 상담 완료 내역
(7, 21, 1, 2, 7, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-26', '15:00', '화', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 8번 클라이언트 상담 완료 내역
(8, 21, 1, 3, 8, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-27', '16:00', '수', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 9번 클라이언트 상담 완료 내역
(9, 21, 1, 4, 9, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-28', '17:00', '목', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 10번 클라이언트 상담 완료 내역
(10, 21, 1, 5, 10, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-29', '18:00', '금', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),




-- TODO: 22번 상담완료
-- 1번 클라이언트 상담 완료 내역
(1, 22, 2, 6, 11, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-20', '09:00', '월', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),

-- 2번 클라이언트 상담 완료 내역
(2, 22, 2, 7, 12, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-21', '10:00', '화', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),

-- 3번 클라이언트 상담 완료 내역
(3, 22, 2, 8, 13, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-22', '11:00', '수', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),

-- 4번 클라이언트 상담 완료 내역
(4, 22, 2, 9, 14, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-23', '12:00', '목', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 5번 클라이언트 상담 완료 내역
(5, 22, 2, 10, 15, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-24', '13:00', '금', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 6번 클라이언트 상담 완료 내역
(6, 22, 2, 6, 16, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-25', '14:00', '월', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 7번 클라이언트 상담 완료 내역
(7, 22, 2, 7, 17, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-26', '15:00', '화', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 8번 클라이언트 상담 완료 내역
(8, 22, 2, 8, 18, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-27', '16:00', '수', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 9번 클라이언트 상담 완료 내역
(9, 22, 2, 9, 19, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-28', '17:00', '목', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 10번 클라이언트 상담 완료 내역
(10, 22, 2, 10, 20, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-29', '18:00', '금', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),



-- TODO: 23번 상담완료
-- 1번 클라이언트 상담 완료 내역
(1, 23, 5, 11, 21, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-20', '09:00', '월', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),

-- 2번 클라이언트 상담 완료 내역
(2, 23, 5, 12, 22, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-21', '10:00', '화', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),

-- 3번 클라이언트 상담 완료 내역
(3, 23, 5, 13, 23, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-22', '11:00', '수', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),

-- 4번 클라이언트 상담 완료 내역
(4, 23, 5, 14, 24, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-23', '12:00', '목', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 5번 클라이언트 상담 완료 내역
(5, 23, 5, 15, 25, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-24', '13:00', '금', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 6번 클라이언트 상담 완료 내역
(6, 23, 5, 11, 26, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-25', '14:00', '월', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 7번 클라이언트 상담 완료 내역
(7, 23, 5, 12, 27, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-26', '15:00', '화', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 8번 클라이언트 상담 완료 내역
(8, 23, 5, 13, 28, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-27', '16:00', '수', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 9번 클라이언트 상담 완료 내역
(9, 23, 5, 13, 29, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-28', '17:00', '목', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 10번 클라이언트 상담 완료 내역
(10, 23, 5, 13, 30, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-29', '18:00', '금', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),


-- TODO: 21번 상담완료
-- 1번 클라이언트 상담 완료 내역
(1, 21, 1, 1, 1, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-20', '09:00', '월', '상담결과 테스트용',
    'REVIEW_COMPLETED', NOW(), NOW()),

-- 2번 클라이언트 상담 완료 내역
(2, 21, 1, 2, 2, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-21', '10:00', '화', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),

-- 3번 클라이언트 상담 완료 내역
(3, 21, 1, 3, 3, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-22', '11:00', '수', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),

-- 4번 클라이언트 상담 완료 내역
(4, 21, 1, 4, 4, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-23', '12:00', '목', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 5번 클라이언트 상담 완료 내역
(5, 21, 1, 5, 5, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-24', '13:00', '금', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 6번 클라이언트 상담 완료 내역
(6, 21, 1, 1, 6, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-25', '14:00', '월', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 7번 클라이언트 상담 완료 내역
(7, 21, 1, 2, 7, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-26', '15:00', '화', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 8번 클라이언트 상담 완료 내역
(8, 21, 1, 3, 8, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-27', '16:00', '수', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 9번 클라이언트 상담 완료 내역
(9, 21, 1, 4, 9, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-28', '17:00', '목', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW()),
-- 10번 클라이언트 상담 완료 내역
(10, 21, 1, 5, 10, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-29', '18:00', '금', '상담결과 테스트용',
 'REVIEW_COMPLETED', NOW(), NOW());








--
-- -- 1번 클라이언트 상담 전 내역
-- (1, 21, 1, 1, 1, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-06-27', '09:00', '월', '상담결과 테스트용',
--  'REVIEW_PENDING', NOW(), NOW()),
-- (1, 21, 1, 1, 1, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-04', '09:00', '월', '상담결과 테스트용',
--  'REVIEW_PENDING', NOW(), NOW()),
--
-- -- 2번 클라이언트 상담 전 내역
-- (2, 22, 1, 2, 2, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-06-28', '10:00', '화', '상담결과 테스트용',
--  'REVIEW_PENDING', NOW(), NOW()),
-- (2, 22, 1, 2, 2, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-05', '10:00', '화', '상담결과 테스트용',
--  'REVIEW_PENDING', NOW(), NOW()),
-- (2, 21, 1, 2, 2, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-12', '10:00', '화', '상담결과 테스트용',
--  'REVIEW_PENDING', NOW(), NOW()),
--
-- -- 3번 클라이언트 상담 전 내역
-- (3, 23, 1, 3, 3, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-06-29', '11:00', '수', '상담결과 테스트용',
--  'REVIEW_PENDING', NOW(), NOW()),
-- (3, 23, 1, 3, 3, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-06', '11:00', '수', '상담결과 테스트용',
--  'REVIEW_PENDING', NOW(), NOW()),
-- -- 4번 클라이언트 상담 전 내역
-- (4, 24, 1, 4, 4, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-06-30', '12:00', '목', '상담결과 테스트용',
--  'REVIEW_PENDING', NOW(), NOW()),
-- (4, 24, 1, 4, 4, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-01', '12:00', '목', '상담결과 테스트용',
--  'REVIEW_PENDING', NOW(), NOW()),
-- (4, 24, 1, 4, 4, 'RESERVATION_SCHEDULED', 'COUNSEL_PENDING', '2024-07-08', '12:00', '목', '상담결과 테스트용',
--  'REVIEW_PENDING', NOW(), NOW());
--
--
-- -- voucher_tb