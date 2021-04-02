package ru.luxoft.music.models;

import java.util.UUID;

/**
 * Singer.
 *
 * @author Evgeniy_Medvedev
 */
public class Singer {

    private UUID singerId;

    public Singer(){
        singerId = UUID.randomUUID();
    }

    private String singerName;

    public UUID getSingerId() {
        return singerId;
    }

    public void setSingerId(UUID singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "singerId=" + singerId +
                ", singerName='" + singerName + '\'' +
                '}';
    }
}