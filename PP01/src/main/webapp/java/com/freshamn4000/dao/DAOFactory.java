package com.freshamn4000.dao;

import com.freshamn4000.interfaces.ClientService;

public interface DAOFactory<T, E> {
    ClientService<T, E> createDAO();
}
