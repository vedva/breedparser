package org.infrostructure.service

import org.breed.Breed
import org.breed.BreedParser
import org.infrostructure.connector.SupaJDBCI

class BreedJDBCService implements StorageServiceI{
    private final static String query = "SELECT * FROM dogs;"
    private SupaJDBCI supaJDBCI

    BreedJDBCService(SupaJDBCI supaJDBCI){
        this.supaJDBCI = supaJDBCI
    }

    List<Breed> getAllBreeds(){
        List<Map> breedsData
        try{
            breedsData = supaJDBCI.executeSelect(query)
        } catch (Exception e){
            println "ERROR: JDBC exception: $e"
            return []
        }
        List<Breed> result = []
        for(breedData in breedsData) {
            def breedDB = new Breed((Integer) breedData["id"], breedData["breed"], breedData["image"], breedData["link"], breedData["article"] )
            result.add(breedDB)
        }
        return result
    }

    boolean addAllBreeds(List<BreedParser> breeds) {
        //TODO
        return true
    }

    boolean deleteAllBreeds(List<Breed> breeds) {
        //TODO
        return true
    }
}
