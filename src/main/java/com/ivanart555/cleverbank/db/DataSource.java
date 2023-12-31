package com.ivanart555.cleverbank.db;

import com.ivanart555.cleverbank.utils.config.ConfigLoader;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DataSource {
    private static final HikariDataSource dataSource;
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(ConfigLoader.getValue("dbUrl"));
        config.addDataSourceProperty(USER, ConfigLoader.getValue(USER));
        config.addDataSourceProperty(PASSWORD, ConfigLoader.getValue(PASSWORD));

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}