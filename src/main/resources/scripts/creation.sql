CREATE
DATABASE IF NOT EXISTS forum;

CREATE TABLE forum.users
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    birth     DATE,
    sex       CHAR(1)
);