package org.infrostructure.service.utilsservice

import org.breed.Breed
import org.breed.BreedParser

class BreedConvertor {
    private final static String tableName = "dogs"
    private final static List<String> columnNames = ["breed", "image", "link", "article"] // Column names to be inserted



    static String convertBreedsToInsertQuery(List<BreedParser> breedsParser) {
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

    static String convertBreedsToDeleteQuery(List<Breed> breeds) {
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
