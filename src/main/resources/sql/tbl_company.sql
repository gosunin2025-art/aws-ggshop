# 15
create table tbl_company
(#기업관리자
                  id               bigint unsigned auto_increment primary key,
 company_name     varchar(255)               default 'admin',
 company_email    varchar(255) not null,
 company_password varchar(255) not null,
 company_address  varchar(255) not null,
 company_status   enum ('active', 'inactive') default 'active',
 created_date     datetime                   default current_timestamp(),
 updated_date     datetime                   default current_timestamp()
);


SET foreign_key_checks = 1;


DROP TABLE tbl_company;
create table tbl_company
(
    id               bigint unsigned auto_increment primary key,
    company_name     varchar(255) default 'admin',
    company_email    varchar(255) not null,
    company_password varchar(255) not null,
    company_address  varchar(255) not null,
    company_status enum('active', 'inactive') default 'active',
    created_date     datetime default current_timestamp(),
    updated_date     datetime default current_timestamp()
);


DROP TABLE tbl_company_employee;


create table tbl_company_employee
(
    id bigint unsigned auto_increment primary key,
    company_employee_name       varchar(255) not null,
    company_employee_rank       varchar(255) not null,
    company_employee_email      varchar(255) not null,
    company_employee_number     varchar(255) not null,
    company_employee_department varchar(255) not null,
    ev_charger_content          varchar(255),
    company_employee_status enum('active', 'inactive') default 'active',
    created_date                datetime default current_timestamp(),
    updated_date                datetime default current_timestamp(),
    employee_company_id         bigint unsigned not null,
    constraint fk_employee_company_id foreign key (employee_company_id) references tbl_company (id)
);


drop table tbl_ev_charger;
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

INSERT INTO tbl_company
(company_name, company_email, company_password, company_address, company_status)
VALUES
    ('EV Power Corp', 'admin@evpower.com', 'password123', 'Seoul, Korea', 'active'),
    ('Green Charge Ltd', 'contact@greencharge.com', 'password456', 'Busan, Korea', 'active');

select * from tbl_company;

INSERT INTO tbl_company_employee
(company_employee_name,
 company_employee_rank,
 company_employee_email,
 company_employee_number,
 company_employee_department,
 ev_charger_content,
 company_employee_status,
 employee_company_id)
VALUES
    ('Kim Minsoo', 'Manager', 'minsoo.kim@evpower.com', '010-1111-2222', 'Operations', 'Main charger manager', 'active', 1),
    ('Lee Jiyoung', 'Engineer', 'jiyoung.lee@evpower.com', '010-3333-4444', 'Maintenance', 'Handles EV charger repairs', 'active', 1),
    ('Park Sunho', 'Supervisor', 'sunho.park@greencharge.com', '010-5555-6666', 'Field Team', 'Regional charger supervisor', 'active', 2);

select * from tbl_company_employee;

INSERT INTO tbl_ev_charger
(ev_charger_address,
 ev_charger_uid,
 ev_charger_content,
 ev_charger_status,
 ev_charger_mod,
 setup_date,
 company_id)
VALUES
    ('Seoul Gangnam Station Parking Lot', 'EV-SEOUL-003', 'Fast charger 150kW', 'active', 'active', '2024-01-10', 1),
    ('Seoul City Hall Basement', 'EV-SEOUL-002', 'Standard charger 50kW', 'active', 'active', '2024-02-05', 1),
    ('Busan Haeundae Beach Parking', 'EV-BUSAN-001', 'Outdoor charger', 'active', 'active', '2024-01-20', 2);

select * from tbl_ev_charger;

drop table tbl_ev_charger;


create table tbl_member(
    id bigint unsigned auto_increment primary key,
    member_email varchar(255),
    member_password varchar(255),
    member_name varchar(255) not null,
    member_status enum('active', 'inactive') default 'active',
    created_datetime datetime default current_timestamp,
    updated_datetime datetime default current_timestamp
);

drop table tbl_member;

select * from tbl_member;

UPDATE tbl_member
SET member_email = 'newuser3@gmail.com'
WHERE id = 3;

UPDATE tbl_member
SET member_password = '1234567890'
WHERE id = 1;



create table tbl_oauth(
                          id bigint unsigned primary key,
                          provider enum('threetier', 'kakao') not null,
                          constraint fk_oauth_member foreign key (id)
                              references tbl_member(id)
);
drop table tbl_oauth;



select * from tbl_oauth;


INSERT INTO tbl_ev_charger (ev_charger_address, ev_charger_uid, ev_charger_content, company_id)
VALUES ('서울특별시 강남구 테헤란로 1', 'EV-TEST-001', '테스트 충전기', 1);


SELECT id, ev_charger_uid FROM tbl_ev_charger;

SELECT id, ev_charger_uid FROM tbl_ev_charger WHERE id = 6;

UPDATE tbl_ev_charger
SET ev_charger_uid = 'EV-SEOUL-001'
WHERE id = 6;