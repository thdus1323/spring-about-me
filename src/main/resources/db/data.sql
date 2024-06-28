-- user_tb (유저)
INSERT INTO user_tb (user_role, email, password, name, phone, profile_image, birth, gender, created_at, updated_at)
VALUES ('CLIENT', 'mimi@nate.com', '1234', '최미정', '0101112222', '/images/client01.jpg', '1990-01-01', 'WOMAN', NOW(),
        NOW()),
       ('CLIENT', 'hae@nate.com', '1234', '장현재', '0101112222', '/images/client02.jpg', '1992-02-02', 'MAN', NOW(),
        NOW()),
       ('CLIENT', 'sae@nate.com', '1234', '이사이', '0101112222', '/images/client03.jpg', '1994-03-03', 'OTHER', NOW(),
        NOW()),
       ('CLIENT', 'jin@nate.com', '1234', '김진호', '0101112222', '/images/client041.jpg', '1996-04-04', 'WOMAN', NOW(),
        NOW()),
       ('CLIENT', 'ji@nate.com', '1234', '서지호', '0101112222', '/images/client05.png', '1998-05-05', 'MAN', NOW(),
        NOW()),
       ('CLIENT', 'suji@nate.com', '1234', '박수지', '0101113333', '/images/client06.jpg', '1990-06-01', 'WOMAN', NOW(),
        NOW()),
       ('CLIENT', 'jong@nate.com', '1234', '김종현', '0101114444', '/images/client07.jpg', '1991-07-01', 'MAN', NOW(),
        NOW()),
       ('CLIENT', 'yuna@nate.com', '1234', '윤아름', '0101115555', '/images/client08.png', '1992-08-01', 'WOMAN', NOW(),
        NOW()),
       ('CLIENT', 'minho@nate.com', '1234', '최민호', '0101116666', '/images/client09.jpg', '1993-09-01', 'MAN', NOW(),
        NOW()),
       ('CLIENT', 'sohee@nate.com', '1234', '이소희', '0101117777', '/images/client10.jpg', '1994-10-01', 'WOMAN', NOW(),
        NOW()),
       ('CLIENT', 'doyoung@nate.com', '1234', '박도영', '0101118888', '/images/client11.jpg', '1995-11-01', 'MAN', NOW(),
        NOW()),
       ('CLIENT', 'yeseul@nate.com', '1234', '김예슬', '0101119999', '/images/client12.jpg', '1996-12-01', 'WOMAN', NOW(),
        NOW()),
       ('CLIENT', 'dongwoo@nate.com', '1234', '정동우', '0101110000', '/images/client13.jpg', '1997-01-01', 'MAN', NOW(),
        NOW()),
       ('CLIENT', 'hyunjoo@nate.com', '1234', '박현주', '0102221111', '/images/client14.jpg', '1998-02-01', 'WOMAN', NOW(),
        NOW()),
       ('CLIENT', 'sanghoon@nate.com', '1234', '김상훈', '0102222222', '/images/client15.jpg', '1999-03-01', 'MAN', NOW(),
        NOW()),
       ('CLIENT', 'eunji@nate.com', '1234', '서은지', '0102223333', '/images/client16.jpg', '2000-04-01', 'WOMAN', NOW(),
        NOW()),
       ('CLIENT', 'woojin@nate.com', '1234', '이우진', '0102224444', '/images/client171.jpg', '2001-05-01', 'MAN', NOW(),
        NOW()),
       ('CLIENT', 'jiwon@nate.com', '1234', '최지원', '0102225555', '/images/client18.jpg', '2002-06-01', 'WOMAN', NOW(),
        NOW()),
       ('CLIENT', 'taeyoung@nate.com', '1234', '박태영', '0102226666', '/images/client19.jpg', '2003-07-01', 'MAN', NOW(),
        NOW()),
       ('CLIENT', 'soyoung@nate.com', '1234', '김소영', '0102227777', '/images/client20.jpg', '2004-08-01', 'WOMAN', NOW(),
        NOW());

-- user_tb (상담사)
INSERT INTO user_tb (user_role, email, password, name, phone, profile_image, birth, gender, level, expert_title,
                     created_at, updated_at)
