create table users
(
  id serial primary key,
  name character varying(200) NOT NULL,
  login character varying(60) NOT NULL,
  email character varying(120) NOT NULL,
  create_date timestamp NOT NULL
);

ALTER TABLE users
ADD CONSTRAINT unique_fields UNIQUE (name, login, email)