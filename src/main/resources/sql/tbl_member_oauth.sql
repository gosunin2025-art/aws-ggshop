# 2
CREATE TABLE tbl_member_oauth
(
    id bigint unsigned primary key,
    provider enum('threetier', 'kakao') not null,
    constraint fk_oauth_member foreign key (id)
        references tbl_member(id)
);

DROP TABLE tbl_member_oauth;
SET foreign_key_checks = 1;

select * from tbl_member_oauth;