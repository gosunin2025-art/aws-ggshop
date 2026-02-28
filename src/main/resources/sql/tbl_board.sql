# 11
create table tbl_board
( #거래 게시글
      id              bigint unsigned auto_increment primary key,
  title           varchar(255)    not null,
  content         text            not null,
  board_filter    enum ('all', '구매', '판매')   default 'all',
  board_status    enum ('active', 'inactive') default 'active',
  created_date    datetime                   default current_timestamp(),
  updated_date    datetime                   default current_timestamp(),
  board_member_id bigint unsigned not null,
  constraint fk_board_member_id foreign key (board_member_id) references tbl_member (id)
);

DROP TABLE tbl_board;
SET foreign_key_checks = 1;


select * from tbl_board;


SELECT * FROM tbl_file;

SELECT * FROM tbl_board_file;

-- 게시글
SELECT * FROM tbl_board WHERE id = 9;

-- 파일
SELECT * FROM tbl_file WHERE id = 23;
SELECT * FROM tbl_board_file WHERE board_id = 9;

-- 태그
SELECT * FROM tbl_board_tag WHERE tag_board_id = 9;