# 7
create table tbl_notification_board
(
    id                           bigint unsigned auto_increment primary key,
    notification_board_status    enum ('read', 'unread') default 'unread',
    notification_board_member_id bigint unsigned,
    constraint fk_notification_board_member_id foreign key (notification_board_member_id) references tbl_comment (id)
);