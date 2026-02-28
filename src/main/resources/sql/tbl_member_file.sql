# 22
create table tbl_member_file
(
    id        bigint unsigned primary key,
    member_id bigint unsigned not null,
    constraint fk_member_file_to_file_id foreign key (id) references tbl_file (id),
    constraint fk_member_file_member_id foreign key (member_id) references tbl_member (id)
);