CREATE TABLE vacancy
(
  id  INTEGER PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(260) NOT NULL,
  text TEXT NOT NULL,
  link TEXT NOT NULL,
  author VARCHAR(120) NOT NULL,
  date VARCHAR(16) NOT NULL
);