package org.infrostructure.connector

interface SupaJDBCI {
    List<Map> executeSelect(String query) throws Exception
    boolean executeInsert(String query)

}