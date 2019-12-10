package com.freshamn4000.dao;

import com.freshamn4000.interfaces.ClientService;

public abstract class DAOFactory<T, E> {
    public abstract ClientService<T, E> getDAO();

    public abstract ClientService<T, E> createDAO();
}