VALUES ('EXPERT', 'expert1@nate.com', '1234', '홍길동', '01012345678', '/images/expert21.jpg', '1985-06-06', 'MAN',
        'LEVEL1',
        '혼자 걷다 보면 외롭고 지칠 때가 있습니다. 당신의 길에 함께하는 동반자가 되고 싶습니다.', NOW(), NOW()),
       ('EXPERT', 'expert2@nate.com', '1234', '이영희', '01087654321', '/images/expert22.jpg', '1987-07-07', 'WOMAN',
        'LEVEL2',
        '저는 당신의 이야기가 궁금하네요. 조금 더 자세하게 말씀해주실 수 있을까요?', NOW(), NOW()),
       ('EXPERT', 'expert3@nate.com', '1234', '박철수', '0103334444', '/images/expert23.jpg', '1980-01-01', 'MAN',
        'LEVEL1',
        '어떤 마음이어도, 어떤 모습이어도 다 괜찮습니다.', NOW(), NOW()),
       ('EXPERT', 'expert4@nate.com', '1234', '김미영', '0103335555', '/images/expert24.jpg', '1982-02-02', 'WOMAN',
        'LEVEL2',
        '자신을 비춰보며 따뜻하게 어루만져 줄 수 있는 시간을 만들어드리고 싶은 상담사 백소림입니다.', NOW(), NOW()),
       ('EXPERT', 'expert5@nate.com', '1234', '이종현', '0103336666', '/images/expert25.jpg', '1984-03-03', 'MAN',
        'LEVEL1',
        '말의 힘을 믿기에 한 마디 한 마디에 진심을 담아 당신의 이야기를 듣고 더하겠습니다.', NOW(), NOW()),
       ('EXPERT', 'expert6@nate.com', '1234', '윤정희', '0103337777', '/images/expert26.jpg', '1986-04-04', 'WOMAN',
        'LEVEL2',
        '어떤 문제로 고민하세요? 함께 해요! 용기가 생겨요! 미래가 보여요!', NOW(), NOW()),
       ('EXPERT', 'expert7@nate.com', '1234', '최현우', '0103338888', '/images/expert27.jpg', '1988-05-05', 'MAN',
        'LEVEL1',
        '그럼에도 불구하고 우리가 우리인 채로 잘 살아갈 수 있어요.', NOW(), NOW()),
       ('EXPERT', 'expert8@nate.com', '1234', '김소정', '0103339999', '/images/expert28.jpg', '1990-06-06', 'WOMAN',
        'LEVEL2',
        '당신 안의 온전함을 다시 찾을 수 있게, 지친 마음이 쉬었다 갈 수 있게 함께 할게요.', NOW(), NOW()),
       ('EXPERT', 'expert9@nate.com', '1234', '박재호', '0104441111', '/images/expert29.jpg', '1992-07-07', 'MAN',
        'LEVEL1',
        '당신에게 소금 같은 사람이 되어 드리고 싶습니다.', NOW(), NOW()),
       ('EXPERT', 'expert10@nate.com', '1234', '이유리', '0104442222', '/images/expert30.jpg', '1994-08-08', 'WOMAN',
        'LEVEL2',
        '괜찮지 않은 그 순간, 온 마음으로 당신의 곁에 있겠습니다.', NOW(), NOW());



