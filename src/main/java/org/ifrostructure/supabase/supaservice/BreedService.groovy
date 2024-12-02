package org.ifrostructure.supabase.supaservice

import groovy.json.JsonSlurper
import org.breed.Breed
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
                new BreedDB((Integer) map.id, (String) map.breed, (String) map.image, (String) map.link, (String) map.article)
            }
        } catch (Exception e) {
            println "ERROR: jsonSlurper exception: $e.message"
            return []
        }

        return result
    }


    static String toJsonBody(List<Breed> breeds) {
        if (breeds == null) {
            return ""
        }
        if (breeds.size() == 0) {
            return ""
        }
        def breedsStrings = breeds.collect() { breed -> breed.stringToJson() }
        return "[ ${breedsStrings.join(",")} ]"

    }

    String postAllBreedsToSupa(List<Breed> breeds) {
        String body = toJsonBody(breeds)
        HttpResponse<String> response = sc.postRequest(END_POINT, body, [:])
        if (response == null) {
            println "ERROR: HTTPResponse is null"
            return []
        }
        if (response.statusCode() != 201) {
            println "ERROR: Status code is not 201: ${response.statusCode()}"
            return []
        }
        return println("breeds are added, status code is ${response.statusCode()}")

    }

    HttpResponse deleteBreedsFromSupa(Map<String, String> params) {

        HttpResponse<String> response = sc.deleteRequest(END_POINT, [:], params)
        if (response == null) {
            println "ERROR: HTTPResponse is null"
            return []
        }
        if (response.statusCode() != 204) {
            println "ERROR: Status code is not 204: ${response.statusCode()}"
            return []
        }
        println("breeds are deleted, status code is ${response.statusCode()}")
        return response

    }

}