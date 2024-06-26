

-- payment_tb 테이블에 데이터 추가
INSERT INTO payment_tb (amount, payment_method, client_id, voucher_id, payment_date, status, imp_uid, merchant_uid)
VALUES
    (50000, 'CREDIT_CARD', 1, 1, NOW(), 'COMPLETED', 'imp_123456789', 'merchant_123456789'),
    (30000, 'CREDIT_CARD', 2, 2, NOW(), 'PENDING', 'imp_987654321', 'merchant_987654321'),
    (45000, 'CREDIT_CARD', 3, 3, NOW(), 'FAILED', 'imp_192837465', 'merchant_192837465'),
    (20000, 'CREDIT_CARD', 4, 4, NOW(), 'COMPLETED', 'imp_564738291', 'merchant_564738291'),
    (60000, 'CREDIT_CARD', 5, 5, NOW(), 'REFUNDED', 'imp_918273645', 'merchant_918273645'),
    (35000, 'CREDIT_CARD', 6, 6, NOW(), 'COMPLETED', 'imp_736281940', 'merchant_736281940'),
    (25000, 'CREDIT_CARD', 7, 7, NOW(), 'PENDING', 'imp_109283746', 'merchant_109283746'),
    (55000, 'CREDIT_CARD', 8, 8, NOW(), 'COMPLETED', 'imp_283746591', 'merchant_283746591'),
    (40000, 'CREDIT_CARD', 9, 9, NOW(), 'FAILED', 'imp_928374651', 'merchant_928374651'),
    (15000, 'CREDIT_CARD', 10, 10, NOW(), 'REFUNDED', 'imp_847362910', 'merchant_847362910');

