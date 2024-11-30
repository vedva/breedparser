package org.ifrostructure.supabase.supabaseconnector

import org.breed.Breed
import org.ifrostructure.supabase.supaservice.BreedService
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
        // Arrange
        String endpoint = "/rest/v1/dogs"
        Map<String, String> headers = [:]
        Map<String, String> params = [:]

        // Act
        HttpResponse<String> response = supabaseHTTP.getRequest(endpoint, headers, params)

        // Assert
        assert response != null: "Response should not be null"
        assert response.statusCode() == 200: "Expected HTTP status 200 but got ${response.statusCode()}"
        assert response.body() != null: "Response body should not be null"

        println "Response Body: ${response.body()}, Response Code: ${response.statusCode()}"
    }

    @Test
    void testPostRequest_withRealCall() {
        // Arrange
        String endpoint = "/rest/v1/dogs"
        Map<String, String> headers = [:]
        List<Breed> breeds = [
                new Breed(name: "new dog-1", image: "new image-1", link: "new link-1", article: "new article -1"),
                new Breed(name: "new dog-2", image: "new image-2", link: "new link-2", article: "new article -2"),
                new Breed(name: "new dog-3", image: "new image-3", link: "new link-3", article: "new article -3")
        ]

        String jsonBody = BreedService.toJsonBody(breeds)
        println jsonBody
        // Act
        HttpResponse<String> response = supabaseHTTP.postRequest(endpoint, jsonBody, headers)

        // Assert
        assert response != null: "Response should not be null"
        assert response.statusCode() == 201: "Expected HTTP status 201 but got ${response.statusCode()}"
        assert response.body() != null: "Response body should not be null"

        println "Response Body: ${response.body()}, Response Code: ${response.statusCode()}"
    }
}
