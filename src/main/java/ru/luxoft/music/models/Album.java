package ru.luxoft.music.models;

import java.util.UUID;

/**
 * Album.
 *
 * @author Evgeniy_Medvedev
 */
public class Album {

    private UUID albumId;

    private String albumName;

    public UUID getAlbumId() {
        return albumId;
    }

    public void setAlbumId(UUID albumId) {
        this.albumId = albumId;
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
}