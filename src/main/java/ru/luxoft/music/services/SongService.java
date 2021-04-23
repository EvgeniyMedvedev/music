package ru.luxoft.music.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.luxoft.music.models.Genre;
import ru.luxoft.music.models.Song;
import ru.luxoft.music.repository.SongRepository;

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
    private SongRepository songRepository;

    @Override
    public UUID create(Song song) {

        songRepository.save(song);
        return song.getSongId();
    }

    @Override
    public Song get(UUID uuid) {
        return songRepository.findById(uuid).orElseThrow();
    }

    @Override
    public List<Song> getAll() {
        List<Song> songs = songRepository.findAll();
        log.info("Repository contain {} elements", songs.size());
        return songs;
    }

    @Override
    public boolean update(Song song) {
        boolean isAbsent;
        Song founded = null;
        if (song.getSongId() != null) {
            founded = songRepository.findBySingerNameAndTitle(song.getSinger().getSingerName(), song.getTitle());
        } else if (song.getTitle() != null) {
            founded = songRepository.findByTitle(song.getTitle());
        }

        isAbsent = founded == null;

        if (!isAbsent) {
            String title = song.getTitle() != null ? song.getTitle() : founded.getTitle();

            if (founded.getAlbum() != null && song.getAlbum() != null) {
                founded.getAlbum().setAlbumName(song.getAlbum().getAlbumName());
            } else {
                founded.setAlbum(song.getAlbum());
            }

            Genre genre = song.getGenre() != null ? song.getGenre() : founded.getGenre();
            founded.setTitle(title);
            founded.setGenre(genre);

            songRepository.save(song);
        }
        log.info("Song is updated - {}", isAbsent);
        return isAbsent;
    }

    @Override
    public boolean delete(Song song) {
        boolean isAbsent = songRepository.findById(song.getSongId()).isEmpty();
        if (!isAbsent) {
            songRepository.delete(song);
        }
        log.info("Song is absent is {}", isAbsent);
        return isAbsent;
    }

    @Override
    public List<Song> takeBySomething(String parameter) {
        List<Song> songs = songRepository.takeBySomething(parameter);
        log.info("Songs are being given into queue");
        return songs;
    }
}