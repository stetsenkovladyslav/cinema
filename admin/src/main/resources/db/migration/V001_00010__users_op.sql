CREATE TABLE IF NOT EXISTS favorite_films
(
    movie_id INT,
    user_id  INT,
    CONSTRAINT favorite_films_movie_id_fk FOREIGN KEY (movie_id) REFERENCES movies (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT favorite_films_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE
);