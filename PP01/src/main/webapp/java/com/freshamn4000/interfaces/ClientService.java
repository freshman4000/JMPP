package com.freshamn4000.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface represents clients service methods.
 *
 * @param <T> entity Type-parameter.
 * @param <E> entity-id Type-parameter.
 */
public interface ClientService<T, E> {
    List<T> showAllUsers() throws SQLException;

    void addUser(T entity) throws SQLException;

    void deleteUser(E field) throws SQLException;

    void updateUser(E field, T entity) throws SQLException;
}
