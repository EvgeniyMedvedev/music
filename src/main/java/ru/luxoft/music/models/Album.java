package ru.luxoft.music.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Album.
 *
 * @author Evgeniy_Medvedev
 */
@Entity
@Table(name = "albums")
public class Album {

    @Id
    private UUID albumId;

    private String albumName;

    @OneToOne(mappedBy = "album")
    private Song song;

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public UUID getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                '}';
    }

    @PrePersist
    public void generateId(){
        albumId = UUID.randomUUID();
    }
}