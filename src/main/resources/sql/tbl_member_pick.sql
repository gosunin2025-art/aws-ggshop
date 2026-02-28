# 9
create table tbl_member_pick
( #인기 추천
      id             bigint unsigned auto_increment primary key,
  pick_board_id  bigint unsigned not null,
  constraint fk_pick_board_id foreign key (pick_board_id) references tbl_board (id),
  pick_member_id bigint unsigned not null,
  constraint fk_pick_member_id foreign key (pick_member_id) references tbl_member (id)
);