package org.ifrostructure.supabase.supaservice

import groovy.json.JsonSlurper
import org.breed.BreedDB
import org.ifrostructure.supabase.supabaseconnector.SupabaseHTTPI
import java.net.http.HttpResponse

class BreedService {
    private final static String END_POINT = "/rest/v1/dogs"
    private SupabaseHTTPI sc;

    BreedService(SupabaseHTTPI sc) {
        this.sc = sc
    }

    List<BreedDB> getAllBreedsFromSupa() {
        HttpResponse<String> response = sc.getRequest(END_POINT, [:], [:])
        if (response == null) {
            println "ERROR: HTTPResponse is null"
            return []
        }
        if (response.statusCode() != 200) {
            println "ERROR: Status code is not 200: ${response.statusCode()}"
            return []
        }
        def jsonSlurper = new JsonSlurper()
        List<BreedDB> result = []
        try {
            // Parse the JSON response into a List of Maps
            List<Map> jsonResponse = jsonSlurper.parseText(response.body().toString())

            // Convert each Map into a Breed object
            result = jsonResponse.collect { map ->
                new BreedDB((Integer) map.id, (String)map.breed, (String)map.image, (String)map.link, (String)map.article)
            }
        } catch (Exception e) {
            println "ERROR: jsonSlurper exception: $e.message"
            return []
        }

        return result
    }

}