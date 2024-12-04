package org.infrostructure.service

import org.breed.Breed
import org.breed.BreedParser

class StorageService {
    StorageServiceI sI;

    StorageService(StorageServiceI sI) {
        this.sI = sI
    }
    List<Breed> storageGetAllBreeds(){
        return sI.getAllBreeds()
    }

    boolean storageAddAllBreeds(List<BreedParser> breeds){
        return sI.addAllBreeds(breeds)
    }

    boolean storageDeleteAllBreeds(List<Breed> breeds){
        return sI.deleteAllBreeds(breeds)
    }
}
