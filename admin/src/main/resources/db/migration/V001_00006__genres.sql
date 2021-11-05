CREATE TABLE IF NOT EXISTS genres
(
    id         serial PRIMARY KEY NOT NULL,
    genre_name VARCHAR(80)        NOT NULL
);

CREATE TABLE IF NOT EXISTS movie_genres
(
    movie_id INT,
    genre_id INT,
    CONSTRAINT movies_genres_movie_id_fk FOREIGN KEY (movie_id) REFERENCES movies (id),
    CONSTRAINT movies_genres_genre_id_fk FOREIGN KEY (genre_id) REFERENCES genres (id)
);