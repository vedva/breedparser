package org

import org.breed.Breed
import org.ifrostructure.supabase.supabaseconnector.SupabaseHTTP
import org.ifrostructure.supabase.supaservice.BreedService
import org.page.DogsPage
import org.utiles.EnvReader
import org.webservice.WebService

class Parser {
    static void main(String[] args) {

        println 'Groovy is working'
        String SUPABASE_URL = EnvReader.getEnvVar("SUPABASE_URL")
        String API_KEY = EnvReader.getEnvVar("SUPABASE_PUBLIC_KEY")
        SupabaseHTTP supabaseHTTP
        supabaseHTTP = new SupabaseHTTP(SUPABASE_URL, API_KEY)

        String URL = "https://www.pedigree.com.ph/dog-breeds?page=0"
        WebService wb = new WebService()

        wb.openBrowser();
        wb.navigateToPage(URL)
        DogsPage dogsPage = new DogsPage(wb)

        dogsPage.clickAcceptCookies()
        List<Breed> breeds = dogsPage.collectBreedsInfoFromOnePage()
        BreedService breedService = new BreedService(supabaseHTTP)
        breedService.postAllBreedsToSupa(breeds)



        //String json = JsonOutput.toJson(breeds)
        //println(json)

        wb.quiteWebDriver()


    }

}
