# 3
create table tbl_member_follow
( #회원 팔로우/팔로잉
    id           bigint unsigned auto_increment primary key,
  follower_id  bigint unsigned not null,
  following_id bigint unsigned not null,
  constraint fk_follower_member_id foreign key (follower_id) references tbl_member (id),
  constraint fk_following_member_id foreign key (following_id) references tbl_member (id),
  created_date datetime default current_timestamp(),
  updated_date datetime default current_timestamp()
);