# 1
CREATE TABLE tbl_member
(
    id              BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    member_email    VARCHAR(255) NOT NULL UNIQUE,
    member_password VARCHAR(255),
    member_name     VARCHAR(255) NOT NULL,
    member_nickname VARCHAR(255) NOT NULL default '닉네임을입력해주세요.',
    member_birth    VARCHAR(255),
    member_address  VARCHAR(255),
    member_filter   ENUM ('individual', 'company') DEFAULT 'individual',
    member_status   ENUM ('active', 'inactive')     DEFAULT 'active',
    created_date    DATETIME                       DEFAULT CURRENT_TIMESTAMP(),
    updated_date    DATETIME                       DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP()
);

DROP TABLE tbl_member;
set foreign_key_checks = 1;
select * from tbl_member;
select * from tbl_member_oauth;




