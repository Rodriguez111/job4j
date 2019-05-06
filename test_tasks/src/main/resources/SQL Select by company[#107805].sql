--db creating:
create database test_tasks01;


CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer REFERENCES company(id),
CONSTRAINT person_pkey PRIMARY KEY (id)
);



INSERT INTO company
(id, name)
VALUES
(1, 'Company1'),
(2, 'Company2'),
(3, 'Company3'),
(4, 'Company4'),
(5, 'Company5'),
(6, 'Company6');

INSERT INTO person
(id, name, company_id)
VALUES
(1, 'Person1', 1),
(2, 'Person2', 1),
(3, 'Person3', 2),
(4, 'Person4', 3),
(5, 'Person5', 3),
(6, 'Person6', 4)
(7, 'Person7', 4),
(8, 'Person8', 5),
(9, 'Person9', 6),
(10, 'Person10', 6);


-- 1) Retrieve in a single query:
-- - names of all persons that are NOT in the company with id = 5
-- - company name for each person
SELECT person.name AS "Name Of person", company.name AS "Name Of company" FROM person
JOIN company ON
person.company_id = company.id
WHERE company_id !=5

--2) Select the name of the company with the maximum number of persons + number of persons in this company
WITH CTE AS (
SELECT   company.name AS "Name_Of_company", COUNT(person.id) AS Cnt, rank() OVER (ORDER BY COUNT(person.id)) AS Rnk   FROM person
JOIN company ON
person.company_id = company.id
GROUP BY company.name
)
SELECT "Name_Of_company", Cnt FROM CTE
WHERE Rnk = (SELECT MAX(rnk) FROM CTE)