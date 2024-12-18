package org.infrostructure.connector

import org.testng.annotations.Test

class UTSQLite {
    @Test
    void testConnectionAndSelectToSQLite() {
        SQLite slite = new SQLite()
        def query = "SELECT * FROM dogs;"
        Object[] parameters = ["breed-1", "image-1"];
        List<Map> result = slite.executeSelect(query);
        println(result)
    }

    @Test
    void testAddRecordsToSQLiteTable() {
        String query = """
                        INSERT INTO dogs (breed, image, link, article)
                        VALUES ('GGG', 'new dog', 'new link', 'new article')
                       """

        SQLite slite = new SQLite()
        boolean success = slite.executeInsert(query)

        println "Insert successful: $success"

    }

    @Test
    void testADeleteRecordsFromSQLite() {
        String query = """
                       DELETE FROM dogs;
                       """

        SQLite slite = new SQLite()
        boolean success = slite.executeDelete(query)

        println "Insert successful: $success"

    }


}
