package ru.luxoft.music.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.luxoft.music.models.Singer;
import ru.luxoft.music.models.Song;

import java.util.UUID;

/**
 * SongRepository.
 *
 * @author Evgeniy_Medvedev
 */
@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {

    @Query(nativeQuery = true, value = "select s.songId, s.title, s.releaseDate, s.albumId, s.singerId, s.genre " +
            "from songs s join singers si using(singerId) where title = :title and si.singerName = :singer")
    Song findBySingerNameAndTitle(String singer, String title);

    Song findBySinger(Singer singer);

    Song findByTitle(String title);

    @Query(nativeQuery = true, value = "update singers set")
    void update(Song song);
}