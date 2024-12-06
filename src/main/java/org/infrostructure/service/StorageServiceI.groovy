package org.infrostructure.service

import org.breed.Breed
import org.breed.BreedParser

interface StorageServiceI {
    List<Breed> getAllBreeds()

    boolean addAllBreeds(List<BreedParser> breeds)

    boolean deleteAllBreeds(List<Breed> breeds)


}