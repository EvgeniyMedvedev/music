package ru.luxoft.music.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Song.
 *
 * @author Evgeniy_Medvedev
 */
@Entity
@Table(name = "songs")
public class Song {

    @Id
    private UUID songId;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "singerId")
    private Singer singer;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "albumId")
    private Album album;

    private String title;

    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    public UUID getSongId() {
        return songId;
    }

    public Singer getSinger() {
        return singer;
    }

    public Song setSinger(Singer singer) {
        this.singer = singer;

        return this;
    }

    public String getTitle() {
        return title;
    }

    public Song setTitle(String title) {
        this.title = title;

        return this;
    }

    public Album getAlbum() {
        return album;
    }

    public Song setAlbum(Album album) {
        this.album = album;

        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Song setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;

        return this;
    }

    public Genre getGenre() {
        return genre;
    }

    public Song setGenre(Genre genre) {
        this.genre = genre;

        return this;
    }

    public void update(Song song) {
        this.singer = song.singer;
        this.title = song.title;
        this.album = song.album;
        this.releaseDate = song.releaseDate;
        this.genre = song.genre;
    }

    @PrePersist
    private void generateId(){
        this.songId = UUID.randomUUID();
        System.out.println("random ID - " + this.songId);
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