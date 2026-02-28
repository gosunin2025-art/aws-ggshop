CREATE VIEW v_member_mypage AS
SELECT
    -- 유저 아이디
    m.id AS member_id,
    -- 유저 이메일
    m.member_email,
    -- 유저 닉네임
    m.member_nickname,
    -- 유저 주소
    m.member_address,
    -- 1. 게시정보 (tbl_board): 내가 쓴 총 게시글 수
    (SELECT COUNT(*) FROM tbl_board b WHERE b.board_member_id = m.id) AS total_boards,
    -- 2. 리뷰정보 (tbl_review): 내가 쓴 총 리뷰 수
    (SELECT COUNT(*) FROM tbl_review r WHERE r.review_member_id = m.id) AS total_reviews,
    -- 3. 팔로우 정보 (tbl_member_follow): 나를 팔로우하는 사람
    (SELECT COUNT(*) FROM tbl_member_follow f WHERE f.following_id = m.id) AS follower_count,
    -- 3. 팔로우 정보 (tbl_member_follow): 내가 팔로우하는 사람
    (SELECT COUNT(*) FROM tbl_member_follow f WHERE f.follower_id = m.id) AS following_count,
    -- 4. 위시리스트 (tbl_member_wish): 내가 찜한 수
    (SELECT COUNT(*) FROM tbl_member_wish w WHERE w.wish_member_id = m.id) AS wish_count,
    -- 5. 픽정보 (tbl_member_pick): 내가 픽한 수
    (SELECT COUNT(*) FROM tbl_member_pick p WHERE p.pick_member_id = m.id) AS pick_count,
    -- 6. 결제내역 (tbl_board_payment): 총 결제 횟수 및 누적 금액
    (SELECT COUNT(*) FROM tbl_board_payment pay WHERE pay.payment_member_id = m.id) AS total_payment_count,
    (SELECT IFNULL(SUM(payment_status), 0) FROM tbl_board_payment pay WHERE pay.payment_member_id = m.id) AS total_payment_amount,
    -- 7. 유저 정보 부가데이터 (예: 자동차 정보 등 연결 시 활용 가능)
    (SELECT car_plate_number FROM tbl_car c WHERE c.car_member_id = m.id LIMIT 1) AS car_number
FROM tbl_member m;

DROP VIEW v_member_mypage;
SET foreign_key_checks = 1;
