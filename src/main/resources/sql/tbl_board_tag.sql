# 12
create table tbl_board_tag
( #거래 해시태그
      id           bigint unsigned auto_increment primary key,
  tag_name     varchar(255)    not null,
  tag_board_id bigint unsigned not null,
  constraint fk_tag_board_id foreign key (tag_board_id) references tbl_board (id)
);

drop table tbl_board_tag;