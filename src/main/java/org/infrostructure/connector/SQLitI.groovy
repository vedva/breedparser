package org.infrostructure.connector

interface SQLitI {
    List<Map> executeSelect(String sql)
    boolean executeInsert(String query)
    boolean executeDelete(String query)

}