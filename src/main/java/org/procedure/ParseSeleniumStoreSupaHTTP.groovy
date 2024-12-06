package org.procedure

import org.breed.BreedParser
import org.infrostructure.connector.SupabaseHTTP
import org.infrostructure.service.BreedHTTPService
import org.infrostructure.service.StorageFactory
import org.infrostructure.service.StorageService
import org.parser.ParserSelenium

class ParseSeleniumStoreSupaHTTP {

    static void addParseToSupaHTTP() {
        println 'process started'

        ParserSelenium parserSelenium = new ParserSelenium()

        List<BreedParser> breeds = parserSelenium.parseBreedsSelenium()
        def ssI = StorageFactory.getStorageService("HTTP")
        StorageService bs = new StorageService(ssI)
        bs.storageAddAllBreeds(breeds)


    }

}
