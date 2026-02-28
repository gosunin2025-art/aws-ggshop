# 10
create table tbl_member_wish
( #회원 위시리스트
      id             bigint unsigned auto_increment primary key,
  wish_member_id bigint unsigned not null,
  constraint fk_wish_member_id foreign key (wish_member_id) references tbl_member (id),
  wish_board_id  bigint unsigned not null,
  constraint fk_wish_board_id foreign key (wish_board_id) references tbl_board (id)
);