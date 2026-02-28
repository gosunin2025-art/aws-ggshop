# 13
create table tbl_board_payment
( #개인간거래내역
                            id                        bigint unsigned auto_increment primary key,
  board_payment_sales_kwh   bigint          not null,
  board_payment_sales_price bigint          not null,
  payment_status            enum ('active', 'inactive') default 'active',
  created_date              datetime                   default current_timestamp(),
  updated_date              datetime                   default current_timestamp(),
  payment_member_id         bigint unsigned not null,
  constraint fk_payment_member_id foreign key (payment_member_id) references tbl_member (id),
  payment_board_id          bigint unsigned not null,
  constraint fk_payment_board_id foreign key (payment_board_id) references tbl_board (id)
);

DROP TABLE tbl_board_payment;
SET foreign_key_checks = 1;