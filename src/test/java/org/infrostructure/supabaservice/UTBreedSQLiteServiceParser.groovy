package org.infrostructure.supabaservice

import org.infrostructure.connector.SQLite
import org.infrostructure.service.BreedSQLiteService
import org.testng.annotations.Test

class UTBreedSQLiteServiceParser {
    private SQLite sqLite;

    @Test
    void testGetBreedDB() {
        sqLite = new SQLite()
        BreedSQLiteService bs = new BreedSQLiteService(sqLite)
        print bs.getAllBreeds()

    }
}
