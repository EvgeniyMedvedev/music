package ru.luxoft.music.repository;

import org.springframework.stereotype.Repository;
import ru.luxoft.music.models.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * SongRepository.
 *
 * @author Evgeniy_Medvedev
 */
@Repository
public class SongInnerCollectionRepository extends AbstractRepository<Song> {

    private final ArrayList<Song> songs;

    public SongInnerCollectionRepository() {
        this.songs = new ArrayList<>();
    }

    @Override
    public Song save(Song song) {
        songs.add(song);
        return song;
    }

    @Override
    public Optional<Song> findById(Song song) {
        return songs.stream().filter(each -> each.equals(song)).findFirst();
    }


    @Override
    public List<Song> getAll() {
        return List.copyOf(songs);
    }


    @Override
    public Optional<Song> findById(UUID uuid) {
        return songs.stream().filter(each -> each.getSongId().equals(uuid)).findFirst();
    }

    @Override
    public Optional<Song> update(Song song) {
        songs.forEach(each -> {
            if (each.getSongId().equals(song.getSongId())) {
                each.update(song);
            }
        });
        return findById(song);
    }

    @Override
    public void delete(Song song) {
        songs.remove(song);
    }

    @Override
    public void delete(UUID uuid) {
        songs.remove(findById(uuid).orElseThrow());
    }
}