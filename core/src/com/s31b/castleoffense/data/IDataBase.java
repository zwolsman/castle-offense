package com.s31b.castleoffense.data;

import java.util.List;

/**
 *
 * @author Shaikun
 */
public interface IDataBase {

    public <T> List<T> getAll(Class<T> type);

    public <T> void insert(T object);

    public <T> void update(T object, T newObject);

    public <T> void delete(T object);
}
