-- Удалить дубликаты через SQL [#106936]
-- Петр Арсентьев,  16.01.19 14:37
-- В системе есть таблица cities. с полями id, name.
-- Система парсит объявления и записывывать города.
-- В коде системы оказался баг. Он записывал дубликаты городов.
-- Москва, Москва, СПб, Казань.
-- Багу поправили на уровне приложения, но таблица все равно содержит дубликаты.
-- Ваша задача написать persistence скрипт, который оставит в таблице только уникальные города. Если было три раза написана Москва. то нужно оставить только одну Москву.

CREATE TABLE Cities
(
City_id  serial primary key,
City VARCHAR(120) NOT NULL
)


INSERT INTO Cities
(City)
VALUES
('Amsterdam'),
('Moscow'),
('Minsk'),
('Prague'),
('London'),
('Paris'),
('Moscow'),
('Munch'),
('Perm'),
('Paris'),
('Moscow'),
('London'),
('Kiev'),
('Lyon'),
('Amsterdam'),
('Odessa')
	
WITH Temp_view as (
SELECT City, MIN(City_id) as Min_id FROM Cities
GROUP BY  City
HAVING COUNT(City_id) > 1
)
DELETE FROM Cities
WHERE City IN (SELECT City FROM Temp_view) AND city_id NOT IN (SELECT Min_id FROM Temp_view)