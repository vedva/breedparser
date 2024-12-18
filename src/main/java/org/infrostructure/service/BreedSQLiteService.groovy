package org.infrostructure.service

import org.breed.Breed
import org.breed.BreedParser
import org.infrostructure.connector.SQLitI
import org.infrostructure.service.utilsservice.BreedConvertor

class BreedSQLiteService implements StorageServiceI{
    private final static String query = "SELECT * FROM dogs;"
    private final static String tableName = "dogs"
    private final static List<String> columnNames = ["breed", "image", "link", "article"] // Column names to be inserted
    SQLitI sqLitI;

    BreedSQLiteService(SQLitI sqLitI){
        this.sqLitI = sqLitI
    }



    List<Breed> getAllBreeds() {
        List<Map> breedsData
        try {
            breedsData = sqLitI.executeSelect(query)
        } catch (Exception e) {
            println "ERROR: JDBC exception: $e"
            return []
        }
        List<Breed> result = []
        for (breedData in breedsData) {
            def breedDB = new Breed((Integer) breedData["id"], breedData["breed"], breedData["image"], breedData["link"], breedData["article"])
            result.add(breedDB)
        }
        return result
    }



    boolean addAllBreeds(List<BreedParser> breeds) {
        String query = BreedConvertor.convertBreedsToInsertQuery(breeds);
        return sqLitI.executeInsert(query)
    }

    boolean deleteAllBreeds(List<Breed> breeds) {
        String query = BreedConvertor.convertBreedsToDeleteQuery(breeds)
        return sqLitI.executeDelete(query)
    }
}
