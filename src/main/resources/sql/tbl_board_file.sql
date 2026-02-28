# 21
create table tbl_board_file
( #파일
           id        bigint unsigned primary key,
  board_id bigint unsigned not null,
  constraint fk_board_file_to_file_id foreign key (id) references tbl_file (id),
  constraint fk_board_file_board_id foreign key (board_id) references tbl_board (id)
);

drop table tbl_board_file;
