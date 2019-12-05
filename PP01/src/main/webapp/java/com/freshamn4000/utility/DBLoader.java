package com.freshamn4000.utility;

import com.freshamn4000.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBLoader {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    @SuppressWarnings("UnusedDeclaration")
    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration
                .addAnnotatedClass(User.class);
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    public static Connection getConnection(String driverName, String connectionURL, String login, String pass) throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        return DriverManager.getConnection(connectionURL, login, pass);
    }
}