package org.infrostructure.connector

import org.testng.annotations.Test


class UTSupaJDBC {

    @Test
    void testConnectionToSupa() {
        def query = "SELECT * FROM dogs;"
        List<Map> result= SupaJDBC.executeSelect(query)
        for(dog in result){
            println(dog.id+1)
        }

    }
}
