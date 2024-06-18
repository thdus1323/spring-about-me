-- User 테이블 더미 데이터 생성
INSERT INTO user_tb (user_role, email, password, name, phone, profile_image, birth, gender, created_at, updated_at)
VALUES ('CLIENT', 'mimi@nate.com', '1234', '최미정', '0101112222', 'client1.jpg', '1990-01-01', 'WOMAN', NOW(), NOW()),
       ('CLIENT', 'hae@nate.com', '1234', '장현재', '0101112222', 'client2.jpg', '1992-02-02', 'MAN', NOW(), NOW()),
       ('CLIENT', 'sae@nate.com', '1234', '이사이', '0101112222', 'client3.jpg', '1994-03-03', 'OTHER', NOW(), NOW()),
       ('CLIENT', 'jin@nate.com', '1234', '김진호', '0101112222', 'client4.jpg', '1996-04-04', 'WOMAN', NOW(), NOW()),
       ('CLIENT', 'ji@nate.com', '1234', '서지호', '0101112222', 'client5.jpg', '1998-05-05', 'MAN', NOW(), NOW());

-- -- Voucher 테이블 더미 데이터 생성
-- INSERT INTO voucher_tb (issued_by, owned_by, voucher_type, price, count, duration, discount, start_date, end_date, is_active, created_at, updated_at)
-- VALUES
--     ((SELECT id FROM user_tb WHERE email='expert1@example.com'), (SELECT id FROM user_tb WHERE email='client1@example.com'), 'TEXT_THERAPY', 100, 10, 30, 10.0, '2023-01-01', '2023-12-31', TRUE, NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='expert1@example.com'), (SELECT id FROM user_tb WHERE email='client2@example.com'), 'VOICE_THERAPY', 200, 5, 60, 15.0, '2023-01-01', '2023-12-31', TRUE, NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='expert2@example.com'), (SELECT id FROM user_tb WHERE email='client3@example.com'), 'VIDEO_THERAPY', 300, 3, 90, 20.0, '2023-01-01', '2023-12-31', TRUE, NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='expert2@example.com'), (SELECT id FROM user_tb WHERE email='client4@example.com'), 'TEXT_THERAPY', 100, 10, 30, 10.0, '2023-01-01', '2023-12-31', TRUE, NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='expert1@example.com'), (SELECT id FROM user_tb WHERE email='client5@example.com'), 'VOICE_THERAPY', 200, 5, 60, 15.0, '2023-01-01', '2023-12-31', TRUE, NOW(), NOW());
--
-- -- Review 테이블 더미 데이터 생성
-- INSERT INTO review_tb (user_id, start_time, counsel_date, created_at, updated_at)
-- VALUES
--     ((SELECT id FROM user_tb WHERE email='client1@example.com'), '10:00', '2023-06-18', NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='client2@example.com'), '11:00', '2023-06-18', NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='client3@example.com'), '12:00', '2023-06-18', NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='client4@example.com'), '13:00', '2023-06-18', NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='client5@example.com'), '14:00', '2023-06-18', NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='client6@example.com'), '15:00', '2023-06-18', NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='client1@example.com'), '16:00', '2023-06-18', NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='client2@example.com'), '17:00', '2023-06-18', NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='client3@example.com'), '18:00', '2023-06-18', NOW(), NOW()),
--     ((SELECT id FROM user_tb WHERE email='client4@example.com'), '19:00', '2023-06-18', NOW(), NOW());
--
-- -- Counsel 테이블 더미 데이터 생성
-- INSERT INTO counsel_tb (client_id, expert_id, voucher_id, review_id, counsel_date, result, created_at, updated_at)
-- VALUES
--     (
--         (SELECT id FROM user_tb WHERE email='client1@example.com'),
--         (SELECT id FROM user_tb WHERE email='expert1@example.com'),
--         (SELECT id FROM voucher_tb LIMIT 1 OFFSET 0),
--     (SELECT id FROM review_tb LIMIT 1 OFFSET 0),
--     '2023-06-18',
--     'Counsel result example 1',
--     NOW(),
--     NOW()
--     ),
-- (
--     (SELECT id FROM user_tb WHERE email='client2@example.com'),
--     (SELECT id FROM user_tb WHERE email='expert1@example.com'),
--     (SELECT id FROM voucher_tb LIMIT 1 OFFSET 1),
--     (SELECT id FROM review_tb LIMIT 1 OFFSET 1),
--     '2023-06-19',
--     'Counsel result example 2',
--     NOW(),
--     NOW()
-- ),
-- (
--     (SELECT id FROM user_tb WHERE email='client3@example.com'),
--     (SELECT id FROM user_tb WHERE email='expert2@example.com'),
--     (SELECT id FROM voucher_tb LIMIT 1 OFFSET 2),
--     (SELECT id FROM review_tb LIMIT 1 OFFSET 2),
--     '2023-06-20',
--     'Counsel result example 3',
--     NOW(),
--     NOW()
-- );
