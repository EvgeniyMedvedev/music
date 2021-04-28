package ru.luxoft.music;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jms.core.JmsTemplate;
import ru.luxoft.music.controller.CrudController;
import ru.luxoft.music.models.Album;
import ru.luxoft.music.models.Genre;
import ru.luxoft.music.models.Singer;
import ru.luxoft.music.models.Song;
import ru.luxoft.music.repository.SongRepository;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MusicApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private JmsTemplate template;

    @Autowired
    private CrudController crudController;

    @BeforeEach
    public void step(){
        songRepository.deleteAll();
    }
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

        template.convertAndSend("java:jms/queue/music/create", song);
        Object answer = template.receiveAndConvert("java:jms/queue/music/create-answer");

        assertNotNull(answer);
        assertEquals(answer.getClass(), UUID.class);
    }

    @Test
    void updateTest() {
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

        songRepository.save(song);

        album.setAlbumName("Memorize");
        song.setAlbum(album);

        template.convertAndSend("java:jms/queue/music/update", song);
        Object o = template.receiveAndConvert("java:jms/queue/music/update-answer");
        assertNotNull(o);
        boolean isAbsent = (boolean) o;
        assertFalse(isAbsent);

        assertEquals(album.getAlbumName(), songRepository.findById(song.getSongId()).orElseThrow().getAlbum().getAlbumName());
    }

    @Test
    void deleteTest() {
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

        songRepository.save(song);

        album.setAlbumName("Memorize");
        song.setAlbum(album);

        template.convertAndSend("java:jms/queue/music/delete", song);
        //for waiting
        template.receiveAndConvert("java:jms/queue/music/delete-answer");

        assertNull(songRepository.findById(song.getSongId()).orElse(null));
    }

    private String url() {
        return "http://localhost:" + port;
    }

}
