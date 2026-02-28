# 14
create table tbl_car
( #자동차
                   id               bigint unsigned auto_increment primary key,
  car_plate_number varchar(255)    not null,
  car_energy_gauge varchar(255)    not null,
  car_status       enum ('active', 'inactive') default 'active',
  car_filter       enum ('양방향', '일반')         default '일반',
  created_date     datetime                   default current_timestamp(),
  updated_date     datetime                   default current_timestamp(),
  car_member_id    bigint unsigned not null,
  constraint fk_car_member_id foreign key (car_member_id) references tbl_member (id)
);

DROP TABLE tbl_car;
SET foreign_key_checks = 1;


INSERT INTO tbl_car (car_plate_number, car_energy_gauge, car_status, car_filter, car_member_id)
VALUES ('12가 3456', '80', 'active', '양방향', 1);


INSERT INTO tbl_car (car_plate_number, car_energy_gauge, car_status, car_filter, car_member_id)
VALUES ('12가 3456', '80', 'active', '양방향', 1);

SELECT id FROM tbl_car;-- member id 확인 후 수정

INSERT INTO tbl_vtog_payment (vtog_payment_sales_kwh, vtog_payment_sales_price, vtog_car_id, vtog_ev_charger_id)
VALUES (100, 50000, 1, 6);