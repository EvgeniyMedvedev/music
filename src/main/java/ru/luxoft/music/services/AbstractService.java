package ru.luxoft.music.services;

import ru.luxoft.music.models.Song;

import java.util.List;
import java.util.UUID;

/**
 * AbstractService.
 *
 * @author Evgeniy_Medvedev
 */
public interface AbstractService<T> {

    UUID create(T t);

    T get(UUID uuid);

    List<T> getAll();

    boolean update(T t);

    boolean delete(T t);

    List<Song> takeBySomething(String parameter);
}