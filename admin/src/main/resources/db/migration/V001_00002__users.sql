CREATE TABLE IF NOT EXISTS users
(
    id         serial primary key,
    first_name VARCHAR(80)        NOT NULL,
    last_name  VARCHAR(80)        NOT NULL,
    username   VARCHAR(24) UNIQUE NOT NULL,
    password   VARCHAR(64)        NOT NULL,
    role       INTEGER            NOT NULL,
    locked     boolean            NOT NULL,
    enabled    boolean            NOT NULL,
    code       VARCHAR(200),
    email      varchar(255)
);

INSERT INTO users (id, first_name, last_name, username, password,
                   role, locked, enabled, code, email)
VALUES (99, 'cinema', 'administrator', 'admin', '$2a$10$HzdgAZCLtMcjnrtOYn2PNu0I7R4Lea8R6DlP2ZhZwxap6jZy5DXmu',
        1, false, true, null, 'cinema.root.root@gmail.com');