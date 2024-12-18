package org.infrostructure.connector

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.infrostructure.connector.utilsconector.Convertor
import org.utiles.EnvReader

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

class SQLite implements SQLitI {

    static final String RESURCE_DB_PATH = EnvReader.getEnvVar("RESURCE_DB_PATH");
    static final String URL_LITE = EnvReader.getEnvVar("URL_LITE") + RESURCE_DB_PATH;
    private static HikariDataSource dataSource;

    static {
        // Setup connection pool configuration
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL_LITE);  // Path to your SQLite DB
        config.setDriverClassName("org.sqlite.JDBC");
        config.setMaximumPoolSize(10);  // Pool size depending on your needs

        dataSource = new HikariDataSource(config);
    }

    static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    //Method to execute a SELECT query and return results without params
    //using try-with resource statement that doesn't need "finally" block.
    List<Map> executeSelect(String sql) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             def resultSet = preparedStatement.executeQuery()) {
            try {
                return Convertor.convertResultSetToMap(resultSet)
            }
            catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        }
    }


    boolean executeInsert(String query) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Execute the query (you can adjust to SELECT, UPDATE, etc. as needed)
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            println "ERROR: Failed to execute insert query: $e"
            return false
        }
    }


    boolean executeDelete(String query) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if rows were deleted
        } catch (Exception e) {
            println "ERROR: Failed to execute delete query: $e"
            return false;
        }
    }


//        // Properly shutdown the connection pool when done
//        public static void shutdown() {
//            if (dataSource != null) {
//                dataSource.close();
//            }
//        }


    // Method to execute a query (can be used in multiple threads)
//    static void executeQuery(String sql, Object[] parameters) {
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//
//            // Set parameters for the query
//            for (int i = 0; i < parameters.length; i++) {
//                preparedStatement.setObject(i + 1, parameters[i]);
//            }
//
//            // Execute the query (you can adjust to SELECT, UPDATE, etc. as needed)
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

}