--
-- -- voucher_history_tb
-- INSERT INTO voucher_history_tb (expert_id, client_id, reservation_id, voucher_type, price, count, duration, discount,
--                                 start_date, end_date, is_active, image_path, created_at, updated_at)
-- VALUES
--     -- Expert 1
--     (21, 1, NULL, 'TEXT_THERAPY', 10000, 1, 60, 0.1, NOW(), NULL, TRUE, '/images/chat.png', NOW(), NOW()),
--     (21, 2, NULL, 'VOICE_THERAPY', 15000, 1, 30, 0.2, NOW(), NULL, TRUE, '/images/call.png', NOW(), NOW()),
--     (21, 3, NULL, 'VIDEO_THERAPY', 20000, 1, 45, 0.15, NOW(), NULL, TRUE, '/images/video.png', NOW(), NOW()),
--     -- Expert 2
--     (22, 4, NULL, 'TEXT_THERAPY', 10000, 1, 60, 0.1, NOW(), NULL, TRUE, '/images/chat.png', NOW(), NOW()),
--     (22, 5, NULL, 'VOICE_THERAPY', 15000, 1, 30, 0.2, NOW(), NULL, TRUE, '/images/call.png', NOW(), NOW()),
--     -- Expert 3
--     (23, 6, NULL, 'VIDEO_THERAPY', 20000, 1, 45, 0.15, NOW(), NULL, TRUE, '/images/video.png', NOW(), NOW()),
--     -- Expert 4
--     (24, 7, NULL, 'TEXT_THERAPY', 10000, 1, 60, 0.1, NOW(), NULL, TRUE, '/images/chat.png', NOW(), NOW()),
--     (24, 8, NULL, 'VIDEO_THERAPY', 20000, 1, 45, 0.15, NOW(), NULL, TRUE, '/images/video.png', NOW(), NOW()),
--     -- Expert 5
--     (25, 9, NULL, 'TEXT_THERAPY', 10000, 1, 60, 0.1, NOW(), NULL, TRUE, '/images/chat.png', NOW(), NOW()),
--     (25, 10, NULL, 'VOICE_THERAPY', 15000, 1, 30, 0.2, NOW(), NULL, TRUE, '/images/call.png', NOW(), NOW()),
--     (25, 11, NULL, 'VIDEO_THERAPY', 20000, 1, 45, 0.15, NOW(), NULL, TRUE, '/images/video.png', NOW(), NOW()),
--     -- Expert 6
--     (26, 12, NULL, 'TEXT_THERAPY', 10000, 1, 60, 0.1, NOW(), NULL, TRUE, '/images/chat.png', NOW(), NOW()),
--     -- Expert 7
--     (27, 13, NULL, 'VOICE_THERAPY', 15000, 1, 30, 0.2, NOW(), NULL, TRUE, '/images/call.png', NOW(), NOW()),
--     (27, 14, NULL, 'VIDEO_THERAPY', 20000, 1, 45, 0.15, NOW(), NULL, TRUE, '/images/video.png', NOW(), NOW()),
--     -- Expert 8
--     (28, 15, NULL, 'TEXT_THERAPY', 10000, 1, 60, 0.1, NOW(), NULL, TRUE, '/images/chat.png', NOW(), NOW()),
--     (28, 16, NULL, 'VOICE_THERAPY', 15000, 1, 30, 0.2, NOW(), NULL, TRUE, '/images/call.png', NOW(), NOW()),
--     (28, 17, NULL, 'VIDEO_THERAPY', 20000, 1, 45, 0.15, NOW(), NULL, TRUE, '/images/video.png', NOW(), NOW()),
--     -- Expert 9
--     (29, 18, NULL, 'TEXT_THERAPY', 10000, 1, 60, 0.1, NOW(), NULL, TRUE, '/images/chat.png', NOW(), NOW()),
--     (29, 19, NULL, 'VOICE_THERAPY', 15000, 1, 30, 0.2, NOW(), NULL, TRUE, '/images/call.png', NOW(), NOW()),
--     -- Expert 10
--     (30, 20, NULL, 'VIDEO_THERAPY', 20000, 1, 45, 0.15, NOW(), NULL, TRUE, '/images/video.png', NOW(), NOW());


