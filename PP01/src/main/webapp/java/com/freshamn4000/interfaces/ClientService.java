package com.freshamn4000.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface ClientService<T, E> {
    public List<T> showAllUsers() throws SQLException;

    public void addUser(T entity) throws SQLException;

    public void deleteUser(E field) throws SQLException;

    public void updateUser(E field, T entity) throws SQLException;
}
