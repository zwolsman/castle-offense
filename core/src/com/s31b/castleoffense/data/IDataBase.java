/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
