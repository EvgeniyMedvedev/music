package ru.luxoft.music.factories;

import ru.luxoft.music.services.AbstractService;

/**
 * Factory.
 *
 * @author Evgeniy_Medvedev
 */
public interface Factory<T> {

    AbstractService<T> getService();


}