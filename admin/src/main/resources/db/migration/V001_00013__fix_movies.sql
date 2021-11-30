ALTER TABLE IF EXISTS movie_genres
    DROP CONSTRAINT IF EXISTS  movies_genres_movie_id_fk,
    DROP CONSTRAINT IF EXISTS movies_genres_genre_id_fk;

ALTER TABLE IF EXISTS movie_countries
    DROP CONSTRAINT IF EXISTS movie_countries_movie_id_fk,
    DROP CONSTRAINT IF EXISTS movie_countries_country_id_fk;

DROP TABLE genres;

DROP TABLE movie_genres;

DROP TABLE countries;

DROP TABLE movie_countries;

ALTER TABLE movies
    ADD COLUMN genre VARCHAR (64),
ADD COLUMN country VARCHAR (64);

