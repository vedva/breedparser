package org.infrostructure.service

import org.infrostructure.connector.SupaJDBC
import org.infrostructure.connector.SupaJDBCI
import org.infrostructure.connector.SupabaseHTTP

class StorageFactory {
    static StorageServiceI getStorageService(String serviceName) {
        StorageServiceI ssI;
        switch (serviceName) {
            case "HTTP":
                SupabaseHTTP supabaseHTTP = new SupabaseHTTP()
                ssI = new BreedHTTPService(supabaseHTTP)
                break
            case "JDBC":
                SupaJDBCI supaJDBCI = new SupaJDBC()
                ssI = new BreedJDBCService(supaJDBCI)
                break
            default :
                throw new IllegalArgumentException("Unknown service name: [$serviceName]")
        }
        return ssI
    }
}
