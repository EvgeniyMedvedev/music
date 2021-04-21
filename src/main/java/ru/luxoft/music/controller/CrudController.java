package ru.luxoft.music.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.luxoft.music.models.Song;
import ru.luxoft.music.services.AbstractService;

import java.util.List;
import java.util.UUID;

/**
 * CrudController.
 *
 * @author Evgeniy_Medvedev
 */
@RestController
public class CrudController {

    //run on web, tests, logs, repo
    @Autowired
    private AbstractService<Song> songService;

    @GetMapping("/get/{uuid}")
    public Song getSong(@RequestParam UUID uuid) {
        return songService.get(uuid);
    }

    @GetMapping("/getAll")
    public List<Song> getAll() {
        return songService.getAll();
    }

    @PostMapping("/create")
    public UUID createSong(@RequestBody Song song) {
        return songService.create(song);
    }

    @PostMapping("/update")
    public boolean updateSong(@RequestBody Song song) {
        return songService.update(song);
    }
}