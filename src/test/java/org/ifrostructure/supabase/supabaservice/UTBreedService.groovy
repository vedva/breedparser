package org.ifrostructure.supabase.supabaservice

import org.breed.Breed
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

    @Test
    void testPostBreed() {
        BreedService breedService = new BreedService(supabaseHTTP)
        List<Breed> breeds = [
                new Breed(name: "new dog-1", image: "new image-1", link: "new link-1", article: "new article -1"),
                new Breed(name: "new dog-2", image: "new image-2", link: "new link-2", article: "new article -2"),
                new Breed(name: "new dog-3", image: "new image-3", link: "new link-3", article: "new article -3")
        ]
        breedService.postAllBreedsToSupa(breeds)


    }
}
