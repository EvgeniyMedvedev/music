package ru.luxoft.music.repository;

import ru.luxoft.music.models.Song;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * AbstractRepository.
 *
 * @author Evgeniy_Medvedev
 */
public abstract class AbstractRepository<T> {

    public abstract UUID add(T t);

    public abstract Optional<T> get(T t);

    public abstract List<T> getAll();

    public abstract Optional<Song> get(UUID uuid);

    public abstract Optional<T> update(T t);

    public abstract void delete(T t);

    public abstract void delete(UUID uuid);
}