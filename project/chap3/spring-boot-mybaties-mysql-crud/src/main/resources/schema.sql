drop table if exists employees;
create table employees(
    id integer primary key auto_increment,
    name varchar(255) not null,
    email_address varchar(255) not null unique,
    address varchar(255),
    salary decimal(8,2)
);