package org.infrostructure.connector

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class SupabaseHTTP implements SupabaseHTTPI {

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
            return response
        } catch (Exception e) {
            e.printStackTrace()
            return null
        }
    }


    HttpResponse postRequest(String url, String body, Map<String, String> headers) {
        try {
            String fullUrl = supabaseUrl + url
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(new URI(fullUrl))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
            headers?.each { key, value ->
                requestBuilder.header(key, value)
            }
            requestBuilder.header("apikey", this.apiKey)
            HttpRequest request = requestBuilder.build()
            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
            return response
        } catch (Exception e) {
            e.printStackTrace()
            return null
        }

    }


    HttpResponse deleteRequest(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            String queryParams = params?.collect { key, value -> "${key}=${URLEncoder.encode(value, 'UTF-8')}" }?.join('&')
            String fullUrl = supabaseUrl + url + (queryParams ? "?${queryParams}" : "")

            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(new URI(fullUrl))
                    .DELETE()

            headers?.each { key, value ->
                requestBuilder.header(key, value)
            }
            requestBuilder.header("apikey", this.apiKey)
            HttpRequest request = requestBuilder.build()
            // Send the request and get the response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString())
            return response
        } catch (Exception e) {
            e.printStackTrace()
            return null
        }

    }

}

