package ru.luxoft.music.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import ru.luxoft.music.models.Song;
import ru.luxoft.music.services.AbstractService;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * CrudController.
 *
 * @author Evgeniy_Medvedev
 */
@Component
public class CrudController {

    private final AbstractService<Song> songService;

    private final Logger logg = Logger.getLogger(CrudController.class.getName());

    public CrudController(AbstractService<Song> songService) {
        this.songService = songService;
    }

    @JmsListener(destination = "java:jms/queue/music/askedBy")
    @SendTo("java:jms/queue/music/askedBy-answer")
    public List<Song> getSong(String parameter) {
        if (parameter.equals("findAll")) {
            return songService.getAll();
        }
        return songService.takeBySomething(parameter);
    }

    @JmsListener(destination = "java:jms/queue/music/create")
    @SendTo("java:jms/queue/music/create-answer")
    public UUID createSong(Song song) {
        logg.info("song is being created - " + song);
        return songService.create(song);
    }

    @JmsListener(destination = "java:jms/queue/music/update")
    @SendTo("java:jms/queue/music/update-answer")
    public boolean updateSong(Song song) {
        logg.info(String.format("Song %s is being updated", song));
        return songService.update(song);
    }

    @JmsListener(destination = "java:jms/queue/music/delete")
    @SendTo("java:jms/queue/music/delete-answer")
    public boolean deleteSong(Song song) {
        logg.info(String.format("Song %s is being deleted", song));
        return songService.delete(song);
    }
}