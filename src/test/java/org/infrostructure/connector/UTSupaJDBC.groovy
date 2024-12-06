package org.infrostructure.connector

import org.testng.annotations.Test


class UTSupaJDBC {

    @Test
    void testConnectionToSupa() {
        SupaJDBC s = new SupaJDBC()
        def query = "SELECT * FROM dogs;"
        List<Map> result = s.executeSelect(query)
        for (dog in result) {
            println(dog.id)
        }
    }

    @Test
    void testAddRecordsToTable() {
        String query = """
                        INSERT INTO dogs (breed, image, link, article)
                        VALUES ('Golden Retriever', 'golden.jpg', 'https://example.com/golden', 'Golden Retrievers are great family dogs.')
                       """

        SupaJDBC supaJDBC = new SupaJDBC()
        boolean success = supaJDBC.executeInsert(query)

        println "Insert successful: $success"

    }
}
