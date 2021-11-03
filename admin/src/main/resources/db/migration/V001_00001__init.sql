CREATE TABLE IF NOT EXISTS movies
(
    id            serial PRIMARY KEY,
    title         VARCHAR(80)        NOT NULL,
    description   VARCHAR(600)       NOT NULL,
    date_added    DATE DEFAULT NOW() NOT NULL,
    date_release DATE DEFAULT NOW() NOT NULL
);












