# use GGSHOP;
SET FOREIGN_KEY_CHECKS = 0;

use ggshop;


# 1
CREATE TABLE tbl_member
(
    id              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    member_email    VARCHAR(255) NOT NULL UNIQUE, -- 중복 방지
    member_password VARCHAR(255),                 -- 소셜 전용 유저는 NULL 가능
    member_name     VARCHAR(255) NOT NULL,
    member_birth    varchar(255),
    member_address  VARCHAR(255),                 -- 소셜 가입 시 주소는 나중에 받을 수도 있음
    member_filter   ENUM ('individual', 'company') DEFAULT 'individual',
    member_status   ENUM ('enable', 'disable')     DEFAULT 'enable',
    created_date    DATETIME                       DEFAULT CURRENT_TIMESTAMP(),
    updated_date    DATETIME                       DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP()
);


# 2
CREATE TABLE tbl_member_oauth
(
    id       BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    provider VARCHAR(50) NOT NULL default 'kakao', -- 'kakao', 'naver', 'google'
    oauth_member_id bigint unsigned not null,
    constraint fk_member_oauth_member_id foreign key (oauth_member_id) references tbl_member (id)
);


# 3
create table tbl_member_follow
( #회원 팔로우/팔로잉
    id           bigint unsigned auto_increment primary key,
    follower_id  bigint unsigned not null,
    following_id bigint unsigned not null,
    constraint fk_follower_member_id foreign key (follower_id) references tbl_member (id),
    constraint fk_following_member_id foreign key (following_id) references tbl_member (id),
    created_date datetime default current_timestamp(),
    updated_date datetime default current_timestamp()
);

# 4
create table tbl_notification_wish
(
    id                          bigint unsigned auto_increment primary key,
    notification_wish_status    enum ('read', 'unread') default 'unread',
    notification_wish_member_id bigint unsigned,
    constraint fk_notification_wish_member_id foreign key (notification_wish_member_id) references tbl_comment (id)
);


# 5
create table tbl_notification_review
(
    id                            bigint unsigned auto_increment primary key,
    notification_review_status    enum ('read', 'unread') default 'unread',
    notification_review_member_id bigint unsigned,
    constraint fk_notification_review_member_id foreign key (notification_review_member_id) references tbl_comment (id)
);


# 6
create table tbl_notification_follow
(
    id                            bigint unsigned auto_increment primary key,
    notification_follow_status    enum ('read', 'unread') default 'unread',
    notification_follow_member_id bigint unsigned,
    constraint fk_notification_follow_member_id foreign key (notification_follow_member_id) references tbl_comment (id)
);


# 7
create table tbl_notification_board
(
    id                           bigint unsigned auto_increment primary key,
    notification_board_status    enum ('read', 'unread') default 'unread',
    notification_board_member_id bigint unsigned,
    constraint fk_notification_board_member_id foreign key (notification_board_member_id) references tbl_comment (id)
);


# 8
create table tbl_notification_comment
(
    id                             bigint unsigned auto_increment primary key,
    notification_comment_status    enum ('read', 'unread') default 'unread',
    notification_comment_member_id bigint unsigned,
    constraint fk_notification_comment_member_id foreign key (notification_comment_member_id) references tbl_comment (id)
);


# 9
create table tbl_member_pick
( #인기 추천
    id             bigint unsigned auto_increment primary key,
    pick_board_id  bigint unsigned not null,
    constraint fk_pick_board_id foreign key (pick_board_id) references tbl_board (id),
    pick_member_id bigint unsigned not null,
    constraint fk_pick_member_id foreign key (pick_member_id) references tbl_member (id)
);


# 10
create table tbl_member_wish
( #회원 위시리스트
    id             bigint unsigned auto_increment primary key,
    wish_member_id bigint unsigned not null,
    constraint fk_wish_member_id foreign key (wish_member_id) references tbl_member (id),
    wish_board_id  bigint unsigned not null,
    constraint fk_wish_board_id foreign key (wish_board_id) references tbl_board (id)
);


# 11
create table tbl_board
( #거래 게시글
    id              bigint unsigned auto_increment primary key,
    title           varchar(255)    not null,
    content         text            not null,
    board_filter    enum ('all', '구매', '판매')   default 'all',
    board_status    enum ('enable', 'disable') default 'enable',
    created_date    datetime                   default current_timestamp(),
    updated_date    datetime                   default current_timestamp(),
    board_member_id bigint unsigned not null,
    constraint fk_board_member_id foreign key (board_member_id) references tbl_member (id)
);


# 12
create table tbl_board_tag
( #거래 해시태그
    id           bigint unsigned auto_increment primary key,
    tag_name     varchar(255)    not null,
    tag_board_id bigint unsigned not null,
    constraint fk_tag_board_id foreign key (tag_board_id) references tbl_board (id)
);


