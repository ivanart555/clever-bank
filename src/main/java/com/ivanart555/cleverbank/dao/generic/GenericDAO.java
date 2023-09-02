package com.ivanart555.cleverbank.dao.generic;

import com.ivanart555.cleverbank.exception.DAOException;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, K extends Serializable> {

    List<T> getAll() throws DAOException;

    T getById(K id) throws DAOException;

    void create(T t) throws DAOException;

    void update(T t) throws DAOException;

    void delete(K id) throws DAOException;

}