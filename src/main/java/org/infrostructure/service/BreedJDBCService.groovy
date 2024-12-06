package org.infrostructure.service

import org.breed.Breed
import org.breed.BreedParser
import org.infrostructure.connector.SupaJDBCI

class BreedJDBCService implements StorageServiceI {
    private final static String query = "SELECT * FROM dogs;"
    private final static String tableName = "dogs"
    private final static List<String> columnNames = ["breed", "image", "link", "article"] // Column names to be inserted
    private SupaJDBCI supaJDBCI

    BreedJDBCService(SupaJDBCI supaJDBCI) {
        this.supaJDBCI = supaJDBCI
    }

    List<Breed> getAllBreeds() {
        List<Map> breedsData
        try {
            breedsData = supaJDBCI.executeSelect(query)
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
        String query = convertBreedsToInsertQuery(breeds);
        return supaJDBCI.executeInsert(query)
    }

    boolean deleteAllBreeds(List<Breed> breeds) {
        String query = convertBreedsToDeleteQuery(breeds)
        return supaJDBCI.executeDelete(query)
    }


    private static String convertBreedsToInsertQuery(List<BreedParser> breedsParser) {

        if (breedsParser == null || breedsParser.isEmpty()) {
            throw new IllegalArgumentException("BreedsParser list is null or empty")
        }


        // Prepare the VALUES part of the query
        List<String> values = breedsParser.collect { breed ->
            // Escape single quotes to avoid SQL injection issues
            String escapedBreed = breed.name.replace("'", "''")
            String escapedImage = breed.image.replace("'", "''")
            String escapedLink = breed.link.replace("'", "''")
            String escapedArticle = breed.article.replace("'", "''")

            "('${escapedBreed}', '${escapedImage}', '${escapedLink}', '${escapedArticle}')"
        }
        // Generate the final query
        return """
        INSERT INTO ${tableName} (${columnNames.join(", ")})
        VALUES ${values.join(",\n")};
    """
    }

    private static String convertBreedsToDeleteQuery(List<Breed> breeds) {
        if (breeds == null || breeds.isEmpty()) {
            throw new IllegalArgumentException("Breeds list is null or empty")
        }

        // Collect breed IDs for deletion
        List<String> ids = breeds.collect { breed ->
            // Assuming breed.id is the identifier for deletion
            if (breed.id == null) {
                throw new IllegalArgumentException("Breed ID cannot be null")
            }
            breed.id.toString()
        }

        // Generate the final DELETE query
        return """
    DELETE FROM ${tableName}
    WHERE id IN (${ids.join(", ")});
    """

    }





}
