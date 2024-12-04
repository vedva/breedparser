package org.infrostructure.supabaservice

import org.infrostructure.connector.SupaJDBC
import org.infrostructure.service.BreedJDBCService
import org.testng.annotations.Test

class UTBreedJDBCServiceParser {

    private SupaJDBC supa;

    @Test
    void testGetBreedDB() {
        supa = new SupaJDBC()
        BreedJDBCService bs = new BreedJDBCService(supa)
        print bs.getAllBreeds()


    }
}
