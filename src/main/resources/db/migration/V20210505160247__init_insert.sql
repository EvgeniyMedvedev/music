INSERT INTO albums(album_name)
VALUES ('Mezmerize');
INSERT INTO singers(singer_name)
VALUES ('System of a Down');
INSERT INTO songs(genre, release_date, title, album_id, singer_id)
SELECT 'ROCK', '2021-04-02', 'Soldier Side', album_id, singer_id
FROM albums
         JOIN singers ON true
WHERE singer_name = 'System of a Down'
  AND album_name = 'Mezmerize';