# 13
create table tbl_board_payment
( #개인간거래내역
    id                        bigint unsigned auto_increment primary key,
    board_payment_sales_kwh   bigint          not null,
    board_payment_sales_price bigint          not null,
    payment_status            enum ('enable', 'disable') default 'enable',
    created_date              datetime                   default current_timestamp(),
    updated_date              datetime                   default current_timestamp(),
    payment_member_id         bigint unsigned not null,
    constraint fk_payment_member_id foreign key (payment_member_id) references tbl_member (id),
    payment_board_id          bigint unsigned not null,
    constraint fk_payment_board_id foreign key (payment_board_id) references tbl_board (id)
);


# 14
create table tbl_car
( #자동차
    id               bigint unsigned auto_increment primary key,
    car_plate_number varchar(255)    not null,
    car_energy_gauge varchar(255)    not null,
    car_status       enum ('enable', 'disable') default 'enable',
    car_filter       enum ('양방향', '일반')         default '일반',
    created_date     datetime                   default current_timestamp(),
    updated_date     datetime                   default current_timestamp(),
    car_member_id    bigint unsigned not null,
    constraint fk_car_member_id foreign key (car_member_id) references tbl_member (id)
);


# 15
create table tbl_company
(#기업관리자
    id               bigint unsigned auto_increment primary key,
    company_name     varchar(255)               default 'admin',
    company_email    varchar(255) not null,
    company_password varchar(255) not null,
    company_address  varchar(255) not null,
    company_status   enum ('enable', 'disable') default 'enable',
    created_date     datetime                   default current_timestamp(),
    updated_date     datetime                   default current_timestamp()
);


# 16
create table tbl_ev_charger
( #EV충전기
    id                 bigint unsigned auto_increment primary key,
    ev_charger_address varchar(255)    not null,
    ev_charger_status  enum ('enable', 'disable') default 'enable',
    ev_charger_mod     enum ('enable', 'disable') default 'enable',
    created_date       datetime                   default current_timestamp(),
    updated_date       datetime                   default current_timestamp(),
    company_id         bigint unsigned not null,
    constraint fk_ev_charger_company_id foreign key (company_id) references tbl_company (id)
);

# 17
create table tbl_company_employee
( #직원
    id                          bigint unsigned auto_increment primary key,
    company_employee_email      varchar(255)    not null,
    company_employee_number     varchar(255)    not null,
    company_employee_department varchar(255)    not null,
    company_employee_status     enum ('enable', 'disable') default 'enable',
    created_date                datetime                   default current_timestamp(),
    updated_date                datetime                   default current_timestamp(),
    employee_company_id         bigint unsigned not null,
    constraint fk_employee_company_id foreign key (employee_company_id) references tbl_company (id)
);


# 18
create table tbl_vtog_payment
(#VtoG거래내역
    id                       bigint unsigned auto_increment primary key,
    vtog_payment_sales_kwh   bigint          not null,
    vtog_payment_sales_price bigint          not null,
    created_date             datetime default current_timestamp(),
    updated_date             datetime default current_timestamp(),
    vtog_car_id              bigint unsigned not null,
    constraint fk_vtog_car_id foreign key (vtog_car_id) references tbl_car (id)
);


# 19
create table tbl_review
(#리뷰
    id               bigint unsigned auto_increment primary key,
    review_content   varchar(255)    not null,
    review_member_id bigint unsigned not null,
    constraint fk_review_member_id foreign key (review_member_id) references tbl_member (id),
    review_board_id  bigint unsigned not null,
    constraint fk_review_board_id foreign key (review_board_id) references tbl_board (id)
);


# 20
create table tbl_comment
( #댓글
    id                bigint unsigned auto_increment primary key,
    comment_content   varchar(255)    not null,
    comment_status    enum ('enable', 'disable') default 'enable',
    created_date      datetime                   default current_timestamp(),
    updated_date      datetime                   default current_timestamp(),
    comment_board_id  bigint unsigned not null,
    constraint fk_comment_board_id foreign key (comment_board_id) references tbl_board (id),
    comment_member_id bigint unsigned not null,
    constraint fk_comment_member_id foreign key (comment_member_id) references tbl_member (id)
);


# 21
create table tbl_board_file
( #파일
    id        bigint unsigned primary key,
    board_id bigint unsigned not null,
    constraint fk_board_file_to_file_id foreign key (id) references tbl_file (id),
    constraint fk_board_file_board_id foreign key (board_id) references tbl_board (id)
);


# 22
create table tbl_member_file
(
    id        bigint unsigned primary key,
    member_id bigint unsigned not null,
    constraint fk_member_file_to_file_id foreign key (id) references tbl_file (id),
    constraint fk_member_file_member_id foreign key (member_id) references tbl_member (id)
);


# 23

create table tbl_file
( #파일
    id          bigint unsigned auto_increment primary key,
    file_name varchar(255) not null,
    file_size varchar(255) not null,
    file_path varchar(255) not null,
    file_status enum ('enable', 'disable') default 'enable'
);
# create table tbl_cpo
# ( #기업(회사)
#     id           bigint unsigned auto_increment primary key,
#     cpo_name     varchar(255)    not null,
#     cpo_address  varchar(255)    not null,
#     cpo_admin_id bigint unsigned not null,
#     constraint fk_cpo_admin_id foreign key (cpo_admin_id) references tbl_admin (id)
# );