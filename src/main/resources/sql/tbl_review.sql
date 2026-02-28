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