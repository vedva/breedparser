package org.infrostructure.connector

import java.net.http.HttpResponse

interface SupabaseHTTPI {


    HttpResponse getRequest(String url, Map<String, String> headers, Map<String, String> params)
    HttpResponse postRequest(String url, String body, Map<String, String> headers)
    HttpResponse deleteRequest(String url, Map<String, String> headers, Map<String, String> params)

}