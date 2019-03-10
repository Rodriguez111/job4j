--db creating:
create database products_queries;
--------------------------------------
-- tables createing:

create table product_type
(
type_id serial primary key,
type_name VARCHAR(120) UNIQUE NOT NULL
);

create table product
(
id serial primary key,
name VARCHAR(160) UNIQUE NOT NULL,
type_id integer NOT NULL REFERENCES product_type(type_id),
expired_date date,
price money NOT NULL,
amount integer NOT NULL
)	

---------------------------------------------
-- filling tables with data:

INSERT INTO product_type
(type_name)
VALUES
('Сыр'),
('Молоко'),
('Хлеб'),
('Мясо'),
('Птица'),
('Рыба');

INSERT INTO product
(name, type_id, expired_date, price, amount)
VALUES
('Сыр козий', 1, '2019-05-08', 30.3, 4),
('Сливки 33% 0,33л', 2, '2019-03-21', 16.5, 8),
('Сыр овечий', 1, '2019-04-25', 32.9, 10),
('Молоко 2,5% 1 л.', 2, '2019-03-29', 12.1, 68),
('Сыр голландский', 1, '2019-04-02', 54.8, 98),
('Вишневое мороженное 1,5 кг.', 2, '2019-06-09', 18.4, 36),
('Молоко 3,5% 1 л.', 2, '2019-03-25', 13.0, 9),
('Мороженное клубничное 1 кг.', 2, '2019-05-02', 18.0, 31),
('Мороженное ванильное 0,5 кг.', 2, '2019-04-07', 16.1, 20),
('Мясо замороженное говядина 0,3 кг.', 4, '2019-06-01', 8.2, 14),
('Хлеб белый', 3, '2019-03-10', 7.4, 1);
------------------------------------
--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product
JOIN product_type ON
product.type_id = product_type.type_id
WHERE type_name = 'Сыр'

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product
WHERE name like '%мороженное%'

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product
WHERE  TO_CHAR(expired_date, 'MM')= TO_CHAR(NOW() + interval '1 month', 'MM')

--4. Написать запрос, который выводит самый дорогой продукт.
 SELECT * FROM product
 WHERE price = (SELECT MAX(price) FROM product)

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT product_type.type_name AS "Тип продукта", SUM(amount) AS "Общее кол-во" FROM product
JOIN product_type ON
product.type_id = product_type.type_id
GROUP BY product.type_id, product_type.type_name

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product
JOIN product_type ON
product.type_id = product_type.type_id
WHERE type_name = 'Сыр' OR type_name = 'Молоко'

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 
SELECT product_type.type_name AS "Тип продуктов" FROM product
JOIN product_type ON
product.type_id = product_type.type_id
GROUP BY product_type.type_name
HAVING SUM(amount) > 10 

--8. Вывести все продукты и их тип.
SELECT name AS "Наименование продукта", type_name AS "Тип продукта" FROM product
JOIN product_type ON
product.type_id = product_type.type_id
ORDER BY name ASC


