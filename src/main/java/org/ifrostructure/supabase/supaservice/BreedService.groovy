package org.ifrostructure.supabase.supaservice

import org.breed.Breed
import org.ifrostructure.supabase.supabaseconnector.SupabaseHTTP
import org.utiles.EnvReader

class BreedService {
    private final static String END_POINT = "/rest/v1/dogs"
    private SupabaseHTTP sc;

    BreedService(SupabaseHTTP sc) {
        this.sc = sc
    }

    List<Breed> getAllBreedsFromSupa(){
        def url = EnvReader.getEnvVar("SUPABASE_URL") + END_POINT
        def apiKey = EnvReader.getEnvVar("SUPABASE_PUBLIC_KEY")

        // Make HTTP GET request
        def response = Request.get(url)
                .addHeader("apikey", apiKey)
                .addHeader("Authorization", "Bearer $apiKey")
                .execute()
                .returnContent()
                .asString()

        return breeds
    }



}