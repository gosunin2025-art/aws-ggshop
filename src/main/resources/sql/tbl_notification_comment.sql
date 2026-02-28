# 8
create table tbl_notification_comment
(
    id                             bigint unsigned auto_increment primary key,
    notification_comment_status    enum ('read', 'unread') default 'unread',
    notification_comment_member_id bigint unsigned,
    constraint fk_notification_comment_member_id foreign key (notification_comment_member_id) references tbl_comment (id)
);