-- comm_tb
INSERT INTO comm_tb (user_id, content, title, category, created_at)
VALUES (1, '집에 가고 싶어서 고민 적습니다', '집에 가고 싶어요', 'GENERAL_CONCERNS', NOW()),
       (1, '회사에서 스트레스 받아요', '회사 고민', 'WORK_LIFE', NOW()),
       (1,  '취업/진로 내용1', '취업/진로 제목1', 'CAREER_JOBS', NOW()),
       (1,  '취업/진로 내용2', '취업/진로 제목2', 'CAREER_JOBS', NOW()),
       (1,  '취업/진로 내용3', '취업/진로 제목3', 'CAREER_JOBS', NOW()),
       (1,  '취업/진로 내용4', '취업/진로 제목4', 'CAREER_JOBS', NOW()),
       (1,  '취업/진로 내용5', '취업/진로 제목5', 'CAREER_JOBS', NOW()),
       (1,  '취업/진로 내용6', '취업/진로 제목6', 'CAREER_JOBS', NOW()),
       (1, '금전/사업내용8', '금전/사업제목8', 'FINANCE_BUSINESS', NOW()),

       (2, '저녁 메뉴를 못 정하겠어요. 추천받고 싶습니다', '배고파요', 'GENERAL_CONCERNS', NOW()),
       (2,  '취업/진로 내용7', '취업/진로 제목7', 'CAREER_JOBS', NOW()),
       (2,  '취업/진로 내용8', '취업/진로 제목8', 'CAREER_JOBS', NOW()),
       (2,  '취업/진로 내용9', '취업/진로 제목9', 'CAREER_JOBS', NOW()),
       (2,  '취업/진로 내용10','취업/진로 제목10', 'CAREER_JOBS', NOW()),
       (2, '직장내용1', '직장제목1', 'WORK_LIFE', NOW()),
       (2, '직장내용2', '직장제목2', 'WORK_LIFE', NOW()),
       (2, '다이어트 중인데 뭐 먹죠?', '다이어트 고민', 'GENERAL_CONCERNS', NOW()),
       (2, '금전/사업내용9', '금전/사업제목9', 'FINANCE_BUSINESS', NOW()),

       (3, '금전/사업내용10', '금전/사업제목10', 'FINANCE_BUSINESS', NOW()),
       (3, '수업시간에 잠이 오면 어떻게 해야 할까요?', '매일매일 졸아요', 'GENERAL_CONCERNS', NOW()),
       (3, '시험 공부가 너무 힘들어요', '시험이 다가오는 것이 무서워요', 'GENERAL_CONCERNS', NOW()),
       (3, '직장내용3', '직장제목3', 'WORK_LIFE', NOW()),
       (3, '직장내용4', '직장제목4', 'WORK_LIFE', NOW()),
       (3, '직장내용5', '직장제목5', 'WORK_LIFE', NOW()),
       (3, '직장내용6', '직장제목6', 'WORK_LIFE', NOW()),
       (3, '직장내용7', '직장제목7', 'WORK_LIFE', NOW()),
       (3, '직장내용8', '직장제목8', 'WORK_LIFE', NOW()),
       (3, '직장내용9', '직장제목9', 'WORK_LIFE', NOW()),

       (4, '친구와 싸웠어요', '친구 고민', 'SOCIAL_INTERACTIONS', NOW()),
       (4, '여행 가고 싶은데 어디가 좋을까요?', '여행 고민', 'GENERAL_CONCERNS', NOW()),
       (4, '직장내용10', '직장제목10', 'WORK_LIFE', NOW()),
       (4, '직장내용11', '직장제목11', 'WORK_LIFE', NOW()),
       (4, '직장내용12', '직장제목12', 'WORK_LIFE', NOW()),
       (4, '연애내용1', '연애제목1', 'RELATIONSHIPS', NOW()),
       (4, '연애내용2', '연애제목2','RELATIONSHIPS', NOW()),
       (4, '연애내용3', '연애제목3', 'RELATIONSHIPS', NOW()),

       (5, '취미를 찾고 싶어요', '취미 고민', 'GENERAL_CONCERNS', NOW()),
       (5, '연애내용4', '연애제목4', 'RELATIONSHIPS', NOW()),
       (5, '연애내용5', '연애제목5','RELATIONSHIPS', NOW()),
       (5, '연애내용6', '연애제목6', 'RELATIONSHIPS', NOW()),
       (5, '연애내용7', '연애제목7', 'RELATIONSHIPS', NOW()),
       (5, '연애내용8', '연애제목8','RELATIONSHIPS', NOW()),
       (5, '연애내용9', '연애제목9', 'RELATIONSHIPS', NOW()),
       (5, '운동을 시작해보려고 하는데...', '나가기 싫어요', 'GENERAL_CONCERNS', NOW()),

       (6, '혼자 밥 먹기 너무 싫어요', '혼밥 고민', 'SOCIAL_INTERACTIONS', NOW()),
       (6, '영화 추천 좀 해주세요', '영화 고민', 'GENERAL_CONCERNS', NOW()),
       (6, '연애내용10', '연애제목10', 'RELATIONSHIPS', NOW()),
       (6, '연애내용11', '연애제목11','RELATIONSHIPS', NOW()),
       (6, '연애내용12', '연애제목12', 'RELATIONSHIPS', NOW()),
       (6, '대인관계내용1', '대인관계제목1', 'SOCIAL_INTERACTIONS', NOW()),
       (6, '대인관계내용2', '대인관계제목2', 'SOCIAL_INTERACTIONS', NOW()),
       (6, '대인관계내용3', '대인관계제목3', 'SOCIAL_INTERACTIONS', NOW()),

       (7, '새로운 프로젝트가 너무 어려워요', '프로젝트 고민', 'WORK_LIFE', NOW()),
       (7, '팀원과의 갈등이 있어요', '팀원 고민', 'WORK_LIFE', NOW()),
       (7, '대인관계내용4', '대인관계제목4', 'SOCIAL_INTERACTIONS', NOW()),
       (7, '대인관계내용5', '대인관계제목5', 'SOCIAL_INTERACTIONS', NOW()),
       (7, '대인관계내용6', '대인관계제목6', 'SOCIAL_INTERACTIONS', NOW()),
       (7, '대인관계내용7', '대인관계제목7', 'SOCIAL_INTERACTIONS', NOW()),
       (7, '대인관계내용8', '대인관계제목8', 'SOCIAL_INTERACTIONS', NOW()),
       (7, '대인관계내용9', '대인관계제목9', 'SOCIAL_INTERACTIONS', NOW()),

       (8, '자기계발을 어떻게 해야 할까요?', '자기계발 고민', 'CAREER_JOBS', NOW()),
       (8, '취업 준비가 막막해요', '취업ㅠㅠ', 'CAREER_JOBS', NOW()),
       (8, '대인관계내용10', '대인관계제목10', 'SOCIAL_INTERACTIONS', NOW()),
       (8, '대인관계내용11', '대인관계제목11', 'ADDICTION_OBSESSION', NOW()),
       (8, '대인관계내용12', '대인관계제목12', 'ADDICTION_OBSESSION', NOW()),
       (8, '중독/집착내용1', '중독/집착제목1', 'ADDICTION_OBSESSION', NOW()),
       (8, '중독/집착내용2', '중독/집착제목2', 'ADDICTION_OBSESSION', NOW()),
       (8, '중독/집착내용3', '중독/집착제목3', 'ADDICTION_OBSESSION', NOW()),

       (9, '연애가 너무 어려워요', '연애.................. 어려움', 'RELATIONSHIPS', NOW()),
       (9, '혼자서도 잘 할 수 있을까요?', '혼자 고민', 'GENERAL_CONCERNS', NOW()),
       (9, '중독/집착내용4', '중독/집착제목4', 'ADDICTION_OBSESSION', NOW()),
       (9, '중독/집착내용5', '중독/집착제목5', 'ADDICTION_OBSESSION', NOW()),
       (9, '중독/집착내용6', '중독/집착제목6', 'ADDICTION_OBSESSION', NOW()),
       (9, '중독/집착내용7', '중독/집착제목7', 'ADDICTION_OBSESSION', NOW()),
       (9, '중독/집착내용8', '중독/집착제목8', 'ADDICTION_OBSESSION', NOW()),
       (9, '중독/집착내용9', '중독/집착제목9', 'ADDICTION_OBSESSION', NOW()),
       (10, '집에서 취미 생활 하기 좋은 게 뭐가 있을까요?', '취미 생활', 'GENERAL_CONCERNS', NOW()),
       (10, '중독/집착내용10', '중독/집착제목10', 'ADDICTION_OBSESSION', NOW()),
       (10, '금전/사업내용1', '금전/사업제목1', 'FINANCE_BUSINESS', NOW()),
       (10, '금전/사업내용2', '금전/사업제목2', 'FINANCE_BUSINESS', NOW()),
       (10, '금전/사업내용3', '금전/사업제목3', 'FINANCE_BUSINESS', NOW()),
       (10, '금전/사업내용4', '금전/사업제목4', 'FINANCE_BUSINESS', NOW()),
       (10, '금전/사업내용5', '금전/사업제목5', 'FINANCE_BUSINESS', NOW()),
       (10, '금전/사업내용6', '금전/사업제목6', 'FINANCE_BUSINESS', NOW()),
       (10, '금전/사업내용7', '금전/사업제목7', 'FINANCE_BUSINESS', NOW()),
       (10, '심리학 책 추천 좀 해주세요', '마음을 책으로 배울 수 있을까요?', 'GENERAL_CONCERNS', NOW());


