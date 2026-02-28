# 4
create table tbl_notification_wish
(
    id                          bigint unsigned auto_increment primary key,
    notification_wish_status    enum ('read', 'unread') default 'unread',
    notification_wish_member_id bigint unsigned,
    constraint fk_notification_wish_member_id foreign key (notification_wish_member_id) references tbl_comment (id)
);