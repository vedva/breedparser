package org.infrostructure.supabaservice

import org.breed.Breed
import org.breed.BreedDB
import org.infrostructure.connector.SupabaseHTTP
import org.infrostructure.service.BreedHTTPService
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import org.utiles.EnvReader

import java.net.http.HttpResponse

class UTBreedHTTPService {
    private static final String TEST_SUPABASE_URL = EnvReader.getEnvVar("SUPABASE_URL")
    private static final String TEST_API_KEY = EnvReader.getEnvVar("SUPABASE_PUBLIC_KEY")

    private SupabaseHTTP supabaseHTTP

    @BeforeMethod
    void setup() {
        supabaseHTTP = new SupabaseHTTP(TEST_SUPABASE_URL, TEST_API_KEY)
    }

    @Test
    void testGetBreedDB() {
        BreedHTTPService breedService = new BreedHTTPService(supabaseHTTP)
        List<BreedDB> breedsDB = breedService.getAllBreedsFromSupa()

        assert breedsDB != null: "The result should not be null"
        println(breedsDB)

    }

    @Test
    void testPostBreed() {
        BreedHTTPService breedService = new BreedHTTPService(supabaseHTTP)
        List<Breed> breeds = [
                new Breed(name: "new dog-1", image: "new image-1", link: "new link-1", article: "new article -1"),
                new Breed(name: "new dog-2", image: "new image-2", link: "new link-2", article: "new article -2"),
                new Breed(name: "new dog-3", image: "new image-3", link: "new link-3", article: "new article -3")
        ]
        breedService.postAllBreedsToSupa(breeds)

        List<BreedDB> breedsDB = breedService.getAllBreedsFromSupa()
        assert breedsDB != null: "The result should not be null"
        println(breedsDB)


    }

    @Test
    void testDeleteBreed() {
        BreedHTTPService breedService = new BreedHTTPService(supabaseHTTP)
        Map<String, String> params = [id: "in.(61,62,63)"] //add correct id(s)
        HttpResponse<String> response = breedService.deleteBreedsFromSupa(params)
        assert response != null: "Response should not be null"
        assert response.statusCode() == 204: "Expected HTTP status 204 but got ${response.statusCode()}"

        println "Response Code: ${response.statusCode()}"

    }

}
