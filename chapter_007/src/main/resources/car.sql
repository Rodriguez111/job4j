--db creating:
create database car;
--------------------------------------
-- tables creating:
CREATE TABLE car_body
(id serial primary key,
bodyType VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE engine
(id serial primary key,
engineType VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE transmission
(id serial primary key,
transmission_type VARCHAR(60) UNIQUE NOT NULL
);

CREATE TABLE car
(id serial primary key,
 manufacturer VARCHAR(60) NOT NULL,
 model VARCHAR(25) NOT NULL,
 car_body_id integer NOT NULL REFERENCES car_body(id),
 engine_id integer NOT NULL REFERENCES engine(id),
 transmission_id integer NOT NULL REFERENCES transmission(id)
);
-----------------------------------------------------
-- filling tables with data:

INSERT INTO car_body
(bodyType)
VALUES
('Hatchback'),
('Sedan'),
('Crossover'),
('Coupe'),
('MPV');

INSERT INTO engine
(engineType)
VALUES
('VEE'),
('INLINE'),
('STRAIGHT'),
('VR and W'),
('BOXER');

INSERT INTO transmission
(transmission_type)
VALUES
('Traditional Automatic'),
('Automated-Manual'),
('Continuously Variable'),
('Dual-Clutch'),
('Direct Shift Gearbox'),
('Tiptronic');

INSERT INTO car
(manufacturer, model, car_body_id, engine_id, transmission_id)
VALUES
('Audi', 'Q3', 3, 1, 1),
('BMW', '8', 2, 4, 6),
('Jeep', 'Wrangler', 4, 3, 2),
('Porsche', '911', 1, 2, 2);
----------------------------
-- 1. Вывести список всех машин и все привязанные к ним детали.
SELECT manufacturer AS "Производитель", model AS "Модель", bodyType AS "Кузов", engineType AS "Тип двигателя", transmission_type AS "Трансмиссия" FROM car
LEFT JOIN car_body
ON car.car_body_id = car_body.id
LEFT JOIN engine
ON car.engine_id = engine.id
LEFT JOIN transmission
ON car.transmission_id = transmission.id

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
SELECT bodyType AS "Детали" FROM car_body
WHERE id NOT IN (SELECT car_body_id FROM car)
UNION 
SELECT engineType FROM engine
WHERE id NOT IN (SELECT engine_id FROM car)
UNION
SELECT  transmission_type FROM transmission
WHERE id NOT IN (SELECT transmission_id FROM car)