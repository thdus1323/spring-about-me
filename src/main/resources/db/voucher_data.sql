-- voucher_tb 테이블에 데이터 추가
INSERT INTO voucher_tb (voucher_type, expert_id, price, count, duration, image_path, created_at,
                        updated_at)
VALUES
    -- Expert 1 has 3 voucher types
    ('TEXT_THERAPY', 21, 15000, 4, 50, '/images/chat.png', NOW(), NOW()), --1

    -- Expert 22
    ('TEXT_THERAPY', 22, 15000, 4, 50, '/images/chat.png', NOW(), NOW()), --2
    ('TEXT_THERAPY', 22, 15000, 4, 50, '/images/chat.png', NOW(), NOW()),
    ('VOICE_THERAPY', 22, 20000, 3, 30, '/images/call.png', NOW(), NOW()),

    -- Expert 23
    ('TEXT_THERAPY', 23, 20000, 4, 50, '/images/chat.png', NOW(), NOW()), --5
    ('TEXT_THERAPY', 23, 20000, 4, 50, '/images/chat.png', NOW(), NOW()),
    ('VIDEO_THERAPY', 23, 30000, 2, 40, '/images/video.png', NOW(), NOW()),

    -- Expert 24
    ('VOICE_THERAPY', 24, 15000, 4, 30, '/images/call.png', NOW(), NOW()), --8
    ('VIDEO_THERAPY', 24, 15000, 4, 40, '/images/video.png', NOW(), NOW()),
    ('VIDEO_THERAPY', 24, 20000, 2, 45, '/images/video.png', NOW(), NOW()),
    ('VIDEO_THERAPY', 24, 30000, 1, 60, '/images/video.png', NOW(), NOW()),

    -- Expert 25
    ('TEXT_THERAPY', 25, 15000, 4, 50, '/images/chat.png', NOW(), NOW()), --12
    ('TEXT_THERAPY', 25, 15000, 4, 50, '/images/chat.png', NOW(), NOW()),
    ('VOICE_THERAPY', 25, 25000, 1, 30, '/images/call.png', NOW(), NOW()),
    ('VIDEO_THERAPY', 25, 40000, 1, 40, '/images/video.png', NOW(), NOW()),

    -- Expert 26
    ('TEXT_THERAPY', 26, 20000, 4, 50, '/images/chat.png', NOW(), NOW()), --16
    ('TEXT_THERAPY', 26, 20000, 4, 50, '/images/chat.png', NOW(), NOW()),
    ('VOICE_THERAPY', 26, 30000, 3, 30, '/images/call.png', NOW(), NOW()),
    ('VIDEO_THERAPY', 26, 45000, 5, 40, '/images/video.png', NOW(), NOW()),

    -- Expert 27
    ('TEXT_THERAPY', 27, 15000, 4, 50, '/images/chat.png', NOW(), NOW()), --20
    ('TEXT_THERAPY', 27, 15000, 4, 50, '/images/chat.png', NOW(), NOW()),
    ('VOICE_THERAPY', 27, 25000, 1, 30, '/images/call.png', NOW(), NOW()),
    ('VIDEO_THERAPY', 27, 30000, 2, 40, '/images/video.png', NOW(), NOW()),

    -- Expert 28
    ('TEXT_THERAPY', 28, 15000, 4, 50, '/images/chat.png', NOW(), NOW()), --24
    ('TEXT_THERAPY', 28, 15000, 4, 50, '/images/chat.png', NOW(), NOW()),
    ('VOICE_THERAPY', 28, 25000, 2, 30, '/images/call.png', NOW(), NOW()),
    ('VIDEO_THERAPY', 28, 30000, 1, 40, '/images/video.png', NOW(), NOW()),

    -- Expert 29
    ('TEXT_THERAPY', 29, 15000, 4, 50, '/images/chat.png', NOW(), NOW()),
    ('TEXT_THERAPY', 29, 15000, 4, 50, '/images/chat.png', NOW(), NOW()),
    ('VOICE_THERAPY', 29, 25000, 3, 30, '/images/call.png', NOW(), NOW()),
    ('VIDEO_THERAPY', 29, 30000, 1, 40, '/images/video.png', NOW(), NOW()),

    -- Expert 30
    ('TEXT_THERAPY', 30, 25000, 2, 45, '/images/chat.png', NOW(), NOW()),
    ('TEXT_THERAPY', 30, 25000, 2, 60, '/images/chat.png', NOW(), NOW()),
    ('TEXT_THERAPY', 30, 25000, 4, 70, '/images/chat.png', NOW(), NOW()),
    ('VOICE_THERAPY', 30, 30000, 2, 30, '/images/call.png', NOW(), NOW()),
    ('VIDEO_THERAPY', 30, 35000, 3, 40, '/images/video.png', NOW(), NOW());