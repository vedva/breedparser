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
}
