-- reservation_tb 테이블
INSERT INTO reservation_tb (expert_id, client_id, voucher_id, schedule_id, status, start_time, reservation_date,
                            day_of_week, created_at, updated_at)
VALUES
    -- Expert 21 (월요일과 금요일)에서 4개 스케줄
    (21, 1, 1, 1, 'COMPLETED', '09:00', '2024-06-24', '월', NOW(), NOW());
--
--     -- Expert 21 (화요일과 수요일)에서 4개 스케줄
--     (21, 5, 2, 2, 'COMPLETED', '09:00', '2024-06-25', '화', NOW(), NOW()),
--     (21, 6, 2, 2, 'SCHEDULED', '10:00', '2024-06-25', '화', NOW(), NOW()),
--     (21, 7, 3, 3, 'COMPLETED', '09:00', '2024-06-26', '수', NOW(), NOW()),
--     (21, 8, 3, 3, 'SCHEDULED', '10:00', '2024-06-26', '수', NOW(), NOW()),
--
--     -- Expert 22 (월요일)에서 4개 스케줄
--     (22, 9, 4, 6, 'CANCELLED', '10:00', '2024-06-24', '월', NOW(), NOW()),
--     (22, 10, 4, 6, 'SCHEDULED', '11:00', '2024-06-24', '월', NOW(), NOW()),
--     (22, 11, 4, 6, 'CANCELLED', '12:00', '2024-06-24', '월', NOW(), NOW()),
--     (22, 12, 4, 6, 'SCHEDULED', '13:00', '2024-06-24', '월', NOW(), NOW()),
--
--     -- Expert 24 (수요일)에서 4개 스케줄
--     (24, 13, 7, 7, 'CANCELLED', '10:00', '2024-06-26', '수', NOW(), NOW()),
--     (24, 14, 7, 7, 'SCHEDULED', '11:00', '2024-06-26', '수', NOW(), NOW()),
--     (24, 15, 7, 7, 'CANCELLED', '12:00', '2024-06-26', '수', NOW(), NOW()),
--     (24, 16, 7, 7, 'SCHEDULED', '13:00', '2024-06-26', '수', NOW(), NOW());
--
