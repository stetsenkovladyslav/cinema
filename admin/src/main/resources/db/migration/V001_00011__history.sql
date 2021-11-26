CREATE TABLE IF NOT EXISTS history
(
    user_id  INT,
    movie_id INT,
    CONSTRAINT history_user_id_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT history_movie_id_fk FOREIGN KEY (movie_id) REFERENCES movies (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    UNIQUE (user_id, movie_id)
);