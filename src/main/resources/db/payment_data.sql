-- -- payment_tb 테이블에 데이터 추가
-- INSERT INTO payment_tb (client_id, voucher_id, expert_id, voucher_price, amount, voucher_type, voucher_count,
--                         voucher_duration,
--                         payment_method, payment_date, payment_status,
--                         imp_uid,
--                         merchant_uid, created_at, updated_at)
-- VALUES
--     --1번 클라이언트가 21번의 텍스트바우처 4회권을 구매해였다.
--     (1, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_123456789',
--         'merchant_123456789',
--         NOW(), NOW()),
--     (1, 3, 21, 25000, 20000, 'VOICE_THERAPY', 4, 30, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_123456789',
--         'merchant_123456789',
--         NOW(), NOW());

INSERT INTO payment_tb (client_id, voucher_id, expert_id, voucher_price, amount, voucher_type, voucher_count,
                        voucher_duration, payment_method, payment_date, payment_status, imp_uid, merchant_uid,
                        created_at, updated_at)
VALUES
    -- 1번 클라이언트가 텍스트 바우처 4회권을 구매하였다.
    (1, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_1234567891',
     'merchant_1234567891', NOW(), NOW()),

    -- 2번 클라이언트가 텍스트 바우처 4회권을 구매하였다.
    (2, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_1234567892',
     'merchant_1234567892', NOW(), NOW()),
    -- 3번 클라이언트가 텍스트 바우처 4회권을 구매하였다.
    (3, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_1234567893',
     'merchant_1234567893', NOW(), NOW()),
    -- 4번 클라이언트가 텍스트 바우처 4회권을 구매하였다.
    (4, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_1234567894',
     'merchant_1234567894', NOW(), NOW()),
    -- 5번 클라이언트가 텍스트 바우처 4회권을 구매하였다.
    (5, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_1234567895',
     'merchant_1234567895', NOW(), NOW()),
    -- 6번 클라이언트가 텍스트 바우처 4회권을 구매하였다.
    (6, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_1234567896',
     'merchant_1234567896', NOW(), NOW()),
    -- 7번 클라이언트가 텍스트 바우처 4회권을 구매하였다.
    (7, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_1234567897',
     'merchant_1234567897', NOW(), NOW()),
    -- 8번 클라이언트가 텍스트 바우처 4회권을 구매하였다.
    (8, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_1234567898',
     'merchant_1234567898', NOW(), NOW()),
    -- 9번 클라이언트가 텍스트 바우처 4회권을 구매하였다.
    (9, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_1234567899',
     'merchant_1234567899', NOW(), NOW()),
    -- 10번 클라이언트가 텍스트 바우처 4회권을 구매하였다.
    (10, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_671776164136',
     'merchant_1234567810', NOW(), NOW());

