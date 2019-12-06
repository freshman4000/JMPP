package com.freshamn4000.utility;

import com.freshamn4000.models.User;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    public static Connection getConnection(String driverName, String connectionURL, String login, String pass) throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        return DriverManager.getConnection(connectionURL, login, pass);
    }
    public static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration
                .addAnnotatedClass(User.class);
        return configuration;
    }
}