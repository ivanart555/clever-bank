package com.ivanart555.cleverbank.dao.generic;

import com.ivanart555.cleverbank.exception.DAOException;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

public interface GenericDAO<T, K extends Serializable> {

    List<T> getAll(Connection connection) throws DAOException;

    T getById(K id, Connection connection) throws DAOException;

    void create(T t, Connection connection) throws DAOException;

    void update(T t, Connection connection) throws DAOException;

    void delete(K id, Connection connection) throws DAOException;

}