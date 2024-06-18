-- User 테이블 더미 데이터 생성
INSERT INTO user_tb (user_role, email, password, name, phone, profile_image, birth, gender, created_at, updated_at)
VALUES
    ('CLIENT', 'client1@example.com', 'password1', 'Client One', '010-1111-2222', 'client1.jpg', '1990-01-01', 'WOMAN', NOW(), NOW()),
    ('CLIENT', 'client2@example.com', 'password2', 'Client Two', '010-2222-3333', 'client2.jpg', '1992-02-02', 'MAN', NOW(), NOW()),
    ('CLIENT', 'client3@example.com', 'password3', 'Client Three', '010-3333-4444', 'client3.jpg', '1994-03-03', 'OTHER', NOW(), NOW()),
    ('CLIENT', 'client4@example.com', 'password4', 'Client Four', '010-4444-5555', 'client4.jpg', '1996-04-04', 'WOMAN', NOW(), NOW()),
    ('CLIENT', 'client5@example.com', 'password5', 'Client Five', '010-5555-6666', 'client5.jpg', '1998-05-05', 'MAN', NOW(), NOW()),
    ('CLIENT', 'client6@example.com', 'password6', 'Client Six', '010-6666-7777', 'client6.jpg', '2000-06-06', 'OTHER', NOW(), NOW()),
    ('EXPERT', 'expert1@example.com', 'password7', 'Expert One', '010-7777-8888', 'expert1.jpg', '1980-01-01', 'MAN', NOW(), NOW()),
    ('EXPERT', 'expert2@example.com', 'password8', 'Expert Two', '010-8888-9999', 'expert2.jpg', '1982-02-02', 'WOMAN', NOW(), NOW());

-- Voucher 테이블 더미 데이터 생성
INSERT INTO voucher_tb (issued_by, owned_by, voucher_type, price, count, duration, discount, start_date, end_date, is_active, created_at, updated_at)
VALUES
    ((SELECT id FROM user_tb WHERE email='expert1@example.com'), (SELECT id FROM user_tb WHERE email='client1@example.com'), 'TEXT_THERAPY', 100, 10, 30, 10.0, '2023-01-01', '2023-12-31', TRUE, NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='expert1@example.com'), (SELECT id FROM user_tb WHERE email='client2@example.com'), 'VOICE_THERAPY', 200, 5, 60, 15.0, '2023-01-01', '2023-12-31', TRUE, NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='expert2@example.com'), (SELECT id FROM user_tb WHERE email='client3@example.com'), 'VIDEO_THERAPY', 300, 3, 90, 20.0, '2023-01-01', '2023-12-31', TRUE, NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='expert2@example.com'), (SELECT id FROM user_tb WHERE email='client4@example.com'), 'TEXT_THERAPY', 100, 10, 30, 10.0, '2023-01-01', '2023-12-31', TRUE, NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='expert1@example.com'), (SELECT id FROM user_tb WHERE email='client5@example.com'), 'VOICE_THERAPY', 200, 5, 60, 15.0, '2023-01-01', '2023-12-31', TRUE, NOW(), NOW());

-- Review 테이블 더미 데이터 생성
INSERT INTO review_tb (user_id, start_time, counsel_date, created_at, updated_at)
VALUES
    ((SELECT id FROM user_tb WHERE email='client1@example.com'), '10:00', '2023-06-18', NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='client2@example.com'), '11:00', '2023-06-18', NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='client3@example.com'), '12:00', '2023-06-18', NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='client4@example.com'), '13:00', '2023-06-18', NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='client5@example.com'), '14:00', '2023-06-18', NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='client6@example.com'), '15:00', '2023-06-18', NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='client1@example.com'), '16:00', '2023-06-18', NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='client2@example.com'), '17:00', '2023-06-18', NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='client3@example.com'), '18:00', '2023-06-18', NOW(), NOW()),
    ((SELECT id FROM user_tb WHERE email='client4@example.com'), '19:00', '2023-06-18', NOW(), NOW());

-- Counsel 테이블 더미 데이터 생성
INSERT INTO counsel_tb (client_id, expert_id, voucher_id, review_id, counsel_date, result, created_at, updated_at)
VALUES
    (
        (SELECT id FROM user_tb WHERE email='client1@example.com'),
        (SELECT id FROM user_tb WHERE email='expert1@example.com'),
        (SELECT id FROM voucher_tb LIMIT 1 OFFSET 0),
    (SELECT id FROM review_tb LIMIT 1 OFFSET 0),
    '2023-06-18',
    'Counsel result example 1',
    NOW(),
    NOW()
    ),
(
    (SELECT id FROM user_tb WHERE email='client2@example.com'),
    (SELECT id FROM user_tb WHERE email='expert1@example.com'),
    (SELECT id FROM voucher_tb LIMIT 1 OFFSET 1),
    (SELECT id FROM review_tb LIMIT 1 OFFSET 1),
    '2023-06-19',
    'Counsel result example 2',
    NOW(),
    NOW()
),
(
    (SELECT id FROM user_tb WHERE email='client3@example.com'),
    (SELECT id FROM user_tb WHERE email='expert2@example.com'),
    (SELECT id FROM voucher_tb LIMIT 1 OFFSET 2),
    (SELECT id FROM review_tb LIMIT 1 OFFSET 2),
    '2023-06-20',
    'Counsel result example 3',
    NOW(),
    NOW()
);
