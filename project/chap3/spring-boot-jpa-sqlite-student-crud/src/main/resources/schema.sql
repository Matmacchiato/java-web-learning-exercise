create table if not exists students(
    id integer primary key autoincrement,
    sid varchar(20) not null unique,
    name varchar(50) not null,
    gender integer
);