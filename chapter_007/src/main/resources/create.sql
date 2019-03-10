--db creating:
create database tracker_system;
-------------------------------------
-- tables creating:
create table roles
(
role_id serial primary key,
role_name VARCHAR (100) UNIQUE NOT NULL
);


create table role_rights
(
right_id serial primary key,
role_id integer NOT NULL REFERENCES roles(role_id),
right_name 	VARCHAR (60) NOT NULL
);


create table users
(
user_id serial primary key,
user_role_id integer NOT NULL REFERENCES roles (role_id),
user_name VARCHAR (100) NOT NULL,
user_surname VARCHAR (100) NOT NULL
);

create table item_attached_files
(
file_id serial primary key,
file_link text
);


create table item_categories
(
category_id serial primary key,
category_name VARCHAR(100) UNIQUE NOT NULL
);

create table item_comments
(
comment_id serial primary key,
comment_body text
);

create table item_statements
(
statement_id serial primary key,
statement_name VARCHAR (60) UNIQUE NOT NULL
);

create table items
(
id serial primary key,
category_id integer NOT NULL REFERENCES item_categories(category_id),
comment_id integer NOT NULL REFERENCES item_comments(comment_id),
statement_id integer NOT NULL REFERENCES item_statements(statement_id),
attached_file_id integer NOT NULL REFERENCES item_attached_files(file_id),
user_id integer NOT NULL REFERENCES users(user_id),
theme text,
body VARCHAR(100) NOT NULL,
create_date timestamp NOT NULL,
last_modified_date timestamp NOT NULL
);

---------------------------------------------
-- filling tables with data:
INSERT INTO roles
(role_name)
VALUES
('Administrator'),
('User');


INSERT INTO role_rights
(role_id, right_name)
VALUES
(1, 'Create items'),
(1, 'Modify items'),
(1, 'Read items'),
(1, 'Close items'),
(2, 'Create items'),
(2, 'Read items');

INSERT INTO users
(user_role_id, user_name, user_surname)
VALUES
(1, 'Ivan', 'Ivanov'),
(2, 'Petr', 'Petrov');

INSERT INTO item_attached_files
(file_link)
VALUES
('c:\file1.txt'),
('c:\file2.txt');

INSERT INTO item_categories
(category_name)
VALUES
('hardware'),
('software');

INSERT INTO item_comments
(comment_body)
VALUES
('comment1'),
('comment2');

INSERT INTO item_statements
(statement_name)
VALUES
('new'),
('in process'),
('solved');

INSERT INTO items
(category_id, comment_id, statement_id, attached_file_id, user_id, theme, body, create_date, last_modified_date)
VALUES
(1, 1, 1, 1, 1, 'Theme-1', 'This is the body of first item', current_timestamp,  current_timestamp),
(2, 2, 2, 2, 2, 'Theme-2', 'This is the body of second item', current_timestamp,  current_timestamp);

