package ru.luxoft.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.luxoft.music.models.Singer;
import ru.luxoft.music.models.Song;

import java.util.List;
import java.util.UUID;

/**
 * SongRepository.
 *
 * @author Evgeniy_Medvedev
 */
@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {

    @Query(nativeQuery = true, value = "select s.song_id, s.title, s.release_date, s.album_id, s.singer_id, s.genre " +
            "from songs s join singers si using(singer_id) where title = :title and si.singer_name = :singer")
    Song findBySingerNameAndTitle(String singer, String title);

    Song findBySinger(Singer singer);

//    Song searchById(UUID uuid);

    Song findByTitle(String title);

    @Query(nativeQuery = true, value =
            "select s.song_id, s.title, s.release_date, s.album_id, s.singer_id, s.genre , si.singer_name, alb.album_name " +
            "from songs s join singers si using(singer_id) join albums alb using(album_id)" +
            "where CONCAT(s.song_id, s.title, s.release_date, s.genre, si.singer_name, alb.album_name)" +
            "ILIKE CONCAT('%', :parameter, '%')")
    List<Song> takeBySomething(String parameter);
}