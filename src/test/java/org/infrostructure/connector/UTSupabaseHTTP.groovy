package org.infrostructure.connector

import org.breed.Breed
import org.infrostructure.service.BreedHTTPService
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import org.utiles.EnvReader

import java.net.http.HttpResponse

class UTSupabaseHTTP {

    private static final String TEST_SUPABASE_URL = EnvReader.getEnvVar("SUPABASE_URL")
    private static final String TEST_API_KEY = EnvReader.getEnvVar("SUPABASE_PUBLIC_KEY")

    private SupabaseHTTP supabaseHTTP

    @BeforeMethod
    void setup() {
        supabaseHTTP = new SupabaseHTTP(TEST_SUPABASE_URL, TEST_API_KEY)
    }

    @Test
    void testGetRequest_withRealCall() {

        String endpoint = "/rest/v1/dogs"
        Map<String, String> headers = [:]
        Map<String, String> params = [:]

        HttpResponse<String> response = supabaseHTTP.getRequest(endpoint, headers, params)

        assert response != null: "Response should not be null"
        assert response.statusCode() == 200: "Expected HTTP status 200 but got ${response.statusCode()}"
        assert response.body() != null: "Response body should not be null"

        println "Response Body: ${response.body()}, Response Code: ${response.statusCode()}"
    }

    @Test
    void testPostRequest_withRealCall() {

        String endpoint = "/rest/v1/dogs"
        Map<String, String> headers = [:]
        List<Breed> breeds = [
                new Breed(name: "new dog-1", image: "new image-1", link: "new link-1", article: "new article -1"),
                new Breed(name: "new dog-2", image: "new image-2", link: "new link-2", article: "new article -2"),
                new Breed(name: "new dog-3", image: "new image-3", link: "new link-3", article: "new article -3")
        ]

        String jsonBody = BreedHTTPService.toJsonBody(breeds)
        println jsonBody
        HttpResponse<String> response = supabaseHTTP.postRequest(endpoint, jsonBody, headers)

        assert response != null: "Response should not be null"
        assert response.statusCode() == 201: "Expected HTTP status 201 but got ${response.statusCode()}"
        assert response.body() != null: "Response body should not be null"

        println "Response Body: ${response.body()}, Response Code: ${response.statusCode()}"
    }

    @Test
    void testDeleteRequest_withRealCall() {

        String endpoint = "/rest/v1/dogs"
        Map<String, String> headers = [:]
        Map<String, String> params = [id: "in.(55,56,57)"]


        HttpResponse<String> response = supabaseHTTP.deleteRequest(endpoint, headers, params)

        assert response != null: "Response should not be null"
        assert response.statusCode() == 204: "Expected HTTP status 204 but got ${response.statusCode()}"

        println "Response Code: ${response.statusCode()}"

    }
}
