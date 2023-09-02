package com.ivanart555.cleverbank.utils.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class SQLStatements {
    private static final Logger logger = LoggerFactory.getLogger(SQLStatements.class);
    private static final String SQL_STATEMENTS_FILE = "/sqlStatements.yml";
    private static final Map<String, Map<String, String>> sqlStatements = loadSQLStatements();

    private static Map<String, Map<String, String>> loadSQLStatements() {
        try (InputStream inputStream = SQLStatements.class.getResourceAsStream(SQL_STATEMENTS_FILE)) {
            if (inputStream != null) {
                Yaml yaml = new Yaml();
                return yaml.load(inputStream);
            } else {
                logger.error("Failed to load sql statements from " + SQL_STATEMENTS_FILE + " file. The file may not exist.");
                return null;
            }
        } catch (IOException e) {
            logger.error("Failed to read sql statements from " + SQL_STATEMENTS_FILE + " file due to an IO error: " + e.getMessage());
            return null;
        }
    }

    public static String getValue(String category, String statementKey) {
        if (sqlStatements != null) {
            Map<String, String> categorySQL = sqlStatements.get(category);
            if (categorySQL != null) {
                return categorySQL.get(statementKey);
            }
        }
        throw new IllegalArgumentException("SQL statement not found for category: " + category + ", statementKey: " + statementKey);
    }
}