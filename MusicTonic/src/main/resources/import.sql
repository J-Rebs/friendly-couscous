/* client generation */
INSERT INTO clients (client_name, login_reference) VALUES ('clientest',1);
INSERT INTO clients (client_name, login_reference) VALUES ('second test client',2);

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

/* all users but second belong to first client*/
INSERT INTO client_user (client_id, user_id) VALUES (1,1);
INSERT INTO client_user (client_id, user_id) VALUES (1,3);
INSERT INTO client_user (client_id, user_id) VALUES (1,4);
INSERT INTO client_user (client_id, user_id) VALUES (1,5);
INSERT INTO client_user (client_id, user_id) VALUES (1,6);
INSERT INTO client_user (client_id, user_id) VALUES (1,7);
INSERT INTO client_user (client_id, user_id) VALUES (1,8);
INSERT INTO client_user (client_id, user_id) VALUES (1,9);
INSERT INTO client_user (client_id, user_id) VALUES (1,10);

INSERT INTO client_user (client_id, user_id) VALUES (2,2);

/* playlist and song generation */
INSERT INTO playlists (is_default, name, owner) VALUES (False, 'CS4LYFELoFI',1);
INSERT INTO playlists (is_default, name, owner) VALUES (False, 'ASE Stress Reliever',2);
INSERT INTO playlists (is_default, name, owner) VALUES (False, 'Its all bytecode to me bebe',3);


INSERT INTO playlist_to_subscriber (playlist_id, user_id) VALUES (1,1);
INSERT INTO playlist_to_subscriber (playlist_id, user_id) VALUES (3,3);

INSERT INTO songs (song_artist, song_duration, song_likes_count, song_lyrics, song_name) VALUES ('Chief Keef', 150, 50, 'love sosa', 'love sosa');
INSERT INTO songs (song_artist, song_duration, song_likes_count, song_lyrics, song_name) VALUES ('SportsArtists', 150, 10, 'winning', 'winning is life');
INSERT INTO songs (song_artist, song_duration, song_likes_count, song_lyrics, song_name) VALUES ('YourGuy', 150, 20, 'everything is awesome', 'everything is awesome');
INSERT INTO songs (song_artist, song_duration, song_likes_count, song_lyrics, song_name) VALUES ('Top4', 150, 2, '$$$', 'Haha');

INSERT INTO client_song (client_id, song_id) VALUES (1,1);
INSERT INTO client_song (client_id, song_id) VALUES (1,2);
INSERT INTO client_song (client_id, song_id) VALUES (1,3);

INSERT INTO client_song (client_id, song_id) VALUES (2,1);
INSERT INTO client_song (client_id, song_id) VALUES (2,2);
INSERT INTO client_song (client_id, song_id) VALUES (2,3);

INSERT INTO client_playlist (client_id, playlist_id) VALUES (1,1);
INSERT INTO client_playlist (client_id, playlist_id) VALUES (1,2);

INSERT INTO playlist_to_songs (playlist_id, song_id) VALUES (1, 1);
INSERT INTO playlist_to_songs (playlist_id, song_id) VALUES (1, 2);
INSERT INTO playlist_to_songs (playlist_id, song_id) VALUES (1, 3);