-- reply_tb
-- 전문가 댓글
INSERT INTO reply_tb (user_id, comm_id, introduction, summary, cause_analysis, solution, created_at)
VALUES (21, 1, '안녕하세요 상담사 입니다', '집에 가고 싶으시군요', '생활 속 스트레스가 주요 원인입니다.', '정기적인 휴식과 상담을 추천합니다.', NOW()),
       (22, 3, '상담 준비 완료', '저녁메뉴가 고민이시군요', '배가 고픈게 원인입니다.', '배가 고프지 않게 수시로 드세요.', NOW()),
       (23, 4, '다이어트가 고민인 당신', '대답해드리는게 인지상정', '생활 속 스트레스가 주요 원인입니다.', 'PT를 추천드립니다.', NOW()),
       (24, 5, '상담사 소개', '잠이 온다구요?', '생활 속 스트레스가 주요 원인입니다.', '정기적인 휴식과 상담을 추천합니다.', NOW()),
       (25, 7, '상담사 소개', '친구랑 싸웠다구요?', '생활 속 스트레스가 주요 원인입니다.', '이겼나요 졌나요?', NOW()),
       (21, 9, '상담사 소개', '상담사 사연 요약', '생활 속 스트레스가 주요 원인입니다.', '밖에 나가서 이것저것 해보세요.', NOW()),
       (30, 11, '혼밥 장인 상담사입니다', '혼밥이 고민', '생활 속 스트레스가 주요 원인입니다.', '저도 혼밥하니 걱정마세요.', NOW()),
       (30, 17, '모솔 상담사입니다', '연애가 고민이시군요', '저는 모솔입니다,', '저도 있으니 너무 걱정마세요.', NOW()),
       (23, 20, '상담사 소개', '상담사 사연 요약', '생활 속 스트레스가 주요 원인입니다.', '정기적인 휴식과 상담을 추천합니다.', NOW());

