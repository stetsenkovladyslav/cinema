CREATE TABLE IF NOT EXISTS images
(
    id         serial PRIMARY KEY NOT NULL,
    image_name VARCHAR(80)        NOT NULL,
    format int NOT NULL

);

CREATE TABLE IF NOT EXISTS movie_images
(
    movie_id INT,
    image_id INT,
    CONSTRAINT movie_images_movie_id_fk FOREIGN KEY (movie_id) REFERENCES movies (id),
    CONSTRAINT movie_images_image_id_fk FOREIGN KEY (image_id) REFERENCES images (id)
);