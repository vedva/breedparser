package org.procedure

import org.breed.Breed
import org.infrostructure.connector.SupabaseHTTP
import org.infrostructure.service.BreedHTTPService
import org.parser.ParserSelenium
import org.utiles.EnvReader

class ParseSeleniumStoreSupaHTTP {

    static void addParseToSupaHTTP() {

        println 'Groovy is working'
        String SUPABASE_URL = EnvReader.getEnvVar("SUPABASE_URL")
        String API_KEY = EnvReader.getEnvVar("SUPABASE_PUBLIC_KEY")
        SupabaseHTTP supabaseHTTP
        supabaseHTTP = new SupabaseHTTP(SUPABASE_URL, API_KEY)
        ParserSelenium parserSelenium = new ParserSelenium()

//        String URL = "https://www.pedigree.com.ph/dog-breeds?page=0"
//        WebService wb = new WebService()
//        wb.openBrowser();
//        wb.navigateToPage(URL)
//        DogsPage dogsPage = new DogsPage(wb)
//        dogsPage.clickAcceptCookies()
//        List<Breed> breeds = dogsPage.collectBreedsInfoFromOnePage()
//        wb.quiteWebDriver()

        List<Breed> breeds = parserSelenium.parseBreedsSelenium()
        BreedHTTPService breedService = new BreedHTTPService(supabaseHTTP)
        breedService.postAllBreedsToSupa(breeds)

    }

}
