package com.freshamn4000.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface represents clients service methods.
 * @param <T> entity Type-parameter.
 * @param <E> entity-id Type-parameter.
 */
public interface ClientService<T, E> {
    List<T> showAllUsers() throws SQLException;

    Long addUser(T entity) throws SQLException;

    void deleteUser(E field) throws SQLException;

    void updateUser(E field, T entity) throws SQLException;

    void setPassword(E field, String password, T entity) throws SQLException;

    void updatePassword(Long field, String password) throws SQLException;

    boolean validateRegistration(String email) throws SQLException;

    boolean validateLogin(String email, String password) throws SQLException;

    boolean validateRole(String email) throws SQLException;
}
