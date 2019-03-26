create table items
(
  id serial primary key,
  name character varying(200) NOT NULL,
  description character varying(200) NOT NULL,
  create_item timestamp NOT NULL,
  comments character varying(2000)
);