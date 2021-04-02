package ru.luxoft.music.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.luxoft.music.models.Song;
import ru.luxoft.music.repository.AbstractRepository;

import java.util.List;
import java.util.UUID;

/**
 * SongService.
 *
 * @author Evgeniy_Medvedev
 */
@Service
public class SongService implements AbstractService<Song> {

    @Autowired
    private AbstractRepository<Song> songRepository;

    @Override
    public UUID create(Song song) {
        return songRepository.add(song);
    }

    @Override
    public Song get(UUID uuid) {
        return songRepository.get(uuid).orElseThrow();
    }

    @Override
    public List<Song> getAll(){
        return songRepository.getAll();
    }

    @Override
    public boolean update(Song song) {
        boolean isAbsent = songRepository.get(song).orElse(null) == null;
        if (!isAbsent)
            songRepository.update(song);

        return isAbsent;
    }

    @Override
    public boolean delete(Song song) {
        boolean isAbsent = songRepository.get(song).orElse(null) == null;
        if (!isAbsent)
            songRepository.delete(song);

        return isAbsent;
    }
}