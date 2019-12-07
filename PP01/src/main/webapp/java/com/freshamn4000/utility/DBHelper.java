package com.freshamn4000.utility;

import com.freshamn4000.models.Registration;
import com.freshamn4000.models.User;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static DBHelper dbHelper;
    private DBHelper() {}

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            return new DBHelper();
        }
        return dbHelper;
    }

    public Connection getConnection(String driverName, String connectionURL, String login, String pass) throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        return DriverManager.getConnection(connectionURL, login, pass);
    }
    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Registration.class);
        return configuration;
    }
}