create table if not exists roles(id int auto_increment primary key, name varchar(255) not null unique);
create table if not exists users(id int auto_increment primary key, name varchar(100), email varchar(100) unique, password varchar(255));
create table if not exists user_role(user_id int, role_id int, foreign key(user_id) references users(id), foreign key(role_id) references roles(id));
insert ignore into roles (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');
insert ignore into users (id, email, password, name) VALUES
    (1, 'admin@123.com', '$2a$10$s.QbX3H0jjzFnJmp7uQQIOEgjiM1p5hqrf0QsU5OUrfeKZXz4dHnG', 'Admin'),
    (2, 'siva@123.com', '$2a$10$s.QbX3H0jjzFnJmp7uQQIOEgjiM1p5hqrf0QsU5OUrfeKZXz4dHnG', 'Siva'),
    (3, 'user@123.com', '$2a$10$s.QbX3H0jjzFnJmp7uQQIOEgjiM1p5hqrf0QsU5OUrfeKZXz4dHnG', 'DemoUser');

insert ignore into user_role(user_id, role_id) values
    (1,1),
    (1,2),
    (3,2);