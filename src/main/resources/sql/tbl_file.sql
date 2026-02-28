# 23
create table tbl_file
( #파일
    id          bigint unsigned auto_increment primary key,
    file_name   varchar(255) not null,
    file_size   varchar(255) not null,
    file_path   varchar(255) not null,
    file_status enum ('active', 'inactive') default 'active'
);


DROP TABLE tbl_file;


SET foreign_key_checks = 1;