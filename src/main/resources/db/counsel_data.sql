
-- RESERVATION_SCHEDULED("확정 예정"),
--     RESERVATION_COMPLETED("예약 확정"),
-- counsel_tb
INSERT INTO counsel_tb (client_id, expert_id, voucher_id, schedule_id, payment_id, reservation_status, counsel_status,
                        counsel_date,
                        counsel_time, day_of_week, result, created_at, updated_at)
VALUES
    --1번 클라이언트가 21번 전문가의 텍스트 바우처 4회권을 구매하고 상담을 1회 완료하였고, 2회 상담을 신청하였다. 그러면 상담완료는 1회 예약은 2회 예약가능한 남은 횟수는 1회이다.
    (1, 21, 3, 1, 2, 'RESERVATION_SCHEDULED', 'COUNSEL_CONFIRMED', '2024-06-22', '09:00', '월', '상담결과 테스트용', NOW(), NOW()),
    (1, 21, 1, 1, 1, 'COUNSEL_COMPLETED', 'COUNSEL_COMPLETED', '2024-06-22', '09:00', '월', '상담결과 테스트용', NOW(), NOW()),
    (1, 21, 1, 1, 1, 'RESERVATION_SCHEDULED', 'COUNSEL_CONFIRMED', '2024-06-22', '09:00', '월', '상담결과 테스트용', NOW(), NOW()),
    (1, 21, 1, 1, 1, 'RESERVATION_COMPLETED', 'COUNSEL_CONFIRMED', '2024-06-22', '09:00', '월', '상담결과 테스트용', NOW(), NOW());

--        (1, 21, 1, 1, '2024-06-23', '10:00', 'COMPLETED', 'COMPLETED', NOW(), NOW());

--        (2, 2, 22, 2, '2024-06-21 10:00:00', 'PENDING', NOW(), NOW()),
--        (3, 3, 23, 6, '2024-06-22 10:00:00', 'PENDING', NOW(), NOW()),
--        (4, 4, 24, 7, '2024-06-23 10:00:00', 'PENDING', NOW(), NOW()),
--        (5, 5, 25, 9, '2024-06-24 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (6, 6, 26, 12, '2024-06-25 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (7, 7, 27, 13, '2024-06-26 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (8, 8, 28, 15, '2024-06-27 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (9, 9, 29, 18, '2024-06-28 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (10, 10, 30, 20, '2024-06-29 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (11, 11, 21, 1, '2024-06-30 10:00:00', 'PENDING', NOW(), NOW()),
--        (12, 12, 22, 4, '2024-07-01 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (13, 13, 23, 6, '2024-07-02 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (14, 14, 24, 7, '2024-07-03 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (15, 15, 25, 9, '2024-07-04 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (16, 16, 26, 12, '2024-07-05 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (17, 17, 27, 13, '2024-07-06 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (18, 18, 28, 15, '2024-07-07 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (19, 19, 29, 18, '2024-07-08 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (20, 20, 30, 20, '2024-07-09 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (21, 21, 21, 1, '2024-07-10 10:00:00', 'PENDING', NOW(), NOW()),
--        (22, 22, 22, 4, '2024-07-11 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (23, 23, 23, 6, '2024-07-12 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (24, 24, 24, 7, '2024-07-13 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (25, 25, 25, 9, '2024-07-14 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (26, 26, 26, 12, '2024-07-15 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (27, 27, 27, 13, '2024-07-16 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (28, 28, 28, 15, '2024-07-17 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (29, 29, 29, 18, '2024-07-18 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (30, 1, 24, 20, '2024-07-19 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (31, 2, 22, 20, '2024-07-19 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (32, 3, 23, 20, '2024-07-19 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (33, 4, 21, 20, '2024-07-19 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (34, 5, 25, 20, '2024-07-19 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (35, 6, 27, 20, '2024-07-19 10:00:00', 'COMPLETED', NOW(), NOW()),
--        (36, 22, 29, 20, '2024-07-19 10:00:00', 'COMPLETED', NOW(), NOW());
