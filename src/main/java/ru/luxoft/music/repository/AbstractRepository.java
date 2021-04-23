package ru.luxoft.music.repository;

import ru.luxoft.music.models.Song;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * AbstractRepository.
 *
 * @param <T> some entity
 *
 * @author Evgeniy_Medvedev
 */
public abstract class AbstractRepository<T> {

    public abstract Song save(T t);

    public abstract Optional<T> findById(T t);

    public abstract List<T> getAll();

    public abstract Optional<Song> findById(UUID uuid);

    public abstract Optional<T> update(T t);

    public abstract void delete(T t);

    public abstract void delete(UUID uuid);
}