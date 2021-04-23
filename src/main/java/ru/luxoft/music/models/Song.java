package ru.luxoft.music.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Song.
 *
 * @author Evgeniy_Medvedev
 */
@Entity
@Table(name = "songs")
//it is for sending list of songs into queue
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "@id",
        scope = Song.class)
public class Song {

    @Id
    @Column(name = "song_id")
    private UUID songId;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "singer_id")
    private Singer singer;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "album_id")
    private Album album;

    private String title;

    @Column(name = "release_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
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
        this.singer.setSingerName(song.singer.getSingerName());
        this.title = song.title;
        this.album.setAlbumName(song.album.getAlbumName());
        this.releaseDate = song.releaseDate;
        this.genre = song.genre;
    }

    @PrePersist
    private void generateId() {
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