create database users;


CREATE TABLE IF NOT EXISTS roles
               (id serial primary key,
               role character varying(60) UNIQUE NOT NULL);


CREATE TABLE IF NOT EXISTS users
                (id serial primary key,
                name character varying(200) NOT NULL,
                RoleId INTEGER REFERENCES roles (id) NOT NULL,
                login character varying(60) UNIQUE NOT NULL,
                password character varying(16) NOT NULL,
                email character varying(120) NOT NULL,
                create_date character varying(19) NOT NULL);

