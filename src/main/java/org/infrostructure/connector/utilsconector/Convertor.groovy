package org.infrostructure.connector.utilsconector

import java.sql.ResultSet

class Convertor {
    static List<Map> convertResultSetToMap(ResultSet resultSet){
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
