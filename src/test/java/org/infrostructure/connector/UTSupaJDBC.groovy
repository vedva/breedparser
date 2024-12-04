package org.infrostructure.connector

import org.testng.annotations.Test


class UTSupaJDBC {

    @Test
    void testConnectionToSupa() {
        SupaJDBC s = new SupaJDBC()
        def query = "SELECT * FROM dogs;"
        List<Map> result= s.executeSelect(query)
        for(dog in result){
            println(dog.id)
        }

    }
}
