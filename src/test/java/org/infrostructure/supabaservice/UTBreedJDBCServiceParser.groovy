package org.infrostructure.supabaservice

import org.breed.Breed
import org.breed.BreedParser
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

    @Test
    void testAddBreedPars() {
        supa = new SupaJDBC()
        List<BreedParser> breeds = [
                new BreedParser(name: "new dog-01", image: "new image-1", link: "new link-1", article: "new article -1"),
                new BreedParser(name: "new dog-02", image: "new image-2", link: "new link-2", article: "new article -2"),
                new BreedParser(name: "new dog-03", image: "new image-3", link: "new link-3", article: "new article -3")
        ]
        BreedJDBCService bs = new BreedJDBCService(supa)
        bs.addAllBreeds(breeds)

    }

    @Test
    void testDeleteBreedsFromDB() {
        supa = new SupaJDBC()
        BreedJDBCService bs = new BreedJDBCService(supa)
        List<Breed> selectedBreeds = new ArrayList<>()
        List<Breed> breeds = bs.getAllBreeds()
        selectedBreeds.addAll(breeds.take(2)) //select breeds to be deleted

        print bs.deleteAllBreeds(selectedBreeds)


    }
}
