# 16
create table tbl_ev_charger
(
    id                 bigint unsigned auto_increment primary key,
    ev_charger_address varchar(255)        not null, #설치주소
    ev_charger_uid     varchar(255) unique not null, #충전소 번호
    ev_charger_content varchar(255), #특이사항
    ev_charger_status enum('active', 'inactive') default 'active',
    ev_charger_mod     enum ('active', 'inactive') default 'active',
    setup_date         datetime                   default current_timestamp(), #설치일자
    created_date       datetime                   default current_timestamp(),
    updated_date       datetime                   default current_timestamp(),
    company_id         bigint unsigned     not null,
    constraint fk_ev_charger_company_id foreign key (company_id) references tbl_company (id)
);
DROP TABLE tbl_ev_charger;
SET foreign_key_checks = 1;

select * from tbl_ev_charger;