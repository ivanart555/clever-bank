package com.ivanart555.cleverbank.services.generic;

import com.ivanart555.cleverbank.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, K extends Serializable> {

    List<T> getAll() throws ServiceException;

    T getById(K id) throws ServiceException;

    void delete(K id) throws ServiceException;

    void update(T t) throws ServiceException;

    void create(T t) throws ServiceException;

}