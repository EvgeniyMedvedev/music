package ru.luxoft.music;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ru.luxoft.music.controller.CrudController;
import ru.luxoft.music.models.Album;
import ru.luxoft.music.models.Genre;
import ru.luxoft.music.models.Singer;
import ru.luxoft.music.models.Song;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MusicApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CrudController crudController;

    @Test
    void contextLoads() {
        assertNotNull(crudController);
    }

    @Test
    void createTest() {
        Song song = new Song();
        Singer singer = new Singer();
        Album album = new Album();
        album.setAlbumName("unknown");
        singer.setSingerName("System of a Down");

        song.setGenre(Genre.ROCK);
        song.setAlbum(album);
        song.setSinger(singer);
        song.setTitle("Soldier Side");
        song.setReleaseDate(LocalDate.of(2021, 4, 2));

        ResponseEntity<UUID> response = testRestTemplate.postForEntity(URI.create(url() + "/create"), song, UUID.class);
        assertNotNull(response);
    }

    private String url() {
        return "http://localhost:" + port;
    }

}
