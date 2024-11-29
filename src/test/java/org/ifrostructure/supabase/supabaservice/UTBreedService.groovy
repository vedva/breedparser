package org.ifrostructure.supabase.supabaservice

import org.breed.BreedDB
import org.ifrostructure.supabase.supabaseconnector.SupabaseHTTP
import org.ifrostructure.supabase.supaservice.BreedService
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import org.utiles.EnvReader

class UTBreedService {
    private static final String TEST_SUPABASE_URL = EnvReader.getEnvVar("SUPABASE_URL")
    private static final String TEST_API_KEY = EnvReader.getEnvVar("SUPABASE_PUBLIC_KEY")

    private SupabaseHTTP supabaseHTTP

    @BeforeMethod
    void setup() {
        supabaseHTTP = new SupabaseHTTP(TEST_SUPABASE_URL, TEST_API_KEY)
    }

    @Test
    void testGetBreedDB() {
        BreedService breedService = new BreedService(supabaseHTTP)
        List<BreedDB> breedsDB = breedService.getAllBreedsFromSupa()
        println(breedsDB)

    }
}
