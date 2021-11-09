CREATE TABLE IF NOT EXISTS users
(
    id         serial primary key,
    first_name VARCHAR (80) NOT NULL,
    last_name  VARCHAR (80) NOT NULL,
    username   VARCHAR (24) UNIQUE NOT NULL,
    password   VARCHAR (64) NOT NULL,
    role       INTEGER      NOT NULL,
    locked     boolean      NOT NULL,
    enabled    boolean      NOT NULL
);