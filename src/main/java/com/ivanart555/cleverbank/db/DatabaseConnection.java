package com.ivanart555.cleverbank.db;

import com.ivanart555.cleverbank.utils.config.ConfigLoader;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class DatabaseConnection {
    private static final HikariDataSource dataSource;
    private static final String SERVER_NAME = "serverName";
    private static final String PORT_NUMBER = "portNumber";
    private static final String DATABASE_NAME = "databaseName";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    static {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(ConfigLoader.getValue("dataSourceClassName"));
        config.addDataSourceProperty(SERVER_NAME, ConfigLoader.getValue(SERVER_NAME));
        config.addDataSourceProperty(PORT_NUMBER, ConfigLoader.getValue(PORT_NUMBER));
        config.addDataSourceProperty(DATABASE_NAME, ConfigLoader.getValue(DATABASE_NAME));
        config.addDataSourceProperty(USER, ConfigLoader.getValue(USER));
        config.addDataSourceProperty(PASSWORD, ConfigLoader.getValue(PASSWORD));

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}