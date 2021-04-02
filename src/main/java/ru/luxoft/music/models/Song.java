package ru.luxoft.music.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Song.
 *
 * @author Evgeniy_Medvedev
 */
public class Song {

    public Song(){
        songId = UUID.randomUUID();
    }

    private UUID songId;

    private Singer singer;

    private String title;

    private Album album;

    private LocalDate releaseDate;

    private Genre genre;

    public UUID getSongId() {
        return songId;
    }

    public void setSongId(UUID songId) {
        this.songId = songId;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void update(Song song){
        this.singer = song.singer;
        this.title = song.title;
        this.album = song.album;
        this.releaseDate = song.releaseDate;
        this.genre = song.genre;
    }

    @Override
    public String toString() {
        return "Song{" +
                "song_id=" + songId +
                ", singer=" + singer +
                ", title='" + title + '\'' +
                ", album=" + album +
                ", releaseDate=" + releaseDate +
                ", genre=" + genre +
                '}';
    }
}