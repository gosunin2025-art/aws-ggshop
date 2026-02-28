# 6
create table tbl_notification_follow
(
    id                            bigint unsigned auto_increment primary key,
    notification_follow_status    enum ('read', 'unread') default 'unread',
    notification_follow_member_id bigint unsigned,
    constraint fk_notification_follow_member_id foreign key (notification_follow_member_id) references tbl_comment (id)
);