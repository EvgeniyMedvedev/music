package ru.luxoft.music.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Singer.
 *
 * @author Evgeniy_Medvedev
 */
@Entity
@Table(name = "singers")
public class Singer {

    @Id
    private UUID singerId;

    @OneToOne(mappedBy = "singer")
    private Song song;

    private String singerName;

    public UUID getSingerId() {
        return singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "singerId=" + singerId +
                ", singerName='" + singerName + '\'' +
                '}';
    }

    @PrePersist
    public void generateId(){
        singerId = UUID.randomUUID();
    }
}