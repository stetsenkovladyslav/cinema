CREATE TABLE IF NOT EXISTS rates
(
    id         serial PRIMARY KEY      NOT NULL,
    value      numeric(4, 2)           not null,
    rate_count int                     not null check ( rate_count >= 0 ),
    create_at  TIMESTAMP DEFAULT NOW() NOT NULL,
    update_at  TIMESTAMP DEFAULT NOW() NOT NULL
);

ALTER TABLE movies
    ADD COLUMN rate_id INT,
    ADD constraint rate_id_fk foreign key (rate_id) references rates (id);

CREATE TABLE IF NOT EXISTS comments
(
    id        serial PRIMARY KEY      NOT NULL,
    comment   varchar(2048)           not null,
    create_at TIMESTAMP DEFAULT NOW() NOT NULL,
    update_at TIMESTAMP DEFAULT NOW() NOT NULL
);

CREATE TABLE IF NOT EXISTS movies_comments
(
    movie_id   INT not null,
    comment_id INT not null,
    constraint movies_comments_movie_id_fk foreign key (movie_id) references movies (id),
    constraint movies_comments_comment_id_fk foreign key (comment_id) references comments (id)
);
