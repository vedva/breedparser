package org.infrostructure.service

import groovy.json.JsonSlurper
import org.breed.BreedParser
import org.breed.Breed
import org.infrostructure.connector.SupabaseHTTPI

import java.net.http.HttpResponse

class BreedHTTPService implements StorageServiceI{
    private final static String END_POINT = "/rest/v1/dogs"
    private SupabaseHTTPI sc;

    BreedHTTPService(SupabaseHTTPI sc) {
        this.sc = sc
    }

    List<Breed> getAllBreeds() {
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
        List<Breed> result = []
        try {
            // Parse the JSON response into a List of Maps
            List<Map> jsonResponse = jsonSlurper.parseText(response.body().toString())

            // Convert each Map into a Breed object
            result = jsonResponse.collect { map ->
                new Breed((Integer) map.id, (String) map.breed, (String) map.image, (String) map.link, (String) map.article)
            }
        } catch (Exception e) {
            println "ERROR: jsonSlurper exception: $e.message"
            return []
        }
        return result
    }


    static String toJsonBody(List<BreedParser> breeds) {
        if (breeds == null) {
            return ""
        }
        if (breeds.size() == 0) {
            return ""
        }
        def breedsStrings = breeds.collect() { breed -> breed.stringToJson() }
        return "[ ${breedsStrings.join(",")} ]"

    }

    boolean addAllBreeds(List<BreedParser> breeds) {
        String body = toJsonBody(breeds)
        HttpResponse<String> response = sc.postRequest(END_POINT, body, [:])
        if (response == null) {
            println "ERROR: HTTPResponse is null"
            return false
        }
        if (response.statusCode() != 201) {
            println "ERROR: Status code is not 201: ${response.statusCode()}"
            return false
        }
        //println("breeds added")
        return true
    }

    boolean deleteAllBreeds(List<Breed> breeds) {
        if (breeds.isEmpty()) {
            println "No breeds to delete."
            return true
        }
        String ids = breeds.collect {breed -> breed.id.toString() }.join(", ")
        Map<String, String> params = [id: "in.(${ids})"]
        //println(params)
        HttpResponse<String> response = sc.deleteRequest(END_POINT, [:], params)

        if (response == null) {
            println "ERROR: HTTPResponse is null"
            return false
        }
        if (response.statusCode() != 204) {
            println "ERROR: Status code is not 204: ${response.statusCode()}"
            return false
        }
        return true

    }

}