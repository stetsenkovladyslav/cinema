CREATE TABLE IF NOT EXISTS directors
(
    id         serial PRIMARY KEY,
    director_name VARCHAR (80) NOT NULL

);

CREATE TABLE IF NOT EXISTS movies_directors
(
    movie_id   INT,
    director_id INT,
    CONSTRAINT movies_directors_movie_id_fk FOREIGN KEY (movie_id) REFERENCES movies (id),
    CONSTRAINT movies_directors_director_id_fk FOREIGN KEY (director_id) REFERENCES directors (id)
);