package org.ifrostructure.supabase.supabaseconnector

import java.net.http.HttpResponse

interface SupabaseHTTPI {


    HttpResponse getRequest(String url, Map<String, String> headers, Map<String, String> params)

}