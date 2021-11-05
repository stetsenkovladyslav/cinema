ALTER TABLE movies
    ADD COLUMN create_at   TIMESTAMP DEFAULT NOW() NOT NULL,
    ADD COLUMN update_at   TIMESTAMP DEFAULT NOW() NOT NULL,
    ADD COLUMN image_id    INT,
    ADD COLUMN video_id    INT,
    ADD COLUMN genre_id    INT,
    ADD COLUMN country_id  INT,
    ADD COLUMN director_id INT,
    ADD CONSTRAINT movies_movie_image_fk FOREIGN KEY (image_id) REFERENCES movies (id),
    ADD CONSTRAINT movies_movie_video_fk FOREIGN KEY (video_id) REFERENCES movies (id),
    ADD CONSTRAINT movies_movie_genre_fk FOREIGN KEY (genre_id) REFERENCES movies (id),
    ADD CONSTRAINT movies_movie_country_fk FOREIGN KEY (country_id) REFERENCES movies (id),
    ADD CONSTRAINT movies_movie_director_fk FOREIGN KEY (director_id) REFERENCES movies (id);

ALTER TABLE users
    ADD COLUMN create_at TIMESTAMP DEFAULT NOW() NOT NULL,
    ADD COLUMN update_at TIMESTAMP DEFAULT NOW() NOT NULL;

ALTER TABLE videos
    ADD COLUMN create_at TIMESTAMP DEFAULT NOW() NOT NULL,
    ADD COLUMN update_at TIMESTAMP DEFAULT NOW() NOT NULL;

ALTER TABLE countries
    ADD COLUMN create_at TIMESTAMP DEFAULT NOW() NOT NULL,
    ADD COLUMN update_at TIMESTAMP DEFAULT NOW() NOT NULL;

ALTER TABLE genres
    ADD COLUMN create_at TIMESTAMP DEFAULT NOW() NOT NULL,
    ADD COLUMN update_at TIMESTAMP DEFAULT NOW() NOT NULL;

ALTER TABLE directors
    ADD COLUMN create_at TIMESTAMP DEFAULT NOW() NOT NULL,
    ADD COLUMN update_at TIMESTAMP DEFAULT NOW() NOT NULL;

ALTER TABLE images
    ADD COLUMN create_at TIMESTAMP DEFAULT NOW() NOT NULL,
    ADD COLUMN update_at TIMESTAMP DEFAULT NOW() NOT NULL;