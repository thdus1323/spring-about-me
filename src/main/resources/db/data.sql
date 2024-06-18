-- user_tb
INSERT INTO user_tb (user_role, email, password, name, phone, profile_image, birth, gender, created_at, updated_at)
VALUES ('CLIENT', 'mimi@nate.com', '1234', '최미정', '0101112222', 'client1.jpg', '1990-01-01', 'WOMAN', NOW(), NOW()),
       ('CLIENT', 'hae@nate.com', '1234', '장현재', '0101112222', 'client2.jpg', '1992-02-02', 'MAN', NOW(), NOW()),
       ('CLIENT', 'sae@nate.com', '1234', '이사이', '0101112222', 'client3.jpg', '1994-03-03', 'OTHER', NOW(), NOW()),
       ('CLIENT', 'jin@nate.com', '1234', '김진호', '0101112222', 'client4.jpg', '1996-04-04', 'WOMAN', NOW(), NOW()),
       ('CLIENT', 'ji@nate.com', '1234', '서지호', '0101112222', 'client5.jpg', '1998-05-05', 'MAN', NOW(), NOW());

-- comm_tb
INSERT INTO comm_tb (user_id, created_at, content, title)
    VALUES (1, NOW(), '집에 가고 싶어서 고민 적습니다', '집에 가고 싶어요'),
             (2, NOW(), '저녁 메뉴를 못 정하겠어요. 추천받고 싶습니다', '배고파요'),
             (3, NOW(), '수업시간에 잠이 오면 어떻게 해야 할까요?','매일매일 졸아요');