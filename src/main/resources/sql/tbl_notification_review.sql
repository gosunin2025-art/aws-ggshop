# 5
create table tbl_notification_review
(
    id                            bigint unsigned auto_increment primary key,
    notification_review_status    enum ('read', 'unread') default 'unread',
    notification_review_member_id bigint unsigned,
    constraint fk_notification_review_member_id foreign key (notification_review_member_id) references tbl_comment (id)
);