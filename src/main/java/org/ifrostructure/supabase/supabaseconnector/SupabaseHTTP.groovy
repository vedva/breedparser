package org.ifrostructure.supabase.supabaseconnector

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

public class SupabaseHTTP implements SupabaseHTTPI {

    private final String supabaseUrl
    private final String apiKey
    private HttpClient httpClient;

    // Constructor
    SupabaseHTTP(String supabaseUrl, String apiKey) {
        this.supabaseUrl = supabaseUrl;
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
    }

    HttpResponse getRequest(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            // Build query parameters
            String queryParams = params?.collect { key, value -> "${key}=${URLEncoder.encode(value, 'UTF-8')}" }?.join('&')
            String fullUrl = supabaseUrl + url + (queryParams ? "?${queryParams}" : "")
            // Build the request
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(new URI(fullUrl))
                    .GET()
            // Add headers
            headers?.each { key, value ->
                requestBuilder.header(key, value)
            }
            requestBuilder.header("apikey", this.apiKey) // Example of adding an API key header
            HttpRequest request = requestBuilder.build()
            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
            // Return the response body
            return response
        } catch (Exception e) {
            e.printStackTrace()
            return null
        }
    }

}

