package com.freshamn4000.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface represents DAO methods.
 * @param <T> entity Type-parameter.
 * @param <E> entity-id Type-parameter.
 */
public interface UserDAO<T, E> {
    List<T> findAllUsers() throws SQLException;

    void addUser(T user) throws SQLException;

    void deleteUser(E userId) throws SQLException;

    void updateUser(E userId, T user) throws SQLException;
}