-- 일반인 댓글
INSERT INTO reply_tb(user_id, comm_id, content, created_at)
VALUES (1, 1, '나도 집에 가고싶어요', NOW()),
       (1, 4, '나도 집에 가고싶어요', NOW()),
       (1, 7, '나도 집에 가고싶어요', NOW()),
       (2, 1, '나도 집에 가고싶어요', NOW()),
       (3, 2, '나도 집에 가고싶어요', NOW()),
       (4, 3, '나도 집에 가고싶어요', NOW()),
       (5, 4, '나도 집에 가고싶어요', NOW()),
       (6, 5, '나도 집에 가고싶어요', NOW()),
       (7, 7, '나도 집에 가고싶어요', NOW()),
       (8, 8, '나도 집에 가고싶어요', NOW()),
       (9, 9, '나도 집에 가고싶어요', NOW()),
       (10, 13, '나도 집에 가고싶어요', NOW()),
       (11, 14, '나도 집에 가고싶어요', NOW()),
       (12, 15, '나도 집에 가고싶어요', NOW()),
       (13, 17, '나도 집에 가고싶어요', NOW()),
       (14, 17, '나도 집에 가고싶어요', NOW()),
       (15, 18, '나도 집에 가고싶어요', NOW()),
       (16, 19, '나도 집에 가고싶어요', NOW()),
       (17, 19, '나도 집에 가고싶어요', NOW());

-- pr_tb
INSERT INTO pr_tb (expert_id, intro, effects, methods)
VALUES (21, '심리상담을 망설이는 분에게: 혼자 고민하지 마세요. 용기가 당신을 행복하게 만들 것입니다. 숨겨진 보물을 찾아내는 기쁨을 함께 경험하세요.',
        '상담사님과의 심리상담 효과: 문제의 원인은 모두 다릅니다. 사람마다 환경과 감정도 다르죠. 개개인의 상담 목표와 달성 방법, 효과에는 차이가 있습니다.',
        '상담사님의 심리상담 방식: 상담 초기 단계에서는 상담신청 동기와 욕구, 문제파악과 목표를 설정합니다. 중기 단계에서는 억압된 감정표출과 문제 해결을 위한 심리적 여정을 돕습니다.'),
       (22, '심리상담을 망설이는 분들에게: 혼자서 힘들어하지 마세요. 상담을 통해 새로운 시각을 얻고 행복을 찾으세요.',
        '상담사님과의 심리상담 효과: 각 개인의 문제와 상황에 맞춘 상담을 통해 더 나은 해결책을 찾을 수 있습니다.',
        '상담사님의 심리상담 방식: 상담 초기에 문제를 파악하고 목표를 설정합니다. 중기에는 감정을 표출하고 해결책을 모색합니다.'),
       (23, '심리상담을 망설이는 분들에게: 상담을 통해 마음의 짐을 덜어내세요. 함께 해결책을 찾아 나갑시다.',
        '상담사님과의 심리상담 효과: 다양한 문제 상황에 맞춘 맞춤형 상담을 제공합니다. 개인의 특성을 고려한 상담을 통해 효과적인 해결을 돕습니다.',
        '상담사님의 심리상담 방식: 초기 상담 단계에서는 문제를 파악하고 목표를 설정합니다. 중기에는 억눌린 감정을 해소하고 문제 해결을 위한 심리적 접근을 시도합니다.'),
       (24, '심리상담을 망설이는 분들에게: 상담을 통해 새로운 시작을 만들어 보세요. 함께 성장할 수 있습니다.',
        '상담사님과의 심리상담 효과: 각 개인의 문제와 상황에 맞춘 개별 상담을 통해 최선의 해결책을 찾습니다.',
        '상담사님의 심리상담 방식: 상담 초기에는 상담신청 동기와 문제를 파악하고 목표를 설정합니다. 중기에는 감정표출과 문제 해결을 위한 접근을 시도합니다.'),
       (25, '심리상담을 망설이는 분들에게: 상담을 통해 마음의 평안을 찾으세요. 함께 더 나은 내일을 준비합시다.',
        '상담사님과의 심리상담 효과: 각기 다른 문제와 상황에 맞춘 상담을 통해 효과적인 해결책을 제공합니다.',
        '상담사님의 심리상담 방식: 초기 단계에서는 상담신청 동기와 문제를 파악하고 목표를 설정합니다. 중기에는 감정을 표출하고 문제 해결을 위한 심리적 접근을 합니다.'),
       (26, '심리상담을 망설이는 분들에게: 혼자 고민하지 마세요. 상담을 통해 마음의 짐을 덜어내세요.',
        '상담사님과의 심리상담 효과: 개개인의 문제에 맞춘 상담을 통해 최적의 해결책을 찾습니다.',
        '상담사님의 심리상담 방식: 상담 초기 단계에서는 문제를 파악하고 목표를 설정합니다. 중기에는 감정표출과 문제 해결을 위한 접근을 시도합니다.'),
       (27, '심리상담을 망설이는 분들에게: 상담을 통해 새로운 길을 찾아보세요. 함께 더 나은 미래를 준비합시다.',
        '상담사님과의 심리상담 효과: 문제와 상황에 맞춘 개별 상담을 통해 최선의 해결책을 찾습니다.',
        '상담사님의 심리상담 방식: 상담 초기 단계에서는 문제를 파악하고 목표를 설정합니다. 중기에는 억눌린 감정을 해소하고 문제 해결을 위한 접근을 시도합니다.'),
       (28, '심리상담을 망설이는 분들에게: 상담을 통해 마음의 짐을 덜어내세요. 새로운 시작을 함께 만들어갑시다.',
        '상담사님과의 심리상담 효과: 각 개인의 문제에 맞춘 맞춤형 상담을 통해 최적의 해결책을 찾습니다.',
        '상담사님의 심리상담 방식: 초기 상담 단계에서는 문제를 파악하고 목표를 설정합니다. 중기에는 감정을 표출하고 문제 해결을 위한 접근을 시도합니다.'),
       (29, '심리상담을 망설이는 분들에게: 상담을 통해 마음의 평안을 찾으세요. 함께 더 나은 내일을 준비합시다.',
        '상담사님과의 심리상담 효과: 각기 다른 문제와 상황에 맞춘 상담을 통해 효과적인 해결책을 제공합니다.',
        '상담사님의 심리상담 방식: 초기 단계에서는 상담신청 동기와 문제를 파악하고 목표를 설정합니다. 중기에는 감정을 표출하고 문제 해결을 위한 심리적 접근을 합니다.'),
       (30, '심리상담을 망설이는 분들에게: 혼자 고민하지 마세요. 상담을 통해 마음의 짐을 덜어내세요.',
        '상담사님과의 심리상담 효과: 개개인의 문제에 맞춘 상담을 통해 최적의 해결책을 찾습니다.',
        '상담사님의 심리상담 방식: 상담 초기 단계에서는 문제를 파악하고 목표를 설정합니다. 중기에는 감정표출과 문제 해결을 위한 접근을 시도합니다.');

