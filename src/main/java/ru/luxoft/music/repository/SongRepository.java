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
public class SongRepository extends AbstractRepository<Song> {

    private final ArrayList<Song> songs;

    public SongRepository() {
        this.songs = new ArrayList<>();
    }

    @Override
    public UUID add(Song song) {
        songs.add(song);
        return song.getSongId();
    }

    @Override
    public Optional<Song> get(Song song) {
        return songs.stream().filter(each -> each.equals(song)).findFirst();
    }


    @Override
    public List<Song> getAll() {
        return List.copyOf(songs);
    }


    @Override
    public Optional<Song> get(UUID uuid) {
        return songs.stream().filter(each -> each.getSongId().equals(uuid)).findFirst();
    }

    @Override
    public Optional<Song> update(Song song) {
        songs.forEach(each -> {
            if (each.getSongId().equals(song.getSongId())) {
                each.update(song);
            }
        });
        return get(song);
    }

    @Override
    public void delete(Song song) {
        songs.remove(song);
    }

    @Override
    public void delete(UUID uuid) {
        songs.remove(get(uuid));
    }
}