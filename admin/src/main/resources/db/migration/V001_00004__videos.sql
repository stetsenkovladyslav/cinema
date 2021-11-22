CREATE TABLE IF NOT EXISTS videos
(
    id         serial PRIMARY KEY NOT NULL,
    video_name VARCHAR(80)        NOT NULL,
    format int NOT NULL
);

CREATE TABLE IF NOT EXISTS movie_videos
(
    movie_id INT,
    video_id INT,
    CONSTRAINT movies_videos_movie_id_fk FOREIGN KEY (movie_id) REFERENCES movies (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT movies_videos_video_id_fk FOREIGN KEY (video_id) REFERENCES videos (id) ON DELETE CASCADE ON UPDATE CASCADE
);