/* client generation */
INSERT INTO clients (client_name) VALUES ('clientest');
INSERT INTO clients (client_name) VALUES ('second test client');

/* user generation for LISTENERS */
INSERT INTO users (age, main_genre, real_name, user_type) VALUES (27, 'pop', 'Joe', 2);
INSERT INTO users (age, main_genre, real_name, user_type) VALUES (43, 'rap', 'Jae', 2);
INSERT INTO users (age, main_genre, real_name, user_type) VALUES (28, 'country', 'John', 2);
INSERT INTO users (age, main_genre, real_name, user_type) VALUES (15, 'pop', 'Arthur', 2);
INSERT INTO users (age, main_genre, real_name, user_type) VALUES (25, 'classical', 'Joe', 2);
INSERT INTO users (age, main_genre, real_name, user_type) VALUES (53, 'country', 'Josephina', 2);
INSERT INTO users (age, main_genre, real_name, user_type) VALUES (20, 'metal', 'Megan', 2);
INSERT INTO users (age, main_genre, real_name, user_type) VALUES (27, 'techno', 'Helen', 2);
INSERT INTO users (age, main_genre, real_name, user_type) VALUES (46, 'country', 'Liag', 2);
INSERT INTO users (age, main_genre, real_name, user_type) VALUES (40, 'classical', 'Alexandra', 2);

/* playlist generation */
INSERT INTO playlists (is_default, name, owner) VALUES (False, 'CS4LYFELoFI',1);
INSERT INTO playlists (is_default, name, owner) VALUES (False, 'ASE Stress Reliever',2);

INSERT INTO playlist_to_subscriber (playlist_id, user_id) VALUES (1,1);
INSERT INTO songs (song_artist, song_duration, song_likes_count, song_lyrics, song_name) VALUES ('Chief Keef', 150, 100000, 'love sosa', 'love sosa');
INSERT INTO playlist_to_songs (playlist_id, song_id) VALUES (1, 1);