ALTER TABLE IF EXISTS movie_videos
    DROP CONSTRAINT IF EXISTS movies_videos_movie_id_fk,
    DROP CONSTRAINT IF EXISTS movies_videos_video_id_fk;

ALTER TABLE IF EXISTS movie_images
    DROP CONSTRAINT IF EXISTS movies_images_movie_id_fk,
    DROP CONSTRAINT IF EXISTS movies_images_video_id_fk;

DROP TABLE movie_videos;

DROP TABLE movie_images;

ALTER TABLE IF EXISTS videos
    ADD COLUMN movie_id INT,
    ADD CONSTRAINT videos_movie_id_fk FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE IF EXISTS images
    ADD COLUMN movie_id INT,
    ADD CONSTRAINT images_movie_id_fk FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE ON UPDATE CASCADE;


ALTER TABLE IF EXISTS history
    DROP CONSTRAINT IF EXISTS history_movie_id_fk,
    ADD CONSTRAINT history_movie_id_fk FOREIGN KEY (movie_id) REFERENCES movies (id) ON DELETE CASCADE ON UPDATE CASCADE;