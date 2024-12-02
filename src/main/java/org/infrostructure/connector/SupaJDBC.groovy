package org.infrostructure.connector

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.utiles.EnvReader

import java.sql.Connection
import java.sql.ResultSet

class SupaJDBC {
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://aws-0-us-east-1.pooler.supabase.com:6543/postgres");
        config.setUsername(EnvReader.getEnvVar("SUPA_USER"));
        config.setPassword(EnvReader.getEnvVar("SUPA_PASSWORD"));
        config.setMaximumPoolSize(10);
        dataSource = new HikariDataSource(config);
    }

    static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    static List<Map> executeSelect(String query) throws Exception {
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


}
