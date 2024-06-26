
-- voucher_tb 테이블에 데이터 추가
INSERT INTO voucher_tb (id, voucher_type, expert_id, price, count, duration, image_path,  created_at,
                        updated_at)
VALUES
    -- Expert 1 has 3 voucher types
    (1, 'TEXT_THERAPY', 21, 10000, 1, 60, '/images/chat.png', NOW(), NOW()),
    (2, 'VOICE_THERAPY', 21, 15000, 1, 30, '/images/call.png',NOW(), NOW()),
    (3, 'VIDEO_THERAPY', 21, 20000, 1, 45, '/images/video.png', NOW(), NOW()),

    -- Expert 2 has 2 voucher types
    (4, 'TEXT_THERAPY', 22, 10000, 1, 60, '/images/chat.png',  NOW(), NOW()),
    (5, 'VOICE_THERAPY', 22, 15000, 1, 30, '/images/call.png',  NOW(), NOW()),

    -- Expert 3 has 1 voucher type
    (6, 'VIDEO_THERAPY', 23, 20000, 1, 45, '/images/video.png', NOW(), NOW()),

    -- Expert 4 has 2 voucher types
    (7, 'TEXT_THERAPY', 24, 10000, 1, 60, '/images/chat.png',  NOW(), NOW()),
    (8, 'VIDEO_THERAPY', 24, 20000, 1, 45, '/images/video.png',  NOW(), NOW()),

    -- Expert 5 has 3 voucher types
    (9, 'TEXT_THERAPY', 25, 10000, 1, 60, '/images/chat.png',  NOW(), NOW()),
    (10, 'VOICE_THERAPY', 25, 15000, 1, 30, '/images/call.png',  NOW(), NOW()),
    (11, 'VIDEO_THERAPY', 25, 20000, 1, 45, '/images/video.png',  NOW(), NOW()),

    -- Expert 6 has 1 voucher type
    (12, 'TEXT_THERAPY', 26, 10000, 1, 60, '/images/chat.png',  NOW(), NOW()),

    -- Expert 7 has 2 voucher types
    (13, 'VOICE_THERAPY', 27, 15000, 1, 30, '/images/call.png',  NOW(), NOW()),
    (14, 'VIDEO_THERAPY', 27, 20000, 1, 45, '/images/video.png',  NOW(), NOW()),

    -- Expert 8 has 3 voucher types
    (15, 'TEXT_THERAPY', 28, 10000, 1, 60, '/images/chat.png',  NOW(), NOW()),
    (16, 'VOICE_THERAPY', 28, 15000, 1, 30, '/images/call.png',  NOW(), NOW()),
    (17, 'VIDEO_THERAPY', 28, 20000, 1, 45, '/images/video.png',  NOW(), NOW()),

    -- Expert 9 has 2 voucher types
    (18, 'TEXT_THERAPY', 29, 10000, 1, 60, '/images/chat.png', NOW(), NOW()),
    (19, 'VOICE_THERAPY', 29, 15000, 1, 30, '/images/call.png',  NOW(), NOW()),

    -- Expert 10 has 1 voucher type
    (20, 'VIDEO_THERAPY', 30, 20000, 1, 45, '/images/video.png',NOW(), NOW());

