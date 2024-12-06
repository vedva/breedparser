package org.infrostructure.connector

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.utiles.EnvReader
import java.sql.Statement
import java.sql.Connection
import java.sql.ResultSet

class SupaJDBC implements SupaJDBCI{
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(EnvReader.getEnvVar("JDBC_URL"));
        config.setUsername(EnvReader.getEnvVar("SUPA_USER"));
        config.setPassword(EnvReader.getEnvVar("SUPA_PASSWORD"));
        config.setMaximumPoolSize(10);
        dataSource = new HikariDataSource(config);
    }

    static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    List<Map> executeSelect(String query) throws Exception {
        Connection connection = getConnection();
        def statement = connection.createStatement()
        def resultSet = statement.executeQuery(query)

        try {
            return convertResultSetToMap(resultSet)
        } finally {
            resultSet.close()
            statement.close()
            connection.close()
        }

    }


    private static List<Map> convertResultSetToMap(ResultSet resultSet){
        List<Map> results = []

            def metaData = resultSet.metaData
            int columnCount = metaData.columnCount
            List<String> columnNames = (1..columnCount).collect { metaData.getColumnName(it) }
            while (resultSet.next()) {
                Map row = [:]
                columnNames.each { column ->
                    row[column] = resultSet.getObject(column)
                }
                results.add(row)
            }
        return results
    }


    boolean executeInsert(String query) {
        Connection connection = null
        Statement statement = null
        try {
            // Get a connection from the pool
            connection = getConnection()
            statement = connection.createStatement()

            // Execute the INSERT query
            int rowsAffected = statement.executeUpdate(query)

            // Check if rows were inserted
            if (rowsAffected > 0) {
                //println "SUCCESS: $rowsAffected row(s) inserted."
                return true
            } else {
                println "WARNING: No rows were inserted."
                return false
            }
        } catch (Exception e) {
            println "ERROR: Failed to execute insert query: $e"
            return false
        } finally {
            // Ensure resources are closed
            if (statement != null) statement.close()
            if (connection != null) connection.close()
        }
    }

    boolean executeDelete(String query) {
        Connection connection = null
        Statement statement = null
        try {
            connection = getConnection()
            statement = connection.createStatement()

            // Execute the DELETE query
            int rowsAffected = statement.executeUpdate(query)

            // Check if rows were deleted
            if (rowsAffected > 0) {
                println "SUCCESS: $rowsAffected row(s) deleted."
                return true
            } else {
                println "WARNING: No rows were deleted."
                return false
            }
        } catch (Exception e) {
            println "ERROR: Failed to execute delete query: $e"
            return false
        } finally {
            if (statement != null) statement.close()
            if (connection != null) connection.close()
        }
    }




}
