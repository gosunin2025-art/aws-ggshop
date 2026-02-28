#17

create table tbl_company_employee
( #직원
                                id bigint unsigned auto_increment primary key,
    company_employee_name       varchar(255) not null,
    company_employee_rank       varchar(255) not null,
    company_employee_email      varchar(255) not null,
    company_employee_number     varchar(255) not null,
    company_employee_department varchar(255) not null,
    ev_charger_content          varchar(255),
    company_employee_status     enum ('active', 'inactive') default 'active',
    created_date                datetime default current_timestamp(),
    updated_date                datetime default current_timestamp(),
    employee_company_id         bigint unsigned not null,
    constraint fk_employee_company_id foreign key (employee_company_id) references tbl_company (id)
);
DROP TABLE tbl_company_employee;
SET foreign_key_checks = 1;