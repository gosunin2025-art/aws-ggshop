# 20
create table tbl_comment
( #댓글
                    id                bigint unsigned auto_increment primary key,
  comment_content   varchar(255)    not null,
  comment_status    enum ('active', 'inactive') default 'active',
  created_date      datetime                   default current_timestamp(),
  updated_date      datetime                   default current_timestamp(),
  comment_board_id  bigint unsigned not null,
  constraint fk_comment_board_id foreign key (comment_board_id) references tbl_board (id),
  comment_member_id bigint unsigned not null,
  constraint fk_comment_member_id foreign key (comment_member_id) references tbl_member (id)
);

DROP TABLE tbl_comment;
SET foreign_key_checks = 1;