CREATE TABLE IF NOT EXISTS countries
(
    id         serial PRIMARY KEY NOT NULL,
    country_name VARCHAR(80)        NOT NULL
);

CREATE TABLE IF NOT EXISTS movie_countries
(
    movie_id INT,
    country_id INT,
    CONSTRAINT movie_countries_movie_id_fk FOREIGN KEY (movie_id) REFERENCES movies (id),
    CONSTRAINT movie_countries_country_id_fk FOREIGN KEY (country_id) REFERENCES countries (id)
);