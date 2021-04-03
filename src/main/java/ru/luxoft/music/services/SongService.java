package ru.luxoft.music.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(SongService.class);

    @Autowired
    private AbstractRepository<Song> songRepository;

    @Override
    public UUID create(Song song) {
        log.info("Song is being created by rest request {}", song);
        return songRepository.add(song);
    }

    @Override
    public Song get(UUID uuid) {
        return songRepository.get(uuid).orElseThrow();
    }

    @Override
    public List<Song> getAll() {
        List<Song> songs = songRepository.getAll();
        log.info("Repository contain {} elements", songs.size());
        return songs;
    }

    @Override
    public boolean update(Song song) {
        boolean isAbsent = songRepository.get(song).orElse(null) == null;
        if (!isAbsent)
            songRepository.update(song);
        log.info("Song is absent is {}", isAbsent);
        return isAbsent;
    }

    @Override
    public boolean delete(Song song) {
        boolean isAbsent = songRepository.get(song).orElse(null) == null;
        if (!isAbsent)
            songRepository.delete(song);
        log.info("Song is absent is {}", isAbsent);
        return isAbsent;
    }
}