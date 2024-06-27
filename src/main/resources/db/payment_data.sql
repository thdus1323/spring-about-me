-- payment_tb 테이블에 데이터 추가
INSERT INTO payment_tb (client_id, voucher_id, expert_id, voucher_price, amount, voucher_type, voucher_count,
                        voucher_duration,
                        payment_method, payment_date, payment_status,
                        imp_uid,
                        merchant_uid, created_at, updated_at)
VALUES (1, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_123456789',
        'merchant_123456789',
        NOW(), NOW()),
       (1, 1, 21, 15000, 15000, 'TEXT_THERAPY', 4, 50, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_123456789',
        'merchant_123456789',
        NOW(), NOW()),
       (1, 3, 21, 25000, 20000, 'VOICE_THERAPY', 4, 30, 'CREDIT_CARD', '2024-06-28', 'COMPLETED', 'imp_123456789',
        'merchant_123456789',
        NOW(), NOW());


