CREATE TABLE IF NOT EXISTS car_body
(
    id serial primary key,
    body_type character varying(60) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS car_brand
(
    id serial primary key,
    car_brand character varying(60) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS engine
(
    id serial primary key,
    engine_type character varying(60) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS transmission
(
    id serial primary key,
    transmission_type character varying(60) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS cars
(
    id serial primary key,
    car_model character varying(60) NOT NULL,
    mileage integer  NOT NULL,
    year integer  NOT NULL,
    vin character varying(17) NOT NULL,
    color character varying(60) NOT NULL,
    car_brand_id integer NOT NULL references car_brand(id),
    body_type_id integer NOT NULL references car_body(id),
    engine_id integer NOT NULL references engine(id),
    engine_volume double precision NOT NULL,
    transmission_id integer NOT NULL references transmission(id)
);

CREATE TABLE IF NOT EXISTS users
(
    id serial primary key,
    login character varying(20) UNIQUE NOT NULL,
    password character varying(255) NOT NULL,
    name character varying(20) NOT NULL,
    surname character varying(20) NOT NULL,
    phone character varying(13) NOT NULL,
    email character varying(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS adverts
(
    id serial primary key,
    date character varying(19) NOT NULL,
    car_id integer NOT NULL references cars(id),
    price double precision NOT NULL,
    user_id integer NOT NULL references users(id),
    sold boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS photos
(
    id serial primary key,
    advert_id integer NOT NULL references adverts(id),
    file_name character varying
);



