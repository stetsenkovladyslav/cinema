ALTER TABLE movies
    ADD COLUMN create_at   TIMESTAMP DEFAULT NOW() NOT NULL,
    ADD COLUMN update_at   TIMESTAMP DEFAULT NOW() NOT NULL;

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