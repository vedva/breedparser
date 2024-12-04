package org.infrostructure.supabaservice

import org.breed.BreedParser
import org.breed.Breed
import org.infrostructure.connector.SupabaseHTTP
import org.infrostructure.service.BreedHTTPService
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

class UTBreedHTTPServiceParser {

    private SupabaseHTTP supabaseHTTP

    @BeforeMethod
    void setup() {
        supabaseHTTP = new SupabaseHTTP()
    }

    @Test
    void testGetBreedDB() {
        BreedHTTPService breedService = new BreedHTTPService(supabaseHTTP)
        List<Breed> breedsDB = breedService.getAllBreeds()

        assert breedsDB != null: "The result should not be null"
        println(breedsDB)

    }

    @Test
    void testPostBreed() {
        BreedHTTPService breedService = new BreedHTTPService(supabaseHTTP)
        List<BreedParser> breeds = [
                new BreedParser(name: "new dog-1", image: "new image-1", link: "new link-1", article: "new article -1"),
                new BreedParser(name: "new dog-2", image: "new image-2", link: "new link-2", article: "new article -2"),
                new BreedParser(name: "new dog-3", image: "new image-3", link: "new link-3", article: "new article -3")
        ]
        breedService.addAllBreeds(breeds)

        List<Breed> breedsDB = breedService.getAllBreeds()
        assert breedsDB != null: "The result should not be null"
        println(breedsDB)


    }

    @Test
    void testDeleteBreed() {
        BreedHTTPService breedService = new BreedHTTPService(supabaseHTTP)
        List<Breed> breeds = breedService.getAllBreeds()
        //Map<String, String> params = [id: "in.(61,62,63)"] //add correct id(s)
        breedService.deleteAllBreeds(breeds)

    }

}
