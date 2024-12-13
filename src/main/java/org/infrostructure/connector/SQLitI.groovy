package org.infrostructure.connector

interface SQLitI {
    List<Map> executeSelect(String sql)

}