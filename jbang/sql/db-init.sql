DROP TABLE IF EXISTS users;
CREATE TABLE users(
  id INT,
  name VARCHAR(20),
  byear INT
);

INSERT INTO users VALUES (1, 'user1', 1995);
INSERT INTO users VALUES (2, 'user2', 1996);