-- spec_tb
INSERT INTO spec_tb (spec_type, details, user_id, start_year, end_year)
VALUES
    -- Expert 1 (홍길동)
    ('CAREER', '어바웃미 선정, 10월의 전문가', 21, 1999, 2005),
    ('CAREER', '사회복지사 2급 (보건복지부)', 21, 2002, 2006),
    ('CAREER', '미술치료사 2급 (국제민간자격증문협회)', 21, 2005, 2011),
    ('CAREER', '자살예방교육전문강사 (한국자살예방센터)', 21, 2007, 2012),
    ('CAREER', '전문 심리상담경력 (11년 5개월)', 21, 2008, 2016),
    ('CAREER', '현 마음숲 심리상담센터 센터장', 21, 2009, 2022),
    ('CAREER', '현 집단상담전문가', 21, 2013, 2017),
    ('CAREER', '전 대구극동방송국 상담사', 21, 2005, 2024),
    ('CAREER', '전 대구동구청소년동반자', 21, 2009, 2024),
    ('EDUCATION', '계명대학교대학원 가족상담학 석사 졸업', 21, 2002, 2009),

    -- Expert 2 (이영희)
    ('CAREER', '전문상담사 2급 (한국상담학회)', 22, 1999, 2005),
    ('CAREER', '교정상담사 2급 (교정상담학회)', 22, 2002, 2006),
    ('CAREER', '기업상담전문가 2급 (한국가족문화상담협회)', 22, 2005, 2011),
    ('CAREER', 'PTSD심리상담사 2급 (한국심리훈련연구소)', 22, 2007, 2012),
    ('CAREER', '현 어바웃미 심리상담사', 22, 2008, 2016),
    ('CAREER', '현 경북대학교병원 사내상담사', 22, 2009, 2022),
    ('CAREER', '현 한국철도청 MOU 및 전문상담사', 22, 2013, 2017),
    ('CAREER', '전 동대구초등학교 워크클래스 상담사', 22, 2009, 2024),
    ('EDUCATION', '연세대학교 졸업', 22, 2002, 2009),

    -- Expert 3 (박철수)
    ('CAREER', '심리상담사 1급 자격증 소지 (한국심리학회)', 23, 1999, 2005),
    ('CAREER', '전문 상담경력 (15년)', 23, 2002, 2006),
    ('CAREER', '현 마음클리닉 소장', 23, 2005, 2011),
    ('CAREER', '전 서울대학교병원 심리상담사', 23, 2008, 2016),
    ('EDUCATION', '서울대학교 심리학과 박사 졸업', 23, 2013, 2017),

    -- Expert 4 (김미영)
    ('CAREER', '미술치료사 1급 자격증 소지 (한국미술치료학회)', 24, 1999, 2005),
    ('CAREER', '아동 심리상담 경력 (10년)', 24, 2002, 2006),
    ('CAREER', '현 아동심리센터 원장', 24, 2005, 2011),
    ('CAREER', '전 연세대학교병원 소아심리상담사', 24, 2008, 2016),
    ('EDUCATION', '연세대학교 아동심리학 석사 졸업', 24, 2013, 2017),

    -- Expert 5 (이종현)
    ('CAREER', '가족상담사 1급 자격증 소지 (한국가족상담학회)', 25, 1999, 2005),
    ('CAREER', '가족 및 부부상담 경력 (12년)', 25, 2002, 2006),
    ('CAREER', '현 가족상담클리닉 소장', 25, 2005, 2011),
    ('CAREER', '전 중앙대학교병원 가족상담사', 25, 2008, 2016),
    ('EDUCATION', '중앙대학교 가족상담학 석사 졸업', 25, 2013, 2017),

    -- Expert 6 (윤정희)
    ('CAREER', '학교상담사 1급 자격증 소지 (한국학교상담학회)', 26, 1999, 2005),
    ('CAREER', '청소년 상담 경력 (13년)', 26, 2002, 2006),
    ('CAREER', '현 청소년상담센터 소장', 26, 2005, 2011),
    ('CAREER', '전 고려대학교병원 청소년상담사', 26, 2008, 2016),
    ('EDUCATION', '고려대학교 교육심리학 석사 졸업', 26, 2013, 2017),

    -- Expert 7 (최현우)
    ('CAREER', '부부상담사 1급 자격증 소지 (한국부부상담학회)', 27, 1999, 2005),
    ('CAREER', '부부 및 커플 상담 경력 (14년)', 27, 2002, 2006),
    ('CAREER', '현 부부상담클리닉 소장', 27, 2008, 2016),
    ('CAREER', '전 성균관대학교병원 부부상담사', 27, 2013, 2017),
    ('EDUCATION', '성균관대학교 부부상담학 석사 졸업', 27, 2013, 2017),

    -- Expert 8 (김소정)
    ('CAREER', '임상심리사 1급 자격증 소지 (한국임상심리학회)', 28, 1999, 2005),
    ('CAREER', '임상심리 경력 (16년)', 28, 2002, 2006),
    ('CAREER', '현 임상심리연구소 소장', 28, 2008, 2016),
    ('CAREER', '전 한양대학교병원 임상심리사', 28, 2013, 2017),
    ('EDUCATION', '한양대학교 임상심리학 석사 졸업', 28, 2013, 2017),

    -- Expert 9 (박재호)
    ('CAREER', '정신건강상담사 1급 자격증 소지 (한국정신건강상담학회)', 29, 1999, 2005),
    ('CAREER', '정신건강 상담 경력 (18년)', 29, 2002, 2006),
    ('CAREER', '현 정신건강센터 소장', 29, 2008, 2016),
    ('CAREER', '전 경희대학교병원 정신건강상담사', 29, 2013, 2017),
    ('EDUCATION', '경희대학교 정신건강학 석사 졸업', 29, 2013, 2019),

    -- Expert 10 (이유리)
    ('CAREER', '놀이치료사 1급 자격증 소지 (한국놀이치료학회)', 30, 2013, 2017),
    ('CAREER', '놀이치료 경력 (11년)', 30, 2013, 2017),
    ('CAREER', '현 놀이치료센터 소장', 30, 2013, 2017),
    ('CAREER', '전 이화여자대학교병원 놀이치료사', 30, 2013, 2017),
    ('EDUCATION', '이화여자대학교 놀이치료학 석사 졸업', 30, 2013, 2017);




